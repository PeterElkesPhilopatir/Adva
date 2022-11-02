package com.peter.adva.util

import android.content.Intent
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import com.ablanco.zoomy.Zoomy
import com.makeramen.roundedimageview.RoundedImageView
import com.peter.adva.MainActivity
import com.peter.adva.R
import com.squareup.picasso.Picasso


@BindingAdapter("imageUrl")
fun bindImage(imgView: RoundedImageView, imgUrl: String) {
    if (imgUrl.isNotEmpty()) {
        try {
            imgUrl.apply { this.trim() }
            imgUrl.let {

//                imgView.clipToOutline = true
//                Glide.with(imgView.context)
//                    .setDefaultRequestOptions(RequestOptions())
//                    .load(
//                        imgUrl.plus(".jpg")
//                        .toUri().buildUpon().scheme("https").build()
//                    )
//                    .placeholder(R.drawable.loading_animation)
//                    .error(R.mipmap.ic_launcher)
//                    .into(imgView)

                Picasso.get().load(imgUrl)
                    .placeholder(R.drawable.loading_animation)
                    .error(R.mipmap.ic_launcher_round)
                    .into(imgView)



//                val builder: Zoomy.Builder =
//                    Zoomy.Builder(imgView.context as MainActivity).target(imgView)
//                builder.enableImmersiveMode(true)
//                    .animateZooming(true)
//                    .interpolator(OvershootInterpolator())
//                    .doubleTapListener {
//                        Toast.makeText(imgView.context, imgUrl, Toast.LENGTH_SHORT).show()
//                    }
//
//
//                builder.register()
            }
        } catch (e: Exception) {
        }
    }
}