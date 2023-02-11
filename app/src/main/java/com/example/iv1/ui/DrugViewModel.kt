package com.example.iv1.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.iv1.data.DataState
import com.example.iv1.data.Drug
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DrugViewModel: ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    val tempList: ArrayList<Drug> = ArrayList()

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        response.value = DataState.Loading
        val ref = FirebaseDatabase.getInstance().getReference()
        val drugRef = ref.child("Drugs")

        drugRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val drugList: ArrayList<Drug> = ArrayList()
                for(ds: DataSnapshot in snapshot.children) run {
                    val drug: Drug? = ds.getValue(Drug::class.java)
                    if (drug != null) {
                        drugList.add(drug)
                    }
                }
                response.value = DataState.Success(drugList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = DataState.Failure(error.message)
            }

        })
    }

    fun selectDrug(drug: Drug) {
        if(!tempList.contains(drug)) {
            tempList.add(drug)
        }
    }

    fun removeDrug(drug: Drug) {
        if(tempList.contains(drug)) {
            tempList.remove(drug)
        }
    }

    fun getSelectedDrugList(): ArrayList<Drug> {
        return tempList
    }
}