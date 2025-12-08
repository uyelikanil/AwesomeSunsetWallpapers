<img align="left" width="48" height="48" src="/docs/images/app-icon.png"> Awesome Sunset Wallpapers
==================
A sunset wallpaper app for Android and iOS that follows JetBrains's Compose Multiplatform guidelines, showcasing shared Kotlin code, platform specific implementations, and integration with native features.

## Screenshots
<img src="/docs/images/screen-shots.png" width="1920">

## Tech Stack
* Kotlin
* Coroutines & Flow
* Jetpack Compose (Android) & Compose Multiplatform
* Koin
* Coil 3
* Ktor
* OkHttp
* Darwin

## Pexels API Key

Awesome Sunset Wallpapers uses the [Pexels API](https://www.pexels.com/api/) to fetch sunset photos.
Pexels provides a RESTful API interface for images and videos.

### 1. Get an API key

1. Create a free account on Pexels.
2. Go to the [Pexels API dashboard](https://www.pexels.com/api/).
3. Generate your **API Key**.

### 2. Android configuration

On Android, the key is read from `local.properties` in the project root.

1. Open (or create) the `local.properties` file at the root of the project.
2. Add the following line:

   ```properties
   pexels.auth.key=your Pexels api key here
   ```

3. Do **not** commit `local.properties`. This file is already ignored by `.gitignore`.

The Android module reads this value at build time and uses it to set the `Authorization` header for Pexels requests.

### 3. iOS configuration

On iOS, the key is provided via an Xcode configuration file.

1. In the `iosApp/config` folder, copy the sample file:

   ```text
   iosApp/config/Secrets.sample.xcconfig → iosApp/config/Secrets.xcconfig
   ```

2. Open `Secrets.xcconfig` and set your key:

   ```xcconfig
   PEXELS_AUTH_KEY = your Pexels api key here
   ```

3. In Xcode, set `Secrets.xcconfig` as the **Base Configuration** for your app target’s Debug and Release configurations.

`Secrets.xcconfig` is listed in `.gitignore`, so your real API key will not be committed, while `Secrets.sample.xcconfig` stays in the repo as a template for other developers.

## License
```
Copyright 2023 Anıl Yılmaz

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
