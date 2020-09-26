package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofHeapDumpInfo(bufferedSource: BufferedSource) {
    var heapId = 0
    var heapNameStringId = 0L

    init {
        heapId = bufferedSource.readInt()
        heapNameStringId = bufferedSource.readLong()
    }
}