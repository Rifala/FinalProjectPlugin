package com.example.finalproject.Model

data class User(
    var id: Int,
    var name: String,
    var username: String,
    var email: String,
    var password: String,
    var updatedAt: String,
    var createdAt: String,
    var token: String
)

data class Barang(
    val id: Int,
    val nama: String,
    val kode: Int,
    val createdAt: String,
    val updatedAt: String
)

data class ListResponse<T>(
    var msg: String,
    var status: Int,
    var data: MutableList<T>
)

data class SingleResponse<T>(
    var msg: String,
    var status: Int,
    var data: T
)