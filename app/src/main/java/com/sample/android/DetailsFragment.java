package com.sample.android;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sample.android.databinding.FragmentDetailsBinding;

import org.parceler.Parcels;

public class DetailsFragment extends Fragment {
   public static final String TAG = "DetailsFragment";

   public static DetailsFragment newInstance(@NonNull Item item, int itemIndex) {
      DetailsFragment fragment = new DetailsFragment();
      Bundle args = new Bundle();
      args.putParcelable("item", Parcels.wrap(item));
      args.putInt("itemIndex", itemIndex);
      fragment.setRetainInstance(true);
      fragment.setArguments(args);
      return fragment;
   }

   private FragmentDetailsBinding binding;
   private ItemEventListener itemListener;

   @Override
   public void onAttach(Context context) {
       super.onAttach(context);
      if (context instanceof ItemEventListener) {
         itemListener = (ItemEventListener) context;
      } else {
         throw new IllegalStateException("Parent activity does not implement ItemListener");
      }
   }

   @Nullable
   @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_details,
            null,
            false);
      binding.setItem((Item) Parcels.unwrap(getArguments().getParcelable("item")));
      binding.setItemIndex(getArguments().getInt("itemIndex"));
      binding.setItemListener(itemListener);
      Log.d(TAG, "onCreateView() binding: " + binding + " itemListener: " + itemListener);
      return binding.getRoot();
   }
}
