package com.sample.android;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel(Parcel.Serialization.BEAN)
public class Movie {

   public Movie() {
   }

   @SerializedName("title")
   public String title;

   @SerializedName("image")
   public String imageUrl;

   @SerializedName("id")
   public int id;

}
