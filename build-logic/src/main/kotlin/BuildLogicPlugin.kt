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
        val mavenProperties = project.loadProperties("buildconfig.properties")
        val publishGroupId = mavenProperties["PUBLISH_GROUP_ID"]?.toString() ?: ""
        val publishVersion = mavenProperties["PUBLISH_VERSION"]?.toString() ?: ""

        project.subprojects {
            configMaven(publishGroupId, publishVersion)
        }
    }

    private fun Project.configMaven(publishGroupId: String, publishVersion: String) {
        afterEvaluate {
            plugins.withId("com.android.library") {
                apply(plugin = "maven-publish")
                configure<PublishingExtension> {
                    publications {
                        create<MavenPublication>("maven") {
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
