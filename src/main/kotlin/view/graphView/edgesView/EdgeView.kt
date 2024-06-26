package view.graphView.edgesView

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel

@Composable
fun <V> edgeView(
    viewModel: EdgeViewModel<V>,
    modifier: Modifier = Modifier
) {
    var edgeColor by remember { mutableStateOf(viewModel.color) }

    Canvas(modifier = modifier.fillMaxSize()) {
        edgeColor = viewModel.color
        drawLine(
            start = Offset(
                viewModel.u.x.toPx() + viewModel.u.radius.toPx(),
                viewModel.u.y.toPx() + viewModel.u.radius.toPx()
            ),
            end = Offset(
                viewModel.v.x.toPx() + viewModel.v.radius.toPx(),
                viewModel.v.y.toPx() + viewModel.v.radius.toPx()
            ),
            color = edgeColor
        )
    }
    if (viewModel.labelVisible) {
        Text(
            modifier = Modifier
                .offset(
                    viewModel.u.x + (viewModel.v.x - viewModel.u.x) / 2,
                    viewModel.u.y + (viewModel.v.y - viewModel.u.y) / 2
                ),
            text = viewModel.label
        )
    }
}