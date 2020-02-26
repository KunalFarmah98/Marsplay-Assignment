package com.apps.kunalfarmah.marsplay;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<DocsItem> data;

    public DataAdapter(List<DocsItem> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitleDisplay());
        holder.journal.setText(data.get(position).getJournal());
        holder.date.setText(data.get(position).getPublicationDate());
        holder.essin.setText(data.get(position).getEissn());
        String Abstract="";
        List<String>abs = data.get(position).getJsonMemberAbstract();
        for(String s:abs){
            Abstract+=s;
            Abstract+="\n";
        }
        holder.abstr.setText(Abstract);
        holder.score.setText(String.valueOf(data.get(position).getScore()));
        String Authors="";
        List<String>auth = data.get(position).getAuthorDisplay();
        for(String author : auth){
            Authors+=author;
            Authors+=", ";
        }
        int size = Authors.length();
        String author="";
        for(int i=0; i<size-1; i++) author += Authors.charAt(i);
        holder.authors.setText(author);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,journal,date,authors,essin,score,abstr;

        public ViewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.Title);
            journal = itemView.findViewById(R.id.Journal);
            date = itemView.findViewById(R.id.Date);
            authors = itemView.findViewById(R.id.Authors);
            essin = itemView.findViewById(R.id.eissn);
            score = itemView.findViewById(R.id.Score);
            abstr = itemView.findViewById(R.id.text);

        }
    }
}
