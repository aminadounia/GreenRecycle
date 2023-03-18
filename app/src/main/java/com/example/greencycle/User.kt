package com.example.greencycle

import java.io.Serializable

data class User(
    val id:Int,
    val mail:String,
    val mdp:String,
    val username : String,
    val num: String,
    val role : String,
    var rc:Int?,
    var nif:Int?,
    var nis:Int?
):Serializable
