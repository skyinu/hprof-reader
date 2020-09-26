package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagUTF8(bufferedSource: BufferedSource, parent: HprofTag) {
    var nameId: Int = 0
    var value: String = ""

    init {
        nameId = bufferedSource.readInt()
        value = bufferedSource.readUtf8((parent.bodyLength - FieldLength.U4.length).toLong())
    }
}