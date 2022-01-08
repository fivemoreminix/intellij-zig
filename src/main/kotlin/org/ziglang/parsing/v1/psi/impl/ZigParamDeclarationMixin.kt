package org.ziglang.parsing.v1.psi.impl

import com.intellij.lang.ASTNode
import org.ziglang.parsing.v1.psi.ZigParamDeclaration

abstract class ZigParamDeclarationMixin(node: ASTNode) : TrivialDeclaration(node), ZigParamDeclaration