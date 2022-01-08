package org.ziglang.parsing.v1.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator
import org.ziglang.parsing.v1.ZigTokenType

class ZigStringManipulator : AbstractElementManipulator<ZigString>() {
    override fun handleContentChange(psi: ZigString, range: TextRange, new: String): ZigString {
        val after = ZigTokenType.fromText(new, psi.project) as? ZigString ?: return psi
        psi.replace(after)
        return after
    }
}
