package com.skyinu.hprof.reader.model

import com.skyinu.hprof.reader.utils.ReaderUtil
import okio.BufferedSource

class HprofHeapClassDump(bufferedSource: BufferedSource) {
    var classObjectId = 0
    var stackSerialNumber = 0
    var superClassObjectId = 0
    var classLoaderObjectId = 0
    var signerObjectId = 0
    var protectionDomainObjectId = 0
    var reservedOne = 0
    var reservedTwo = 0
    var instanceSize = 0
    var constantPoolList: List<ConstantPool> = listOf()
    var staticFieldList: List<StaticField> = listOf()
    var instanceFieldList: List<InstanceField> = listOf()

    init {
        classObjectId = bufferedSource.readInt()
        stackSerialNumber = bufferedSource.readInt()
        superClassObjectId = bufferedSource.readInt()
        classLoaderObjectId = bufferedSource.readInt()
        signerObjectId = bufferedSource.readInt()
        protectionDomainObjectId = bufferedSource.readInt()
        reservedOne = bufferedSource.readInt()
        reservedTwo = bufferedSource.readInt()
        instanceSize = bufferedSource.readInt()
        var listObjectSize = bufferedSource.readShort()
        val tmpPoolList = mutableListOf<ConstantPool>()
        constantPoolList = tmpPoolList
        repeat(listObjectSize.toInt()) {
            val constantPool = ConstantPool()
            constantPool.constantPoolIndex = bufferedSource.readShort()
            constantPool.entryType = bufferedSource.readByte()
            constantPool.any = ReaderUtil.readValueByType(constantPool.entryType, bufferedSource)
            tmpPoolList.add(constantPool)
        }

        listObjectSize = bufferedSource.readShort()
        val tmpStaticList = mutableListOf<StaticField>()
        staticFieldList = tmpStaticList
        repeat(listObjectSize.toInt()) {
            val staticField = StaticField()
            staticField.nameStringId = bufferedSource.readInt()
            staticField.entryType = bufferedSource.readByte()
            staticField.any = ReaderUtil.readValueByType(staticField.entryType, bufferedSource)
            tmpStaticList.add(staticField)
        }

        listObjectSize = bufferedSource.readShort()
        val tmpInstanceList = mutableListOf<InstanceField>()
        instanceFieldList = tmpInstanceList
        repeat(listObjectSize.toInt()) {
            val instanceField = InstanceField()
            instanceField.nameStringId = bufferedSource.readInt()
            instanceField.entryType = bufferedSource.readByte()
            tmpInstanceList.add(instanceField)
        }
    }

    class ConstantPool {
        var constantPoolIndex: Short = 0
        var entryType: Byte = 0
        var any = Any()
    }

    class StaticField {
        var nameStringId = 0
        var entryType: Byte = 0
        var any = Any()
    }

    class InstanceField {
        var nameStringId = 0
        var entryType: Byte = 0
    }
}