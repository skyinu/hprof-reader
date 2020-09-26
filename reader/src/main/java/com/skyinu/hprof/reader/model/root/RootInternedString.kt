package com.skyinu.hprof.reader.model.root

import okio.BufferedSource

class RootInternedString(bufferedSource: BufferedSource) {
    var objectId = 0

    init {
        objectId = bufferedSource.readInt()
    }
}