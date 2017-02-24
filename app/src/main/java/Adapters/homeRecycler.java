package Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import zw.co.zimcybers.kinsleykajiva.a263chatcom.R;

/**
 * Created by kinsley kajiva on 4/19/2016.
 */
public class homeRecycler  extends RecyclerView.Adapter<homeRecycler.CustomViewHolder>{
    private ArrayList<HashMap<String, String>> feedItemList;
    private Context mContext;
    public homeRecycler(Context context, ArrayList<HashMap<String, String>> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }


    @Override
    public homeRecycler.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(homeRecycler.CustomViewHolder holder, int position) {

        HashMap<String, String> feedItem = feedItemList.get(position);
        holder.txtTitle.setText(feedItem.get("title"));
        holder.txtdate.setText(feedItem.get("description"));
        holder.txtDescription.setText(feedItem.get("category"));
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView txtTitle,txtdate,txtDescription;
        public CustomViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.news_title3);
            this.txtdate = (TextView) itemView.findViewById(R.id.news_time3);
            this.txtDescription = (TextView) itemView.findViewById(R.id.news_description3);
        }
    }
}
