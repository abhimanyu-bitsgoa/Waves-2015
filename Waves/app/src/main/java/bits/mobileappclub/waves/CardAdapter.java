package bits.mobileappclub.waves;

/**
 * Created by Aronzxxx on 03-10-2015.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;



public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<NatureItem> mItems;
    static String eventName;

    public CardAdapter() {
        super();
        mItems = new ArrayList<NatureItem>();
        NatureItem nature = new NatureItem();
        nature.setName("Dhinchak");
        nature.setDes("Dance");
        nature.setThumbnail(R.drawable.dance1);
        mItems.add(nature);

        nature = new NatureItem();
        nature.setName("Indian Rock");
        nature.setDes("Music");
        nature.setThumbnail(R.drawable.music1);
        mItems.add(nature);

        nature = new NatureItem();
        nature.setName("Shutter Island");
        nature.setDes("Litrature");
        nature.setThumbnail(R.drawable.literary1);
        mItems.add(nature);

        nature = new NatureItem();
        nature.setName("Rangmanch");
        nature.setDes("Drama");
        nature.setThumbnail(R.drawable.specials1);
        mItems.add(nature);


        nature = new NatureItem();
        nature.setName("Open Quiz");
        nature.setDes("Quiz");
        nature.setThumbnail(R.drawable.quiz1);
        mItems.add(nature);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_card_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                eventName = ((TextView) v.findViewById(R.id.eventNameTimeline)).getText().toString();

                Intent intent = new Intent(v.getContext(), EventDescription.class);
                intent.putExtra("eventName", eventName);
                v.getContext().startActivity(intent);
            }
        });


        return viewHolder;
    }



    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        NatureItem nature = mItems.get(i);
        viewHolder.tvNature.setText(nature.getName());
        viewHolder.tvDesNature.setText(nature.getDes());
        viewHolder.imgThumbnail.setImageResource(nature.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvNature;
        public TextView tvDesNature;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvNature = (TextView)itemView.findViewById(R.id.eventNameTimeline);
            tvDesNature = (TextView)itemView.findViewById(R.id.categoryTimeline);
        }
    }





}