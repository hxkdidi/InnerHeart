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
import daixin.me.bugua.ui.model.Contentlist;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by shidai on 2016/4/24.
 */
public class LoveRecycleAdapter extends RecyclerView.Adapter<LoveRecycleAdapter.ViewHolder> {

    private  Context mContext;
    private  List<Contentlist> mContentlist;
    private int height;
    private OnLoadMoreListener moreListener = null;

    public LoveRecycleAdapter(Context context, List<Contentlist> contentlist) {
        this.mContext = context;
        this.mContentlist = contentlist;
    }

    @Override
    public LoveRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(mContext, R.layout.item_cardview, null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (moreListener!=null){
            moreListener.loadUpdateData(mContentlist);
        }
        Contentlist contentlist = mContentlist.get(position);
        Picasso.with(mContext).load(contentlist.contentImg).into(holder.ivItemMain);
        holder.textViewLabel.setText(contentlist.userName+"=="+position);
        holder.getTextViewDesc.setText(contentlist.title);
    }

    @Override
    public int getItemCount() {
        return mContentlist.size()==0?0:mContentlist.size();
    }

    //黑夜了~
    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView ivItemMain;
        private TextView textViewLabel;
        private TextView getTextViewDesc;
        private ViewHolder(View itemView) {
            super(itemView);
            ivItemMain = (CircleImageView) itemView.findViewById(R.id.iv_item_main);
            textViewLabel = (TextView) itemView.findViewById(R.id.tv_item_label);
            getTextViewDesc = (TextView) itemView.findViewById(R.id.tv_item_desc);
        }
    }

    //pull-dowm
    public void addData(List<Contentlist> newContentlistList){
        newContentlistList.addAll(mContentlist);
        mContentlist.removeAll(mContentlist);
        mContentlist.addAll(newContentlistList);
        notifyDataSetChanged();
    }

    //pull-up
    public void addMoreData(List<Contentlist> newContentlistList){
        mContentlist.addAll(newContentlistList);
        notifyDataSetChanged();
    }
    public void setLoadMoreListener(OnLoadMoreListener loadMoreListener){
        this.moreListener = loadMoreListener;
    }


}
