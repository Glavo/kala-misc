package asia.kala.misc;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Objects;
import java.util.concurrent.ThreadFactory;

final class CleanerImpl extends Cleaner implements Runnable {
    final ReferenceQueue<Object> queue = new ReferenceQueue<>();

    final PhantomCleanable first = new PhantomCleanable();

    static CleanerImpl createImpl(ThreadFactory threadFactory) {
        CleanerImpl impl = new CleanerImpl();

        new PhantomCleanable(impl, null, impl);

        Thread thread = threadFactory == null ? new Thread(impl) : threadFactory.newThread(impl);
        thread.setDaemon(true);
        thread.setPriority(3);
        thread.start();

        return impl;
    }

    public final void run() {
        while (first.next != first) {
            try {
                PhantomCleanable ref = (PhantomCleanable) queue.remove(60 * 1000L);
                if (ref != null) {
                    ref.clean();
                }
            } catch (Throwable ignored) {
            }
        }
    }

    @Override
    public final Cleanable register(Object obj, Runnable action) {
        Objects.requireNonNull(obj);
        Objects.requireNonNull(action);

        return new PhantomCleanable(obj, action, this);
    }

    static final class PhantomCleanable extends PhantomReference<Object> implements Cleaner.Cleanable {
        private final Runnable action;

        private final PhantomCleanable first;

        private PhantomCleanable prev = this;
        private PhantomCleanable next = this;

        private volatile Object keepAlive;

        PhantomCleanable() {
            super(null, null);
            this.first = this;
            action = null;
        }

        PhantomCleanable(Object referent, Runnable action, CleanerImpl impl) {
            super(referent, impl.queue);
            this.action = action;
            this.first = impl.first;

            synchronized (impl.first) {
                prev = impl.first;
                next = impl.first.next;
                next.prev = this;
                impl.first.next = this;
            }

            Objects.requireNonNull(referent); // reachabilityFence
        }

        public final void clean() {
            synchronized (first) {
                if (next == this) {
                    return;
                }
                next.prev = prev;
                prev.next = next;
                prev = this;
                next = this;
            }
            clear();
            if (action != null) {
                action.run();
            }
        }
    }
}
