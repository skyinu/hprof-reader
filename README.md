# hprof-reader
a practice project to learn the data format of hprof.

## difference between java and Android

### hprof tag

| tag            | java         | Android     |
| :------------- | :----------: | -----------: |
| HPROF_TAG_STRING |  yes  |   yes  |
| HPROF_TAG_LOAD_CLASS  |  yes  |   yes  |
| HPROF_TAG_UNLOAD_CLASS  |  yes  |  yes   |
| HPROF_TAG_STACK_FRAME |  yes  |  yes   |
| HPROF_TAG_STACK_TRACE  |  yes  |   yes  |
| HPROF_TAG_ALLOC_SITES  |  yes  |  yes   |
| HPROF_TAG_HEAP_SUMMARY  |  yes  | yes    |
| HPROF_TAG_START_THREAD  |  yes  |  yes   |
| HPROF_TAG_END_THREAD  |  yes  |   yes  |
| HPROF_TAG_HEAP_DUMP  |  yes  | yes    |
| HPROF_TAG_HEAP_DUMP_SEGMENT  |  yes  |  yes   |
| HPROF_TAG_CPU_SAMPLES  |  yes  |  yes   |
| HPROF_TAG_CONTROL_SETTINGS | yes   | yes    |

### heap tag
| tag            | java         | Android     |
| :------------- | :----------: | -----------: |
| ROOT UNKNOWN |  yes  |  yes   |
| ROOT JNI GLOBAL |  yes  |  yes   |
| ROOT JNI LOCAL |  yes  |   yes  |
| ROOT JAVA FRAME | yes   |  yes   |
| ROOT NATIVE STACK | yes   | yes    |
| ROOT STICKY CLASS | yes   |  yes   |
| ROOT THREAD BLOCK |  yes  |  yes   |
| ROOT MONITOR USED |  yes  |  yes   |
| ROOT THREAD OBJECT |  yes  | yes    |
| CLASS DUMP |  yes  |  yes   |
| INSTANCE DUMP | yes   |  yes   |
| OBJECT ARRAY DUMP | yes   | yes    |
| PRIMITIVE ARRAY DUMP | yes   |   yes  |
| HPROF_HEAP_DUMP_INFO  |  no  |  yes   |
| HPROF_HEAP_DUMP_INFO  |  no  |  yes   |
| HPROF_ROOT_INTERNED_STRING  |  no  |  yes   |
| HPROF_ROOT_FINALIZING  |  no  |  yes   |
| HPROF_ROOT_DEBUGGER  |  no |  yes   |
| HPROF_ROOT_REFERENCE_CLEANUP   |  no |  yes   |
| HPROF_ROOT_VM_INTERNAL   |  no |  yes   |
| HPROF_ROOT_JNI_MONITOR  |  no |  yes   |
| HPROF_UNREACHABLE  |  no |  yes   |
| HPROF_PRIMITIVE_ARRAY_NODATA_DUMP  |  no |  yes   |

## reference
+ [hprof manual](http://hg.openjdk.java.net/jdk6/jdk6/jdk/raw-file/tip/src/share/demo/jvmti/hprof/manual.html#mozTocId848088)
+ [heapDumper](https://hg.openjdk.java.net/jdk/jdk/file/9a73a4e4011f/src/hotspot/share/services/heapDumper.cpp)
+ [android hprof](https://android.googlesource.com/platform/art/+/refs/heads/android10-c2f2-release/runtime/hprof/hprof.cc)