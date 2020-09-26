package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagStackTrace(bufferedSource: BufferedSource, parent: HprofTag) {
    var stackTraceSerialNumber = 0
    var threadSerialNumber = 0
    var numberOfFrames = 0
    var stackFrameId: List<Int> = listOf()

    init {
        stackTraceSerialNumber = bufferedSource.readInt()
        threadSerialNumber = bufferedSource.readInt()
        numberOfFrames = bufferedSource.readInt()
        val idCount = parent.bodyLength / 4 - 3
        val tmpStackFrameId = mutableListOf<Int>()
        repeat(idCount) {
            tmpStackFrameId.add(bufferedSource.readInt())
        }
        stackFrameId = tmpStackFrameId
    }
}