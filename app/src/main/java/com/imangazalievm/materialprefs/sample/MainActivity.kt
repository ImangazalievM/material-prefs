package com.imangazalievm.materialprefs.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imangazalievm.materialprefs.dsl.defaultPrefValues
import com.imangazalievm.materialprefs.dsl.defaultPrefsStorage
import com.imangazalievm.materialprefs.dsl.prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val defValues = defaultPrefValues {
            "enable_check" to true
            "enable_switch" to false
            "my_label" to "Hello world!"
        }

        val storage = defaultPrefsStorage("my_app", defValues)

        prefs(prefsView, storage) {

            category("User Settings") {
                preference {
                    title("My account")
                    icon(R.drawable.ic_account_box_24)
                    showArrow(true)
                    onClick {
                        showMessage("My account")
                    }
                }

                switch("enable_switch") {
                    title("Notifications")
                    summary("Receive notifications")
                    onChecked { isChecked ->
                        showMessage("Notifications: $isChecked")
                    }
                }

                checkbox("enable_check") {
                    title("Personal Data Transfer")
                    summary("Allow personal data transfer to the third party")
                    onChecked { isChecked ->
                        showMessage("Personal Data Transfer: $isChecked")
                    }
                }

            }

            category("App Settings") {
                labelPreference("app_language") {
                    title("App Language")
                    icon(R.drawable.ic_language)
                    onClick {
                        showMessage("My account")
                    }
                }

                preference {
                    title("Appearance")
                    icon(R.drawable.ic_palette_24)
                    onClick {
                        showMessage("My account")
                    }
                }
            }

            category("App Information") {
                preference {
                    title("Change Log")
                    icon(R.drawable.ic_info_24)
                    onClick {
                        showMessage("My account")
                    }
                }

                preference {
                    title("Support")
                    icon(R.drawable.ic_help_24)
                    onClick {

                    }
                }
            }
        }
    }

    private fun showMessage(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}