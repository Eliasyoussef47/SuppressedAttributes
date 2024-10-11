package com.eliasyoussef47.php.suppressedattributes

import ai.grazie.utils.toLinkedSet
import javax.swing.table.AbstractTableModel

class SuppressedAttributesListModel : AbstractTableModel() {
	var data: LinkedHashSet<String> = linkedSetOf()

	override fun getRowCount(): Int = data.size

	override fun getColumnCount(): Int = 1

	override fun getValueAt(rowIndex: Int, columnIndex: Int): Any = data.elementAt(rowIndex)

	override fun getColumnName(column: Int): String = "Attribute FQN"

	override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean = true

	override fun setValueAt(aValue: Any, rowIndex: Int, columnIndex: Int) {
		val dataList = data.toMutableList()
		dataList[rowIndex] = aValue as String
		data = dataList.toLinkedSet()
		fireTableCellUpdated(rowIndex, columnIndex)
	}

	fun addRow(value: String) {
		data.add(value)
		fireTableRowsInserted(data.size - 1, data.size - 1)
	}

	fun removeRow(rowIndex: Int) {
		val value = data.elementAtOrNull(rowIndex) ?: return
		data.remove(value)
		fireTableDataChanged()
	}
}
