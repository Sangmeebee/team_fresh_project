package com.sangmeebee.teamfreshproject.util

import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.sangmeebee.teamfreshproject.R
import com.sangmeebee.teamfreshproject.const.IMAGE_BASE_URL

@BindingAdapter("imageUrl")
fun ImageView.setImageByUrl(url: String?) {
    if (url == null) {
        isVisible = false
    } else {
        isVisible = true
        val imageUrl = if (url.contains(IMAGE_BASE_URL)) {
            url
        } else {
            IMAGE_BASE_URL + url
        }
        Glide
            .with(context)
            .load(imageUrl)
            .placeholder(R.color.secondary_200)
            .error(R.color.secondary_200)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(10)))
            .into(this)
    }
}

@BindingAdapter("htmlText")
fun TextView.setHtmlText(text: String) {
    setText(HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY).toString().trim())
}
