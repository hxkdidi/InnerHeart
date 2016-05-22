package daixin.me.bugua.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import daixin.me.bugua.R;
import daixin.me.bugua.listeners.OnLoadMoreListener;
import daixin.me.bugua.ui.model.meizhi.Img;
import daixin.me.bugua.view.ItemImage;

/**
 * Created by shidai on 2016/4/24.
 */
public class MeiZhiRecycleAdapter extends RecyclerView.Adapter<MeiZhiRecycleAdapter.ViewHolder> {

    private  Context mContext;
    private  List<Img> mContentlist ;
    private OnLoadMoreListener moreListener;

    public MeiZhiRecycleAdapter(Context context, List<Img> contentlist) {
        this.mContext = context;
        this.mContentlist = contentlist;
    }

    @Override
    public MeiZhiRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_meizhi, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (moreListener!=null){
            moreListener.loadUpdateData(mContentlist);
        }
        Img img = mContentlist.get(position);
        holder.ivItemMeizhi.setOriginalSize(img.getImageWidth(),img.getImageHeight());
        Picasso.with(mContext).load(img.getImageUrl()).into(holder.ivItemMeizhi);
        holder.tagMeiZhi.setText(img.getDesc());
    }

    @Override
    public int getItemCount() {
        return mContentlist.size()==0?0:mContentlist.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private ItemImage ivItemMeizhi;
        private TextView tagMeiZhi;
        private ViewHolder(View itemView) {
            super(itemView);
            ivItemMeizhi = (ItemImage) itemView.findViewById(R.id.iv_pic);
            tagMeiZhi = (TextView) itemView.findViewById(R.id.tv_pic_tag);
        }
    }

    //pull-dowm
    public void addData(List<Img> newContentlistList){
        newContentlistList.addAll(mContentlist);
        mContentlist.removeAll(mContentlist);
        mContentlist.addAll(newContentlistList);
        notifyDataSetChanged();
    }

    //pull-up
    public void addMoreData(List<Img> newContentlistList){
        mContentlist.addAll(newContentlistList);
        notifyDataSetChanged();
    }

    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener){
        this.moreListener = loadMoreListener;
    }
}
