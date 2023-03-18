package com.example.greencycle

import java.io.Serializable

data class announcementSimple(
    val id:Int,
    val name:String,
    val id_user:Int,
    val prix : Int,
    val poids: Int,
    val description : String,
    var image:String?,
    var etat : String,
    var categorie:String?,
    val id_recycler:Int?
):Serializable
