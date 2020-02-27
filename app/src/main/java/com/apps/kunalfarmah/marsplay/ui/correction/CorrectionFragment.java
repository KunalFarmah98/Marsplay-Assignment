package com.apps.kunalfarmah.marsplay.ui.correction;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.kunalfarmah.marsplay.DataAdapter;
import com.apps.kunalfarmah.marsplay.DocsItem;
import com.apps.kunalfarmah.marsplay.UpperClassResponse;
import com.apps.kunalfarmah.marsplay.R;
import com.apps.kunalfarmah.marsplay.RequestInterface;
import com.apps.kunalfarmah.marsplay.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CorrectionFragment extends Fragment {

    List<DocsItem> docs;
    public CorrectionFragment() {
    }

    RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_correction, container, false);
        rv = root.findViewById(R.id.correctionRecycler);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.plos.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final RequestInterface request = retrofit.create(RequestInterface.class);
        Call<UpperClassResponse> call = request.getJSON();
        // Set up progress before call
        final ProgressDialog progressDoalog;
        progressDoalog = new ProgressDialog(getContext());
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Loading...");
        progressDoalog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDoalog.show();
        try {
            call.enqueue(new Callback<UpperClassResponse>() {
                @Override
                public void onResponse(Call<UpperClassResponse> call, retrofit2.Response<UpperClassResponse> response) {
                   progressDoalog.dismiss();
                    UpperClassResponse jsonResponse = response.body();

                    Log.d("MyResponse", jsonResponse.toString());
                    Response r = jsonResponse.getResponse();
                    docs = r.getDocs();
                    for (DocsItem i : docs) {
                        Log.d("Score", String.valueOf(i.getScore()));
                    }
                    Log.d("Size", String.valueOf(docs.size()));
                    DataAdapter adapter = new DataAdapter(docs,"Correction",getActivity().getApplicationContext());
                    rv.setAdapter(adapter);
                }


                @Override
                public void onFailure(Call<UpperClassResponse> call, Throwable t) {
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