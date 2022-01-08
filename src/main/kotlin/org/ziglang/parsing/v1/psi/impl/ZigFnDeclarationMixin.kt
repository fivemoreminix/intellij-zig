package org.ziglang.parsing.v1.psi.impl

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import org.ziglang.parsing.v1.psi.ZigFnDeclaration

abstract class ZigFnDeclarationMixin(node: ASTNode) : TrivialDeclaration(node), ZigFnDeclaration {
    override fun processDeclarations(
        processor: PsiScopeProcessor,
        substitutor: ResolveState,
        lastParent: PsiElement?,
        place: PsiElement
    ) =
        fnProto.parameterList.paramDeclarationList.all {
            it.processDeclarations(
                processor,
                substitutor,
                lastParent,
                place
            )
        }
                && super.processDeclarations(processor, substitutor, lastParent, place)
}