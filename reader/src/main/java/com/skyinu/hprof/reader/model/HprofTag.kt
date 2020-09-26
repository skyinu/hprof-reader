package com.skyinu.hprof.reader.model

class HprofTag {
    var tagId: Byte = 0
    var timeStamp = 0
    var bodyLength = 0
    var body: Any = Any()

    companion object {
        internal const val STRING_IN_UTF8 = 0x01
        internal const val LOAD_CLASS = 0x02
        internal const val UNLOAD_CLASS = 0x03
        internal const val STACK_FRAME = 0x04
        internal const val STACK_TRACE = 0x05
        internal const val ALLOC_SITES = 0x06
        internal const val HEAP_SUMMARY = 0x07
        internal const val START_THREAD = 0x0a
        internal const val END_THREAD = 0x0b
        internal const val HEAP_DUMP = 0x0c
        internal const val HEAP_DUMP_SEGMENT = 0x1c
        internal const val HEAP_DUMP_END = 0x2c
        internal const val CPU_SAMPLES = 0x0d
        internal const val CONTROL_SETTINGS = 0x0e
        internal const val ROOT_UNKNOWN = 0xff
        internal const val ROOT_JNI_GLOBAL = 0x01
        internal const val ROOT_JNI_LOCAL = 0x02
        internal const val ROOT_JAVA_FRAME = 0x03
        internal const val ROOT_NATIVE_STACK = 0x04
        internal const val ROOT_STICKY_CLASS = 0x05
        internal const val ROOT_THREAD_BLOCK = 0x06
        internal const val ROOT_MONITOR_USED = 0x07
        internal const val ROOT_THREAD_OBJECT = 0x08
        internal const val CLASS_DUMP = 0x20
        internal const val INSTANCE_DUMP = 0x21
        internal const val OBJECT_ARRAY_DUMP = 0x22
        internal const val PRIMITIVE_ARRAY_DUMP = 0x23

        /**
         * Android format addition
         *
         * Specifies information about which heap certain objects came from. When a sub-tag of this type
         * appears in a HPROF_HEAP_DUMP or HPROF_HEAP_DUMP_SEGMENT record, entries that follow it will
         * be associated with the specified heap.  The HEAP_DUMP_INFO data is reset at the end of the
         * HEAP_DUMP[_SEGMENT].  Multiple HEAP_DUMP_INFO entries may appear in a single
         * HEAP_DUMP[_SEGMENT].
         *
         * Format: u1: Tag value (0xFE) u4: heap ID ID: heap name string ID
         */
        internal const val HEAP_DUMP_INFO = 0xfe
        internal const val ROOT_INTERNED_STRING = 0x89
        internal const val ROOT_FINALIZING = 0x8a
        internal const val ROOT_DEBUGGER = 0x8b
        internal const val ROOT_REFERENCE_CLEANUP = 0x8c
        internal const val ROOT_VM_INTERNAL = 0x8d
        internal const val ROOT_JNI_MONITOR = 0x8e
        internal const val ROOT_UNREACHABLE = 0x90
        internal const val PRIMITIVE_ARRAY_NODATA = 0xc3
    }
}