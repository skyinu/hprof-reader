package com.skyinu.hprof.reader.model.root

import okio.BufferedSource

class RootDebugger(bufferedSource: BufferedSource) {
    var objectId = 0

    init {
        objectId = bufferedSource.readInt()
    }
}