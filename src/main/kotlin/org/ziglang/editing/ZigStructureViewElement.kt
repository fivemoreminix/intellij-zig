package org.ziglang.editing

import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.navigation.ItemPresentation
import com.intellij.pom.Navigatable
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiNamedElement
import org.ziglang.parsing.v1.ZigFile
import org.ziglang.icons.ZigIcons
import org.ziglang.parsing.v1.psi.ZigFnDeclaration
import org.ziglang.parsing.v1.psi.ZigGlobalVarDeclaration

class ZigStructureViewElement(private val root: NavigatablePsiElement) :
    StructureViewTreeElement, ItemPresentation, SortableTreeElement, Navigatable by root {
    override fun getLocationString() = ""
    override fun getIcon(open: Boolean) = when (root) {
        is ZigFile -> ZigIcons.ZIG_FILE
        is ZigFnDeclaration -> ZigIcons.ZIG_FUN
        is ZigGlobalVarDeclaration -> ZigIcons.ZIG_VAR
        else -> ZigIcons.ZIG_BIG_ICON
    }

    override fun getPresentableText() = cutText(root.presentText(), 60)

    override fun getPresentation() = this
    override fun getValue() = root
    override fun getAlphaSortKey() = (root as? PsiNamedElement)?.name.orEmpty()
    override fun getChildren() = root
        .children
        .filter { it.treeViewTokens }
        .map { ZigStructureViewElement(it as NavigatablePsiElement) }
        .toTypedArray()
}