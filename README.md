<p align="center">
<img src=https://www.svgrepo.com/show/169150/chess.svg alt="Logo_chess" width=70>
<h3 align="center">I LOVE Board Games</h3>
<p align="center">
The app was created as a project for the Ubiquitous Applications course at Poznań University of Technology.
It displays some data about provided user's board games and extensions stored at [the BoardGameGeek Api](https://boardgamegeek.com/wiki/page/BGG_XML_API&redirectedfrom=XML_API#).
</p>

## Table of contents
1. [How to run](#run)
2. [Tech Stack](#stack)
3. [App description](#description)
4. [Development plan](#plan)

## How to run
<a name="run"></a>
(Download)[https://github.com/MichalxPZ/I-Love-Board-Games/releases/tag/V1.0.0] release via github -> see releases page on right side of the screen

## Stack
<a name="stack"></a>
Application is written in native android using MVVM architecture and multi modular structure.  
All gradle files were rewritten into Kotlin DSL.  
Tech stack:  
| Tech stack    |           |
| ------------- |:-------------:|
| Android     | ![android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)|
| Kotlin      | ![kotlin](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)|
| Coroutines   | ![kotlin](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)|
| Hilt      | ! [Dagger2-Hilt - dependency injection library](https://dagger.dev/hilt/)|
| Room - SQLite  | ![Room](https://img.shields.io/badge/SQLite-07405E?style=for-the-badge&logo=sqlite&logoColor=white)|
| Retrofit | OkHttp  |  [Retrofit](https://square.github.io/retrofit/)|
| Material3      | ![Material](https://img.shields.io/badge/material%20design-757575?style=for-the-badge&logo=material%20design&logoColor=white)|
| Coil      |  [Coil - image loading library](https://coil-kt.github.io/coil/compose/)|


I used modularization strategy to isolate every module and also every layer of it -- every feature has it's own module divided into sub-modules for data, domain and presentation layer.  
More details available in the [documentation](https://developer.android.com/topic/architecture)  

<p align="center">
<img src=https://developer.android.com/topic/libraries/architecture/images/mad-arch-overview.png">
<h3 align="center">Layers in Android application architecture</h3>

## Description
<a name="description"></a>
Application has some cool features like  sorting games or filtering them by query.
For two and more recorded positions in the ranking -- application draws a graph with them.  
![App Demo 1](art/ezgif-4-39f58df234.gif)  

## Plan, some thoughts and what would I do if I had unlimited time
<a name="plan"></a>
The biggest struggle while creating this application was an API.  
Parsing response into Kotlin Data Classes was very hard due to XML responses. Definitely this api has a lot of flaws that made it hard to implement while remaining functionality of the App.  

If I had unlimited time I'd definitely write testcases to make sure that at least domain level logic is tested.  Some E2E test cases would do the job too.   
I'd also like to store users data remotely on Firebase to remain information on previous game positions.  
It would be nice to have some crashlitics system implemented - I've never done this yet but is sound like a fun task.  

