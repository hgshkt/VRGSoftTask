plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {

    val pagingVersion = "3.3.0"

    implementation("androidx.paging:paging-common:$pagingVersion")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
}