package com.example.newsapi.api

import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.newsapi.R

@BindingAdapter("imageUrl")
fun loadImgFromUrl(imageView: ImageView, url: String){
    Glide.with(imageView)
        .load(url)
        .placeholder(R.drawable.ic_image)
        .into(imageView)
}

@BindingAdapter("cardColor")
fun changeCardBackgroundColor(cardView: CardView, colorId: Int){
    cardView.setCardBackgroundColor(
        ContextCompat.getColor(cardView.context,colorId)
    )
}
@BindingAdapter("imageId")
fun changeImgByResourceId(imageView: ImageView, resId: Int){
    imageView.setImageResource(resId)
}
@BindingAdapter("lanchUrl")
fun lanchUrl(view: View, url: String){
    view.setOnClickListener {
        val browsIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        view.context.startActivity(browsIntent)
    }
}