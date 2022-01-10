package org.ziglang.parsing.v1

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.ziglang.ZigLanguage
import org.ziglang.parsing.v1.psi.ZigTypes

class ZigParserDefinition : ParserDefinition {
    override fun createParser(project: Project?) = ZigParser()
    override fun createLexer(project: Project?) = ZigLexerAdapter()
    override fun createFile(viewProvider: FileViewProvider) = ZigFile(viewProvider)
    override fun createElement(node: ASTNode?): PsiElement = ZigTypes.Factory.createElement(node)
    override fun getStringLiteralElements() = ZigTokenType.STRINGS
    override fun getCommentTokens() = ZigTokenType.COMMENTS
    override fun getFileNodeType() = FILE
    override fun getWhitespaceTokens(): TokenSet = TokenSet.WHITE_SPACE
    override fun spaceExistanceTypeBetweenTokens(left: ASTNode?, right: ASTNode?) =
        ParserDefinition.SpaceRequirements.MAY

    private companion object FileHolder {
        private val FILE = IFileElementType(ZigLanguage.INSTANCE)
    }
}