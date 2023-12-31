# News API

You can use this project like,  
**REFERENCE** and **INSPIRATION,**  
Please don't **STEAL** my project.

**Use common sense**

Thanks

This an Android Project when you going to see a list of news and if you click in some item, you are going to see a details of this news.

![](gifs/navigation.gif)

If your device there is biometrics activate you need to authenticate first

![](gifs/biometric.gif)

There is error screen

![](gifs/fail_and_retry.gif)

I tried to write a project as clean as possible, using the best practices in 2023 based on SOLID.

- unidirectional flow
- Jetpack Compose
- coroutines flow with StateFlow
- It's prepared for Light/dark mode
- MVVM architecture and I decided to not use the Domain layer in this project.
- Gradle Kotlin DSL
- Gradle plugins
- Gradle Version Catalog (.toml)
- Gradle BuildSrc 
- Flavor in a sub module
- unit tests with MockK
- this project is accessible.
- Karma commit http://karma-runner.github.io/6.4/dev/git-commit-msg.html

I hope you are enjoying the project.


## To run

I used an Open API, to see this project running you need to access https://newsapi.org and register.
In the project you need to change the "local.properties" file to add the API key.

Need to add this line in local.properties:
```
API_KEY=YOUR_NEWS_API_ORG
```


## Authors

Bruno Brustoloni e Oliveira

- email: bbrustol@gmail.com
- LinkedIn: [https://www.linkedin.com/in/bbrustol/](http://www.linkedin.com/in/bbrustol)