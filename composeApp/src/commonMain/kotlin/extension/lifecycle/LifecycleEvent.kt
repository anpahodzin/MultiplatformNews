package extension.lifecycle

import com.arkivanov.essenty.lifecycle.Lifecycle
import kotlin.jvm.JvmStatic

enum class LifecycleEvent {
    ON_CREATE,
    ON_START,
    ON_RESUME,
    ON_PAUSE,
    ON_STOP,
    ON_DESTROY,
    ON_ANY;


    companion object {

        @JvmStatic
        public fun downFrom(state: Lifecycle.State): LifecycleEvent? {
            return when (state) {
                Lifecycle.State.CREATED -> ON_DESTROY
                Lifecycle.State.STARTED -> ON_STOP
                Lifecycle.State.RESUMED -> ON_PAUSE
                else -> null
            }
        }

        @JvmStatic
        public fun downTo(state: Lifecycle.State): LifecycleEvent? {
            return when (state) {
                Lifecycle.State.DESTROYED -> ON_DESTROY
                Lifecycle.State.CREATED -> ON_STOP
                Lifecycle.State.STARTED -> ON_PAUSE
                else -> null
            }
        }

        @JvmStatic
        public fun upFrom(state: Lifecycle.State): LifecycleEvent? {
            return when (state) {
                Lifecycle.State.INITIALIZED -> ON_CREATE
                Lifecycle.State.CREATED -> ON_START
                Lifecycle.State.STARTED -> ON_RESUME
                else -> null
            }
        }

        @JvmStatic
        fun upTo(state: Lifecycle.State): LifecycleEvent? {
            return when (state) {
                Lifecycle.State.CREATED -> ON_CREATE
                Lifecycle.State.STARTED -> ON_START
                Lifecycle.State.RESUMED -> ON_RESUME
                else -> null
            }
        }
    }
}