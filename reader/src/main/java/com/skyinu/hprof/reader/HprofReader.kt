package com.skyinu.hprof.reader

import com.skyinu.hprof.reader.model.*
import com.skyinu.hprof.reader.model.HprofTag.Companion.ALLOC_SITES
import com.skyinu.hprof.reader.model.HprofTag.Companion.END_THREAD
import com.skyinu.hprof.reader.model.HprofTag.Companion.HEAP_DUMP
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

    private fun parseTag(bufferSource: BufferedSource): Map<Byte, List<HprofTag>> {
        val hprofTagMap = mutableMapOf<Byte, MutableList<HprofTag>>()
        while (!bufferSource.exhausted()) {
            val tag = bufferSource.readByte()
            val hprofTag = HprofTag()
            hprofTag.tagId = tag
            hprofTag.timeStamp = bufferSource.readInt()
            hprofTag.bodyLength = bufferSource.readInt()
            val hprofTagList = if (hprofTagMap[tag] != null) {
                hprofTagMap[tag]
            } else {
                val list = mutableListOf<HprofTag>()
                hprofTagMap[tag] = list
                list
            }
            hprofTagList!!.add(hprofTag)
            when (tag) {
                STRING_IN_UTF8.toByte() -> {
                    hprofTag.body = HprofTagUTF8(bufferSource, hprofTag)
                }
                LOAD_CLASS.toByte() -> {
                    hprofTag.body = HprofTagLoadClass(bufferSource, hprofTag)
                }
                UNLOAD_CLASS.toByte() -> {
                    hprofTag.body = HprofTagUnLoadClass(bufferSource, hprofTag)
                }
                STACK_FRAME.toByte() -> {
                    hprofTag.body = HprofTagStackFrame(bufferSource, hprofTag)
                }
                STACK_TRACE.toByte() -> {
                    hprofTag.body = HprofTagStackTrace(bufferSource, hprofTag)
                }
                ALLOC_SITES.toByte() -> {
                    hprofTag.body = HprofTagAllocSites(bufferSource, hprofTag)
                }
                HEAP_SUMMARY.toByte() -> {
                    hprofTag.body = HprofTagHeapSummary(bufferSource, hprofTag)
                }
                START_THREAD.toByte() -> {
                    hprofTag.body = HprofTagStartThread(bufferSource, hprofTag)
                }
                END_THREAD.toByte() -> {
                    hprofTag.body = HprofTagEndThread(bufferSource, hprofTag)
                }
                HEAP_DUMP.toByte(), HEAP_DUMP_SEGMENT.toByte() -> {
                    hprofTag.body = HprofTagHeapDump(bufferSource, hprofTag)
                }
                else -> {
                    bufferSource.skip(hprofTag.bodyLength.toLong())
                }
            }
        }
        return hprofTagMap
    }
}