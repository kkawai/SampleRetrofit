package com.sample.android;

import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Bindings {
   /*
   private static final SimpleDateFormat HOUR_FORMAT = new SimpleDateFormat("hh:mm a");

   @android.databinding.BindingAdapter({"bind:timeSpan"})
   public static void setTimeSpan(TextView textView, Person person) {
      Date arriveTime = new Date(person.getArriveTime() * 1000);
      Date leaveTime = new Date(person.getLeaveTime() * 1000);
      String timeSpan = HOUR_FORMAT.format(arriveTime) + " - " + HOUR_FORMAT.format(leaveTime);
      textView.setText(timeSpan);
   }

   @android.databinding.BindingAdapter({"bind:textColor"})
   public static void setTextColor(TextView textView, Person person) {
      Resources r = textView.getContext().getResources();
      textView.setTextColor(person.getId() != 0 ? r.getColor(R.color.dark_text) : r.getColor(R.color.light_text));
   }*/

   @android.databinding.BindingAdapter({"bind:fullName"})
   public static void setFullName(TextView textView, Item item) {
      textView.setText(item.getFirstName() + " " + item.getLastName());
      //Resources r = textView.getContext().getResources();
      //textView.setTextColor(person.getId() != 0 ? r.getColor(R.color.dark_text) : r.getColor(R
      //      .color.light_text));
   }

   @android.databinding.BindingAdapter({"bind:imageUrl"})
   public static void setImageUrl(ImageView imageView, String url) {
      Glide.with((Activity) imageView.getContext()).load(url).into(imageView);
   }
}
