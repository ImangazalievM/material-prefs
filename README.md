![Image](/images/logo.png)

# Material Preferences

![Image](/images/screenshots.png)

## 💻 Installation
[ ![Download](https://api.bintray.com/packages/imangazaliev/maven/material-prefs%3Acore/images/download.svg) ](https://bintray.com/imangazaliev/maven/material-prefs%3Acore/_latestVersion)

Add this in app's ```build.gradle``` file:
```groovy
implementation 'com.imangazaliev.material-prefs:core:<version>'
implementation 'com.imangazaliev.material-prefs:dialogs:<version>'
implementation 'com.imangazaliev.material-prefs:date-time:<version>'
implementation 'com.imangazaliev.material-prefs:color-picker:<version>'
```

## ⭐ Features

* Convenient and extensible DSL
* Flexible appearance settings
* Unified view on Lollipop and Pre-Lollipop
* Ability to use custom storage
* Default prefs values
* Light and dark themes

## 🔥 Usage

To start using the library you need to do 3 simple steps:
1. Add `MaterialPreferencesView` in your layout
2. Provide preferences storage:<br>
    3.1 Default storage - `DefaultPreferencesStorage`<br>
    3.1 Any custom storage which implements the `PreferencesStorage` interface
3. Build prefs screen via MaterialPrefs DSL

If you want to use `DefaultPreferencesStorage` you have to provide initial values through `DefaultValuesContainer`.

**Step 1**
Place the `MaterialPreferencesView` in your layout:
```xml
<com.imangazaliev.materialprefs.views.MaterialPreferencesView
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
                //my action
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

### Core

**Simple preference:**
```kotlin
preference {
    title("Title")
    summary("My description")
    icon(R.drawable.ic_my_icon)
    showArrow(true)
    onClick {
        //my action
    }
}
```

**Switch:**
```kotlin
switch("my_key") {
    title("Title")
    summary("My description")
    onChecked { isChecked ->
        //my action
    }
}
```

**Checkbox:**
```kotlin
checkbox("my_key") {
    title("Title")
    summary("My description")
    onChecked { isChecked ->
        //my action
    }
}
```

**Label preference:**
```kotlin
labelPreference("my_key", String::class) {
    title("Title")
    onClick {
        //my action
    }
}
```

### Dialogs

**Single Choice:**
```kotlin
listSingleChoice("my_key", String::class) {
    title("Title")
    icon(R.drawable.ic_my_icon)
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

**Multi Choice:**
```kotlin
listMultiChoice("my_key", String::class) {
    title("Title")
    allowEmptySelection(false)
    //required
    listValuesSerializer { it.joinToString() }
    //required
    listValuesDeserializer {
        if (it.isNotEmpty()) {
            it.split(",")
                .map { number -> number.trim().toInt() }
        } else emptyList()
    }             
    items(
        listOf(
            ListItem("one", "Item 1"),
            ListItem("two", "Item 2"),
            ListItem("three", "Item 3")
        )
    )
}
```

**Text Input:**
```kotlin
textInput("my_key") {
    title("Title")
    icon(R.drawable.ic_username)
    onNewInput { 
        //my action       
    }
}
```

### DateTime

**Date picker:**
```kotlin
datePicker("my_key") {
    title("Title")
    val formatter = SimpleDateFormat("dd.MM.yyyy ", Locale.US)
    valuePresenter { formatter.format(it) }
    onValueSelected {
        //my action
    }
}
```

**Time picker:**
```kotlin
timePicker("my_key") {
    title("Title")
    val formatter = SimpleDateFormat("hh:mm ", Locale.US)
    valuePresenter { formatter.format(it) }
    onValueSelected {
        //my action
    }
}
```

**Date and picker:**
```kotlin
dateTimePicker("my_key") {
    title("Title")
    val formatter = SimpleDateFormat("hh:mm dd.MM.yyyy ", Locale.US)
    valuePresenter { formatter.format(it) }
    onValueSelected {
        //my action
    }
}
```


### ColorPicker

```kotlin
colorPicker("my_key") {
    title("Title")
    val colors = intArrayOf(Color.RED, Color.GREEN, Color.BLUE)
    colors(colors)
    onColorSelected {
        //my action
    }
}
```

### Custom preference item

To create custom preference item you have to do 3 steps:

**Step 1:**
Create preference view class inherited from `PreferenceView` or `BasePreferenceView`. 

If your view inherited from `BasePreferenceView` you have to implement `createValueView` method:

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

Copyright (c) 2020 Mahach Imangazaliev

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
