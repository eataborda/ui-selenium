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
    systemProperty("chrome.switches", System.getProperty("chrome.switches"))
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.13.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("ch.qos.logback:logback-classic:1.4.11")
    testImplementation("ch.qos.logback:logback-core:1.4.11")
    testImplementation("org.slf4j:slf4j-simple:2.0.9")
    testImplementation("commons-io:commons-io:2.13.0")
}