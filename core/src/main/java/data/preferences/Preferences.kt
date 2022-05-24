package data.preferences

interface Preferences {
    fun saveUserName(userName: String)

    fun saveShouldShowOnboarding(shouldShow: Boolean)

    fun loadUserName(): String?

    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_USER_NAME = "user_name"
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }
}