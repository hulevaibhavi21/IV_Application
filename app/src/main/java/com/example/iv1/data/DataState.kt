package com.example.iv1.data

sealed class DataState {
    class Success(val data: ArrayList<Drug>): DataState()
    class Failure(val message: String): DataState()
    object Loading: DataState()
    object Empty: DataState()
}
