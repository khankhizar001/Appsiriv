package com.example.appiness.appsrivnews.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appiness.appsrivnews.R;
import com.example.appiness.appsrivnews.activities.WebviewActivity;
import com.example.appiness.appsrivnews.internet.NetworkCheck;
import com.example.appiness.appsrivnews.pojo.Item;

import java.util.List;


/**
 * Created by appiness on 11/5/18.
 */

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.ListViewHolder> {

    private List<Item> item;
    private int news_list_row;
    private Context context;

    public ListViewAdapter(List<Item> item, int news_list_row, Context context) {
        this.item = item;
        this.news_list_row = news_list_row;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(news_list_row, parent, false);
        return new ListViewHolder(view);
        
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        holder.title.setText(item.get(position).getTitle());

        String dateString =item.get(position).getDate();
        holder.date.setText(dateString.substring(5,dateString.indexOf("",16)));




        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(NetworkCheck.isConnected(context))
                {
                    Intent intent = new Intent(context,WebviewActivity.class);
                    intent.putExtra("title",item.get(position).getTitle());
                    intent.putExtra("url",item.get(position).getLink());
                    context.startActivity(intent);
                }else{

                    Toast.makeText(context,R.string.no_connection,Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView date;
        public ListViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tvtitle);
            date = (TextView) view.findViewById(R.id.tvDate);

        }
    }
}
