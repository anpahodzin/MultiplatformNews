package core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlin.concurrent.Volatile

@Volatile
private var isImmediateSupported: Boolean = true

@Suppress("UnusedReceiverParameter")
val MainCoroutineDispatcher.immediateOrFallback: MainCoroutineDispatcher
    get() {
        if (isImmediateSupported) {
            try {
                return Dispatchers.Main.immediate
            } catch (ignored: UnsupportedOperationException) {
            } catch (ignored: NotImplementedError) {
            }

            isImmediateSupported = false
        }

        return Dispatchers.Main
    }