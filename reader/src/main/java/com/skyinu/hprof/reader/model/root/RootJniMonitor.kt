package com.skyinu.hprof.reader.model.root

import okio.BufferedSource

class RootJniMonitor(bufferedSource: BufferedSource) {
    var objectId = 0
    var stackTraceSerialNumber = 0
    var stackDepth = 0

    init {
        objectId = bufferedSource.readInt()
        stackTraceSerialNumber = bufferedSource.readInt()
        stackDepth = bufferedSource.readInt()
    }
}