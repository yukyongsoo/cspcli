package com.yuk.cspcli.shell.helper

import org.springframework.shell.table.BeanListTableModel
import org.springframework.shell.table.BorderStyle
import org.springframework.shell.table.TableBuilder
import org.springframework.stereotype.Component

@Component
class TableMaker(private val shellHelper: ShellHelper) {
    fun <T> printTable(dataList : List<T>){
        val model = BeanListTableModel<T>(dataList)
        val tableBuilder = TableBuilder(model)
        tableBuilder.addFullBorder(BorderStyle.oldschool)
        tableBuilder.build().render(80)
        shellHelper.print(tableBuilder.build().render(80))
    }
}