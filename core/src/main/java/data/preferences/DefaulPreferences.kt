package data.preferences

import android.content.SharedPreferences
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DefaulPreferences(
    private val sharedPref: SharedPreferences,
): Preferences {

    override fun saveUserName(userName: String) {
        sharedPref.edit()
            .putString(Preferences.KEY_USER_NAME, userName)
            .apply()
    }

    override fun saveLastSynchDate(date: LocalDate) {
        val formattedDate: String = date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"))
        sharedPref.edit()
            .putString(Preferences.KEY_LAST_SYNCH_DATE, formattedDate)
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

    override fun loadLastSynchDate(): LocalDate? {
        val formattedDate: String = LocalDate.of(2021, 5, 26).format(DateTimeFormatter.ofPattern("dd-MMM-yy"))
        val dateFormatter = DateTimeFormatter.ofPattern("dd-MMM-yy")
        val dateString = sharedPref.getString(Preferences.KEY_LAST_SYNCH_DATE, formattedDate)
        return LocalDate.parse(dateString, dateFormatter)
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return sharedPref.getBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, true)
    }
}