package data.preferences

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationSettings(
    val userName: String? = null,
    val shouldShowOnboarding: Boolean = true
)
