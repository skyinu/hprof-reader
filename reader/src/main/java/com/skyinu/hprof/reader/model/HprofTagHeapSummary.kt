package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagHeapSummary(bufferedSource: BufferedSource, parent: HprofTag) {
    var totalLiveBytes = 0
    var totalLiveInstance = 0
    var totalByteAllocate = 0L
    var totalInstanceAllocated = 0L

    init {
        totalLiveBytes = bufferedSource.readInt()
        totalLiveInstance = bufferedSource.readInt()
        totalByteAllocate = bufferedSource.readLong()
        totalInstanceAllocated = bufferedSource.readLong()
    }
}