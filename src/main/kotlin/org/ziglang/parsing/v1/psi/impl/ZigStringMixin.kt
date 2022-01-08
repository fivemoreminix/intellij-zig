package org.ziglang.parsing.v1.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.ElementManipulators
import com.intellij.psi.LiteralTextEscaper
import org.ziglang.parsing.v1.psi.ZigString

abstract class ZigStringMixin(node: ASTNode) : ZigExprImpl(node), ZigString {
    override fun isValidHost() = true
    override fun updateText(text: String): ZigStringMixin = ElementManipulators.handleContentChange(this, text)
    override fun createLiteralTextEscaper() = LiteralTextEscaper.createSimple(this)
}