plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.70"
    id("org.jetbrains.dokka") version "0.10.1"
    `java-library`
    `maven-publish`
}

group = "com.reddove"
version = "0.1.0"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("junit:junit:4.12")
}

tasks.withType(Jar::class) {
    manifest {
        attributes["Manifest-Version"] = "1.0"
        attributes["Implementation-Title"] = "Red Dove Test library"
        attributes["Implementation-Version"] = "0.1.0"
        attributes["Implementation-Vendor"] = "Red Dove Consultants Limited"
        attributes["Implementation-Vendor-Id"] = "com.reddove"
    }
}

publishing {
    publications {
        create<MavenPublication>("com.reddove.config") {
            from(components["java"])
        }
    }

    repositories {
        maven {
            setUrl("https://bintray.com/vsajip/test")
            metadataSources {
                gradleMetadata()
            }
        }
    }
}

