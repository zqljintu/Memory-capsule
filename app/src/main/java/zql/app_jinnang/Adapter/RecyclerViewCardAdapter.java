package zql.app_jinnang.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.Bean.Noteinfo;
import zql.app_jinnang.R;
import zql.app_jinnang.UserSeting;
import zql.app_jinnang.View.ListActivityImp;
import zql.app_jinnang.View.NoteinfoActivity;

/**
 * Created by 尽途 on 2018/4/12.
 */

public class RecyclerViewCardAdapter extends RecyclerView.Adapter<RecyclerViewCardAdapter.Viewholder> {
    private ArrayList<NoteBean>notelist;
    private Context context;
    private ListActivityImp listActivityImp;
    private UserSeting userSeting;

    public RecyclerViewCardAdapter(ArrayList<NoteBean>mnotelist,Context mcontext,ListActivityImp mlistactivityimp){
        this.notelist=mnotelist;
        this.context=mcontext;
        this.listActivityImp=mlistactivityimp;
        userSeting=(UserSeting)listActivityImp.getListApplication();
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View iten_recycler= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclercard,parent,false);
        Viewholder viewholder=new Viewholder(iten_recycler);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
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
        startNoteinfoActivity(holder.recycler_text_note,notelist.get(position));

    }

    @Override
    public int getItemCount() {
        return notelist==null ? 0 : notelist.size();
    }

    public static class Viewholder extends RecyclerView.ViewHolder{
        ImageView recycler_image_notetype,recycler_image_menu;
        TextView recycler_text_note,recycler_text_time;
        public Viewholder(View itemView){
            super(itemView);
            recycler_image_notetype=(ImageView)itemView.findViewById(R.id.recycler_image_notetype);
            recycler_image_menu=(ImageView)itemView.findViewById(R.id.recycler_image_menu);
            recycler_text_note=(TextView)itemView.findViewById(R.id.recycler_text_note);
            recycler_text_time=(TextView)itemView.findViewById(R.id.recycler_text_time);
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
