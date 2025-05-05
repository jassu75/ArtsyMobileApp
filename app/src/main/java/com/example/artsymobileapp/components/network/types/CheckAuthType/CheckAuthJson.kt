package com.example.artsymobileapp.components.network.types.CheckAuthType

import com.example.artsymobileapp.components.network.types.userType.UserType

data class CheckAuthJson(
    val authenticated:Boolean,
    val user:UserType?
)