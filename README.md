![Image](/images/logo.png)

# Material Preferences
[ ![Download](https://api.bintray.com/packages/imangazaliev/maven/material-prefs%3Acore/images/download.svg) ](https://bintray.com/imangazaliev/maven/material-prefs%3Acore/_latestVersion)

### 💻 Installation
Add this in app's ```build.gradle``` file:

```groovy
    implementation 'com.github.imangazalievm.material-prefs:core:1.0.0'
    implementation 'com.github.imangazalievm.material-prefs:dialogs:1.0.0'
    implementation 'com.github.imangazalievm.material-prefs:date-time:1.0.0'
    implementation 'com.github.imangazalievm.material-prefs:color-picker:1.0.0'
```

## ⭐ Features

* Convenient and extensible DSL
* Flexible appearance settings
* Unified view on Lollipop and Pre-Lollipop
* Ability to use custom storage
* Default prefs values
* Light and dark themes

## 🔥 Usage

To start using the library you need to do 3simple steps:
1. Add `MaterialPreferencesView` in your layout
2. Provide preferences storage:<br>
    3.1 Default storage - `DefaultPreferencesStorage`<br>
    3.1 Any custom storage which implements the `PreferencesStorage` interface
3. Builds prefs screen via MaterialPrefs DSL

If want to use `DefaultPreferencesStorage` you have to provide initial values through `DefaultValuesContainer`.

**Step 1**
Place the `MaterialPreferencesView` in your layout:
```xml
<com.imangazalievm.materialprefs.views.MaterialPreferencesView
        android:id="@+id/prefsView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

**Step 2**
Create prefs storage and provide initial values:
```kotlin
val defValues = defaultPrefValues {
    "my_string" to "Jhon Doe"
    "my_int" to 99
    "my_long" to 5L
    "my_float" to 2.5f
    "my_boolean" to true
}

val storage = defaultPrefsStorage("my_prefs", defValues)
```

**Step 3**
Add pref items via MaterialPrefs DSL:
```kotlin
prefs(prefsView, storage) {
    category("My category") {
         preference {
            title("My pref item")
            icon(R.drawable.my_icon)
            onClick {
                showMessage("Clicked!")
            }
        }
    }
}
            
```


## 📄 Documentation

The library includes 4 modules:
* **core** - contains main code: simple prefs, checkbox, switch
* **dialogs** - dialogs prefs: single and multiple choice
* **date-time** - date and time pickers
* **color-picker** - color picker

Three last modules based on [Material Dialogs](https://github.com/afollestad/material-dialogs) library.

All prefs values save their values automatically after a value is changed.

### Core

**Simple preference:**
```kotlin
preference {
    title("My account")
    icon(R.drawable.ic_account_box)
    showArrow(true)
    onClick {
        showMessage("My account")
    }
}
```

**Switch:**
```kotlin
switch("my_key") {
    title("Notifications")
    summary("Receive notifications")
    onChecked { isChecked ->
        showMessage("Notifications: $isChecked")
    }
}
```

**Checkbox:**
```kotlin
checkbox("my_key") {
    title("Personal Data Transfer")
    summary("Allow personal data transfer to the third party")
    onChecked { isChecked ->
        showMessage("Personal Data Transfer: $isChecked")
    }
}
```

**Label preference:**
```kotlin
labelPreference("my_key", String::class) {
    title("App version")
    onClick {
        showMessage("App version")
    }
}
```

### Dialogs

```kotlin
listSingleChoice("my_key", String::class) {
    title("Title")
    icon(R.drawable.ic_myicon)
    showRadioButtons(true)
    items(
        listOf(
            ListItem("ar", "Arabic"),
            ListItem("en", "English"),
            ListItem("ru", "Russian")
        )
    )
}
```

**Text input:**
```kotlin
textInput("my_key") {
    title("Title")
    icon(R.drawable.ic_username)
}
```

### DateTime

**Date picker:**
```kotlin
datePicker("my_key") {
    
}
```

**Time picker:**
```kotlin
timePicker("my_key") {
    
}
```

**Date and picker:**
```kotlin
dateTimePicker("my_key") {
    
}
```


### ColorPicker

```kotlin
colorPicker("my_key") {
    
}
```

### Custom pref item

To create custom pref item you have to do 3 steps:

**Step 1:**
Create preference view class inherited from `PreferenceView` or `BasePreferenceView`. 

If your view inherited from `BasePreferenceView` you have to implement `getValueView` method:

```kotlin
class MyPreferenceView(
    context: Context,
    attrs: AttributeSet? = null,
    themeResId: Int = 0
) : BasePreferenceView(context, attrs, themeResId) {

    override fun createValueView(parent: ViewGroup): View {
        return parent.inflate(R.layout.my_pref_view)
    }

}
```

**Step 2:**
Create preference class inherited from `Preference` or `BasePreference` and implement 3 methods:
```kotlin
abstract class MyPreference(
    key: String,
    container: PrefsContainer,
    private val appearanceManager: PreferencesAppearance
) : BasePreference<MyPreference, MyPreferenceView, String>(
    container = container,
    key = key,
    appearanceManager = appearanceManager
) {

    override fun createView(): V {
    }

    override fun loadValue(view: V) {
    }
 
    override fun initView(view: V) {
    }

}
```

Third generic parameter of `BasePreference` is a type of data, that will be save in the preferences, so it must be one of the following types:
* String
* Int
* Long
* Float
* Boolean

**Step 3:**
Create extension method for `MaterialSettings`:
```kotlin
fun myPreference(builder: PreferenceBuilder<MyPreference>) {
    MyPreference(container, appearanceManager)
        .apply(builder)
        .also { addPreference(it) }
}
```

## 🤝 License

```
The MIT License

Copyright (c) 2016-2020 Mahach Imangazaliev

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```