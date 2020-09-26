package com.skyinu.hprof.reader.model.root

import okio.BufferedSource

class RootJavaFrame(bufferedSource: BufferedSource) {
    var objectId = 0
    var threadSerialNumber = 0
    var frameOfStackTraceNumber = 0

    init {
        objectId = bufferedSource.readInt()
        threadSerialNumber = bufferedSource.readInt()
        frameOfStackTraceNumber = bufferedSource.readInt()
    }
}