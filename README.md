[![automation-ui-test-run](https://github.com/eataborda/selenium-serenity-gradle-java-junit/actions/workflows/automation-ui-test-run.yml/badge.svg)](https://github.com/eataborda/selenium-serenity-gradle-java-junit/actions/workflows/automation-ui-test-run.yml)
[![Gradle](https://img.shields.io/badge/Gradle-7.4.2-blue)](https://gradle.org/releases/)
[![Gradle Plugin Portal](https://img.shields.io/badge/serenity.gradle.plugin-3.4.2-blue)](https://plugins.gradle.org/plugin/net.serenity-bdd.serenity-gradle-plugin/3.4.2)

# Basic UI automation 
A basic UI automation using Java + Junit + Selenium + Serenity + Gradle. Automation
created to test the [Sauce Demo](https://www.saucedemo.com/) page

Contains:
- Basic build.gradle.kts config to execute Junit tests with Serenity
- UI automation using the POM pattern
- Config to generate a Serenity test report with screenshots

## Use sample project locally
- Verify that you have `Git`
- Verify that you have `Java` installed, also that you already setup the following environment variables: `$PATH` and `$JAVA_HOME`
- Clone the repository and move inside that path:
```shellscript
$ git clone git@github.com:eataborda/selenium-serenity-gradle-java-junit.git
$ cd ./selenium-serenity-gradle-java-junit
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