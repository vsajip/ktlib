import com.jfrog.bintray.gradle.BintrayExtension

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.70"
    id("org.jetbrains.dokka") version "0.10.1"
    `java-library`
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.4"
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

sourceSets {
    main {
        java {
            srcDir("src/main")
        }
    }
    test {
        java {
            srcDir("src/test")
        }
    }
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

fun prop(s: String) = project.findProperty(s) as String?

publishing {
    publications.register("publication", MavenPublication::class) {
        from(components["java"])
    }
}

val publication by publishing.publications

bintray {
    user = prop("bintrayUser")
    key = prop("bintrayAPIKey")
    publish = true
    override = true
    setPublications(publication.name)
    pkg(delegateClosureOf<BintrayExtension.PackageConfig> {
        repo = "test"
        name = "com.reddove.ktlib"
        userOrg = "reddove"
        websiteUrl = "https://www.red-dove.com"
        vcsUrl = "https://github.com/vsajip/ktlib"
        setLabels("configuration")
        setLicenses("BSD-3-Clause")
    })
}
