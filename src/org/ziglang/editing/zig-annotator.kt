package org.ziglang.editing

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import org.ziglang.*
import org.ziglang.psi.*
import org.ziglang.psi.impl.prevSiblingTypeIgnoring

class ZigAnnotator : Annotator {
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when (element) {

			is ZigSymbol -> symbol(element, holder)

    //If, there are a lot of stupid code!
      is ZigIfBlock -> ifExpr(element.expr, holder)
      is ZigIfExprOrBlock -> ifExpr(element.exprList.firstOrNull() ?: return, holder)
      is ZigIfErrorBlock -> ifExpr(element.exprList.firstOrNull() ?: return, holder)
      is ZigIfErrorExprOrBlock -> ifExpr(element.exprList.firstOrNull() ?: return, holder)
      is ZigTestBlock -> ifExpr(element.exprList.firstOrNull() ?: return, holder)
      is ZigTestExprOrBlock -> ifExpr(element.exprList.firstOrNull() ?: return, holder)
		}
	}

	@Suppress("RemoveRedundantBackticks", "LocalVariableName")
	private fun symbol(element: ZigSymbol, holder: AnnotationHolder) {
		when {
			element.isFunctionName ->
				holder.createInfoAnnotation(element, null)
						.textAttributes = ZigSyntaxHighlighter.FUNCTION_DECLARATION
			element.isBuiltinFunctionName -> {
				if (element.text in builtinFunctions)
					holder.createInfoAnnotation(element, null)
							.textAttributes = ZigSyntaxHighlighter.BUILTIN_FUNCTION_CALL
				else holder.createErrorAnnotation(element, ZigBundle.message("zig.lint.unknown-builtin-symbol")).run {
					highlightType = ProblemHighlightType.LIKE_UNKNOWN_SYMBOL
					@Suppress("LocalVariableName")
					val `@` = element.prevSiblingTypeIgnoring(
							ZigTypes.AT_SYM,
							ZigTokenType.LINE_COMMENT,
							TokenType.WHITE_SPACE
					)
					if (null != `@`) registerFix(ZigRemoveElementIntention(`@`,
							ZigBundle.message("zig.lint.un-builtin")))
				}
			}
		}
	}

  private fun ifExpr(condition: ZigExpr, holder: AnnotationHolder) {
    when {
      condition is ZigBoolean -> holder.createWarningAnnotation(condition, "Condition always '${condition.text}'")
    }
  }
}
