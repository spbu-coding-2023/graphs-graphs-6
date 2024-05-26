package view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import viewModel.GraphChooseViewModel

@Composable
fun <V> graphChooseScreen(viewModel: GraphChooseViewModel<V>, onDismiss: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Button(
            onClick = {
                viewModel.selectGraph()
                onDismiss()
            },
            enabled = true
        ) {
            Text(
                text = "Graph"
            )
        }

        Button(
            onClick = {
                viewModel.selectDirectedGraph()
                onDismiss()
            },
            enabled = true
        ) {
            Text(
                text = "Directed graph"
            )
        }

        Button(
            onClick = {
                viewModel.selectWeightedGraph()
                onDismiss()
            },
            enabled = true
        ) {
            Text(
                text = "Weighted graph"
            )
        }

        Button(
            onClick = {
                viewModel.selectWeightedDirectedGraph()
                onDismiss()
            },
            enabled = true
        ) {
            Text(
                text = "Weighted directed graph"
            )
        }
    }
}