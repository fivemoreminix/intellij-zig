group = "org.ziglang"
version = "0.1.5"

plugins {
	java
	idea
	id("org.jetbrains.intellij") version "1.5.3"
	id("org.jetbrains.grammarkit") version "2021.2.2"
	kotlin("jvm") version "1.6.0"
}

intellij {
	version.set("IC-2021.3")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))

	implementation("org.eclipse.mylyn.github", "org.eclipse.egit.github.core", "2.1.5") {
		exclude(module = "gson")
	}

	testImplementation(group = "org.jetbrains.kotlin", name = "kotlin-stdlib-jdk8", version = "1.6.0")
	testImplementation(group = "junit", name = "junit", version = "4.13.2")
	testImplementation(kotlin("test-junit"))
}

sourceSets {
	main {
		java.srcDirs("src/main/generated")
	}
}

tasks {

	compileKotlin {
		kotlinOptions {
			jvmTarget = "11"
		}

		dependsOn(generateLexer, generateParser)
	}

	compileTestKotlin {
		kotlinOptions {
			jvmTarget = "11"
		}
	}

	generateLexer {
		// source flex file
		source.set("grammar/v1/zig-lexer.flex")

		// target directory for lexer
		targetDir.set("src/main/generated/org/ziglang/parsing/v1")

		// target classname, target file will be targetDir/targetClass.java
		targetClass.set("ZigLexer")

		// if set, plugin will remove a lexer output file before generating new one. Default: false
		purgeOldFiles.set(true)
	}

	generateParser {
		// source bnf file
		source.set("grammar/v1/zig-grammar.bnf")

		// optional, task-specific root for the generated files. Default: none
		targetRoot.set("src/main/generated/")

		// path to a parser file, relative to the targetRoot  
		pathToParser.set("/org/ziglang/parsing/v1/ZigParser.java")

		// path to a directory with generated psi files, relative to the targetRoot 
		pathToPsiRoot.set("org/ziglang/parsing/v1/psi")

		// if set, the plugin will remove a parser output file and psi output directory before generating new ones. Default: false
		purgeOldFiles.set(true)
	}

	patchPluginXml {
		changeNotes.set(file("src/main/resources/META-INF/change-notes.html").readText())
		pluginDescription.set(file("src/main/resources/META-INF/description.html").readText())
	}
	
	clean {
		delete.add("src/main/generated/")		
	}
}