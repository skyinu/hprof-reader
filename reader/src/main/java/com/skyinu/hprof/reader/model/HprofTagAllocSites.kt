package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagAllocSites(bufferedSource: BufferedSource, parent: HprofTag) {
    var maskFlag: Short = 0
    var cutoffRatio = 0F
    var totalLiveBytes = 0
    var totalLiveInstance = 0
    var totalByteAllocate = 0L
    var totalInstanceAllocated = 0L
    var numberOfSites = 0
    var sites: List<Sites> = listOf()

    init {
        maskFlag = bufferedSource.readShort()
        cutoffRatio = bufferedSource.readUtf8(FieldLength.U4.length.toLong()).toFloat()
        totalLiveBytes = bufferedSource.readInt()
        totalLiveInstance = bufferedSource.readInt()
        totalByteAllocate = bufferedSource.readLong()
        totalInstanceAllocated = bufferedSource.readLong()
        numberOfSites = bufferedSource.readInt()
        val tmpSites = mutableListOf<Sites>()
        repeat(numberOfSites) {
            val sites = Sites()
            sites.isArray = bufferedSource.readByte()
            sites.classSerialNumber = bufferedSource.readInt()
            sites.stackTraceSerialNumber = bufferedSource.readInt()
            sites.numberOfAliveBytes = bufferedSource.readInt()
            sites.numberOfAliveInstance = bufferedSource.readInt()
            sites.numberOfAllocatedBytes = bufferedSource.readInt()
            sites.numberOfAllocatedInstance = bufferedSource.readInt()
            tmpSites.add(sites)
        }
        sites = tmpSites
    }

    class Sites {
        var isArray: Byte = 0
        var classSerialNumber = 0
        var stackTraceSerialNumber = 0
        var numberOfAliveBytes = 0
        var numberOfAliveInstance = 0
        var numberOfAllocatedBytes = 0
        var numberOfAllocatedInstance = 0
    }
}