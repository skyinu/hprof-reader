package com.skyinu.hprof.reader.model

import com.skyinu.hprof.reader.utils.ReaderUtil
import okio.BufferedSource

class HprofHeapBasicArrayDump(bufferedSource: BufferedSource) {
    var arrayObjectId = 0
    var stackSerialNumber = 0
    var numberOfElements = 0
    var elementsType: Byte = 0
    var elements: List<Any> = listOf()

    var count = 0L

    init {
        arrayObjectId = bufferedSource.readInt()
        stackSerialNumber = bufferedSource.readInt()
        numberOfElements = bufferedSource.readInt()
        elementsType = bufferedSource.readByte()
        count += (FieldLength.U4.length * 3) + FieldLength.U1.length
        println("HprofHeapBasicArrayDump numberOfElements $numberOfElements elementsType $elementsType ")
        val tmpElements = mutableListOf<Any>()
        repeat(numberOfElements) {
            val element = ReaderUtil.readValueByType(elementsType, bufferedSource)
            count += element.second
            tmpElements.add(element.first)
        }
        elements = tmpElements
    }
}