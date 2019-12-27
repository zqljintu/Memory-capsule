package com.zql.app_jinnang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.zql.app_jinnang.Bean.Means;
import com.zql.app_jinnang.Bean.NoteBean;
import com.zql.app_jinnang.R;
import com.zql.app_jinnang.View.ListActivityImp;
import com.zql.app_jinnang.View.NoteinfoActivity;

/**
 * Created by 尽途 on 2018/4/12.
 */

public class RecyclerViewCardAdapter extends RecyclerView.Adapter<RecyclerViewCardAdapter.Viewholder> {
    private ArrayList<NoteBean>notelist;
    private Context context;
    private ListActivityImp listActivityImp;

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐
     */
    private int mEmptyType = 0;

    public RecyclerViewCardAdapter(ArrayList<NoteBean>mnotelist,Context mcontext,ListActivityImp mlistactivityimp){
        this.notelist=mnotelist;
        this.context=mcontext;
        this.listActivityImp=mlistactivityimp;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iten_recycler= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclercard,parent,false);
        Viewholder viewholder=new Viewholder(iten_recycler);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        int itemViewType=getItemViewType(position);
        if (EMPTY_VIEW!=itemViewType){

        }
        setMenuListener(holder.recycler_image_menu,notelist.get(position));
        switch (notelist.get(position).getNotetype().toString()){
            case "旅行":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_travel);
                holder.recycler_text_note.setText(R.string.note_travel);
                break;
            case "学习":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_study);
                holder.recycler_text_note.setText(R.string.note_study);
                break;
            case "工作":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_work);
                holder.recycler_text_note.setText(R.string.note_work);
                break;
            case "日记":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_diary);
                holder.recycler_text_note.setText(R.string.note_diary);
                break;
            case "生活":
                holder.recycler_image_notetype.setImageResource(R.drawable.icon_live);
                holder.recycler_text_note.setText(R.string.note_live);
                break;
            default:
                break;
        }
        holder.recycler_text_note.setText(Means.getNotetextOnRecyclerCard(notelist.get(position).getNoteinfo()));
        holder.recycler_text_time.setText(notelist.get(position).getCreatetime());
        startNoteinfoActivity(holder.linearLayout,notelist.get(position));
    }

    @Override
    public int getItemCount() {
        return notelist==null ? 0 : notelist.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        ImageView recycler_image_notetype,recycler_image_menu;
        TextView recycler_text_note,recycler_text_time;
        LinearLayout linearLayout;
        public Viewholder(View itemView){
            super(itemView);
            recycler_image_notetype=(ImageView)itemView.findViewById(R.id.recycler_image_notetype);
            recycler_image_menu=(ImageView)itemView.findViewById(R.id.recycler_image_menu);
            recycler_text_note=(TextView)itemView.findViewById(R.id.recycler_text_note);
            recycler_text_time=(TextView)itemView.findViewById(R.id.recycler_text_time);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.recycler_item);
        }
    }
    public static class EmptyViewholder extends RecyclerView.ViewHolder{
        public EmptyViewholder(View emptyView){
            super(emptyView);
        }
    }
    private void setMenuListener(View view, final NoteBean noteBean){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listActivityImp.opensheeetdialog(noteBean);
            }
        });
    }
    private void startNoteinfoActivity(View view,final NoteBean noteBean){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(listActivityImp.getListActivityConent(),NoteinfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                mintent.putExtras(bundle);
                context.startActivity(mintent);
            }
        });
    }
}
