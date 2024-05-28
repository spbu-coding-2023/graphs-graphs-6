package view.screens.mainScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import view.graphView.graphsView.directedGraphView
import view.graphView.graphsView.graphView
import viewModel.screensViewModels.mainScreensViewModels.MainScreenViewModel

@Composable
fun <V> mainScreen(viewModel: MainScreenViewModel<V>, isDirected: Boolean) {
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
            Button(
                onClick = viewModel::resetGraphView,
                enabled = true
            ) {
                Text(
                    text = "Reset"
                )
            }
            var expanded by remember { mutableStateOf(false) }
            val items =
                mutableListOf("Select key vertices", "Select communities", "Find cycles for a vertex")
            if (isDirected) items.add("Select strongly connected components")
            else items.add("Find bridges")
            fun onItemSelected(item: String) {
                when (item) {
                    "Select key vertices" -> viewModel.selectKeyVertices()
                    "Select communities" -> {}
                    "Select strongly connected components" -> viewModel.selectStronglyConnectedComponents()
                    "Find bridges" -> viewModel.findBridges()
                    "Find cycles for a vertex" -> {}
                }
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                items.forEach { item ->
                    DropdownMenuItem(onClick = {
                        onItemSelected(item)
                        expanded = false
                    }) {
                        Text(item)
                    }
                }
            }
            Button(
                onClick = { expanded = true },
                enabled = true
            ) {
                Text(
                    text = "Algorithms"
                )
            }
        }

        Surface(
            modifier = Modifier.weight(1f)
        ) {
            if (isDirected) directedGraphView(viewModel.graphViewModel)
            else graphView(viewModel.graphViewModel)
        }
    }
}