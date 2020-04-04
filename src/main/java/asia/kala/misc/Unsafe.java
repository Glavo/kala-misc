package asia.kala.misc;

import java.lang.reflect.Field;

public final class Unsafe {
    private Unsafe() {
    }

    private static final sun.misc.Unsafe theUnsafe;

    static {
        Class<sun.misc.Unsafe> cls = sun.misc.Unsafe.class;
        try {
            Field field = cls.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            theUnsafe = (sun.misc.Unsafe) field.get(null);
        } catch (Throwable e) {
            throw new AssertionError(e);
        }
    }

    /**
     * The value of {@code addressSize()}
     */
    public static final int ADDRESS_SIZE = sun.misc.Unsafe.ADDRESS_SIZE;

    /**
     * Fetches a value from a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #allocateMemory
     */
    public static byte getByte(long address) {
        return theUnsafe.getByte(address);
    }

    /**
     * Stores a value into a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #getByte(long)
     */
    public static void putByte(long address, byte x) {
        theUnsafe.putByte(address, x);
    }

    /**
     * Fetches a value from a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #allocateMemory
     */
    public static short getShort(long address) {
        return theUnsafe.getShort(address);
    }

    /**
     * Stores a value into a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #getShort(long)
     */
    public static void putShort(long address, short x) {
        theUnsafe.putShort(address, x);
    }

    /**
     * Fetches a value from a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #allocateMemory
     */
    public static char getChar(long address) {
        return theUnsafe.getChar(address);
    }

    /**
     * Stores a value into a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #getChar(long)
     */
    public static void putChar(long address, char x) {
        theUnsafe.putChar(address, x);
    }

    /**
     * Fetches a value from a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #allocateMemory
     */
    public static int getInt(long address) {
        return theUnsafe.getInt(address);
    }

    /**
     * Stores a value into a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #getInt(long)
     */
    public static void putInt(long address, int x) {
        theUnsafe.putInt(address, x);
    }

    /**
     * Fetches a value from a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #allocateMemory
     */
    public static long getLong(long address) {
        return theUnsafe.getLong(address);
    }

    /**
     * Stores a value into a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #getLong(long)
     */
    public static void putLong(long address, long x) {
        theUnsafe.putLong(address, x);
    }

    /**
     * Fetches a value from a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #allocateMemory
     */
    public static float getFloat(long address) {
        return theUnsafe.getFloat(address);
    }

    /**
     * Stores a value into a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #getFloat(long)
     */
    public static void putFloat(long address, float x) {
        theUnsafe.putFloat(address, x);
    }

    /**
     * Fetches a value from a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #allocateMemory
     */
    public static double getDouble(long address) {
        return theUnsafe.getDouble(address);
    }

    /**
     * Stores a value into a given memory address.  If the address is zero, or
     * does not point into a block obtained from {@link #allocateMemory}, the
     * results are undefined.
     *
     * @see #getDouble(long)
     */
    public static void putDouble(long address, double x) {
        theUnsafe.putDouble(address, x);
    }

    /**
     * Fetches a native pointer from a given memory address.  If the address is
     * zero, or does not point into a block obtained from {@link
     * #allocateMemory}, the results are undefined.
     *
     * <p>If the native pointer is less than 64 bits wide, it is extended as
     * an unsigned number to a Java long.  The pointer may be indexed by any
     * given byte offset, simply by adding that offset (as a simple integer) to
     * the long representing the pointer.  The number of bytes actually read
     * from the target address may be determined by consulting {@link
     * #addressSize}.
     *
     * @see #allocateMemory
     */
    public static long getAddress(long address) {
        return theUnsafe.getAddress(address);
    }

    /**
     * Stores a native pointer into a given memory address.  If the address is
     * zero, or does not point into a block obtained from {@link
     * #allocateMemory}, the results are undefined.
     *
     * <p>The number of bytes actually written at the target address may be
     * determined by consulting {@link #addressSize}.
     *
     * @see #getAddress(long)
     */
    public static void putAddress(long address, long x) {
        theUnsafe.putAddress(address, x);
    }


    /**
     * Allocates a new block of native memory, of the given size in bytes.  The
     * contents of the memory are uninitialized; they will generally be
     * garbage.  The resulting native pointer will never be zero, and will be
     * aligned for all value types.  Dispose of this memory by calling {@link
     * #freeMemory}, or resize it with {@link #reallocateMemory}.
     *
     * <em>Note:</em> It is the resposibility of the caller to make
     * sure arguments are checked before the methods are called. While
     * some rudimentary checks are performed on the input, the checks
     * are best effort and when performance is an overriding priority,
     * as when methods of this class are optimized by the runtime
     * compiler, some or all checks (if any) may be elided. Hence, the
     * caller must not rely on the checks and corresponding
     * exceptions!
     *
     * @throws RuntimeException if the size is negative or too large
     *                          for the native size_t type
     * @throws OutOfMemoryError if the allocation is refused by the system
     * @see #getByte(long)
     * @see #putByte(long, byte)
     */
    public static long allocateMemory(long bytes) {
        return theUnsafe.allocateMemory(bytes);
    }

