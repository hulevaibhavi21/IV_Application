package com.example.iv1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.iv1.data.Drug

@Composable
fun ShowSelectedList(
    drugs: ArrayList<Drug>,
    onCheckBtnClicked: (ArrayList<Drug>) -> Unit,
    onCancelBtnClicked: () -> Unit = {},
    viewModel: DrugViewModel
) {
    if(drugs.isEmpty() || drugs.size == 1) {
        Text(text = "Not enough drugs to perform compatibility check.", textAlign = TextAlign.Center)
    } else {
        LazyColumn {
            items(drugs) {drug ->
                SelectedListItem(drug, viewModel)
            }
        }
        Row{
            OutlinedButton(
                onClick = { onCheckBtnClicked(drugs) },
            ) {
                Text(text = "Check Incompatibility")
            }

            OutlinedButton(
                onClick = { onCancelBtnClicked() },
            ) {
                Text(text = "Cancel")
            }
        }
    }
}


@Composable
fun SelectedListItem(
    drug: Drug,
    viewModel: DrugViewModel
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h5.fontSize)
            OutlinedButton(onClick = { viewModel.removeDrug(drug) }) {
                Text(text = "Remove")
            }
        }

    }
}


