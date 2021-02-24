import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("jvm") version "1.4.30"
  id("com.github.ben-manes.versions") version "0.36.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib"))
  implementation("com.google.jimfs:jimfs:1.2")
  implementation("com.googlecode.concurrent-trees:concurrent-trees:2.6.1")

  implementation("com.github.ajalt.clikt:clikt:3.1.0")
}

tasks {
  listOf("Loader").forEach {
    register("Loader", JavaExec::class) {
      main = "${it}Kt"
//      findProperty("ath")?.let { args = listOf("--path=$it") }
      classpath = sourceSets["main"].runtimeClasspath
    }
  }

  withType<KotlinCompile> {
    kotlinOptions {
      languageVersion = "1.5"
      apiVersion = "1.5"
      jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
  }
}