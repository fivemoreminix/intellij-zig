package org.ziglang.parsing

import com.intellij.openapi.fileTypes.LanguageFileType
import org.ziglang.ZIG_EXTENSION
import org.ziglang.ZIG_NAME
import org.ziglang.ZigLanguage
import org.ziglang.i18n.ZigBundle
import org.ziglang.icons.ZigIcons

object ZigFileType : LanguageFileType(ZigLanguage.INSTANCE) {
    override fun getIcon() = ZigIcons.ZIG_FILE
    override fun getName() = ZIG_NAME
    override fun getDefaultExtension() = ZIG_EXTENSION
    override fun getDescription() = ZigBundle.message("zig.description")
}