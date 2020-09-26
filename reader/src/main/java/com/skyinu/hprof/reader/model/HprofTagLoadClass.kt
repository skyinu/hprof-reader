package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagLoadClass(bufferedSource: BufferedSource, parent: HprofTag) {
    var classSerialNumber = 0
    var objectId = 0
    var stackTraceSerialNumber = 0
    var classNameId = 0

    init {
        classSerialNumber = bufferedSource.readInt()
        objectId = bufferedSource.readInt()
        stackTraceSerialNumber = bufferedSource.readInt()
        classNameId = bufferedSource.readInt()
    }
}