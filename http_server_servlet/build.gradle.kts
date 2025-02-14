import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply(from = "../gradle/kotlin.gradle")
apply(from = "../gradle/bintray.gradle")
apply(from = "../gradle/dokka.gradle")
apply(from = "../gradle/testng.gradle")

// IMPORTANT: Required for compiling classes in test dependencies. It *MUST* be before dependencies

plugins{
    java
}

val compileTestKotlin: KotlinCompile by tasks

val entityTests: SourceSetOutput = project(":port_http_server").sourceSets["test"].output

compileTestKotlin.dependsOn(tasks.getByPath(":port_http_server:compileTestKotlin"))

dependencies {
    "api"(project(":port_http_server"))
    "compileOnly"("javax.servlet:javax.servlet-api:${properties.get("servletVersion")}")

    "testImplementation"(project(":port_http_client"))
    "testImplementation"(entityTests)
    "testImplementation"("org.eclipse.jetty:jetty-webapp:${properties.get("jettyVersion")}") {
        exclude(module = "slf4j-api")
    }
}
