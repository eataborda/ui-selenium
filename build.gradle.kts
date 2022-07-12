plugins {
    id("net.serenity-bdd.serenity-gradle-plugin") version "3.2.5"
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
    systemProperty("browser", System.getProperty("browser"))
    systemProperty("takeScreenshots", System.getProperty("takeScreenshots"))
}



dependencies {
    testImplementation("net.serenity-bdd:serenity-core:3.2.5")
    testImplementation("net.serenity-bdd:serenity-junit:3.2.5")
    testImplementation("net.serenity-bdd:serenity-rest-assured:3.2.5")
    testImplementation("junit", "junit", "4.13.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("org.slf4j:slf4j-simple:1.7.7")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.2.0")
}
