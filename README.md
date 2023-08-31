## **1. Architecture & Design:**
- **Architectural Pattern**:
  - The app employs the **MVVM (Model-View-ViewModel)** pattern, promoting a clean separation of concerns and ease of testing.
- **API Handling**:
  - **Retrofit** for API requests.
  - **Moshi** for JSON parsing.
  - **OkHttp** for network request logging.
- **Image Loading**:
  - Images are loaded using **Coil** in conjunction with Compose.

## **2. Data Persistence:**
- **Local Database**:
  - **Room** is used for data persistence, ensuring favorite ads are available offline.

## **3. User Interface:**
- **UI Framework**:
  - Designed using **Jetpack Compose**.
- **Additional UI Components**:
  - Material Design 2 were explicitly incorporated to get pull-to-refresh.
- **Ads Filtering**:
  - The ViewModel uses a sealed class for different ad filters.

## **4. Performance:**
- **Scrolling**:
  - **LazyList** is used for efficient scrolling. CPU usage has been monitored with Android Profiler to ensure for optimal performance.

## **5. Error Handling & Edge Cases:**
- **Network Handling**:
  - Fetches cached data in the absence of a network connection.
  - Undefined ad-types are cast to "unknown" for consistency.

## **6. Code Quality:**
- **Code Standards**:
  - **ktlint** for static code analysis.
- **Testability**:
  - Interfaces for components that require testing or mocking.

## **7. Proud Moments:**
- **UI Implementation**:
  - Proud of the design and responsiveness.
- **Ads Filtering**:
  - Seamless filter functionality.
- **Testing**:
  - Initial foundation for unit testing.
- **Modularization**:
  - Separated network-related components into a separate module, promoting a decoupled and maintainable architecture.

## **8. Areas of Improvement:**
- **Testing**:
  - Extended testing with **Jetpack Compose UI** and Paparazzi.
- **Continuous Integration**:
  - Implementing a CI pipeline to run tests and static code analysis.
- **Product Flavors**:
  - Added for mocking and possible implementation of a test-API.
- **Localization**:
  - Moving hardcoded strings to a resource file.
- **UI**:
  - Replace callbacks in viewmodel and presentation with UI-events (sealed class).
- **Error Handling**:
  - Improved mechanisms for network and API errors.

## **9. What else with More Time:**
- **Testing, CI/CD, Theming, Product Flavors, Localization, Error Handling**:

![Alt Text](https://media.giphy.com/media/eshz2ncxHVp6r1aBW4/giphy.gif)
