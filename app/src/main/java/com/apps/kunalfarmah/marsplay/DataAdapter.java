package com.apps.kunalfarmah.marsplay;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Iterator;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private List<DocsItem> data;
    private String type;
    private Context context;
    String Abstract = "";
    static int num;

    public DataAdapter(List<DocsItem> data, String type, Context context) {
        this.data = data;
        this.type=type;
        this.context = context;
        //this.filter=data;

        for(Iterator<DocsItem> it = data.iterator(); it.hasNext();){
            DocsItem val = it.next();
            if((val.getArticleType().equals(type)))it.remove();
        }
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final DataAdapter.ViewHolder holder, final int position) {

            holder.title.setText(data.get(position).getTitleDisplay());
            holder.journal.setText("Journal: " + data.get(position).getJournal().toUpperCase());
            String Date = data.get(position).getPublicationDate();
            int ind = Date.indexOf('T');
            Date = Date.substring(0,ind);
            holder.date.setText("Date: " + Date);
            holder.essin.setText("(EISSN: "+data.get(position).getEissn()+")");

            List<String> abs = data.get(position).getJsonMemberAbstract();

            String limited = "";
            Abstract="";
            if(data.get(position).getArticleType().equals("Correction")){
                Log.d("Length",String.valueOf(abs.size()));
                holder.abstr.setVisibility(View.GONE);
                holder.Abstract.setVisibility(View.GONE);
            }
            else {
                holder.abstr.setVisibility(View.VISIBLE);
                Abstract = abs.get(0);
                int i=0;
                char a0 = Abstract.charAt(0);
                if(a0=='\n'){
                    Abstract=Abstract.substring(1);
                }
                for(i=0; i<Abstract.length(); i++){
                    if(Abstract.charAt(i)!=' '){
                        Abstract = Abstract.substring(i);
                        break;
                    }
                }

                Log.d("Abstract", String.valueOf(position+" ")+String.valueOf(Abstract));
                int dotcount=0;
                for(i=0; i<Abstract.length(); i++){
                    if(Abstract.charAt(i)=='.'){
                        ++dotcount;
                    }
                    limited+=Abstract.charAt(i);
                    if(dotcount==10)break;
                }
                holder.abstr.setText(limited);
            }
            holder.score.setText("Scored: " + String.valueOf(data.get(position).getScore()));

            String Authors = "";
            List<String> auth = data.get(position).getAuthorDisplay();
            for (String author : auth) {
                Authors += author;
                Authors += ", ";
            }
            int size = Authors.length();
            String author = "";
            for (int i = 0; i < size - 2; i++) author += Authors.charAt(i);
            holder.authors.setText("Authors: " + author);

            holder.content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(data.get(position).getArticleType().equals("Correction")) {
                        holder.exp_tv.setVisibility(View.GONE);
                        Toast.makeText(context,"This is a Correction, No Abstract Available"
                        ,Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(holder.exp_tv.getVisibility()==View.GONE){
                            holder.exp_tv.setVisibility(View.VISIBLE);
                            num=position;
                        }
                        else if(holder.exp_tv.getVisibility()==View.VISIBLE && num!=position){
                            holder.exp_tv.setVisibility(View.GONE);
                        }

                    }
                }
            });

            holder.readmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent read = new Intent(context,ReadActivity.class);
                    read.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    String s = data.get(position).getJsonMemberAbstract().get(0);
                    int i=0;
                    char a0 = s.charAt(0);
                    if(a0=='\n'){
                        s=s.substring(1);
                    }
                    for(i=0; i<s.length(); i++){
                        if(s.charAt(i)!=' '){
                            s = s.substring(i);
                            break;
                        }
                    }
                    read.putExtra("Title",data.get(position).getTitleDisplay());
                    read.putExtra("Text",s);
                    context.startActivity(read);
                    holder.exp_tv.setVisibility(View.GONE);
                }
            });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title,journal,date,authors,essin,score,abstr;
        TextView Abstract,readmore;
        LinearLayout exp_tv,content;

        public ViewHolder(View itemView) {
            super(itemView);
            title= itemView.findViewById(R.id.Title);
            journal = itemView.findViewById(R.id.Journal);
            date = itemView.findViewById(R.id.Date);
            authors = itemView.findViewById(R.id.Authors);
            essin = itemView.findViewById(R.id.eissn);
            score = itemView.findViewById(R.id.Score);
            abstr = itemView.findViewById(R.id.ltd_text);

            Abstract = itemView.findViewById(R.id.Abstract);
            readmore = itemView.findViewById(R.id.read_more);
            exp_tv = itemView.findViewById(R.id.Expandable_tv);
            exp_tv.setVisibility(View.GONE);
            content = itemView.findViewById(R.id.content);

        }
    }
}
