[![automation-ui-test-run](https://github.com/eataborda/ui-selenium/actions/workflows/automation-ui-test-run.yml/badge.svg)](https://github.com/eataborda/ui-selenium/actions/workflows/automation-ui-test-run.yml)
[![Gradle](https://img.shields.io/badge/Gradle-8.3-blue)](https://gradle.org/releases/)
# Basic UI automation 
A basic UI automation using Java + Junit + Selenium + Gradle. Automation
created to test the [Sauce Demo](https://www.saucedemo.com/) page

Contains:
- Basic build.gradle.kts config to execute Junit tests with Selenium
- UI automation using the POM pattern

## Use sample project locally
- Verify that you have `Git`
- Verify that you have `Java` installed, also that you already setup the following environment variables: `$PATH` and `$JAVA_HOME`
- Clone the repository and move inside that path:
```shellscript
$ git clone git@github.com:eataborda/ui-selenium.git
$ cd ./ui-selenium
```

### Run tests
- Run all tests on the src (Can select: chrome, firefox, edge):
```
$ ./gradlew -Dwebdriver.driver="chrome"
```
- Run all tests inside class using junit tags (@WithTagValuesOf):
```
$ ./gradlew -Dtags="regression" -Dwebdriver.driver="firefox"
```
- Run tests with screenshots options (Can select: no, selenium, serenity):
```
$ ./gradlew -Dtags="regression" -Dscreenshots="serenity" -Dwebdriver.driver="edge"
```