package com.skyinu.hprof.reader.model

enum class FieldLength(val length: Int) {
    U1(1),
    U2(2),
    U4(4),
    U8(8)
}