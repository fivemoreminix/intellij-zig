package org.ziglang.parsing.v2

//class ZigTokenTypeV2(debugName: String) : IElementType(debugName, ZigLanguage.INSTANCE) {
//    companion object TokenHolder {
//        @JvmField
//        val COMMENTS = TokenSet.create(ZigTypes.LINE_COMMENT, ZigTypes.DOC_COMMENT)
//        @JvmField
//        val STRINGS = TokenSet.create(ZigTypes.STRING_LITERAL_SINGLE, ZigTypes.STRING_LITERAL)
//        @JvmField
//        val IDENTIFIERS = TokenSet.create(ZigTypes.IDENTIFIER, ZigTypes.BUILTINIDENTIFIER)
//
//        fun fromText(string: String, project: Project): PsiElement = PsiFileFactory
//            .getInstance(project)
//            .createFileFromText(ZigLanguage.INSTANCE, string)
//            .firstChild
//            .let { (it as? PsiErrorElement)?.firstChild ?: it }
//    }
//}