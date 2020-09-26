package com.skyinu.hprof.reader.model.root

import okio.BufferedSource

class RootThreadBlock(bufferedSource: BufferedSource) {
    var objectId = 0
    var threadSerialNumber = 0

    init {
        objectId = bufferedSource.readInt()
        threadSerialNumber = bufferedSource.readInt()
    }
}