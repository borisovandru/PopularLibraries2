package com.android.popularlibraries.data.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
class UsersRepository(
    @Json(name = "html_url")
    val htmlUrl: String?,
    @Json(name = "id")
    val id: Int?,
    var userId: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "description")
    val description: String?,
    var likeCounter:Int=0
):Parcelable