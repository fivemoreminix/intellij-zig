// This is a generated file. Not intended for manual editing.
package org.ziglang.parsing.v1.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigTestBlock extends PsiElement {

  @NotNull
  ZigBlock getBlock();

  @Nullable
  ZigBlockBlock getBlockBlock();

  @NotNull
  List<ZigExpr> getExprList();

}
