package com.eliasyoussef47.php.suppressedattributes

import com.intellij.openapi.options.Configurable
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.Nls
import java.util.*
import javax.swing.JComponent

/**
 * Provides controller functionality for application settings.
 */
internal class SuppressedAttributesSettingsConfigurable(private var project: Project) :
	Configurable,
	Configurable.Beta {
	private var settingsComponent: SuppressedAttributesSettingsComponent? = null

	@Nls(capitalization = Nls.Capitalization.Title)
	override fun getDisplayName(): String {
		return "Suppressed Attributes"
	}

	override fun getPreferredFocusedComponent(): JComponent {
		return settingsComponent!!.preferredFocusedComponent
	}

	override fun createComponent(): JComponent {
		settingsComponent = SuppressedAttributesSettingsComponent()
		return settingsComponent!!.panel
	}

	override fun isModified(): Boolean {
		val state: SuppressedAttributesSettings.State =
			Objects.requireNonNull(SuppressedAttributesSettings.getInstance(project).state)

		val component = assertSettingsComponentNotNull()

		return component.suppressingAttributes != state.suppressingAttributes
	}

	override fun apply() {
		val state: SuppressedAttributesSettings.State =
			Objects.requireNonNull(SuppressedAttributesSettings.getInstance(project).state)

		val component = assertSettingsComponentNotNull()

		state.suppressingAttributes = component.suppressingAttributes
	}

	override fun reset() {
		val state: SuppressedAttributesSettings.State =
			Objects.requireNonNull(SuppressedAttributesSettings.getInstance(project).state)

		val component = assertSettingsComponentNotNull()

		component.suppressingAttributes = state.suppressingAttributes
	}

	private fun assertSettingsComponentNotNull() =
		settingsComponent ?: throw createSettingsComponentNotInitializedException()

	override fun disposeUIResources() {
		settingsComponent = null
	}

	private fun createSettingsComponentNotInitializedException() = IllegalStateException(
		"Settings component is not initialized. Something went incredibly wrong before this."
	)
}
