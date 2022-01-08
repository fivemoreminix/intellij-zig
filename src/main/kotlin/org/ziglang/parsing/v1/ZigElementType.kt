package org.ziglang.parsing.v1

import com.intellij.psi.tree.IElementType
import org.ziglang.ZigLanguage

class ZigElementType(debugName: String) : IElementType(debugName, ZigLanguage.INSTANCE)