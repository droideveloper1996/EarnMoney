package online.earnmoney.com.makemoney_earnonline.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import online.earnmoney.com.makemoney_earnonline.NewsClass;
import online.earnmoney.com.makemoney_earnonline.R;

/**
 * Created by Abhishek on 10/12/2017.
 */

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewAdapter.NewsViewHolder> {

    Context context;
    ArrayList<NewsClass> newsClasses;
    public static OnItemClickListner itemClickListner;

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.three_news_list_view, parent, false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {

        Picasso.with(context).load(newsClasses.get(position).getmNewsUrl()).into(holder.primaryImageView);
        Picasso.with(context).load(newsClasses.get(position).getmNewsUrl()).into(holder.SecondaryImageView);
        Picasso.with(context).load(newsClasses.get(position).getmNewsUrl()).into(holder.thirdImageView);
        holder.NewsTextView.setText(newsClasses.get(position).getmNewsHeading());

    }

    @Override
    public int getItemCount() {
        return newsClasses.size();
    }

    public NewsRecyclerViewAdapter(Context ctx, ArrayList<NewsClass> NewsBundle, OnItemClickListner itemOnClickListner) {
        this.context = ctx;
        this.newsClasses = NewsBundle;
        this.itemClickListner = (OnItemClickListner) itemOnClickListner;
    }


    public interface OnItemClickListner {
        void onItemClick(int position);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        ImageView primaryImageView;
        ImageView SecondaryImageView;
        ImageView thirdImageView;
        TextView NewsTextView;

        public NewsViewHolder(View itemView) {
            super(itemView);

            primaryImageView = itemView.findViewById(R.id.main_image_view);
            SecondaryImageView = itemView.findViewById(R.id.secondary_image_view);
            thirdImageView = itemView.findViewById(R.id.tertiory_image_view);
            NewsTextView = itemView.findViewById(R.id.main_news_heading);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            int positon = getAdapterPosition();
            itemClickListner.onItemClick(positon);
        }
    }
}
