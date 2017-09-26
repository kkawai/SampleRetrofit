package com.sample.android;

public interface ItemEventListener {
   void onItemClicked(Item item, int itemPosition);

   void onItemDeleted(int itemPosition);
}
