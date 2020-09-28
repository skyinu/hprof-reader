package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofHeapDumpInfo(bufferedSource: BufferedSource) {
    var heapId = 0
    var heapNameStringId = 0L

    var count = 0L

    init {
        heapId = bufferedSource.readInt()
        heapNameStringId = bufferedSource.readInt().toLong()
        count += FieldLength.U4.length + FieldLength.U4.length
    }
}