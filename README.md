# Personal Expense Tracker

Android application to track your daily expenses, manage your budget, and visualize your spending habits.

## Features

### 📊 Overview Dashboard
- **Total Balance Spent**: Get a quick glance at your overall expenditure.
- **Monthly Summary**: See how much you spent last month compared to now.
- **Category Breakdown**: Visual summary of spending distributed across different categories.

### 📜 Transaction History
- **Full History**: A complete list of all your recorded expenses.
- **Category Filtering**: Quickly filter your transactions by category (Food, Transport, Shopping, Bills, etc.) to analyze specific spending areas.

### ✏️ Expense Management
- **Add Expense**: Easily record new expenses with amount, date, category, and notes.
- **Edit Expense**: Modify existing records by clicking on them in the history list.
- **Date Picker**: Intuitive Material 3 date selection for accurate record-keeping.

### 🛠 Tech Stack
- **UI**: Jetpack Compose (100% declarative UI)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Database**: Room Persistence Library
- **Dependency Injection**: Koin
- **Navigation**: Jetpack Navigation Component (Type-safe routes)
- **Design**: Material Design 3 (M3)
- **Localization**: Fully localized via `strings.xml`

## Project Structure
The project follows a clean architecture approach, organized by feature and layer:
- `data/`: Room entities, DAOs, and Repository implementations.
- `ui/`:
    - `screens/`: Feature-specific screens (Home, Manage).
    - `base/`: Shared base classes for ViewModels and UI.
    - `composables/`: Reusable UI components.
    - `theme/`: Material 3 theme configuration (Color, Type, Shape).
    - `nav/`: Navigation graphs and screen definitions.
- `di/`: Koin modules for dependency injection.

## Getting Started
1. Clone the repository.
2. Open the project in Android Studio (Ladybug or newer).
3. Sync the project with Gradle files.
4. Run the app on an emulator or physical device.
