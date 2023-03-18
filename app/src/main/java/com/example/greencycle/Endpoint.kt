package com.example.greencycle

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

//endpoint c'est service
interface Endpoint {
//getuser selon le mail et password pour se connecter
    @GET("getuser/{mail}/{mdp}")
    suspend fun getuser(
        @Path("mail") mail: String,
        @Path("mdp") mdp: String
    ): Response<User?>

//Add user dans la creation de compte
    @POST("adduser")
    suspend fun adduser(@Body user: User): Response<String>

    //Get toutes les annonces des provider
    @GET("getannouncements")
    suspend fun getannouncements(): Response<List<announcement>>

    //Get toutes les annonces en attentes pour un recycler
    @GET("getannouncementswaiting/{id_recycler}")
    suspend fun getannouncementswaiting(@Path("id_recycler") id_recycler: Int): Response<List<announcement>>

    //Get toutes les annonces acceptées pour un recycler
    @GET("getannouncementsaccepted/{id_recycler}")
    suspend fun getannouncementsaccepted(@Path("id_recycler") id_recycler: Int): Response<List<announcement>>

    //mettre a jour une annoucement
    @POST("updateannouncement")
    suspend fun updateannouncement(@Body order: Order): Response<String>

    //getuser selon son id
    @GET("getuserbyid/{id}")
    suspend fun getuserbyid(@Path("id") id: Int): Response<User?>

    //get les annonces  d'un provider selon son id
    @GET("getannouncementsUser/{id}")
    suspend fun getannouncementsUser(@Path("id")id : Int): Response<List<announcement>>

    //Get toutes les annonces acceptées pour un provider
    @GET("getOrderAccepted/{id}")
    suspend fun getOrderAccepted
                (        @Path("id") id: Int
    )
            : Response<List<announcement>>

    //Get toutes les annonces en attentes pour un provider
    @GET("getOrderWaiting/{id}")
    suspend fun getOrderWaiting
                (        @Path("id") id: Int
    )
            : Response<List<announcement>>

    //Ajouter une annonces
    @POST("addAnnouncement")
    suspend fun addAnnouncement(@Body announcement: announcementSimple): Response<String>

    //get les annonces selon ca categorie
    @GET("getannouncementsCategorie/{categorie}")
    suspend fun getannouncementsCategorie(@Path("categorie") categorie: String): Response<List<announcement>>
//mettre a jour l'etat d'une annonce

    @POST("updateannouncementcancel/{id}")
    suspend fun updateannouncementcancel(@Path("id") id: Int): Response<String>



}
