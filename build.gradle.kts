plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("net.serenity-bdd:serenity-core:3.2.5")
    testImplementation("net.serenity-bdd:serenity-junit:3.2.5")
    testImplementation("net.serenity-bdd:serenity-rest-assured:3.2.5")
    testImplementation("junit", "junit", "4.13.2")
    testImplementation("org.assertj:assertj-core:3.22.0")
    testImplementation("org.slf4j:slf4j-simple:1.7.36")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.2.0")
}
