package com.example.task_diagnal_technologies.api.pojo


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class RomanticComedy(
    @SerializedName("page")
    var page: Page = Page()
) {
    data class Page(
        @SerializedName("content-items")
        var contentItems: ContentItems = ContentItems(),
        @SerializedName("page-num")
        var pageNum: String = "",
        @SerializedName("page-size")
        var pageSize: String = "",
        @SerializedName("title")
        var title: String = "",
        @SerializedName("total-content-items")
        var totalContentItems: String = ""
    ) {
        data class ContentItems(
            @SerializedName("content")
            var content: MutableList<Content> = mutableListOf()
        ) {
            @Parcelize
            data class Content(
                var id : Int = 0,
                @SerializedName("name")
                var name: String = "",
                @SerializedName("poster-image")
                var posterImage: String = ""
            ) : Parcelable
        }
    }
}