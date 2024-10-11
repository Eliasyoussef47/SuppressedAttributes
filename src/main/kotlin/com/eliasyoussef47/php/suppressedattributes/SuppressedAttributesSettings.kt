package com.eliasyoussef47.php.suppressedattributes

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.NotNull

@Suppress("LightServiceMigrationCode")
@State(
	name = "com.eliasyoussef47.php.suppressedattributes.SuppressedAttributesSettings",
	storages = [
		Storage("SuppressedAttribute.State.xml")
	]
)
internal class SuppressedAttributesSettings : PersistentStateComponent<SuppressedAttributesSettings.State> {
	internal class State {
		var suppressingAttributes: LinkedHashSet<String> = linkedSetOf()

		override fun equals(other: Any?): Boolean {
			if (this === other) return true
			if (javaClass != other?.javaClass) return false

			val otherState = other as? State ?: return false
			return suppressingAttributes == otherState.suppressingAttributes
		}

		override fun hashCode(): Int {
			return suppressingAttributes.hashCode()
		}
	}

	private var appSettingsState = State()

	override fun getState(): State {
		return appSettingsState
	}

	override fun loadState(@NotNull state: State) {
		appSettingsState = state
	}

	companion object {
		fun getInstance(project: Project): SuppressedAttributesSettings {
			return project.getService(SuppressedAttributesSettings::class.java)
		}
	}
}
