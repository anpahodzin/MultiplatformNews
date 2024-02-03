package main.component

import com.arkivanov.decompose.ComponentContext

class MainDefaultComponent(
    componentContext: ComponentContext
) : MainComponent, ComponentContext by componentContext {


}