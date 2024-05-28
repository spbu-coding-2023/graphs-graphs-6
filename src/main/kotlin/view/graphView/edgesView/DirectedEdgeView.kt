package view.graphView.edgesView

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import viewModel.graphViewModel.edgesViewModel.EdgeViewModel
import kotlin.math.PI
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun <V> directedEdgeView(
    viewModel: EdgeViewModel<V>,
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        val source = Offset(
            viewModel.u.x.toPx() + viewModel.u.radius.toPx(),
            viewModel.u.y.toPx() + viewModel.u.radius.toPx()
        )
        val destination = Offset(
            viewModel.v.x.toPx() + viewModel.v.radius.toPx(),
            viewModel.v.y.toPx() + viewModel.v.radius.toPx()
        )
        val arrowSize = 35f
        val angle = atan2(destination.y - source.y, destination.x - source.x)
        val rotationAngle = PI / 12
        val arrowSourceX = destination.x - arrowSize * cos(angle + rotationAngle)
        val arrowSourceY = destination.y - arrowSize * sin(angle + rotationAngle)
        val arrowDestinationX = destination.x - arrowSize * cos(angle - rotationAngle)
        val arrowDestinationY = destination.y - arrowSize * sin(angle - rotationAngle)
        val path = Path().apply {
            moveTo(source.x, source.y)
            lineTo(destination.x, destination.y)
            moveTo(destination.x, destination.y)
            lineTo(arrowSourceX.toFloat(), arrowSourceY.toFloat())
            moveTo(destination.x, destination.y)
            lineTo(arrowDestinationX.toFloat(), arrowDestinationY.toFloat())
        }
        drawPath(
            path = path,
            color = Color.Black,
            style = Stroke(width = 1.3.dp.toPx())
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
