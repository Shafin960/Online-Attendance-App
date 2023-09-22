package com.example.failapp.models

data class Meta(
    val currentPage: Int,
    val from: Int,
    val lastPage: Int,
    val path: String,
    val perPage: Int,
    val to: Int,
    val total: Int
)