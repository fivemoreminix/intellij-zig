package org.ziglang.parsing.v1.psi.impl

import com.intellij.psi.PsiNameIdentifierOwner

interface IZigSymbol : PsiNameIdentifierOwner {
    val isFunctionName: Boolean
    val isParameter: Boolean
    val isVariableName: Boolean
    val isDeclaration: Boolean
}