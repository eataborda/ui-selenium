plugins {
    id("net.serenity-bdd.serenity-gradle-plugin") version "4.0.12"
    java
    eclipse
    idea
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

defaultTasks ("clean", "test", "aggregate")

tasks.clean {
    delete("target")
}

tasks.test {
    useJUnit()
    testLogging.showStandardStreams = true
    ignoreFailures = true
    dependsOn(tasks.clean)
    finalizedBy(tasks.aggregate)
}

tasks.withType<Test> {
    systemProperty("tags", System.getProperty("tags"))
    systemProperty("screenshots", System.getProperty("screenshots"))
    systemProperty("webdriver.driver", System.getProperty("webdriver.driver"))
    systemProperty("chrome.switches", System.getProperty("chrome.switches"))
}

dependencies {
    testImplementation("net.serenity-bdd:serenity-core:4.0.12")
    testImplementation("net.serenity-bdd:serenity-junit:4.0.12")
    testImplementation("junit", "junit", "4.13.2")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.5")
}