<p align="center"><img src="banner.svg" alt="Pokedex — Native Android app"/></p>

<p align="center">
  <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white" alt="Kotlin"/>
  <img src="https://img.shields.io/badge/Jetpack_Compose-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white" alt="Compose"/>
  <img src="https://img.shields.io/badge/Material_3-757575?style=flat-square&logo=materialdesign&logoColor=white" alt="Material 3"/>
  <img src="https://img.shields.io/badge/Hilt-00A9E0?style=flat-square&logo=dagger&logoColor=white" alt="Hilt"/>
  <img src="https://img.shields.io/badge/Room-6DB33F?style=flat-square&logo=sqlite&logoColor=white" alt="Room"/>
  <img src="https://img.shields.io/badge/Retrofit-48B983?style=flat-square&logo=squareup&logoColor=white" alt="Retrofit"/>
  <img src="https://img.shields.io/badge/API_35-3DDC84?style=flat-square&logo=android&logoColor=black" alt="API 35"/>
  <img src="https://img.shields.io/badge/License-MIT-e94f5f?style=flat-square" alt="MIT"/>
</p>
<p align="center">
  <a href="https://github.com/Pelosin/Pokedex/actions/workflows/ci.yml"><img src="https://github.com/Pelosin/Pokedex/actions/workflows/ci.yml/badge.svg" alt="CI"/></a>
</p>

A native Android Pokédex built with **Kotlin and Jetpack Compose**, structured with **Clean Architecture**: browse every Pokémon in a card grid, open rich detail pages, favourite the ones you catch, and keep browsing even offline.

## ✨ Features

- 📱 **Pokémon grid** — scrollable card list, 2 columns, type-coloured cards
- 🔎 **Search & filters** — live search by name and favourites filter
- 📊 **Detail pages** — stats, attributes, type and evolution chain per Pokémon
- 🔔 **Recommendation notifications** — periodic Pokémon recommendations, straight from the app
- 💾 **Offline cache** — Pokémon data stored with Room, available without connection
- 🎬 **Splash screen & first-run welcome** — splash screen API + first-time-only welcome
- 🌗 **Material 3 theming** — dark/light, type-based card colours, GIF support (Coil)

## 📸 Screenshots

| Welcome | Pokédex list |
|---|---|
| ![](screenshots/welcome.png) | ![](screenshots/pokedex_list.png) |
| **Pokémon details** | **Evolutions** |
| ![](screenshots/pokemon_details.png) | ![](screenshots/pokemon_evolutions.png) |

## 🏛 Architecture

```
┌──────────────────────────────┐
│  UI (Jetpack Compose, M3)     │  Screens, composables, ViewModels + StateFlow
├──────────────────────────────┤
│  Domain                       │  Models (Pokemon, Stats, Type, Evolution)
│                               │  Repository interfaces
├──────────────────────────────┤
│  Data                         │  PokemonRepository (impl)
│   ├─ Remote: Retrofit/PokeAPI │
│   └─ Local: Room (offline)    │
└──────────────────────────────┘
        DI: Hilt (8 modules) · MVVM · UiState
```

Each layer only talks to the one below it — ViewModels depend on domain interfaces, `data` provides the implementations, and Hilt wires it all together.

## 🛠 Tech stack

| Area | Choice |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose, Material 3, Navigation Compose, Splash Screen API |
| Architecture | Clean Architecture (data/domain/ui), MVVM, StateFlow |
| DI | Hilt (kapt) |
| Networking | Retrofit + Gson (PokeAPI) |
| Persistence | Room (offline cache) |
| Images | Coil (incl. GIF support), Accompanist |
| Testing | JUnit (data source & repository tests) |

## 📂 Project structure

```
app/src/main/java/com/example/mypokedex/
├── core/           # Application class
├── data/
│   ├── local/      # Room: entities, DAOs, database source
│   ├── remote/     # Retrofit: PokeApi, DTOs, remote source
│   ├── repository/ # PokemonRepository implementation
│   └── utils/      # Constants, FunctionResponse, prefs helper
├── di/             # 8 Hilt modules
├── domain/         # Models + repository interfaces
└── ui/
    ├── composables/  # Reusable composables (list, details, topbar…)
    ├── screens/      # FirstTime, Pokedex, Details, Error
    ├── viewmodels/   # MVVM ViewModels
    └── notifications/ # Recommendation notifications
```

## 🚀 Roadmap

- [ ] Multi-language support
- [ ] Data sync when back online
- [ ] About & Settings screens
- [ ] Sorting by type / evolution stage

## ▶️ Build & run

```bash
git clone https://github.com/Pelosin/Pokedex.git
cd Pokedex
./gradlew assembleDebug
# Install on device/emulator (API 35+)
adb install app/build/outputs/apk/debug/app-debug.apk
```

Requires JDK 17+ and Android SDK with platform 35.

## 📄 License

[MIT](LICENSE) © João Pelosi
