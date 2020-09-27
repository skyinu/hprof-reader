package com.skyinu.hprof.reader

import com.skyinu.hprof.reader.model.*
import com.skyinu.hprof.reader.model.HprofTag.Companion.ALLOC_SITES
import com.skyinu.hprof.reader.model.HprofTag.Companion.CONTROL_SETTINGS
import com.skyinu.hprof.reader.model.HprofTag.Companion.CPU_SAMPLES
import com.skyinu.hprof.reader.model.HprofTag.Companion.END_THREAD
import com.skyinu.hprof.reader.model.HprofTag.Companion.HEAP_DUMP
import com.skyinu.hprof.reader.model.HprofTag.Companion.HEAP_DUMP_END
import com.skyinu.hprof.reader.model.HprofTag.Companion.HEAP_DUMP_SEGMENT
import com.skyinu.hprof.reader.model.HprofTag.Companion.HEAP_SUMMARY
import com.skyinu.hprof.reader.model.HprofTag.Companion.LOAD_CLASS
import com.skyinu.hprof.reader.model.HprofTag.Companion.STACK_FRAME
import com.skyinu.hprof.reader.model.HprofTag.Companion.STACK_TRACE
import com.skyinu.hprof.reader.model.HprofTag.Companion.START_THREAD
import com.skyinu.hprof.reader.model.HprofTag.Companion.STRING_IN_UTF8
import com.skyinu.hprof.reader.model.HprofTag.Companion.UNLOAD_CLASS
import com.skyinu.hprof.reader.utils.ReaderUtil
import okio.*
import java.io.File

class HprofReader {

    fun read(hprofFile: File) {
        val bufferSource = hprofFile.source().buffer()
        val hprofHeader = parseHeader(bufferSource = bufferSource)
        val hprofTagList = parseTag(bufferSource)
    }

    private fun parseHeader(bufferSource: BufferedSource): HprofHeader {
        val header = HprofHeader()
        header.versionName = ReaderUtil.readerNullEndString(bufferSource)
        header.identifiers = bufferSource.readInt()
        header.timeStamp = bufferSource.readLong()
        return header
    }

    private fun parseTag(bufferSource: BufferedSource): Map<Int, List<HprofTag>> {
        val hprofTagMap = mutableMapOf<Int, MutableList<HprofTag>>()
        println("hprof tag min $STRING_IN_UTF8 max $HEAP_DUMP_END")
        while (!bufferSource.exhausted()) {
            val tag = ReaderUtil.readUnsignedByte(bufferSource)
            if (tag < STRING_IN_UTF8 || tag > HEAP_DUMP_END) {
                error("hprof tag $tag remain size ${bufferSource.buffer.size}")
            }
            val hprofTag = HprofTag()
            hprofTag.tagId = tag
            hprofTag.timeStamp = bufferSource.readInt()
            hprofTag.bodyLength = ReaderUtil.readUnsignedInt(bufferSource)
            val hprofTagList = if (hprofTagMap[tag] != null) {
                hprofTagMap[tag]
            } else {
                val list = mutableListOf<HprofTag>()
                hprofTagMap[tag] = list
                list
            }
            hprofTagList!!.add(hprofTag)
            when (tag) {
                STRING_IN_UTF8 -> {
                    hprofTag.body = HprofTagUTF8(bufferSource, hprofTag)
                }
                LOAD_CLASS -> {
                    hprofTag.body = HprofTagLoadClass(bufferSource, hprofTag)
                }
                UNLOAD_CLASS -> {
                    hprofTag.body = HprofTagUnLoadClass(bufferSource, hprofTag)
                }
                STACK_FRAME -> {
                    hprofTag.body = HprofTagStackFrame(bufferSource, hprofTag)
                }
                STACK_TRACE -> {
                    hprofTag.body = HprofTagStackTrace(bufferSource, hprofTag)
                }
                ALLOC_SITES -> {
                    hprofTag.body = HprofTagAllocSites(bufferSource, hprofTag)
                }
                HEAP_SUMMARY -> {
                    hprofTag.body = HprofTagHeapSummary(bufferSource, hprofTag)
                }
                START_THREAD -> {
                    hprofTag.body = HprofTagStartThread(bufferSource, hprofTag)
                }
                END_THREAD -> {
                    hprofTag.body = HprofTagEndThread(bufferSource, hprofTag)
                }
                HEAP_DUMP, HEAP_DUMP_SEGMENT -> {
                    hprofTag.body = HprofTagHeapDump(bufferSource, hprofTag)
                }
                HEAP_DUMP_END -> {
                }
                CPU_SAMPLES -> {
                    hprofTag.body = HprofTagCpuSamples(bufferSource, hprofTag)
                }
                CONTROL_SETTINGS -> {
                    hprofTag.body = HprofTagControlSettings(bufferSource, hprofTag)
                }
                else -> {
                    bufferSource.skip(hprofTag.bodyLength)
                }
            }
        }
        return hprofTagMap
    }
}