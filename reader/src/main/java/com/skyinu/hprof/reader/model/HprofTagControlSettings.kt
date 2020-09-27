package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagControlSettings(bufferedSource: BufferedSource, parent: HprofTag) {
    var bitMaskFlag = 0
    var stackTraceDepth: Short = 0

    init {
        bitMaskFlag = bufferedSource.readInt()
        stackTraceDepth = bufferedSource.readShort()
    }
}