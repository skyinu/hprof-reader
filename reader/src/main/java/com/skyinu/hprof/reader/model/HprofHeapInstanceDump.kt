package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofHeapInstanceDump(bufferedSource: BufferedSource) {
    var objectId = 0
    var stackSerialNumber = 0
    var classObjectId = 0
    var values: ByteArray = ByteArray(0)

    init {
        objectId = bufferedSource.readInt()
        stackSerialNumber = bufferedSource.readInt()
        classObjectId = bufferedSource.readInt()
        val remainValueBytes = bufferedSource.readInt()
        values = bufferedSource.readByteArray(remainValueBytes.toLong())
    }
}