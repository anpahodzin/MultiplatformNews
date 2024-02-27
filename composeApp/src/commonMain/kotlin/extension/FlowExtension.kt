package extension

import LocalLifecycleOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.arkivanov.essenty.lifecycle.Lifecycle
import com.arkivanov.essenty.lifecycle.LifecycleOwner
import core.immediateOrFallback
import extension.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

fun <T> Flow<T>.flowWithLifecycle(
    lifecycle: Lifecycle,
    minActiveState: Lifecycle.State = Lifecycle.State.STARTED
): Flow<T> = callbackFlow {
    lifecycle.repeatOnLifecycle(minActiveState) {
        this@flowWithLifecycle.collect {
            send(it)
        }
    }
    close()
}

@Composable
internal fun <T> Flow<T>.collectSideEffect(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onEvent: suspend (T) -> Unit
) {
    LaunchedEffect(this, lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediateOrFallback) {
                this@collectSideEffect.collect(onEvent)
            }
        }
    }
}