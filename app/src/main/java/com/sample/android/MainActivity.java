package com.sample.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.sample.android.api.APIClient;
import com.sample.android.api.APIInterface;
import com.sample.android.api.RemoteResponse;
import com.sample.android.scroll.EndlessRecyclerViewScrollListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements ItemEventListener {

   private static final int ITEMS_PER_PAGE = 5;
   private static final String TAG = "MainActivity";

   private RecyclerView recyclerView;
   private MyAdapter adapter;
   private APIInterface apiInterface;
   private int currentPage = 1;
   private int totalItems;

   @Override
   protected void onCreate(final Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      //binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

      recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
      adapter = new MyAdapter(this);
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
      recyclerView.addOnScrollListener(createEndlessScrollListener(linearLayoutManager));
      recyclerView.setLayoutManager(linearLayoutManager);
      recyclerView.setAdapter(adapter);

      apiInterface = APIClient.getClient().create(APIInterface.class);

      if (savedInstanceState != null) {
         adapter.restoreFromInstanceState(savedInstanceState);
         currentPage = savedInstanceState.getInt("currentPage", 1);
         totalItems = savedInstanceState.getInt("totalItems", 0);
         recyclerView.scrollToPosition(savedInstanceState.getInt("lastVisibleItemPosition"));
      } else {
         loadItems(currentPage);
      }

      //loadMovies();

   }

   @Override
   public void onSaveInstanceState(Bundle outState) {
      super.onSaveInstanceState(outState);
      adapter.onSaveInstanceState(outState);
      outState.putInt("currentPage", currentPage);
      outState.putInt("totalItems", totalItems);
      outState.putInt("lastVisibleItemPosition", ((LinearLayoutManager) recyclerView
            .getLayoutManager()).findFirstVisibleItemPosition());
   }

   private void loadMovies() {
      Call call = apiInterface.getMovies();
      call.enqueue(new Callback() {
         @Override
         public void onResponse(Call call, Response response) {
            List<Movie> movies = (List<Movie>) response.body();
            //adapter.addItems(movies);
         }

         @Override
         public void onFailure(Call call, Throwable t) {
            Log.e(TAG, "failed", t);
         }
      });

   }

   private void loadItems(int page) {
      Call call = apiInterface.getUsers(page, ITEMS_PER_PAGE);
      call.enqueue(new Callback() {
         @Override
         public void onResponse(Call call, Response response) {
            RemoteResponse remoteResponse = (RemoteResponse) response.body();
            currentPage = remoteResponse.page;
            totalItems = remoteResponse.totalItems;
            if (remoteResponse.items.size() > 0) {
               adapter.addItems(remoteResponse.items);
            }
         }

         @Override
         public void onFailure(Call call, Throwable t) {
            Log.e(TAG, "failed", t);
         }
      });

   }

   @Override
   public void onBackPressed() {
      Fragment fragment = getSupportFragmentManager().findFragmentByTag(DetailsFragment.TAG);
      if (fragment != null) {
         getSupportFragmentManager().beginTransaction().remove(fragment).commit();
         return;
      }
      super.onBackPressed();
   }

   private EndlessRecyclerViewScrollListener createEndlessScrollListener(LinearLayoutManager
                                                                               linearLayoutManager) {
      return new EndlessRecyclerViewScrollListener(linearLayoutManager) {
         @Override
         public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
            if (totalItems == totalItemsCount) {
               Log.d(TAG, "onLoadMore.. dont load more, no more to load: " + totalItemsCount);
               return;
            }
            Log.d(TAG, "onLoadMore loading next page: " + (currentPage + 1));
            loadItems(currentPage + 1);
         }
      };
   }

   @Override
   public void onItemClicked(Item item, int itemPosition) {
      getSupportFragmentManager().beginTransaction().replace(R.id.activity_main,
            DetailsFragment.newInstance(item, itemPosition), DetailsFragment.TAG).commit();
   }

   @Override
   public void onItemDeleted(int itemPosition) {
      onBackPressed();
      adapter.deleteItem(itemPosition);
   }
}
