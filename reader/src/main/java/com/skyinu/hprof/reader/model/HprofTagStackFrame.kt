package com.skyinu.hprof.reader.model

import okio.BufferedSource

class HprofTagStackFrame(bufferedSource: BufferedSource, parent: HprofTag) {
    var stackFrameId = 0
    var methodNameId = 0
    var methodSignatureId = 0
    var sourceFileNameId = 0
    var classSerialNumber = 0
    var lineNumber = 0

    init {
        stackFrameId = bufferedSource.readInt()
        methodNameId = bufferedSource.readInt()
        methodSignatureId = bufferedSource.readInt()
        sourceFileNameId = bufferedSource.readInt()
        classSerialNumber = bufferedSource.readInt()
        lineNumber = bufferedSource.readInt()
    }
}