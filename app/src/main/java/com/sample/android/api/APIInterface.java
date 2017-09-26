package com.sample.android.api;

import com.sample.android.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

   @GET("/api/users")
   Call<RemoteResponse> getUsers(@Query("page") int page,
                                 @Query("per_page") int itemsPerPage);

   @GET("/fixture/movies.json")
   Call<List<Movie>> getMovies();

   /* @GET("/api/unknown")
   Call<MultipleResource> doGetListResources();

   @POST("/api/users")
   Call<User> createUser(@Body User user);

   @GET("/photos")
   Call<List<Photo>> getPhotos(@Query("page") String page);

   @FormUrlEncoded
   @POST("/api/users?")
   Call<UserList> doCreateUserWithField(@Field("name") String name, @Field("job") String job);

   @GET("/api/v2/pipelines/agxzfm1haWxmb29nYWVyMQsSDE9yZ2FuaXphdGlvbiIKc3RyZWFrLmNvbQwLEghXb3JrZmxvdxiAgICSz72CCgw/boxes")
   Call<RemoteResponse> getBoxes(@Query("page") int page, @Query("limit") int limit);
   */

}