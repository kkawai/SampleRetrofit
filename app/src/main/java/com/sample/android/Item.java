package com.sample.android;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class Item {

   public Item() {
   }

   @ParcelConstructor
   public Item(int id, String firstName, String lastName, String imageUrl) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.imageUrl = imageUrl;
   }

   @SerializedName("id")
   private int id;

   @SerializedName("first_name")
   private String firstName;

   @SerializedName("last_name")
   private String lastName;

   @SerializedName("avatar")
   private String imageUrl;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getImageUrl() {
      return imageUrl;
   }

   public void setImageUrl(String imageUrl) {
      this.imageUrl = imageUrl;
   }

   @Override
   public boolean equals(Object obj) {
      Item other = (Item) obj;
      return this.id == other.id;
   }

   @Override
   public int hashCode() {
      return id;
   }
}
