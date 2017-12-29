package online.earnmoney.com.makemoney_earnonline.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import online.earnmoney.com.makemoney_earnonline.NewsClass;
import online.earnmoney.com.makemoney_earnonline.R;

/**
 * Created by Abhishek on 18/12/2017.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridHolder> {

    private ArrayList<NewsClass> gridDataArrayList;
    private Context mCtx;

    public GridAdapter(Context mCtx, ArrayList<NewsClass> gridDataArrayList) {
        this.mCtx = mCtx;
        this.gridDataArrayList = gridDataArrayList;

    }

    @Override
    public GridHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.grid_list_view, parent, false);
        GridHolder gridHolder = new GridHolder(view);
        return gridHolder;
    }

    @Override
    public void onBindViewHolder(GridHolder holder, int position) {

        Picasso.with(mCtx).load(gridDataArrayList.get(position).getmNewsUrl()).placeholder(R.drawable.image_avatar).into(holder.grid_thumbnail);
        holder.grid_news_heading.setText(gridDataArrayList.get(position).getmNewsHeading());
    }

    @Override
    public int getItemCount() {
        return gridDataArrayList.size();
    }

    public class GridHolder extends RecyclerView.ViewHolder {

        View view;
        TextView grid_news_heading;
        ImageView grid_thumbnail;

        public GridHolder(View itemView) {
            super(itemView);
            grid_thumbnail = (ImageView) itemView.findViewById(R.id.grid_news_thumbnail);
            grid_news_heading = (TextView) itemView.findViewById(R.id.grid_news_heading);
        }
    }
}
