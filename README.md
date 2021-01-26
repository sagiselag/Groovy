# Groovy
Android music player Kotlin application built under purely Outside-In TDD  with efficient &amp; effective Espresso UI tests using MockWebServer ("Mockoon") with MVVMi architectural pattern using Coroutines, Live Data, Kotlin Flow, Retrofit, Hilt DI, and Jetpack Navigation.

Please follow the instractions to mock the web server

1. download and install Mockoon from https://mockoon.com/
2. add route is for the playlists with the path "playlists"
3. copy the contents of the JSON "4.4_playlists" file 
4. add a Route specific latency - 400ms for example 
5. add route is for the first playlist details with the path "playlist-details/1"
6. copy the contents of the JSON "playlist-details" file 
7. add a Route specific latency - 500ms for example 
8. run Mockoon
