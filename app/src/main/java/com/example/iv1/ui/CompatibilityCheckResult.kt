package com.example.iv1.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.iv1.data.Drug

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StartCheck(viewModel: DrugViewModel) {
    val drugList = viewModel.getSelectedDrugList()
    val toCheck: ArrayList<Pair<Drug, Drug>> = ArrayList()
    for(i in 0 until drugList.size - 1) {
        for(j in i+1 until drugList.size) {
            if(i != j) {
                toCheck.add(Pair(drugList[i], drugList[j]))
            }
        }
    }
    LazyColumn {
        toCheck.forEach { pair ->
            stickyHeader {
                Text(
                    text = pair.first.drug_name.trim() + " -- " + pair.second.drug_name.trim(),
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(5.dp)
                        .fillMaxWidth()
                )
            }
            items(listOf(pair)) {drugPair ->
                DisplayCheck(drugPair.first, drugPair.second)
            }
        }
    }
}

@Composable
fun DisplayCheck(drug1: Drug, drug2: Drug) {
    val res: ArrayList<String> = ArrayList()
    for(i in 0 until drug1.incompatibility_drugs.size) {
        res.add(drug1.incompatibility_drugs[i].lowercase().trim())
    }
    val temp = res.contains(drug2.drug_name.lowercase().trim())

//    println(toCheck.second.drug_name.lowercase().trim())
//    println(res)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(text = drug1.drug_name.trim() + " -- " + drug2.drug_name.trim() + " --> " + temp, fontSize = MaterialTheme.typography.h5.fontSize)
        }
    }
}
