package asia.kala.misc;

import java.lang.ref.WeakReference;
import java.util.concurrent.ThreadFactory;

/**
 * {@code Cleaner} manages a set of object references and corresponding cleaning actions.
 * <p>
 * This class is compatible with {@code java.lang.ref.Cleaner}'s API, but can be used on Java 7/8.
 * <p>
 * See the
 * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/ref/Cleaner.html#compatible-cleaners">java.lang.ref.Cleaner API Note</a>
 * for more details.
 */
public abstract class Cleaner {
    private static WeakReference<Cleaner> defaultCleaner;

    /**
     * Get the default Cleaner.
     * <p>
     * This method will always return the same Cleaner when the reference to the Cleaner
     * it returns is maintained elsewhere.
     */
    public static Cleaner getDefault() {
        WeakReference<Cleaner> defaultCleaner = Cleaner.defaultCleaner;
        Cleaner cleaner;
        if (defaultCleaner != null) {
            cleaner = defaultCleaner.get();
            if (cleaner != null) {
                return cleaner;
            }
        }
        synchronized (Cleaner.class) {
            defaultCleaner = Cleaner.defaultCleaner;
            if (defaultCleaner != null) {
                cleaner = defaultCleaner.get();
                if (cleaner != null) {
                    return cleaner;
                }
            }
            cleaner = create();
            Cleaner.defaultCleaner = new WeakReference<>(cleaner);
            return cleaner;
        }
    }

    /**
     * Returns a new {@code Cleaner}.
     * <p>
     * See the
     * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/ref/Cleaner.html#create()">java.lang.ref.Cleaner#create document</a>
     * for more details.
     *
     * @return a new {@code Cleaner}
     * @throws SecurityException if the current thread is not allowed to
     *                           create or start the thread.
     */
    public static Cleaner create() {
        return CleanerImpl.createImpl(null);
    }

    /**
     * Returns a new {@code Cleaner} using a {@code Thread} from the {@code ThreadFactory}.
     * <p>
     * See the
     * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/ref/Cleaner.html#create(java.util.concurrent.ThreadFactory)">java.lang.ref.Cleaner#create document</a>
     * for more details.
     *
     * @param threadFactory a {@code ThreadFactory} to return a new {@code Thread}
     *                      to process cleaning actions
     * @return a new {@code Cleaner}
     * @throws IllegalThreadStateException if the thread from the thread
     *                                     factory was {@link Thread.State#NEW not a new thread}.
     * @throws SecurityException           if the current thread is not allowed to
     *                                     create or start the thread.
     */
    public static Cleaner create(ThreadFactory threadFactory) {
        if (threadFactory == null) {
            throw new NullPointerException();
        }
        return CleanerImpl.createImpl(threadFactory);
    }

    /**
     * Registers an object and a cleaning action to run when the object
     * becomes phantom reachable. Refer to the
     * <a href="https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/ref/Cleaner.html#compatible-cleaners">API Note</a>
     * above for cautions about the behavior of cleaning actions.
     *
     * @param obj    the object to monitor
     * @param action a {@code Runnable} to invoke when the object becomes phantom reachable
     * @return a {@code Cleanable} instance
     */
    public abstract Cleanable register(Object obj, Runnable action);

    /**
     * {@code Cleanable} represents an object and a cleaning action registered in a {@code Cleaner}.
     */
    public interface Cleanable {

        /**
         * Unregisters the cleanable and invokes the cleaning action.
         * The cleanable's cleaning action is invoked at most once
         * regardless of the number of calls to {@code clean}.
         */
        void clean();
    }
}
