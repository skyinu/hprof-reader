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

    var count = 0

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
        count += (FieldLength.U4.length * 9)
        var listObjectSize = ReaderUtil.readUnsignedShort(bufferedSource)
        println("HprofHeapClassDump constant pool $listObjectSize ")
        count += FieldLength.U2.length
        val tmpPoolList = mutableListOf<ConstantPool>()
        constantPoolList = tmpPoolList
        repeat(listObjectSize) {
            val constantPool = ConstantPool()
            constantPool.constantPoolIndex = bufferedSource.readShort()
            constantPool.entryType = bufferedSource.readByte()
            val element = ReaderUtil.readValueByType(constantPool.entryType, bufferedSource)
            constantPool.any = element.first
            count += element.second + FieldLength.U2.length + FieldLength.U1.length
            tmpPoolList.add(constantPool)
        }

        listObjectSize = ReaderUtil.readUnsignedShort(bufferedSource)
        println("HprofHeapClassDump static field $listObjectSize ")
        count += FieldLength.U2.length
        val tmpStaticList = mutableListOf<StaticField>()
        staticFieldList = tmpStaticList
        repeat(listObjectSize) {
            val staticField = StaticField()
            staticField.nameStringId = bufferedSource.readInt()
            staticField.entryType = bufferedSource.readByte()
            val element = ReaderUtil.readValueByType(staticField.entryType, bufferedSource)
            staticField.any = element.first
            count += element.second + +FieldLength.U4.length + FieldLength.U1.length
            tmpStaticList.add(staticField)
        }

        listObjectSize = ReaderUtil.readUnsignedShort(bufferedSource)
        println("HprofHeapClassDump instance field $listObjectSize ")
        count += FieldLength.U2.length
        val tmpInstanceList = mutableListOf<InstanceField>()
        instanceFieldList = tmpInstanceList
        repeat(listObjectSize) {
            val instanceField = InstanceField()
            instanceField.nameStringId = bufferedSource.readInt()
            instanceField.entryType = bufferedSource.readByte()
            tmpInstanceList.add(instanceField)
            count += FieldLength.U4.length + FieldLength.U1.length
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