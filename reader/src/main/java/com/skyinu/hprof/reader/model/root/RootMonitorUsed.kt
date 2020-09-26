package com.skyinu.hprof.reader.model.root

import okio.BufferedSource

class RootMonitorUsed(bufferedSource: BufferedSource) {
    var objectId = 0

    init {
        objectId = bufferedSource.readInt()
    }
}