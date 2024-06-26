package view.screens.mainScreens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.OutlinedTextField
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
            var dialogOpen by remember { mutableStateOf(false) }
            var cycleVertex by remember { mutableStateOf(0) }
            val items =
                mutableListOf("Find cycles for a vertex", "Select key vertices")
            if (isDirected) items.add("Select strongly connected components")
            else items.add(0, "Find bridges")
            fun onItemSelected(item: String) {
                when (item) {
                    "Select key vertices" -> viewModel.selectKeyVertices()
                    "Select strongly connected components" -> viewModel.selectStronglyConnectedComponents()
                    "Find bridges" -> viewModel.findBridges()
                    "Find cycles for a vertex" -> {
                        dialogOpen = true
                    }
                }
            }
            if (dialogOpen) {
                AlertDialog(
                    onDismissRequest = {
                        dialogOpen = false
                    },
                    title = { Text("Enter the vertex, please") },
                    text = {
                        Column {
                            OutlinedTextField(
                                value = cycleVertex.toString(),
                                onValueChange = { cycleVertex = it.toIntOrNull() ?: 0 },
                                label = { Text("Vertex") }
                            )
                        }
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                dialogOpen = false
                                viewModel.findCycle(cycleVertex)
                            }
                        ) {
                            Text("OK")
                        }
                    },
                    dismissButton = {
                        Button(
                            onClick = { dialogOpen = false }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
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
            graphView(viewModel.graphViewModel)
        }
    }
}