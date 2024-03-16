package core.flow

import core.immediateOrFallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

interface Cancellable {
    fun cancel()
}

class AnyStateFlow<T : Any>(
    private val source: StateFlow<T>
) : StateFlow<T> by source {
    override val value: T
        get() = source.value

    fun collect(
        onEach: (T) -> Unit
    ): Cancellable {
        val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediateOrFallback)

        this.source.onEach {
            onEach(it)
        }.launchIn(scope)

        return object : Cancellable {
            override fun cancel() {
                scope.cancel()
            }
        }
    }
}

fun <T : Any> StateFlow<T>.wrapToAny(): AnyStateFlow<T> = AnyStateFlow(this)