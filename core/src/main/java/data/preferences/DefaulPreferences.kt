package data.preferences

import android.content.SharedPreferences

class DefaulPreferences(
    private val sharedPref: SharedPreferences
): Preferences {

    override fun saveUserName(userName: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_USER_NAME, userName)
            .apply()
    }

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        sharedPref.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
            .apply()
    }

    override fun loadUserName(): String? {
        return sharedPref.getString(Preferences.KEY_USER_NAME, "")
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPref.getBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, true)
    }
}