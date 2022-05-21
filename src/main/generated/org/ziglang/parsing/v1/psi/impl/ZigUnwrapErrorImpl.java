// This is a generated file. Not intended for manual editing.
package org.ziglang.parsing.v1.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.ziglang.parsing.v1.psi.ZigTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.ziglang.parsing.v1.psi.*;

public class ZigUnwrapErrorImpl extends ASTWrapperPsiElement implements ZigUnwrapError {

  public ZigUnwrapErrorImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitUnwrapError(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ZigExpr> getExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZigExpr.class);
  }

}
