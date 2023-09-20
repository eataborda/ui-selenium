plugins {
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

defaultTasks ("clean", "test")

tasks.clean {
    delete("target")
}

tasks.test {
    useJUnitPlatform()
    testLogging.showStandardStreams = true
    ignoreFailures = true
    dependsOn(tasks.clean)
}

tasks.withType<Test> {
    systemProperty("tags", System.getProperty("tags"))
    systemProperty("screenshots", System.getProperty("screenshots"))
    systemProperty("driver", System.getProperty("driver"))
    systemProperty("chrome.switches", System.getProperty("chrome.switches"))
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.12.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.slf4j:slf4j-simple:2.0.5")
    testImplementation("commons-io:commons-io:2.13.0")
}