apply {
    from("$rootDir/compose-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreUI))
    "implementation"(project(Modules.core))
    "implementation"(project(Modules.rankingHistoryDomain))
    "implementation"("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    "implementation"("androidx.compose.material:material:1.1.1")
    "implementation"("io.github.bytebeats:compose-charts:0.1.2")

}