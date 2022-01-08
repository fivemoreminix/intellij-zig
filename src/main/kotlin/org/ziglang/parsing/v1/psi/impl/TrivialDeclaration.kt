package org.ziglang.parsing.v1.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil
import org.ziglang.parsing.v1.ZigTokenType
import org.ziglang.util.orTrue
import org.ziglang.parsing.v1.psi.ZigSymbol

abstract class TrivialDeclaration(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    override fun getNameIdentifier() = PsiTreeUtil.findChildOfType(this, ZigSymbol::class.java)
    override fun setName(name: String) = also { nameIdentifier?.replace(ZigTokenType.fromText(name, project)) }
    override fun getName() = nameIdentifier?.text

    override fun processDeclarations(
        processor: PsiScopeProcessor, substitutor: ResolveState, lastParent: PsiElement?, place: PsiElement
    ) =
        nameIdentifier?.processDeclarations(processor, substitutor, lastParent, place).orTrue()
}