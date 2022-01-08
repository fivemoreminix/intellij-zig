package org.ziglang

import com.intellij.testFramework.LightPlatformTestCase
import org.ziglang.parsing.v1.ZigTokenType

class ZigBasicParsingTest : LightPlatformTestCase() {
	fun testParse() {
		ZigTokenType.fromText("abc", project).javaClass
				.let(::println)
	}
}