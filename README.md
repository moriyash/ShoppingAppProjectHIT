# Shopping Manager App

An Android application to manage your shopping list. This app allows users to authenticate using Firebase, view a predefined shopping list, and adjust quantities for each product. The app saves the shopping list and user data to Firebase Realtime Database.

## Features

- **Login Screen**: 
  - Users can log in with their email and password. 
  - Option to navigate to the registration screen if the user doesn't have an account.

- **Registration Screen**: 
  - Users can register with their email, password, and phone number.
  - User data is stored in Firebase Realtime Database.

- **Shopping List Screen**: 
  - Displays a predefined list of 8 products.
  - Users can adjust the quantity of each product.
  - The shopping list and quantities are saved to Firebase Realtime Database under the user's profile.

## Technologies Used

- **Android Studio**: Development environment.
- **Firebase Authentication**: User authentication.
- **Firebase Realtime Database**: Stores user data and shopping list.
- **RecyclerView**: For displaying the shopping list items.
- **Java**: Programming language for the app's functionality.
- **Firebase SDK**: Used for Firebase integration.

## Usage

1. **Registration**: Users need to fill in the email, password, and phone number to register.
2. **Login**: After registration, users can log in using their credentials.
3. **Shopping List**: After logging in, a predefined shopping list is displayed on the shopping list screen.
4. **Quantity**: Users can adjust the quantity of each product in the list.
5. **Firebase**: The shopping list and quantities are saved to Firebase Realtime Database under the user's profile.

## Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/DvirUliel/ShoppingManagerApp.git
2. Open the project in Android Studio.
3. Add your Firebase configuration to the app by following these steps:
   - Go to Firebase Console.
   - Create a new project and add your Android app to the project.
   - Download the google-services.json file and place it in the app/ folder of your project.
4. Build and run the app on your Android device or emulator.

