import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

apply(from = "../gradle/kotlin.gradle")
apply(from = "../gradle/bintray.gradle")
apply(from = "../gradle/dokka.gradle")
apply(from = "../gradle/testng.gradle")

val compileTestKotlin: KotlinCompile by tasks

plugins{
    java
}

// IMPORTANT: Required for compiling classes in test dependencies. It *MUST* be before dependencies
compileTestKotlin.dependsOn(tasks.getByPath(":port_http_server:compileTestKotlin"))

val entityTests: SourceSetOutput = project(":port_http_server").sourceSets["test"].output

val entityTestsHexangoWeb: SourceSetOutput = project(":hexagon_web").sourceSets["test"].output

dependencies {
    "api"(project(":http_server_servlet"))
    "api"("org.eclipse.jetty:jetty-webapp:${properties.get("jettyVersion")}") { exclude(module = "slf4j-api") }

    "testImplementation"(project(":port_http_client"))
    "testImplementation"(entityTests)
    "testImplementation"(project(":hexagon_web"))
    "testImplementation"(entityTestsHexangoWeb)
}
