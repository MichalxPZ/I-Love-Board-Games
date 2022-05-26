package data.preferences

import java.time.LocalDate

interface Preferences {
    fun saveUserName(userName: String)

    fun saveLastSynchDate(date: LocalDate)

    fun saveShouldShowOnboarding(shouldShow: Boolean)

    fun loadUserName(): String?

    fun loadLastSynchDate(): LocalDate?

    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_USER_NAME = "user_name"
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
        const val KEY_LAST_SYNCH_DATE = "last_synch_date"
    }
}