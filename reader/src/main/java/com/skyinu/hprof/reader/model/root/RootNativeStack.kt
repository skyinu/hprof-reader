package com.skyinu.hprof.reader.model.root

import com.skyinu.hprof.reader.model.FieldLength
import okio.BufferedSource

class RootNativeStack(bufferedSource: BufferedSource) {
    var objectId = 0
    var threadSerialNumber = 0
    var count = 0

    init {
        objectId = bufferedSource.readInt()
        threadSerialNumber = bufferedSource.readInt()
        count += (FieldLength.U4.length * 2)
    }
}