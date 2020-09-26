package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagStartThread(bufferedSource: BufferedSource, parent: HprofTag) {
    var threadSerialNumber = 0
    var threadObjectId = 0
    var stackTraceSerialNumber = 0
    var threadNameStringId = 0
    var threadGroupNameId = 0
    var threadParentGroupNameId = 0

    init {
        threadSerialNumber = bufferedSource.readInt()
        threadObjectId = bufferedSource.readInt()
        stackTraceSerialNumber = bufferedSource.readInt()
        threadNameStringId = bufferedSource.readInt()
        threadGroupNameId = bufferedSource.readInt()
        threadParentGroupNameId = bufferedSource.readInt()
    }
}