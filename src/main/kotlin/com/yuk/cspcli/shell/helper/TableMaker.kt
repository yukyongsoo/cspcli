package com.yuk.cspcli.shell.helper

import org.springframework.shell.table.ArrayTableModel
import org.springframework.shell.table.BeanListTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.TableBuilder
import org.springframework.stereotype.Component
import kotlin.reflect.KClass
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.memberProperties

@Component
class TableMaker(private val shellHelper: ShellHelper) {
    fun printTable(dataList: List<Any>, propertyClazz: KClass<out Any>) {
        val propertyNames = propertyClazz.memberProperties.map { it.name }.toTypedArray()
        val flattenList = dataList.map {objects ->
            objects::class.declaredMemberProperties.map {
                it.getter.call(objects).toString()
            }.toTypedArray()
        }.toTypedArray()

        val contentData : Array<Array<String>> = arrayOf(propertyNames,*flattenList)
        val model = ArrayTableModel(contentData)
        val tableBuilder = TableBuilder(model)
        tableBuilder.addHeaderBorder(BorderStyle.fancy_double)
        tableBuilder.addFullBorder(BorderStyle.oldschool)
        tableBuilder.build().render(80)
        shellHelper.print(tableBuilder.build().render(80))
    }
}