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

class HprofTagHeapDump(bufferedSource: BufferedSource, parent: HprofTag) {
    var subTagMap: Map<Int, List<Any>> = mapOf()

    init {
        val hprofTagMap = mutableMapOf<Int, MutableList<Any>>()
        subTagMap = hprofTagMap
        var readCount = 0L
        System.err.println("HprofTagHeapDump -------------------------------- ")
        do {
            val tag = ReaderUtil.readUnsignedByte(bufferedSource)
            readCount += FieldLength.U1.length
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
                    readCount += unkownRoot.count
                    hprofTagList.add(unkownRoot)
                }
                ROOT_JNI_GLOBAL -> {
                    val jniGlobalRoot = RootJniGlobal(bufferedSource)
                    readCount += jniGlobalRoot.count
                    hprofTagList.add(jniGlobalRoot)
                }
                ROOT_JNI_LOCAL -> {
                    val jniLocal = RootJniLocal(bufferedSource)
                    readCount += jniLocal.count
                    hprofTagList.add(jniLocal)
                }
                ROOT_JAVA_FRAME -> {
                    val javaFrame = RootJavaFrame(bufferedSource)
                    readCount += javaFrame.count
                    hprofTagList.add(javaFrame)
                }
                ROOT_NATIVE_STACK -> {
                    val nativeStack = RootNativeStack(bufferedSource)
                    readCount += nativeStack.count
                    hprofTagList.add(nativeStack)
                }
                ROOT_STICKY_CLASS -> {
                    val stickyClass = RootStickyClass(bufferedSource)
                    readCount += stickyClass.count
                    hprofTagList.add(stickyClass)
                }
                ROOT_THREAD_BLOCK -> {
                    val threadBlock = RootThreadBlock(bufferedSource)
                    readCount += threadBlock.count
                    hprofTagList.add(threadBlock)
                }
                ROOT_MONITOR_USED -> {
                    val monitorUsed = RootMonitorUsed(bufferedSource)
                    readCount += monitorUsed.count
                    hprofTagList.add(monitorUsed)
                }
                ROOT_THREAD_OBJECT -> {
                    val threadObject = RootThreadObject(bufferedSource)
                    readCount += threadObject.count
                    hprofTagList.add(threadObject)
                }
                CLASS_DUMP -> {
                    val hprofHeapClassDump = HprofHeapClassDump(bufferedSource)
                    readCount += hprofHeapClassDump.count
                    hprofTagList.add(hprofHeapClassDump)
                }
                INSTANCE_DUMP -> {
                    val hprofHeapInstanceDump = HprofHeapInstanceDump(bufferedSource)
                    readCount += hprofHeapInstanceDump.count
                    hprofTagList.add(hprofHeapInstanceDump)
                }
                OBJECT_ARRAY_DUMP -> {
                    val hprofHeapObjectArrayDump = HprofHeapObjectArrayDump(bufferedSource)
                    readCount += hprofHeapObjectArrayDump.count
                    hprofTagList.add(hprofHeapObjectArrayDump)
                }
                PRIMITIVE_ARRAY_DUMP -> {
                    val hprofHeapBasicArrayDump = HprofHeapBasicArrayDump(bufferedSource)
                    readCount += hprofHeapBasicArrayDump.count
                    hprofTagList.add(hprofHeapBasicArrayDump)
                }
                HEAP_DUMP_INFO -> {
                    val hprofHeapDumpInfo = HprofHeapDumpInfo(bufferedSource)
                    readCount += hprofHeapDumpInfo.count
                    hprofTagList.add(hprofHeapDumpInfo)
                }
                ROOT_INTERNED_STRING -> {
                    val rootInternalString = RootInternedString(bufferedSource)
                    readCount += rootInternalString.count
                    hprofTagList.add(rootInternalString)
                }
                ROOT_FINALIZING -> {
                    val rootFinalizing = RootFinalizing(bufferedSource)
                    readCount += rootFinalizing.count
                    hprofTagList.add(rootFinalizing)
                }
                ROOT_DEBUGGER -> {
                    val rootDebugger = RootDebugger(bufferedSource)
                    readCount += rootDebugger.count
                    hprofTagList.add(rootDebugger)
                }
                ROOT_REFERENCE_CLEANUP -> {
                    val rootReferenceCleanup = RootReferenceCleanup(bufferedSource)
                    readCount += rootReferenceCleanup.count
                    hprofTagList.add(rootReferenceCleanup)
                }
                ROOT_VM_INTERNAL -> {
                    val rootVmInternal = RootVmInternal(bufferedSource)
                    readCount += rootVmInternal.count
                    hprofTagList.add(rootVmInternal)
                }
                ROOT_JNI_MONITOR -> {
                    val rootJniMonitor = RootJniMonitor(bufferedSource)
                    readCount += rootJniMonitor.count
                    hprofTagList.add(rootJniMonitor)
                }
                ROOT_UNREACHABLE -> {
                    val rootUnreachable = RootUnreachable(bufferedSource)
                    readCount += rootUnreachable.count
                    hprofTagList.add(rootUnreachable)
                }
                PRIMITIVE_ARRAY_NODATA -> {
                    throw UnsupportedOperationException("PRIMITIVE_ARRAY_NODATA cannot be parsed")
                }
            }
        } while (readCount < parent.bodyLength)
        println("HprofTagHeapDump readCount $readCount body length ${parent.bodyLength} ")
        if (readCount > parent.bodyLength) {
            error("HprofTagHeapDump error happened,read wrong size")
        }
    }
}