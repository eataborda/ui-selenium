
plugins {
    java
    eclipse
    idea
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

val allureVersion = "2.29.1"
val aspectJVersion = "1.9.24"

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

defaultTasks ("clean", "test")

tasks.clean {
    delete("target")
}

tasks.test {
    doFirst{
        jvmArgs = listOf("-javaagent:${agent.singleFile}")
    }

    val includedTags = System . getProperty ("includeTags")
    val includedTagsSet = (includedTags!=null)

    val excludedTags = System . getProperty ("excludeTags")
    val excludeTagsSet = (excludedTags!=null)

    if(includedTagsSet && excludeTagsSet){
        useJUnitPlatform{
            includeTags (includedTags)
            excludeTags (excludedTags)
        }
    } else if (includedTagsSet && !excludeTagsSet){
        useJUnitPlatform{
            includeTags (includedTags)
        }
    } else if (!includedTagsSet && excludeTagsSet){
        useJUnitPlatform{
            excludeTags(excludedTags)
        }
    } else{
        useJUnitPlatform()
    }
    testLogging.showStandardStreams = true
    ignoreFailures = true
    dependsOn(tasks.clean)
}

tasks.withType<Test> {
    systemProperty("driver", System.getProperty("driver"))
    systemProperty("includeTags", System.getProperty("includeTags"))
    systemProperty("excludeTags", System.getProperty("excludeTags"))
    systemProperty("driverArguments", System.getProperty("driverArguments"))
    systemProperty("showAllureAttachments", System.getProperty("showAllureAttachments","true"))
    systemProperty("junit.jupiter.extensions.autodetection.enabled", true)
}

dependencies {
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.33.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.13.0")
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-junit5:$allureVersion")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("ch.qos.logback:logback-classic:1.5.16")
    testImplementation("ch.qos.logback:logback-core:1.5.18")
    testImplementation("org.slf4j:slf4j-simple:2.0.17")
    testImplementation("commons-io:commons-io:2.19.0")
}