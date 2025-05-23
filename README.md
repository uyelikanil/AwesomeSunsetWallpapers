<img align="left" src="/app/src/main/res/mipmap-mdpi/ic_launcher.png"> Awesome Sunset Wallpapers
==================
A sunset wallpaper app for Android. It follows Android's architecture guideline and entirely written with Kotlin.

## Screenshots
<img src="/docs/images/wallpaper-list-screen.png" width="270"> <img src="/docs/images/wallpaper-detail-screen.png" width="270"> <img src="/docs/images/wallpaper-full-screen.png" width="270">

## Tech Stack
* Kotlin
* Coroutines & Flow
* Android Architecture Components
* Dagger Hilt
* Coil
* Retrofit
* OkHttp

## Pexels API Key

Awesome Sunset Wallpapers uses the [Pexels API](https://www.pexels.com/api/) for constructing RESTful API. 
Pexels provides a RESTful API interface of images and videos.

Once you have the key, add this line to the `local.properties` file, in the project's root folder:

```
pexels.auth.key="your Pexels api key"
```

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
