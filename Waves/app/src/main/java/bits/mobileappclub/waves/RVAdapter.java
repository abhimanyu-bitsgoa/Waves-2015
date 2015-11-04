package bits.mobileappclub.waves;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseObject;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by HP1 on 01-Nov-15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ImageViewHolder> {
    List<ParseObject> gallery;
    Context context;

    RVAdapter(List<ParseObject> gallery, Context context) {
        this.gallery = gallery;
        this.context=context;
    }

    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item, parent, false);
        ImageViewHolder pvh = new ImageViewHolder(v);
        return pvh;

    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        ParseObject p=this.gallery.get(position);
        Log.d("URL",p.getString("url"));
        Picasso.with(this.context)
                .load(p.getString("url"))
                .placeholder(R.mipmap.ic_launcher) // optional
                .error(R.mipmap.ic_launcher)         // optional
                .into(holder.image);
        holder.tv.setText(p.getString("caption"));
    }

    @Override
    public int getItemCount() {
        return gallery.size();
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView tv;
        ImageViewHolder(View itemView) {
            super(itemView);/*
            Picasso.with(this)
                .load(imageURL)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mip)
                .into(imageView);*/
            image = (ImageView) itemView.findViewById(R.id.image);
            tv=(TextView) itemView.findViewById(R.id.gallery_tv);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}