package com.assignment.flickerfinder.network.responses
import com.google.gson.annotations.SerializedName

data class PhotoList(
    @SerializedName("photos")
    val photos: Photos? = null,
    @SerializedName("stat")
    val stat: String? = null
)

class Photos {
    @SerializedName("pages")
    var pages: Int? = null
    @SerializedName("perpage")
    var perpage: Int? = null
    @SerializedName("total")
    var total: Int? = null
    @SerializedName("photo")
    var photo: List<Photo>? = null
}

class Photo {
    @SerializedName("id")
    var id: String? = null
    @SerializedName("owner")
    var owner: String? = null
    @SerializedName("secret")
    var secret: String? = null
    @SerializedName("server")
    var server: String? = null
    @SerializedName("farm")
    var farm: Int? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("ispublic")
    var ispublic: Int? = null
    @SerializedName("isfriend")
    var isfriend: Int? = null
    @SerializedName("isfamily")
    var isfamily: Int? = null
    @SerializedName("url_o")
    var urlO: String? = null
    @SerializedName("height_o")
    var heightO: Int? = null
    @SerializedName("width_o")
    var widthO: Int? = null
    @SerializedName("url_q")
    var urlQ: String? = null
    @SerializedName("height_q")
    var heightQ: Int? = null
    @SerializedName("width_q")
    var widthQ: Int? = null
}
