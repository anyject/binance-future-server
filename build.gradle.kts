import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("kapt") version "1.5.31"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
}

group = "com.anyject"
version = "0.1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17
val springCloudVersion = "2022.0.2"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    implementation("io.github.openfeign:feign-jackson")
    implementation("io.github.openfeign:feign-okhttp")
    implementation("commons-codec:commons-codec")
    implementation("io.netty:netty-resolver-dns-native-macos:4.1.90.Final:osx-aarch_64")
    implementation("com.github.ulisesbocchio:jasypt-spring-boot-starter:3.0.5")
    kapt("org.springframework.boot:spring-boot-configuration-processor:3.0.4")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.awaitility:awaitility")
}


dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.withType<Test> {
    useJUnitPlatform()
    exclude("com/anyject/learning/**")
}

tasks.processResources {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/resources", "src/main/resources/config")
        }
    }
}

kapt {
    arguments {
        arg("spring.profiles.active", "testnet")
    }
}