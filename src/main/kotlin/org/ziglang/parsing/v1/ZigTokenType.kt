package org.ziglang.parsing.v1

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import org.ziglang.ZigLanguage
import org.ziglang.parsing.v1.psi.ZigTypes

class ZigTokenType(debugName: String) : IElementType(debugName, ZigLanguage.INSTANCE) {
    companion object TokenHolder {
        @JvmField
        val LINE_COMMENT = ZigTokenType("comment")

        @JvmField
        val COMMENTS = TokenSet.create(LINE_COMMENT)

        @JvmField
        val STRINGS = TokenSet.create(ZigTypes.STR)

        @JvmField
        val IDENTIFIERS = TokenSet.create(ZigTypes.SYM, ZigTypes.SYMBOL)

        fun fromText(string: String, project: Project): PsiElement = PsiFileFactory
            .getInstance(project)
            .createFileFromText(ZigLanguage.INSTANCE, string)
            .firstChild
            .let { (it as? PsiErrorElement)?.firstChild ?: it }
    }
}

