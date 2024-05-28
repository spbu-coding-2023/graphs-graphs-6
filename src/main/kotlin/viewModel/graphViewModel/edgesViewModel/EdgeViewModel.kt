package viewModel.graphViewModel.edgesViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import viewModel.graphViewModel.VertexViewModel

open class EdgeViewModel<V>(
    val u: VertexViewModel<V>,
    val v: VertexViewModel<V>,
    color: Color
) {
    open val label: String = ""
    open val labelVisible: Boolean = false
    private var _color = mutableStateOf(color)
    var color: Color
        get() = _color.value
        set(value) {
            _color.value = value
        }
}