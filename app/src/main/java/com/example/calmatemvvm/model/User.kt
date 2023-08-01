package com.example.calmatemvvm.model

import javax.inject.Inject

data class User @Inject constructor(
    var username: String,
    var email: String,
    var password: String,
    var first_name: String,
    var last_name: String,
    var move_goal: Int,
    var created_at: String
) {
    constructor() : this("", "", "", "", "", 0, "") {
    }
}