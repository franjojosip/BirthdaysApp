# Birthdays application
An application that allows you to keep track of birthdays

## Instalation
Clone this repository and import it into Android Studio
```bash
git clone https://github.com/franjojosip/BirthdaysApp
```

## Project setup and technologies
- Architecture - MVVM architecture with LiveData and Observable
- Koin DI (reduce boilerplate)
- RxJava (reactive programming)
- NavigationComponent (simple navigation with navController)
- Apollo for GraphQL (Apollo + ApolloRxSupport)
- JUnit for basic test

## Build variants
This project contains "debug" and "release" with the same API URL

## Additional classes
- SingleLiveData - for Navigation events (handle events only once, without triggering again after rotation, etc.)
- AppVM - handle observable with error handling and handle disposables inside ViewModel

## Utils
- DateConverter Util required for converting server date and handling convert age to string used on UI

## API
- GraphQL querry used for this application is shown below:
```bash
query {
  person {
    id
    name
    date_of_birth
  }
}
```
- Each data from the obtained list from the server will be mapped to the PersonUI object used on UI

## Documentation
- Application contains comments which help to understand features and their implementation inside the application
