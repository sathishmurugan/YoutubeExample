package com.example.sathish.youtubeexample;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {
    private MyListData[] listdata;
    MainActivity mainActivity;


    public MyListAdapter(MyListData[] myListDataList, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        this.listdata = myListDataList;
    }

    @NonNull
    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View listItem = layoutInflater.inflate(R.layout.my_list, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ViewHolder viewHolder, int position) {
        // final MyListData myListData = myListDataList.get(position);
        final MyListData myListData = listdata[position];
        viewHolder.textView.setText(listdata[position].getVideoName());
        viewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Toast.makeText(view.getContext(), "click on item: " + myListData.getVideoName(), Toast.LENGTH_LONG).show();
               
                mainActivity.setVideoNewUrl(myListData.getVideoUrl());
                //  mainActivity.getPlayer().loadVideo(myListData.getVideoUrl());
                mainActivity.getYouTubePlayerView().initialize("AIzaSyDXF_JXprKJOLDCqQnVw1LaetDlEJcjPj4", mainActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listdata.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.videoName);
        }
    }


}
