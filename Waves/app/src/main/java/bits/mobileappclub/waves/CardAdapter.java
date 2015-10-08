package bits.mobileappclub.waves;

/**
 * Created by Aronzxxx on 03-10-2015.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{






    ParseObject p0;

    ParseQuery<ParseObject> query = ParseQuery.getQuery("Events").whereMatches("testCol", "1");

    List<CardInfo> mItems;
    static String eventName;

    public CardAdapter(int num) {
        super();
        try{
            mItems = new ArrayList<CardInfo>();
            CardInfo card = new CardInfo();
            //Getting Parse from Local Database;







            if (num == 1) {
                ParseQuery<ParseObject> query0 = ParseQuery.getQuery("Day0");
                query0.fromLocalDatastore();
                query0.orderByAscending("time");
                List <ParseObject> pObj0= query0.find();


                for(int i=0;i<pObj0.size();i++) {

                    System.out.println(pObj0.get(i).get("title")+"is at "+pObj0.get(i).get("time"));

                }





            }
            if (num == 2) {

                ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Day1");
                query1.fromLocalDatastore();
                query1.orderByAscending("time");
                List <ParseObject> pObj0= query1.find();


                for(int i=0;i<pObj0.size();i++) {

                    System.out.println(pObj0.get(i).get("title")+"is at "+pObj0.get(i).get("time"));

                }



            }
            if (num == 3) {

                ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Day2");
                query2.fromLocalDatastore();
                query2.orderByAscending("time");
                List <ParseObject> pObj0= query2.find();


                for(int i=0;i<pObj0.size();i++) {

                    System.out.println(pObj0.get(i).get("title")+"is at "+pObj0.get(i).get("time"));

                }



            }




            else {

                ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Day3");
                query3.fromLocalDatastore();
                query3.orderByAscending("time");
                List <ParseObject> pObj0= query3.find();


                for(int i=0;i<pObj0.size();i++) {

                    System.out.println(pObj0.get(i).get("title")+"is at "+pObj0.get(i).get("time"));

                }


            }
    } catch (Exception e) {
        e.printStackTrace();
    }
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
        CardInfo card = mItems.get(i);
        viewHolder.tvcard.setText(card.getName());
        viewHolder.tvDescard.setText(card.getDes());
        viewHolder.imgThumbnail.setImageResource(card.getThumbnail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvcard;
        public TextView tvDescard;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvcard = (TextView)itemView.findViewById(R.id.eventNameTimeline);
            tvDescard = (TextView)itemView.findViewById(R.id.categoryTimeline);
        }
    }


    public void buildParObj(String objId,int imgId){

        CardInfo card = new CardInfo();
        try {
            p0 = query.get(objId);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        card.setName(p0.getString("title").toString());
        card.setDes(p0.getString("category").toString());
        card.setThumbnail(imgId);
        mItems.add(card);
    }






}

