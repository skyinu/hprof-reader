package com.skyinu.hprof.reader.model.root

import com.skyinu.hprof.reader.model.FieldLength
import okio.BufferedSource

class RootUnreachable(bufferedSource: BufferedSource) {
    var objectId = 0
    var count = 0L

    init {
        objectId = bufferedSource.readInt()
        count += FieldLength.U4.length
    }
}