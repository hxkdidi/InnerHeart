package daixin.me.bugua.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import daixin.me.bugua.R;
import daixin.me.bugua.ui.model.music.Songlist;
import daixin.me.bugua.view.ItemImage;

/**
 * Created by shidai on 2016/4/24.
 */
public class MusicRecycleAdapter extends RecyclerView.Adapter<MusicRecycleAdapter.ViewHolder> {

    private  Context mContext;
    private  List<Songlist> mContentlist ;


    public MusicRecycleAdapter(Context context, List<Songlist> contentlist) {
        this.mContext = context;
        this.mContentlist = contentlist;
    }

    @Override
    public MusicRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_music, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Songlist song = mContentlist.get(position);
        holder.tvSongName.setText(song.getSongname());
        holder.tvUserName.setText(song.getSingername());
        if (null!=song.getAlbumpicBig()){
            Picasso.with(mContext).load(song.getAlbumpicBig()).into(holder.ivAlbum);
            return;
        }else if(null!=song.getAlbumpicSmall()){
            Picasso.with(mContext).load(song.getAlbumpicSmall()).into(holder.ivAlbum);
            return;
        }else {
            Picasso.with(mContext).load("http://image.tianjimedia.com/uploadImages/2015/165/05/2LW6164Q70E8_680x500.jpg").into(holder.ivAlbum);
        }
    }

    @Override
    public int getItemCount() {
        return mContentlist.size()==0?0:mContentlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ItemImage ivAlbum;
        private TextView tvSongName;
        private TextView tvUserName;
        private ViewHolder(View itemView) {
            super(itemView);
            ivAlbum = (ItemImage) itemView.findViewById(R.id.iv_album);
            tvSongName = (TextView) itemView.findViewById(R.id.tv_song_name);
            tvUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
        }
    }

    //pull-dowm
    public void addData(List<Songlist> newContentlistList){
        newContentlistList.addAll(mContentlist);
        mContentlist.removeAll(mContentlist);
        mContentlist.addAll(newContentlistList);
        notifyDataSetChanged();
    }

    //pull-up
    public void addMoreData(List<Songlist> newContentlistList){
        mContentlist.addAll(newContentlistList);
        notifyDataSetChanged();
    }
}
