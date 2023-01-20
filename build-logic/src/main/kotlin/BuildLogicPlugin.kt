import com.thoughtworks.ark.loadProperties
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create

class BuildLogicPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.subprojects {
            configMaven()
        }
    }

    private fun Project.readConfig(name: String): String {
        return project.properties[name] as String? ?: System.getenv(name) ?: ""
    }

    private fun Project.configMaven() {
        val mavenProperties = project.loadProperties("buildconfig.properties")
        val publishGroupId = mavenProperties["PUBLISH_GROUP_ID"]?.toString() ?: ""
        val publishVersion = mavenProperties["PUBLISH_VERSION"]?.toString() ?: ""

        afterEvaluate {
            plugins.withId("com.android.library") {
                apply(plugin = "maven-publish")
                configure<PublishingExtension> {
                    repositories {
                        maven {
                            url = uri(readConfig("MAVEN_REPO"))
                            isAllowInsecureProtocol = true
                            credentials {
                                username = readConfig("MAVEN_USER")
                                password = readConfig(("MAVEN_PWD"))
                            }
                        }
                    }
                    publications {
                        create<MavenPublication>("snapshot") {
                            afterEvaluate {
                                from(components.getByName("prodRelease"))
                                groupId = publishGroupId
                                version = "$publishVersion-SNAPSHOT"
                            }
                        }

                        create<MavenPublication>("release") {
                            afterEvaluate {
                                from(components.getByName("prodRelease"))
                                groupId = publishGroupId
                                version = publishVersion
                            }
                        }
                    }
                }
            }
        }
    }
}
