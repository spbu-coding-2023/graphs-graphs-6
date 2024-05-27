package view.screens.mainScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import view.graphView.graphsView.weightedDirectedGraphView
import view.graphView.graphsView.weightedGraphView
import viewModel.screensViewModels.mainScreensViewModels.MainScreenViewModelWeightedGraph

@Composable
fun <V> mainScreenWeightedGraph(viewModel: MainScreenViewModelWeightedGraph<V>, isDirected: Boolean) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Column(modifier = Modifier.width(370.dp)) {
            Row {
                Checkbox(checked = viewModel.showVerticesLabels.value, onCheckedChange = {
                    viewModel.showVerticesLabels.value = it
                })
                Text("Show vertices labels", fontSize = 28.sp, modifier = Modifier.padding(4.dp))
            }
            Row {
                Checkbox(checked = viewModel.showEdgesLabels.value, onCheckedChange = {
                    viewModel.showEdgesLabels.value = it
                })
                Text("Show edges labels", fontSize = 28.sp, modifier = Modifier.padding(4.dp))
            }
            Button(
                onClick = viewModel::resetGraphView,
                enabled = true
            ) {
                Text(
                    text = "Reset default settings"
                )
            }
            Button(
                onClick = viewModel::setVerticesColor,
                enabled = true
            ) {
                Text(
                    text = "Set colors"
                )
            }
        }

        Surface(
            modifier = Modifier.weight(1f)
        ) {
            if (isDirected) weightedDirectedGraphView(viewModel.graphViewModel)
            else weightedGraphView(viewModel.graphViewModel)
        }
    }
}