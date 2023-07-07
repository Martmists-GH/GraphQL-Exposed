pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        google()
    }
}

rootProject.name = "ktor-exposed-graphql"

include(":gql-annotations")
include(":gql-processor")
