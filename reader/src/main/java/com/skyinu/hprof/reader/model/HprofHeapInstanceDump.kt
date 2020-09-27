package com.skyinu.hprof.reader.model

import com.skyinu.hprof.reader.utils.ReaderUtil
import okio.BufferedSource

class HprofHeapInstanceDump(bufferedSource: BufferedSource) {
    var objectId = 0
    var stackSerialNumber = 0
    var classObjectId = 0
    var values: ByteArray = ByteArray(0)

    var count = 0L

    init {
        objectId = bufferedSource.readInt()
        stackSerialNumber = bufferedSource.readInt()
        classObjectId = bufferedSource.readInt()
        count += (FieldLength.U4.length * 3)
        val remainValueBytes = ReaderUtil.readUnsignedInt(bufferedSource)
        count += (FieldLength.U2.length + remainValueBytes)
        values = bufferedSource.readByteArray(remainValueBytes)
    }
}