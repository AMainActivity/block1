package ama.test.block1

import android.content.Context
import android.content.SharedPreferences

class ProfileRepository {
    companion object {
        fun profilePreference(
            context: Context,
            name: String = PROFILE_PREFERENCES
        ): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

        private inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
            val editMe = edit()
            operation(editMe)
            editMe.apply()
        }

        var SharedPreferences.userPhoto
            get() = getString(DEFAULT_SETTINGS_PROFILE_PHOTO, "") ?: ""
            set(value) {
                editMe {
                    it.putString(DEFAULT_SETTINGS_PROFILE_PHOTO, value)
                }
            }

        const val PROFILE_PREFERENCES = "PROFILE_PREFERENCES"
        const val DEFAULT_SETTINGS_PROFILE_PHOTO = "DEFAULT_SETTINGS_PROFILE_PHOTO"
    }
}