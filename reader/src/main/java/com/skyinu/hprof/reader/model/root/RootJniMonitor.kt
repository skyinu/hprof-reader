package com.skyinu.hprof.reader.model.root

import com.skyinu.hprof.reader.model.FieldLength
import okio.BufferedSource

class RootJniMonitor(bufferedSource: BufferedSource) {
    var objectId = 0
    var stackTraceSerialNumber = 0
    var stackDepth = 0
    var count = 0L

    init {
        objectId = bufferedSource.readInt()
        stackTraceSerialNumber = bufferedSource.readInt()
        stackDepth = bufferedSource.readInt()
        count += (FieldLength.U4.length * 3)
    }
}