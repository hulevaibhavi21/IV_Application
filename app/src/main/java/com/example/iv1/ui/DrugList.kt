package com.example.iv1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.iv1.data.DataState
import com.example.iv1.data.Drug

@Composable
fun SetData(
    viewModel: DrugViewModel,
    onDoneBtnClicked: (ArrayList<Drug>) -> Unit
) {
    when(val result = viewModel.response.value) {
        is DataState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is DataState.Success -> {
            ShowDrugList(result.data, onDoneBtnClicked = onDoneBtnClicked, viewModel)
        }
        is DataState.Failure -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = result.message, fontSize = MaterialTheme.typography.h5.fontSize)
            }
        }
        else -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error fetching data !!", fontSize = MaterialTheme.typography.h5.fontSize)
            }
        }
    }
}


@Composable
fun ShowDrugList(
    drugs: ArrayList<Drug>,
    onDoneBtnClicked: (ArrayList<Drug>) -> Unit,
    viewModel: DrugViewModel
) {
    LazyColumn {
        items(drugs) {drug ->
            ListItem(drug, viewModel)
        }
    }
    OutlinedButton(
        onClick = { onDoneBtnClicked(drugs) },
    ) {
        Text(text = "Next")
    }
}


@Composable
fun ListItem(drug: Drug, viewModel: DrugViewModel) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h5.fontSize)
            if(!viewModel.getSelectedDrugList().contains(drug)) {
                OutlinedButton(onClick = { viewModel.selectDrug(drug) }) {
                    Text(text = "Add")
                }
            }
        }
    }
}
