package com.skyinu.hprof.reader.model.root

import okio.BufferedSource

class RootJniGlobal(bufferedSource: BufferedSource) {
    var objectId = 0
    var globalRefId = 0

    init {
        objectId = bufferedSource.readInt()
        globalRefId = bufferedSource.readInt()
    }
}