// This is a generated file. Not intended for manual editing.
package org.ziglang.parsing.v1.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigAsmInput extends PsiElement {

  @Nullable
  ZigAsmClobbers getAsmClobbers();

  @NotNull
  List<ZigAsmInputItem> getAsmInputItemList();

}
