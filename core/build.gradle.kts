plugins {
    kotlin("plugin.serialization") version "1.6.21"
}

apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(pl.org.akai.buildsrc.Serialization.serialization)
}