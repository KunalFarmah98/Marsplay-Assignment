package com.apps.kunalfarmah.marsplay;


import retrofit2.Call;
import retrofit2.http.GET;


public interface RequestInterface {
        @GET("search?q=title:DNA")
        Call<UpperClassResponse> getJSON();
}
