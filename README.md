# 📄 LineTextEdit

**LineTextEdit** is a Kotlin-based text editing component built with **Jetpack Compose** and **Hilt**.  
It allows users to edit multi-line text on a per-line basis, offering intuitive UI and modern UX with material design.

---

## ✨ Features

- ✅ Standard multi-line text editing (TextEdit View)
- ✂️ Line-by-line editing
- 📌 Line selection and range deletion
- 📝 Individual line modification with multi-line support
- 💾 Save and cancel actions
- 🎨 Material Design UI

---

## 🧱 Tech Stack

| Technology | Description |
|------------|-------------|
| Kotlin | Programming language |
| Jetpack Compose | UI framework |
| Hilt | Dependency injection |
| ViewModel | State management |
| Material Theme | Design system |

---

## Usage
```kotlin
    var content by remember { mutableStateOf("Sample Text") }
    LineTextEdit(
        content,
        modifier = Modifier.fillMaxWidth()
            .weight(1f),
        onSave = { text ->
            content1 = text
        },
        useLineWrap = false,
        useButtonContainer = false,
    )
```

## ScreenShot
![sample](screenshots/screenshot1.jpg?raw=true)

## License

This component was developed through several iterations after providing prompts to both Copilot and Gemini. 
Many of the key insights that shaped its design were derived from their responses. 
It is released as open-source under the MIT license.

I do not expect to make frequent updates to this repository. 
If you need to modify it, feel free to do so as you wish. 
I kindly ask that you refrain from submitting pull requests or issues.

MIT License © 2025
