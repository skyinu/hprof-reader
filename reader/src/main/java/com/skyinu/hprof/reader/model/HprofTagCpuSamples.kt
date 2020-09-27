package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagCpuSamples(bufferedSource: BufferedSource, parent: HprofTag) {
    var sampleCount = 0
    var sampleList: List<CpuSample> = listOf()

    init {
        sampleCount = bufferedSource.readInt()
        val traces = bufferedSource.readInt()
        val tmpSampleList = mutableListOf<CpuSample>()
        repeat(traces) {
            val cpuSample = CpuSample()
            cpuSample.sampleCount = bufferedSource.readInt()
            cpuSample.stackTraceSerialNumber = bufferedSource.readInt()
            tmpSampleList.add(cpuSample)
        }
        sampleList = tmpSampleList
    }

    class CpuSample {
        var sampleCount = 0
        var stackTraceSerialNumber = 0
    }
}