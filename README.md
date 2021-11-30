# Birthdays application
An application that allows you to keep track of birthdays

## Instalation
Clone this repository and import into Android Studio
```bash
git clone https://github.com/franjojosip/BirthdaysApp
```

## Project setup and technologies
- Arhictecture - MVVM architecture with LiveData and Observable
- Koin DI
- RxJava
- NavigationComponent
- Apollo for graphQL (Apollo + ApolloRxSupport)
- JUnit for basic test
- DataBinding (Bind data to view, memory leaks are handled in Fragment OnDestroyView lifecycle method)

## Build variants
This project contains "debug" and "release" with the same API url

## Additional classes
- SingleLiveData - for Navigation events
- AppVM - handle observable with error handling and handle diposables inside ViewModel

## Resources
- Project uses Roboto fonts


## Features
- Birthday List screen which contains list of people and their birthdays
- Click on person opens SingleBirthday screen which shows person data

## Testing
- This project contain PersonRepositoryTest which is not fully implemented (test with Koin DI should be used)


