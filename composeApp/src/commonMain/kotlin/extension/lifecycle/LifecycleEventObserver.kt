package extension.lifecycle

import com.arkivanov.essenty.lifecycle.Lifecycle

class LifecycleEventObserver(private val onEvent: (LifecycleEvent) -> Unit) : Lifecycle.Callbacks {
    override fun onCreate() {
        onEvent(LifecycleEvent.ON_CREATE)
    }

    override fun onDestroy() {
        onEvent(LifecycleEvent.ON_DESTROY)
    }

    override fun onPause() {
        onEvent(LifecycleEvent.ON_PAUSE)
    }

    override fun onResume() {
        onEvent(LifecycleEvent.ON_RESUME)
    }

    override fun onStart() {
        onEvent(LifecycleEvent.ON_START)
    }

    override fun onStop() {
        onEvent(LifecycleEvent.ON_STOP)
    }
}