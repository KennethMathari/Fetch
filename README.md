# Fetch Mobile App
This is a native Android app that displays a filtered and sorted list of items.

## Setup
To set up and run this project on your local machine, follow these steps:

1. Clone the repository:
> git clone <https://github.com/KennethMathari/Fetch>
2. Open the project in Android Studio.
- Launch Android Studio and select Open an existing Android Studio project. Then navigate to the directory where you cloned the repository and select the project.
3. Sync Gradle files:
- After opening the project, Gradle will automatically start syncing. Wait for the process to finish.

## Build and Run
After opening the project, build and run to install the app in the emulator or a connected device.

## App Features
1. <b>List Items</b>
- Displays a filtered & sorted list of items.

## API
> Products API: <https://fetch-hiring.s3.amazonaws.com/hiring.json>

## Libraries & Plugins
- <b>Jetpack Compose </b>: For building the UI in a declarative manner.
- <b>Koin </b>: For dependency injection to manage dependencies efficiently.
- <b>Retrofit </b>: For network operations to fetch data from the API.
- <b>Kotlinx Serialization </b>: Facilitates data serialization and deserialization in a format-agnostic way.
- <b>Instantiator </b>: a little Kotlin library that uses reflection to fill data class with random test data.
- <b>JUnit </b>: For unit testing.
- <b>MockK </b>: For mocking dependencies in tests.

Other dependencies are listed in the build.gradle files.

## Architecture
The project follows the MVI (Model-View-Intent) architecture

## App Demo
![img.png](img.png)