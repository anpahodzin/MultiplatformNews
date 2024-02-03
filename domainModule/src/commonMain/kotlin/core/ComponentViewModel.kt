package core

import co.touchlab.kermit.Logger
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.instancekeeper.InstanceKeeper
import com.arkivanov.essenty.instancekeeper.getOrCreate
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.koin.core.Koin
import org.koin.core.parameter.ParametersHolder
import org.koin.mp.KoinPlatformTools

abstract class ComponentViewModel : InstanceKeeper.Instance, CoroutineScope {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Logger.e("ComponentViewModelError", throwable)
    }

    override val coroutineContext =
        SupervisorJob() + Dispatchers.Main.immediateOrFallback + coroutineExceptionHandler

    override fun onDestroy() {
        coroutineContext.cancel()
    }
}

inline fun <reified T : ComponentViewModel> ComponentContext.getOrCreateViewModel(
    noinline parameters: (() -> ParametersHolder)? = null
): T {
    val koin: Koin = KoinPlatformTools.defaultContext().get()

    return instanceKeeper.getOrCreate(key = T::class, factory = {
        koin.get(parameters = parameters)
    })
}