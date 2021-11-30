# Birthdays application
An application that allows you to keep track of birthdays

## Instalation
Clone this repository and import into Android Studio
```bash
git clone https://github.com/franjojosip/BirthdaysApp
```

## Project setup and technologies
- Arhictecture - MVVM architecture with LiveData and Observable
- Koin DI (reduce boilerplate)
- RxJava (reactive programming)
- NavigationComponent (simple navigation with navController)
- Apollo for GraphQL (Apollo + ApolloRxSupport)
- JUnit for basic test

## Build variants
This project contains "debug" and "release" with the same API url

## Additional classes
- SingleLiveData - for Navigation events
- AppVM - handle observable with error handling and handle diposables inside ViewModel

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
- Each data from the obtained list from the server is mapped to PersonUI object used on UI
