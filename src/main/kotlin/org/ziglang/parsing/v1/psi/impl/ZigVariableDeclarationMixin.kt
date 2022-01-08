package org.ziglang.parsing.v1.psi.impl

import com.intellij.lang.ASTNode
import org.ziglang.parsing.v1.psi.ZigVariableDeclaration

abstract class ZigVariableDeclarationMixin(node: ASTNode) : TrivialDeclaration(node), ZigVariableDeclaration