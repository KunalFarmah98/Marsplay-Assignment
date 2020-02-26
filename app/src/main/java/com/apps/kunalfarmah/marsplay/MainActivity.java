package com.apps.kunalfarmah.marsplay;


import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;


import com.apps.kunalfarmah.marsplay.ui.research_article.ResearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import java.sql.Wrapper;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private DataAdapter adapter;
    private List<DocsItem> docs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_research, R.id.navigation_correction)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navView.setSelectedItemId(R.id.navigation_research);

        ResearchFragment research = new ResearchFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment,research)
                .addToBackStack("Research").commit();


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.plos.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        RequestInterface request = retrofit.create(RequestInterface.class);
//        Call<Farmah> call = request.getJSON();
//        try {
//            call.enqueue(new Callback<Farmah>() {
//                @Override
//                public void onResponse(Call<Farmah> call, retrofit2.Response<Farmah> response) {
////                    if(!response.isSuccessful()){
////                        throw new RuntimeException();
////                    }
//                    Farmah jsonResponse = response.body();
//
//                    Log.d("MyResponse",jsonResponse.toString());
//                    Response r =jsonResponse.getResponse();
//                    docs = r.getDocs();
//                    for(DocsItem i: docs){
//                        Log.d("Score",String.valueOf(i.getScore()));
//                    }
//                    Log.d("Size",String.valueOf(docs.size()));
//                    adapter = new DataAdapter(docs);
//                    Fragment ArticleFragment = new ResearchFragment(adapter);
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.nav_host_fragment,ArticleFragment).addToBackStack("Atricles").commit();
//                }
//
//                @Override
//                public void onFailure(Call<Farmah> call, Throwable t) {
//                    Log.d("Error", t.getMessage());
//                }
//            });
//        }
//        catch (Exception e){
//            Log.d("Error",e.getMessage());
//        }

    }
}