    /**
     * Resizes a new block of native memory, to the given size in bytes.  The
     * contents of the new block past the size of the old block are
     * uninitialized; they will generally be garbage.  The resulting native
     * pointer will be zero if and only if the requested size is zero.  The
     * resulting native pointer will be aligned for all value types.  Dispose
     * of this memory by calling {@link #freeMemory}, or resize it with
     * #reallocateMemory.  The address passed to this method may be null, in
     * which case an allocation will be performed.
     *
     * <em>Note:</em> It is the resposibility of the caller to make
     * sure arguments are checked before the methods are called. While
     * some rudimentary checks are performed on the input, the checks
     * are best effort and when performance is an overriding priority,
     * as when methods of this class are optimized by the runtime
     * compiler, some or all checks (if any) may be elided. Hence, the
     * caller must not rely on the checks and corresponding
     * exceptions!
     *
     * @throws RuntimeException if the size is negative or too large
     *                          for the native size_t type
     * @throws OutOfMemoryError if the allocation is refused by the system
     * @see #allocateMemory
     */
    public static long reallocateMemory(long address, long bytes) {
        return theUnsafe.reallocateMemory(address, bytes);
    }

    /**
     * Sets all bytes in a given block of memory to a fixed value
     * (usually zero).  This provides a <em>single-register</em> addressing mode.
     */
    public static void setMemory(long address, long bytes, byte value) {
        theUnsafe.setMemory(address, bytes, value);
    }

    /**
     * Sets all bytes in a given block of memory to a copy of another
     * block.  This provides a <em>single-register</em> addressing mode.
     */
    public static void copyMemory(long srcAddress, long destAddress, long bytes) {
        theUnsafe.copyMemory(srcAddress, destAddress, bytes);
    }

    /**
     * Disposes of a block of native memory, as obtained from {@link
     * #allocateMemory} or {@link #reallocateMemory}.  The address passed to
     * this method may be null, in which case no action is taken.
     *
     * <em>Note:</em> It is the resposibility of the caller to make
     * sure arguments are checked before the methods are called. While
     * some rudimentary checks are performed on the input, the checks
     * are best effort and when performance is an overriding priority,
     * as when methods of this class are optimized by the runtime
     * compiler, some or all checks (if any) may be elided. Hence, the
     * caller must not rely on the checks and corresponding
     * exceptions!
     *
     * @throws RuntimeException if any of the arguments is invalid
     * @see #allocateMemory
     */
    public static void freeMemory(long address) {
        theUnsafe.freeMemory(address);
    }

    /**
     * Reports the size in bytes of a native pointer, as stored via {@link
     * #putAddress}.  This value will be either 4 or 8.  Note that the sizes of
     * other primitive types (as stored in native memory blocks) is determined
     * fully by their information content.
     */
    public static int addressSize() {
        return ADDRESS_SIZE;
    }

    /**
     * Reports the size in bytes of a native memory page (whatever that is).
     * This value will always be a power of two.
     */
    public static int pageSize() {
        return theUnsafe.pageSize();
    }

    /**
     * Throws the exception without telling the verifier.
     */
    public static void throwException(Throwable ee) {
        theUnsafe.throwException(ee);
    }

    /**
     * Ensures that loads before the fence will not be reordered with loads and
     * stores after the fence; a "LoadLoad plus LoadStore barrier".
     * <p>
     * Corresponds to C11 atomic_thread_fence(memory_order_acquire)
     * (an "acquire fence").
     * <p>
     * A pure LoadLoad fence is not provided, since the addition of LoadStore
     * is almost always desired, and most current hardware instructions that
     * provide a LoadLoad barrier also provide a LoadStore barrier for free.
     *
     * @since 1.8
     */
    public static void loadFence() {
        theUnsafe.loadFence();
    }

    /**
     * Ensures that loads and stores before the fence will not be reordered with
     * stores after the fence; a "StoreStore plus LoadStore barrier".
     * <p>
     * Corresponds to C11 atomic_thread_fence(memory_order_release)
     * (a "release fence").
     * <p>
     * A pure StoreStore fence is not provided, since the addition of LoadStore
     * is almost always desired, and most current hardware instructions that
     * provide a StoreStore barrier also provide a LoadStore barrier for free.
     *
     * @since 1.8
     */
    public static void storeFence() {
        theUnsafe.storeFence();
    }

    /**
     * Ensures that loads and stores before the fence will not be reordered
     * with loads and stores after the fence.  Implies the effects of both
     * loadFence() and storeFence(), and in addition, the effect of a StoreLoad
     * barrier.
     * <p>
     * Corresponds to C11 atomic_thread_fence(memory_order_seq_cst).
     *
     * @since 1.8
     */
    public static void fullFence() {
        theUnsafe.fullFence();
    }
}
