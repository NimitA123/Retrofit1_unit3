package com.example.retrofit1_lyold_video;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
/*The Api call present in this call*/
public interface ApiService {
    @GET("/api/users/{userId}")
     /*** {userId} means your are continue changing the data write it if u do not want to error in the program if some one change last url of the retrofit*****/
    /******Here we are getting the the list from the retrofit Api *****/
    Call<ResponseModel> getUser(@Path("userId") int userId);


}
