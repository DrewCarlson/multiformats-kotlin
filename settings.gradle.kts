pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven {
            url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
        }
    }

}

rootProject.name = "multiformats"

include(
    ":multibase",
    ":multiaddr",
    ":multihash"
)

enableFeaturePreview("GRADLE_METADATA")