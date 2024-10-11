package com.eliasyoussef47.php.suppressedattributes

import com.intellij.codeInspection.InspectionSuppressor
import com.intellij.codeInspection.SuppressQuickFix
import com.intellij.psi.PsiElement
import com.jetbrains.php.lang.psi.elements.Field
import com.jetbrains.php.lang.psi.elements.Method
import com.jetbrains.php.lang.psi.elements.PhpAttributesOwner

class AttributeSuppressor : InspectionSuppressor {
	override fun isSuppressedFor(element: PsiElement, toolId: String): Boolean {
		val appSettings = element.project.getService(SuppressedAttributesSettings::class.java)

		// Check if the inspection tool is "PhpUnused".
		if (toolId != "PhpUnused") {
			return false
		}

		// Suppress inspection if the method has the Factory attribute
		if (element is PhpAttributesOwner && (element is Method || element is Field)) {
			if (hasFactoryAnnotation(element, appSettings)) {
				return true
			}
		}

		return false
	}

	private fun hasFactoryAnnotation(element: PhpAttributesOwner, appSettings: SuppressedAttributesSettings): Boolean {
		val suppressingAttributesSet: Set<String> = appSettings.state.suppressingAttributes
		return element.attributes.any {
			it.fqn in suppressingAttributesSet
		}
	}

	override fun getSuppressActions(element: PsiElement?, toolId: String): Array<SuppressQuickFix> {
		// No quick fixes provided, return empty array
		return SuppressQuickFix.EMPTY_ARRAY
	}
}