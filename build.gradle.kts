plugins {
    java
}

group = "ru.zankov"
version = "1.0.0"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.seleniumhq.selenium:selenium-java:4.23.0")
    testImplementation("io.appium:java-client:9.3.0")
    testImplementation("org.testng:testng:7.8.0")
    testImplementation("io.github.bonigarcia:webdrivermanager:5.6.3")
    testImplementation("org.slf4j:slf4j-api:2.0.9")
    testImplementation("org.slf4j:slf4j-simple:2.0.9")
}

tasks.withType<Test> {
    useTestNG {
        suites("src/test/resources/web-tests.xml", "src/test/resources/mobile-tests.xml")
    }
}
