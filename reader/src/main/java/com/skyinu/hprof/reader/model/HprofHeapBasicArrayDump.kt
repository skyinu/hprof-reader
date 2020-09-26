package com.skyinu.hprof.reader.model

import com.skyinu.hprof.reader.utils.ReaderUtil
import okio.BufferedSource

class HprofHeapBasicArrayDump(bufferedSource: BufferedSource) {
    var arrayObjectId = 0
    var stackSerialNumber = 0
    var numberOfElements = 0
    var elementsType: Byte = 0
    var elements: List<Any> = listOf()

    init {
        arrayObjectId = bufferedSource.readInt()
        stackSerialNumber = bufferedSource.readInt()
        numberOfElements = bufferedSource.readInt()
        elementsType = bufferedSource.readByte()
        val tmpElements = mutableListOf<Any>()
        repeat(numberOfElements) {
            tmpElements.add(ReaderUtil.readValueByType(elementsType, bufferedSource))
        }
        elements = tmpElements
    }
}