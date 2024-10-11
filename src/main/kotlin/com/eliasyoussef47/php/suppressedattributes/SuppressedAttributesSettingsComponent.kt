package com.eliasyoussef47.php.suppressedattributes

import com.intellij.ui.ToolbarDecorator
import com.intellij.ui.components.JBLabel
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.FormBuilder
import javax.swing.JComponent
import javax.swing.JPanel


class SuppressedAttributesSettingsComponent {
	companion object {
		private const val STANDARD_NEW_VALUE: String = "\\Example\\Fully\\Qualified\\Name\\SuppressingAttribute"
		private const val TABLE_LABEL_TEXT: String = "Suppressing attributes:"
	}

	val panel: JPanel
	private val tableModel = SuppressedAttributesListModel()
	private val table = JBTable(tableModel)

	init {
		val toolbarDecorator = ToolbarDecorator.createDecorator(table)
			.setAddAction { tableModel.addRow(STANDARD_NEW_VALUE) }
			.setRemoveAction {
				val selectedRow = table.selectedRow
				if (selectedRow != -1) {
					tableModel.removeRow(selectedRow)
				}
			}

		panel = FormBuilder.createFormBuilder()
			.addLabeledComponent(JBLabel(TABLE_LABEL_TEXT), toolbarDecorator.createPanel(), 1, true)
			.addComponentFillVertically(JPanel(), 0)
			.panel
	}

	val preferredFocusedComponent: JComponent
		get() = table

	var suppressingAttributes: LinkedHashSet<String>
		get() = tableModel.data
		set(value) {
			tableModel.data = value
		}
}
