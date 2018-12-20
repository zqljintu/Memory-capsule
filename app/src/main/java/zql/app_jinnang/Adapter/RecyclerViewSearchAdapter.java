package zql.app_jinnang.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;

import java.util.List;

import zql.app_jinnang.Bean.Means;
import zql.app_jinnang.Bean.NoteBean;
import zql.app_jinnang.R;
import zql.app_jinnang.View.NoteinfoActivity;

/**
 * Created by 尽途 on 2018/5/3.
 */

public class RecyclerViewSearchAdapter extends RecyclerView.Adapter<RecyclerViewSearchAdapter.Viewholder>{
    private List<NoteBean>notelist;
    private Context context;

    public RecyclerViewSearchAdapter(List<NoteBean> mnotelist, Context mcontext){
        this.notelist=mnotelist;
        this.context=mcontext;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item_search= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suggestioncard,parent,false);
        Viewholder viewholder=new Viewholder(item_search);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        startNoteinfoActivity(holder.relativeLayout,notelist.get(position));
        holder.recycler_text_note.setText(Means.getNotetextOnSearchCard(notelist.get(position).getNoteinfo().toString()));
    }

    @Override
    public int getItemCount() {
        return notelist==null?0:notelist.size();
    }

    public  class Viewholder extends RecyclerView.ViewHolder{
        TextView recycler_text_note;
        RelativeLayout relativeLayout;
        public Viewholder(View itemView){
            super(itemView);
            recycler_text_note=(TextView)itemView.findViewById(R.id.textview_item_suggestion);
            relativeLayout=(RelativeLayout)itemView.findViewById(R.id.rela_suggestion);
        }
    }
    private void startNoteinfoActivity(View view,final NoteBean noteBean){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mintent=new Intent(context,NoteinfoActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("noteinfo", Means.changefromNotebean(noteBean));
                mintent.putExtras(bundle);
                context.startActivity(mintent);
            }
        });
    }

}
