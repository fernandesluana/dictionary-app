# Dictionary App

A versatile Dictionary App built with Kotlin, featuring a UI implemented with Jetpack Compose. The app utilizes the Dictionary API from https://api.dictionaryapi.dev/ to fetch word definitions without the need for an API key. Retrofit is employed to handle API requests efficiently, while Room database is used to cache searched words for offline access. Kotlin coroutines manage asynchronous operations, ensuring smooth performance.

## Stack

- **Kotlin** - First-class and official programming language for Android development.
- **Jetpack Compose** - Modern toolkit for building native Android UI.
- **Retrofit** - A type-safe HTTP client for Android and Java to handle API requests.
- **Room** - SQLite object mapping library for local data caching.
- **Coroutines** - For managing background threads and asynchronous operations.
- **Dictionary API** - Provides word definitions.

## Features

- Search for word definitions
- View word definitions, including part of speech, pronunciation, and meanings
- Offline access with cached word definitions
- Clean and intuitive UI using Jetpack Compose

## Build and Run

### Prerequisites:

- Android Studio

### Clone this repository:

```bash
git clone https://github.com/fernandesluana/dictionary-app.git
