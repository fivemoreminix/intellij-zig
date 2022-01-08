package org.ziglang.parsing.v1.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.TokenType
import com.intellij.psi.scope.PsiScopeProcessor
import org.ziglang.parsing.v1.ZigTokenType
import org.ziglang.parsing.v1.psi.*

abstract class ZigSymbolMixin(node: ASTNode) : ZigExprImpl(node), ZigSymbol {
    final override val isFunctionName: Boolean
        get() = parent is ZigFnProto && prevSiblingTypeIgnoring(
            ZigTypes.FN_KEYWORD,
            TokenType.WHITE_SPACE,
            ZigTokenType.LINE_COMMENT
        ) != null

    final override val isParameter: Boolean
        get() = parent is ZigParamDeclaration
    final override val isVariableName: Boolean
        get() = (parent is ZigVariableDeclaration) && (
                    prevSiblingTypeIgnoring(ZigTypes.CONST_KEYWORD, TokenType.WHITE_SPACE, ZigTokenType.LINE_COMMENT)?:
                    prevSiblingTypeIgnoring(ZigTypes.VAR_KEYWORD, TokenType.WHITE_SPACE, ZigTokenType.LINE_COMMENT)
                ) != null

    override val isDeclaration: Boolean
        get() = isFunctionName ||
                isParameter ||
                isVariableName

    override fun getNameIdentifier() = this
    private var referenceImpl: ZigSymbolRef? = null

    /** For [ZigSymbolMixin], we cannot have a reference if it's a declaration. */
    override fun getReference() = referenceImpl ?: ZigSymbolRef(this).also { referenceImpl = it }

    override fun processDeclarations(
        processor: PsiScopeProcessor,
        state: ResolveState,
        lastParent: PsiElement?,
        place: PsiElement
    ) =
        processor.execute(this, state)

    override fun setName(name: String): PsiElement = replace(ZigTokenType.fromText(name, project))
    override fun getName(): String = text
    override fun subtreeChanged() {
//		type = null  TODO ZigExpr implements IZigExpr
        referenceImpl = null
        super.subtreeChanged()
    }
}