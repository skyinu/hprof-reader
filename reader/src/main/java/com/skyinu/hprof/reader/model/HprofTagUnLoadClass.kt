package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagUnLoadClass(bufferedSource: BufferedSource, parent: HprofTag) {
    var classSerialNumber = 0

    init {
        classSerialNumber = bufferedSource.readInt()
    }
}