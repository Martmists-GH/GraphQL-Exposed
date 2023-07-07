plugins {
    kotlin("jvm")
    kotlin("kapt")
}

repositories {
    mavenCentral()
    google()
}

dependencies {
    kapt("com.google.auto.service:auto-service:1.0.1")
    implementation("com.google.auto.service:auto-service-annotations:1.0.1")

    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.0-RC-1.0.11")
    implementation(project(":gql-annotations"))
}