package com.sample.android.api;

import com.sample.android.auth.BasicAuthInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

   private static Retrofit retrofit = null;

   public static Retrofit getClient() {

      if (retrofit != null)
         return retrofit;

      HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
      OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(new BasicAuthInterceptor("myusername", "mypass"))
            .build();
      retrofit = new Retrofit.Builder()
            .baseUrl("https://reqres.in")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client).build();
      return retrofit;
   }

}