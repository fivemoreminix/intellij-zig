package org.ziglang.parsing.v1.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import org.ziglang.parsing.v1.psi.ZigBlock
import org.ziglang.parsing.v1.psi.ZigLocalVariableDeclaration

abstract class ZigBlockMixin(node: ASTNode) : ASTWrapperPsiElement(node), ZigBlock {
    override fun processDeclarations(
        processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement
    ) =
        statementList.all {
            it.firstChild
                .let { (it as? ZigLocalVariableDeclaration)?.variableDeclaration ?: it }
                .processDeclarations(processor, state, lastParent, place)
        }
}