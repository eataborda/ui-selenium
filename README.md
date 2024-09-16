[![automation-ui-test-run](https://github.com/eataborda/ui-selenium/actions/workflows/automation-ui-test-run.yml/badge.svg)](https://github.com/eataborda/ui-selenium/actions/workflows/automation-ui-test-run.yml)
[![Gradle](https://img.shields.io/badge/Gradle-8.9-blue)](https://gradle.org/releases/)
[![Junit5](https://img.shields.io/badge/Junit5-5.10.3-blue)](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine)
[![Selenium](https://img.shields.io/badge/Selenium-4.24.0-blue)](https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java)

# Selenium UI automation
A basic UI automation using Java + Junit + Selenium + Gradle. Automation
created to test the [Sauce Demo](https://www.saucedemo.com/) page

Contains:
- Basic build.gradle.kts config to execute Selenium tests with Junit5
- UI automation using the POM pattern
- Allure HTML report

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
$ ./gradlew -Ddriver="chrome"
```
- Run all tests inside a class or a specific method using junit tags (@Tag):
```
$ ./gradlew -DincludeTags="regression" -Ddriver="firefox"
```
In this way you can use the following tags depending on the tests you need to run:
- General: regression, smoke
- Class level: standard-user, locked-out-user, problem-user
- Method level (@Test): buy-items, remove-cart-items, cart-items-persistence, order-inventory-items, verify-checkout-data, login-locked-user,
login-wrong-password, inventory-item-src-issues, inventory-filter-issues

### Generate report
Once you have run the tests you can generate the Allure report by running the following command:
```
$ allure generate build/allure-results --clean
```
After running the above command successfully you will be able to find the report in the following path: `your-project-path/allure-report`

### Open the report
Locally you can open the report in two ways:
- Using the command:
```
$ allure open /your-project-path/allure-report
```
The command starts a local web server and show the report directory's contents. Opens the report in your default browser.
- Opening the report file `/your-project-path/allure-report/index.html` in the browser of your choice. This shows the static contents of the report directory without starting any local web server.