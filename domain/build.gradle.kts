plugins {
    id("java-library")
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {


    val pagingVersion = "3.2.0"

    implementation(libs.androidx.paging.common.jvm)

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
}