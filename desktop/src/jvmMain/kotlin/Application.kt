import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
// import dev.bitbakery.boilerplate.app.App
// import dev.bitbakery.boilerplate.inject.JvmApplicationComponent

const val WINDOW_WIDTH = 1366
const val WINDOW_HEIGHT = 768

fun main() =
    application {
        Window(
            title = "Boilerplate",
            state = rememberWindowState(width = WINDOW_WIDTH.dp, height = WINDOW_HEIGHT.dp),
            onCloseRequest = ::exitApplication,
        ) {
//            val appComponent = JvmApplicationComponent::class.create()
//            val imageLoaderFactory = appComponent.imageLoaderFactory
//            val viewModelFactoryOwner = appComponent.vmFactoryOwner
//
//            App(
//                imageLoaderFactory = imageLoaderFactory,
//                viewModelFactoryOwner = viewModelFactoryOwner,
//            )
        }
    }
