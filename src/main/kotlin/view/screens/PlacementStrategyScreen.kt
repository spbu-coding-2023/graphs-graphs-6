package view.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import viewModel.screensViewModels.PlacementStrategyViewModel

@Composable
fun placementStrategyScreen(viewModel: PlacementStrategyViewModel, onDismiss: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Button(
            onClick = {
                viewModel.selectYifanHu()
                onDismiss()
            },
            enabled = true
        ) {
            Text(
                text = "YifanHu"
            )
        }

        Button(
            onClick = {
                viewModel.selectCircular()
                onDismiss()
            },
            enabled = true
        ) {
            Text(
                text = "Circular"
            )
        }
    }
}