package com.skyinu.hprof.reader.model.root

import com.skyinu.hprof.reader.model.FieldLength
import okio.BufferedSource

class RootUnkown(bufferedSource: BufferedSource) {
    var objectId = 0
    var count = 0

    init {
        objectId = bufferedSource.readInt()
        count += FieldLength.U4.length
    }
}