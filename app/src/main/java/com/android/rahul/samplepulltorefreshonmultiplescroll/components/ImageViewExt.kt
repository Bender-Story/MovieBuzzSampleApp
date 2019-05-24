package com.android.rahul.samplepulltorefreshonmultiplescroll.components


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.android.rahul.samplepulltorefreshonmultiplescroll.Constants.IMAGE_SUB_URL
import com.android.rahul.samplepulltorefreshonmultiplescroll.Constants.IMAGE_URL
import com.bumptech.glide.Glide

// ImageURL databinding for AppCompatImageView
@BindingAdapter("imageURL")
fun AppCompatImageView.imageUrl(path: String?) {
    Glide.with(this.context).load(buildURL(path)).into(this)
}

fun buildURL(url: String?): String {
    return IMAGE_URL+ IMAGE_SUB_URL + url
}