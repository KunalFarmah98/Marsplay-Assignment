package com.apps.kunalfarmah.marsplay.ui.research_article;

import android.os.Bundle;
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
import com.apps.kunalfarmah.marsplay.R;

public class ResearchFragment extends Fragment {

    DataAdapter adapter;
    public ResearchFragment(DataAdapter adapter) {
        this.adapter=adapter;
    }

    RecyclerView rv;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_research, container, false);
        rv = root.findViewById(R.id.researchRecycler);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        return root;
    }
}