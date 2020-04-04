## Kala misc

Provide public alternative to `sun.misc` package.

### Adding Kala misc to your build

// TODO

### Features

* Compatible with Java 7+

* Provide `module-info.class`, can be easily packaged by the jlink tool

* Provide  `kala.misc.Cleaner` (fully API compatible with Java 9 [`Cleaner`](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/lang/ref/Cleaner.html) ) instead of `sun.misc.Cleaner`

* Provide public alternative to `sun.misc.Unsafe` (incomplete)

  * Currently supported functions: 
  
    * `<type> get<type>(long address)` 
    * `void put<type>(long address, <type> x)`
    * `long allocateMemory(long bytes)`
    * `long reallocateMemory(long address, long bytes)`
    * `void setMemory(long address, long bytes, byte value)`  
    * `void copyMemory(long srcAddress, long destAddress, long bytes)`
    * `void freeMemory(long address)`
    * `int addressSize()`
    * `int pageSize()`
    * `void throwException(Throwable ee)`
    * `void loadFence()`
    * `void storeFence()`
    * `void fullFence()`
  
  * Before removing `sun.misc.Unsafe` in the JDK, delegate the implementation to it
  
  * After removing `sun.misc.Unsafe` in JDK, it will be migrated to JNI implementation while ensuring API compatibility