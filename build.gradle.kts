plugins {
	id("java")
	id("org.jetbrains.kotlin.jvm") version "1.9.25"
	id("org.jetbrains.intellij.platform") version "2.1.0"
}

group = "com.eliasyoussef47.php.suppressedattributes"
version = "0.2.1-beta"

repositories {
	mavenCentral()

	intellijPlatform {
		defaultRepositories()
	}
}

dependencies {
	intellijPlatform {
		phpstorm("2024.2.3")
		bundledPlugin("com.jetbrains.php")
		instrumentationTools()
		zipSigner()
	}
}

tasks {
	// Set the JVM compatibility versions
	withType<JavaCompile> {
		sourceCompatibility = "17"
		targetCompatibility = "17"
	}
	withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
		kotlinOptions.jvmTarget = "17"
	}

	patchPluginXml {
		sinceBuild.set("232")
		untilBuild.set(provider { null })
	}

	signPlugin {
		certificateChainFile.set(file(System.getenv("CERTIFICATE_CHAIN_FILE")))
		privateKeyFile.set(file(System.getenv("PRIVATE_KEY_FILE")))
		password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
	}

	publishPlugin {
		token.set(System.getenv("PUBLISH_TOKEN"))
	}
}
