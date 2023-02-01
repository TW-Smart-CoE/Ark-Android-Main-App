import com.thoughtworks.ark.setupMaven
import org.gradle.api.Plugin
import org.gradle.api.Project

class BuildLogicPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.subprojects {
            setupMaven()
        }
    }
}
