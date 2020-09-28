package com.skyinu.hprof.reader.model

enum class BasicType(val hprofType: Byte, val byteSize: Int) {
    OBJECT(2, 4),
    BOOLEAN(4, 1),
    CHAR(5, 2),
    FLOAT(6, 4),
    DOUBLE(7, 8),
    BYTE(8, 1),
    SHORT(9, 2),
    INT(10, 4),
    LONG(11, 8);
}