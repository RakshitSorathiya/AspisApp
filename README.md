# Malware URL Fetcher

## Project Overview
This project is an Android application that retrieves recent malware URLs from the [URLHaus API](https://urlhaus.abuse.ch/) and displays them in a structured list format. 


<p align="center">
  <img src="https://lh3.googleusercontent.com/d/1xqPut4tpE6P4J82Xp7uD5FdvNTru8tVM" alt="alternatetext">
</p>


## Assignment Tasks Completion
### 1. Backend API Call
- Implemented a network request to retrieve recent malware URLs from:
  ```
  GET https://urlhausapi.abuse.ch/v1/urls/recent/limit/10/
  ```
- Properly handled loading, success, and error states.
- Used Retrofit for API calls and Kotlin coroutines for asynchronous operations.

### 2. UI Implementation
- Designed a UI layout closely matching the provided reference image.
- Displayed retrieved URLs in a list format.
- Implemented smooth scrolling and efficient rendering for large datasets.

### 3. Code Quality
- Applied Dependency Injection using Dagger for better modularity and testability.
- Followed MVVM architecture for a clean separation of concerns.
- Wrote reusable and maintainable code following best practices.
- Ensured scalability by modularizing components for future extensions.

## Tech Stack
- **Kotlin** – Primary programming language
- **Retrofit** – Networking library for API calls
- **Dagger** – Dependency injection
- **MVVM Architecture** – For better separation of concerns

## Setup Instructions
1. Clone the repository:
   ```sh
   git clone <repository_url>
   ```
2. Open the project in Android Studio.
3. Sync dependencies using Gradle.
4. Run the application on an emulator or physical device.

---

### Author
Rakshit Sorathiya
