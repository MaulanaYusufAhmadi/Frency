package com.dicoding.frency.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.dicoding.frency.R
import com.dicoding.frency.ViewModelFactory
import com.dicoding.frency.ui.editprofile.EditProfileActivity
import com.dicoding.frency.ui.login.LoginActivity
import com.dicoding.frency.utils.DarkMode
import java.util.Locale

class MyPreferenceFragment : PreferenceFragmentCompat() {

    private val viewModel by viewModels<AccountViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        val editProfile = findPreference<Preference>("edit_account")
        editProfile?.setOnPreferenceClickListener {
            startActivity(Intent(requireContext(), EditProfileActivity::class.java))
            true
        }

        val theme = findPreference<ListPreference>(getString(R.string.pref_key_dark))
        theme?.setOnPreferenceChangeListener { _, newValue ->
            when (newValue) {
                "auto" -> updateTheme(DarkMode.FOLLOW_SYSTEM.value)
                "on" -> updateTheme(DarkMode.ON.value)
                "off" -> updateTheme(DarkMode.OFF.value)
            }
            true
        }

        val logoutPreference = findPreference<Preference>("logout")
        logoutPreference?.setOnPreferenceClickListener {
            val builder = AlertDialog.Builder(requireActivity())
            builder.setMessage(getString(R.string.are_you_sure_you_want_to_log_out))
            builder.setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                viewModel.logout()
                dialog.dismiss()

                val intent = Intent(requireContext(), LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                requireActivity().finishAffinity()
            }
            builder.setNegativeButton(getString(R.string.no)) { dialog, _ ->
                dialog.dismiss()
            }
            val dialog = builder.create()
            dialog.show()
            true
        }

        val languagePreference = findPreference<ListPreference>("pref_key_language")
        languagePreference?.setOnPreferenceChangeListener { _, newValue ->
            when (newValue) {
                "en", "in" -> setAppLanguage(newValue.toString())
            }
            true
        }

    }

    private fun setAppLanguage(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = requireContext().resources
        val configuration = resources.configuration
        configuration.setLocale(locale)
        resources.updateConfiguration(configuration, resources.displayMetrics)

        // Restart activity agar perubahan bahasa dapat diterapkan
        requireActivity().recreate()
    }

    private fun updateTheme(mode: Int): Boolean {
        AppCompatDelegate.setDefaultNightMode(mode)
        requireActivity().recreate()
        return true
    }
}