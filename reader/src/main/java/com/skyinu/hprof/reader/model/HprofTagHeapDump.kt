package com.skyinu.hprof.reader.model

import com.skyinu.hprof.reader.model.HprofTag.Companion.CLASS_DUMP
import com.skyinu.hprof.reader.model.HprofTag.Companion.HEAP_DUMP_INFO
import com.skyinu.hprof.reader.model.HprofTag.Companion.INSTANCE_DUMP
import com.skyinu.hprof.reader.model.HprofTag.Companion.OBJECT_ARRAY_DUMP
import com.skyinu.hprof.reader.model.HprofTag.Companion.PRIMITIVE_ARRAY_DUMP
import com.skyinu.hprof.reader.model.HprofTag.Companion.PRIMITIVE_ARRAY_NODATA
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_DEBUGGER
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_FINALIZING
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_INTERNED_STRING
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_JAVA_FRAME
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_JNI_GLOBAL
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_JNI_LOCAL
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_JNI_MONITOR
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_MONITOR_USED
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_NATIVE_STACK
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_REFERENCE_CLEANUP
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_STICKY_CLASS
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_THREAD_BLOCK
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_THREAD_OBJECT
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_UNKNOWN
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_UNREACHABLE
import com.skyinu.hprof.reader.model.HprofTag.Companion.ROOT_VM_INTERNAL
import com.skyinu.hprof.reader.model.root.*
import com.skyinu.hprof.reader.utils.ReaderUtil
import okio.Buffer
import okio.BufferedSource

class HprofTagHeapDump(heapBufferedSource: BufferedSource, parent: HprofTag) {
    var subTagMap: Map<Int, List<Any>> = mapOf()

    init {
        val hprofTagMap = mutableMapOf<Int, MutableList<Any>>()
        subTagMap = hprofTagMap
        val bufferedSource = Buffer()
        heapBufferedSource.read(bufferedSource, parent.bodyLength.toLong())
        do {
            val tag = ReaderUtil.readUnsignedByte(bufferedSource)
            val hprofTagList = if (hprofTagMap[tag] != null) {
                hprofTagMap[tag]!!
            } else {
                val list = mutableListOf<Any>()
                hprofTagMap[tag] = list
                list
            }
            when (tag) {
                ROOT_UNKNOWN -> {
                    val unkownRoot = RootUnkown(bufferedSource)
                    hprofTagList.add(unkownRoot)
                }
                ROOT_JNI_GLOBAL -> {
                    val jniGlobalRoot = RootJniGlobal(bufferedSource)
                    hprofTagList.add(jniGlobalRoot)
                }
                ROOT_JNI_LOCAL -> {
                    val jniLocal = RootJniLocal(bufferedSource)
                    hprofTagList.add(jniLocal)
                }
                ROOT_JAVA_FRAME -> {
                    val javaFrame = RootJavaFrame(bufferedSource)
                    hprofTagList.add(javaFrame)
                }
                ROOT_NATIVE_STACK -> {
                    val nativeStack = RootNativeStack(bufferedSource)
                    hprofTagList.add(nativeStack)
                }
                ROOT_STICKY_CLASS -> {
                    val stickyClass = RootStickyClass(bufferedSource)
                    hprofTagList.add(stickyClass)
                }
                ROOT_THREAD_BLOCK -> {
                    val threadBlock = RootThreadBlock(bufferedSource)
                    hprofTagList.add(threadBlock)
                }
                ROOT_MONITOR_USED -> {
                    val monitorUsed = RootMonitorUsed(bufferedSource)
                    hprofTagList.add(monitorUsed)
                }
                ROOT_THREAD_OBJECT -> {
                    val threadObject = RootThreadObject(bufferedSource)
                    hprofTagList.add(threadObject)
                }
                CLASS_DUMP -> {
                    val hprofHeapClassDump = HprofHeapClassDump(bufferedSource)
                    hprofTagList.add(hprofHeapClassDump)
                }
                INSTANCE_DUMP -> {
                    val hprofHeapInstanceDump = HprofHeapInstanceDump(bufferedSource)
                    hprofTagList.add(hprofHeapInstanceDump)
                }
                OBJECT_ARRAY_DUMP -> {
                    val hprofHeapObjectArrayDump = HprofHeapObjectArrayDump(bufferedSource)
                    hprofTagList.add(hprofHeapObjectArrayDump)
                }
                PRIMITIVE_ARRAY_DUMP -> {
                    val hprofHeapBasicArrayDump = HprofHeapBasicArrayDump(bufferedSource)
                    hprofTagList.add(hprofHeapBasicArrayDump)
                }
                HEAP_DUMP_INFO -> {
                    val hprofHeapDumpInfo = HprofHeapDumpInfo(bufferedSource)
                    hprofTagList.add(hprofHeapDumpInfo)
                }
                ROOT_INTERNED_STRING -> {
                    val rootInternalString = RootInternedString(bufferedSource)
                    hprofTagList.add(rootInternalString)
                }
                ROOT_FINALIZING -> {
                    val rootFinalizing = RootFinalizing(bufferedSource)
                    hprofTagList.add(rootFinalizing)
                }
                ROOT_DEBUGGER -> {
                    val rootDebugger = RootDebugger(bufferedSource)
                    hprofTagList.add(rootDebugger)
                }
                ROOT_REFERENCE_CLEANUP -> {
                    val rootReferenceCleanup = RootReferenceCleanup(bufferedSource)
                    hprofTagList.add(rootReferenceCleanup)
                }
                ROOT_VM_INTERNAL -> {
                    val rootVmInternal = RootVmInternal(bufferedSource)
                    hprofTagList.add(rootVmInternal)
                }
                ROOT_JNI_MONITOR -> {
                    val rootJniMonitor = RootJniMonitor(bufferedSource)
                    hprofTagList.add(rootJniMonitor)
                }
                ROOT_UNREACHABLE -> {
                    val rootUnreachable = RootUnreachable(bufferedSource)
                    hprofTagList.add(rootUnreachable)
                }
                PRIMITIVE_ARRAY_NODATA -> {
                    throw UnsupportedOperationException("PRIMITIVE_ARRAY_NODATA cannot be parsed")
                }
            }
        } while (!bufferedSource.exhausted())
    }
}