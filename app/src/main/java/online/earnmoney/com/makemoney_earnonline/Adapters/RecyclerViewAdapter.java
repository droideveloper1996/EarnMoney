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
 * Created by Abhishek on 08/12/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context mCtx;
    private ArrayList<NewsClass> newsBundle = new ArrayList<>();

    public RecyclerViewAdapter(Context ctx, ArrayList<NewsClass> NewsBundle) {
        this.mCtx = ctx;
        this.newsBundle = NewsBundle;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mCtx).inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Picasso.with(mCtx).load(newsBundle.get(position).getmNewsUrl()).into(holder.imageView);
        holder.textView.setText(newsBundle.get(position).getmNewsHeading());


    }

    @Override
    public int getItemCount() {
        return newsBundle.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.list_item_news_image_view);
            textView = itemView.findViewById(R.id.list_item_heading);
        }
    }
}
