package com.sample.android;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.android.databinding.ItemBinding;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   public MyAdapter(ItemEventListener onItemClickedListener) {
      this.onItemClickedListener = onItemClickedListener;
   }

   private List<Item> items = new ArrayList<>();
   private ItemEventListener onItemClickedListener;

   public void addItems(List<Item> newItems) {
      int from = this.items.size();
      this.items.addAll(newItems);
      notifyItemRangeInserted(from, newItems.size());
   }

   public void deleteItem(int index) {
      this.items.remove(index);
      notifyItemRemoved(index);
   }

   public void restoreFromInstanceState(Bundle savedInstanceState) {
      if (savedInstanceState.containsKey("items")) {
         addItems((List<Item>) Parcels.unwrap(savedInstanceState.getParcelable("items")));
      }
   }

   public void onSaveInstanceState(Bundle outState) {
      outState.putParcelable("items", Parcels.wrap(items));
   }

   @Override
   public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      final ItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()
      ), R.layout.item, parent, false);
      final MyViewHolder holder = new MyViewHolder(binding);
      binding.getRoot().setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            int position = holder.getAdapterPosition();
            Item item = items.get(position);
            onItemClickedListener.onItemClicked(item, position);
         }
      });
      return holder;
   }

   @Override
   public void onBindViewHolder(MyViewHolder holder, int position) {
      holder.binding.setItem(items.get(position));
   }

   @Override
   public int getItemCount() {
      return items.size();
   }

   static class MyViewHolder extends RecyclerView.ViewHolder {
      private ItemBinding binding;

      public MyViewHolder(ItemBinding binding) {
         super(binding.getRoot());
         this.binding = binding;
      }
   }
}
