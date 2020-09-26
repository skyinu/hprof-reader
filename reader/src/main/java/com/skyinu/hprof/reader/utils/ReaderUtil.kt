package com.skyinu.hprof.reader.utils

import com.skyinu.hprof.reader.model.BasicType
import okio.BufferedSource

object ReaderUtil {
    private const val BYTE_MASK = 0xff
    private const val INT_MASK = 0xffffffffL
    private const val SHORT_MASK = 0xFFFF

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

    fun readUnsignedByte(bufferSource: BufferedSource): Int {
        return bufferSource.readByte().toInt() and BYTE_MASK
    }

    fun readUnsignedInt(bufferSource: BufferedSource): Long {
        return bufferSource.readInt().toLong() and INT_MASK
    }

    fun readUnsignedShort(bufferSource: BufferedSource): Int {
        return bufferSource.readShort().toInt() and SHORT_MASK
    }
}