package org.ziglang.parsing.v1

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import org.ziglang.ZigLanguage
import org.ziglang.parsing.ZigFileType
import org.ziglang.parsing.v1.psi.ZigGlobalVarDeclaration

class ZigFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZigLanguage.INSTANCE) {
    override fun getFileType() = ZigFileType
    override fun processDeclarations(
        processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement
    ): Boolean = children.all {
        ((it as? ZigGlobalVarDeclaration)?.variableDeclaration ?: it).processDeclarations(
                processor,
                state,
                lastParent,
                place
            )
    }
}

