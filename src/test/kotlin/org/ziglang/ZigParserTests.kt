package org.ziglang

import com.intellij.testFramework.ParsingTestCase
import org.ziglang.parsing.v1.ZigParserDefinition
import kotlin.test.Test

class ZigParserTests : ParsingTestCase("", ZIG_EXTENSION, ZigParserDefinition()) {
	override fun getTestDataPath() = "src/test/resources/parsing"
	override fun skipSpaces() = true

	@Test
	fun testComments() {
		println(name)
		doTest(true)
	}

	fun testParsing0() {
		println(name)
		doTest(true)
	}

	fun testComptimeCodes() {
		println(name)
		doTest(true)
	}

	fun testStrings() {
		println(name)
		doTest(true)
	}

	fun testNumbers() {
		println(name)
		doTest(true)
	}

	fun testFunctionCall() {
		println(name)
		doTest(true)
	}

	fun testParameters() {
		println(name)
		doTest(true)
	}

	fun testFloatMode() {
		println(name)
		doTest(true)
	}

	fun testFoo() {
		println(name)
		doTest(true)
	}

	fun testArrays() {
		println(name)
		doTest(true)
	}

	fun testValues() {
		println(name)
		doTest(true)
	}

	fun testIf() {
		println(name)
		doTest(true)
	}

	fun testHelloWorld() {
		println(name)
		doTest(true)
	}

	fun testTemp() {
		println(name)
		doTest(true)
	}
}

