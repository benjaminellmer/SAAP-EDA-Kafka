plugins {
    application
    id("java")
}

application {
    mainClass.set("at.sail.saap.ellmer.Main")
}

group = "at.sail.saap.ellmer"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven")
    }
}

dependencies {
    implementation("org.apache.kafka:kafka-clients:2.7.2")
    implementation("io.confluent:kafka-avro-serializer:6.0.1")

    implementation("com.google.code.gson:gson:2.8.6")

    implementation("org.slf4j:slf4j-api:1.7.30")
    runtimeOnly("org.slf4j:slf4j-simple:1.7.30")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}
