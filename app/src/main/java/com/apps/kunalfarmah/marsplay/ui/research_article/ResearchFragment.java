package com.apps.kunalfarmah.marsplay.ui.research_article;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.kunalfarmah.marsplay.DataAdapter;
import com.apps.kunalfarmah.marsplay.DocsItem;
import com.apps.kunalfarmah.marsplay.Farmah;
import com.apps.kunalfarmah.marsplay.R;
import com.apps.kunalfarmah.marsplay.RequestInterface;
import com.apps.kunalfarmah.marsplay.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResearchFragment extends Fragment {

    List<DocsItem> docs;
    public ResearchFragment() {
    }

    RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_research, container, false);
        rv = root.findViewById(R.id.researchRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.plos.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<Farmah> call = request.getJSON();
        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Loading...");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        try {
            call.enqueue(new Callback<Farmah>() {
                @Override
                public void onResponse(Call<Farmah> call, retrofit2.Response<Farmah> response) {
//                    if(!response.isSuccessful()){
//                        throw new RuntimeException();
//                    }
                    Farmah jsonResponse = response.body();
                    progressDoalog.dismiss();

                    Log.d("MyResponse", jsonResponse.toString());
                    Response r = jsonResponse.getResponse();
                    docs = r.getDocs();
                    for (DocsItem i : docs) {
                        Log.d("Score", String.valueOf(i.getScore()));
                    }
                    Log.d("Size", String.valueOf(docs.size()));
                    DataAdapter adapter = new DataAdapter(docs,"Research Article",getActivity().getApplicationContext());
                    rv.setAdapter(adapter);
                }


                @Override
                public void onFailure(Call<Farmah> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                }
            });
        }
        catch (Exception e){
            Log.d("Error",e.getMessage());
        }

        return root;
    }
}