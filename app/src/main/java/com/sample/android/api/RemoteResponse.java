package com.sample.android.api;

import com.google.gson.annotations.SerializedName;
import com.sample.android.Item;

import java.util.List;

public class RemoteResponse {

   @SerializedName("page")
   public int page;

   @SerializedName("per_page")
   public int itemsPerPage;

   @SerializedName("total_pages")
   public int totalPages;

   @SerializedName("total")
   public int totalItems;

   @SerializedName("data")
   public List<Item> items;

}
