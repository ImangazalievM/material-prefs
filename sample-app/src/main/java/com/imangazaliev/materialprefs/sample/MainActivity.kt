package com.imangazaliev.materialprefs.sample

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.imangazaliev.materialprefs.dsl.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val defValues = defaultPrefValues {
            "username" to "Jhon Doe"
            "list" to "me_2"
            "list_2" to 2
            "list_multi" to "2,3"
            "my_color" to "#ff0000"
            "user_birthday" to GregorianCalendar(1990, 7, 5).timeInMillis
            "my_time" to Date().time
            "my_datetime" to Date().time
            "data_transfer" to false
            "notifications" to true
            "app_version" to "1.4.2"
            "app_language" to "en"
        }

        val storage = defaultPrefsStorage("my_app", defValues)

        prefs(prefsView, storage) {
            category("User Settings") {
                preference {
                    title("My account")
                    icon(R.drawable.ic_account_box)
                    showArrow(true)
                    onClick {
                        showMessage("My account")
                    }
                }

                textInput("username") {
                    title("Username")
                    icon(R.drawable.ic_username)
                }

                datePicker("user_birthday") {
                    title("My birthday")
                    requireFutureDate(true)
                }

                switch("notifications") {
                    title("Notifications")
                    summary("Receive notifications")
                    onChecked { isChecked ->
                        showMessage("Notifications: $isChecked")
                    }
                }

                checkbox("data_transfer") {
                    title("Personal Data Transfer")
                    summary("Allow personal data transfer to the third party")
                    onChecked { isChecked ->
                        showMessage("Personal Data Transfer: $isChecked")
                    }
                }

            }

            category("App Settings") {
                listSingleChoice("app_language", String::class) {
                    title("App Language")
                    icon(R.drawable.ic_language)
                    showRadioButtons(true)
                    items(
                        listOf(
                            ListItem("ar", "Arabic"),
                            ListItem("en", "English"),
                            ListItem("ru", "Russian")
                        )
                    )
                }

                preference {
                    title("Appearance")
                    icon(R.drawable.ic_palette)
                    onClick {
                        showMessage("Appearance")
                    }
                }
            }

            category("App Information") {
                labelPreference("app_version", String::class) {
                    title("App version")
                    onClick {
                        showMessage("App version")
                    }
                }

                preference {
                    title("Change Log")
                    icon(R.drawable.ic_info)
                    onClick {
                        showMessage("My account")
                    }
                }

                preference {
                    title("Support")
                    icon(R.drawable.ic_help)
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