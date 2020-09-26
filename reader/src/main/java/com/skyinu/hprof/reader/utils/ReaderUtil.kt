package com.skyinu.hprof.reader.utils

import com.skyinu.hprof.reader.model.BasicType
import okio.BufferedSource

object ReaderUtil {
    fun readerNullEndString(bufferSource: BufferedSource): String {
        val byteArray = mutableListOf<Byte>()
        while (true) {
            val byte = bufferSource.readByte()
            if (byte.compareTo(0) == 0) {
                break
            } else {
                byteArray.add(byte)
            }
        }
        return String(byteArray.toByteArray())
    }

    fun readValueByType(type: Byte, bufferSource: BufferedSource): Any {
        return when (type) {
            BasicType.BOOLEAN.hprofType, BasicType.BYTE.hprofType -> {
                bufferSource.readByte()
            }
            BasicType.CHAR.hprofType, BasicType.SHORT.hprofType -> {
                bufferSource.readShort()
            }
            BasicType.INT.hprofType, BasicType.FLOAT.hprofType -> {
                bufferSource.readInt()
            }
            BasicType.DOUBLE.hprofType, BasicType.LONG.hprofType -> {
                bufferSource.readLong()
            }
            else -> {
                bufferSource.readInt()
            }
        }
    }
}