package bits.mobileappclub.waves;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by HP1 on 01-Nov-15.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ImageViewHolder>{
   int[] imageID;
    RVAdapter(int[] imageID){
        this.imageID = imageID;
    }


    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_item,parent, false);
        ImageViewHolder pvh = new ImageViewHolder(v);
        return pvh;

    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {

       holder.image.setImageResource(imageID[position]);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ImageViewHolder extends RecyclerView.ViewHolder {


        ImageView image;

        ImageViewHolder(View itemView) {
            super(itemView);


            image = (ImageView)itemView.findViewById(R.id.image);
        }
    }
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}