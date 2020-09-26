package com.skyinu.hprof.reader.utils

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
}