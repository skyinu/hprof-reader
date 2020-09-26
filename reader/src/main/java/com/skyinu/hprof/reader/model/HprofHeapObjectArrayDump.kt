package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofHeapObjectArrayDump(bufferedSource: BufferedSource) {
    var arrayObjectId = 0
    var stackSerialNumber = 0
    var numberOfElements = 0
    var arrayClassObjectId = 0
    var elements: List<Int> = listOf()

    init {
        arrayObjectId = bufferedSource.readInt()
        stackSerialNumber = bufferedSource.readInt()
        numberOfElements = bufferedSource.readInt()
        arrayClassObjectId = bufferedSource.readInt()
        val tmpElements = mutableListOf<Int>()
        repeat(numberOfElements) {
            tmpElements.add(bufferedSource.readInt())
        }
        elements = tmpElements
    }
}