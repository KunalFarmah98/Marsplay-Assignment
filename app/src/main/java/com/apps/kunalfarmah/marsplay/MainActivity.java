package com.apps.kunalfarmah.marsplay;


import android.os.Bundle;
import android.util.Log;


import com.apps.kunalfarmah.marsplay.ui.research_article.ResearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DataAdapter adapter;
    private ArrayList<Data> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.plos.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RequestInterface request = retrofit.create(RequestInterface.class);
        Call<Response> call = request.getJSON();
        try {
            call.enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
//                    if(!response.isSuccessful()){
//                        throw new RuntimeException();
//                    }
                    Response jsonResponse = response.body();
                    Log.d("Response",jsonResponse.toString());
                    List<DocsItem>docs = jsonResponse.getDocs();
//                    for(DocsItem i: docs){
//                        Log.d("Score",String.valueOf(i.getScore()));
//                    }

                    adapter = new DataAdapter(docs);
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        }
        catch (Exception e){
            Log.d("Error",e.getMessage());
        }
        Fragment ArticleFragment = new ResearchFragment(adapter);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.nav_host_fragment,ArticleFragment).addToBackStack("Atricles").commit();
    }
}
