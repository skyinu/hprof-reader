package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagEndThread(bufferedSource: BufferedSource, parent: HprofTag) {
    var threadSerialNumber = 0

    init {
        threadSerialNumber = bufferedSource.readInt()
    }
}