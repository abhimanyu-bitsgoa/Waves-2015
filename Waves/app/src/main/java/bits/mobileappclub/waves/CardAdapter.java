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
    List <ParseObject> pObj0,pObj1,pObj2,pObj3;
    public CardAdapter(int num) {
        super();
        try{
            mItems = new ArrayList<CardInfo>();
            CardInfo card = new CardInfo("","","","","",9);
            //Getting Parse from Local Database;
            ParseQuery<ParseObject> query0 = ParseQuery.getQuery("Day0");
            query0.fromLocalDatastore();
            query0.orderByAscending("time");


            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Day1");
            query1.fromLocalDatastore();
            query1.orderByAscending("time");

            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Day2");
            query2.fromLocalDatastore();
            query2.orderByAscending("time");

            ParseQuery<ParseObject> query3 = ParseQuery.getQuery("Day3");
            query3.fromLocalDatastore();
            query3.orderByAscending("time");

             pObj0= query0.find();
            pObj1= query1.find();
            pObj2= query2.find();
            pObj3= query3.find();


            if (num == 1) {

                //List <ParseObject> pObj0= query0.find();
                mItems = new ArrayList<CardInfo>();

                for(int i=0;i<pObj0.size();i++) {

                    System.out.println(pObj0.get(i).get("title")+"is at "+pObj0.get(i).get("time"));

                }
                for(int i=0;i<pObj0.size();i++) {
                mItems.add(new CardInfo(pObj0.get(i).getString("title").toString(),pObj0.get(i).getString("stage").toString(),pObj0.get(i).getString("category").toString(),pObj0.get(i).getString("time").toString(),pObj0.get(i).getString("venue").toString(),getThumbnail(pObj0.get(i).getString("title").toString())));
                }




            }
            if (num == 2) {

                mItems = new ArrayList<CardInfo>();
               // List <ParseObject> pObj0= query1.find();


                for(int i=0;i<pObj1.size();i++) {

                    System.out.println(pObj1.get(i).get("title")+"is at "+pObj1.get(i).get("time"));

                }

                for(int i=0;i<pObj1.size();i++) {
                    mItems.add(new CardInfo(pObj1.get(i).getString("title").toString(), pObj1.get(i).getString("stage").toString(), pObj1.get(i).getString("category").toString(), pObj1.get(i).getString("time").toString(), pObj1.get(i).getString("venue").toString(), getThumbnail(pObj1.get(i).getString("title").toString())));
                }


            }
            if (num == 3) {


               // List <ParseObject> pObj0= query2.find();
                mItems = new ArrayList<CardInfo>();

                for(int i=0;i<pObj2.size();i++) {

                    System.out.println(pObj2.get(i).get("title") + "is at " + pObj2.get(i).get("time"));

                }

                /*for(int i=0;i<pObj1.size();i++) {
                    mItems.add(new CardInfo(pObj1.get(i).getString("title").toString(), pObj1.get(i).getString("stage").toString(), pObj1.get(i).getString("category").toString(), pObj1.get(i).getString("time").toString(), pObj1.get(i).getString("venue").toString(), getThumbnail(pObj1.get(i).getString("title").toString())));
                }*/


            }




            else {


               // List <ParseObject> pObj0= query3.find();
                mItems = new ArrayList<CardInfo>();

                for(int i=0;i<pObj3.size();i++) {

                    System.out.println(pObj3.get(i).get("title") + "is at " + pObj3.get(i).get("time"));

                }

                /*for(int i=0;i<pObj1.size();i++) {
                    mItems.add(new CardInfo(pObj1.get(i).getString("title").toString(), pObj1.get(i).getString("stage").toString(), pObj1.get(i).getString("category").toString(), pObj1.get(i).getString("time").toString(), pObj1.get(i).getString("venue").toString(), getThumbnail(pObj1.get(i).getString("title").toString())));
                }*/


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
        viewHolder.tvDescard.setText(card.getStage());
        viewHolder.imgThumbnail.setImageResource(card.getThumbnail());
        viewHolder.tvCategory.setText(card.getType());
        viewHolder.tvVenue.setText(card.getVenue());
        viewHolder.tvTime.setText(card.getTime());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder{

        public ImageView imgThumbnail;
        public TextView tvcard;
        public TextView tvDescard;
        public TextView tvCategory;
        public TextView tvVenue;
        public TextView tvTime;


        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView)itemView.findViewById(R.id.img_thumbnail);
            tvcard = (TextView)itemView.findViewById(R.id.eventNameTimeline);
            tvDescard = (TextView)itemView.findViewById(R.id.categoryTimeline);
            tvCategory = (TextView)itemView.findViewById(R.id.categoryTimeline);
            tvVenue = (TextView)itemView.findViewById(R.id.venueTimeline);
            tvTime = (TextView)itemView.findViewById(R.id.timeTimeline);

        }
    }


    public void buildParObj(String objId,int imgId){

        CardInfo card = new CardInfo("","","","","",9);
        try {
            p0 = query.get(objId);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        card.setName(p0.getString("title").toString());
        card.setStage(p0.getString("category").toString());
        card.setThumbnail(imgId);
        //pObj0.add(card);
    }


    public int getThumbnail(String eventName){
        switch (eventName){
            case "Alaap" : return (R.drawable.alaaptt);
                 

            case "Artathon" : return (R.drawable.artathontt);
                 

            case "Contention" : return (R.drawable.contentiontt);
                 

            case "Dhinchak" : return (R.drawable.dhinchaktt);
                 


            case "Fash-P" : return (R.drawable.fashptt);
                 



            case "Indian Rock" : return (R.drawable.indinarocktt);
                 


            case "JAM" : return (R.drawable.justaminutett);
                 


            case "Juke Box" : return (R.drawable.jukeboxtt);
                 


            case "Jumelè" : return (R.drawable.jumelett);
                 


            case "Lex Omnia" : return (R.drawable.lexomniatt);
                 


            case "Mezzotint" : return (R.drawable.mezzotinttt);
                 


            case "Montage" : return (R.drawable.montagett);
                 


            case "Mr and Ms Waves" : return (R.drawable.mrmswavestt);
                 


            case "Natyanjali" : return (R.drawable.natyanjalitt);
                 


            case "Nukkad Natak" : return (R.drawable.nukkadnataktt);
                 


            case "Panorama" : return (R.drawable.panaromatt);
                 


            case "Portraiture" : return (R.drawable.portraiturett);
                 


            case "Rangmanch" : return (R.drawable.rangmanchtt);
                 


            case "Ratatouille" : return (R.drawable.ratattouillett);
                 


            case "Reverse Flash" : return (R.drawable.reverseflashtt);
                 


            case "Searock" : return (R.drawable.searocktt);
                 


            case "Show Me The Funny" : return (R.drawable.showmethefunnytt);
                 


            case "Shutter Island" : return (R.drawable.shuttertt);
                 


            case "Silence Of The Amps" : return (R.drawable.silenceoftheampstt);
                 


            case "Sizzle" : return (R.drawable.sizzlett);
                 


            case "Skime" : return (R.drawable.skimett);
                 


            case "Solonote" : return (R.drawable.solonotett);
                 


            case "Entertainment Quiz" : return (R.drawable.entertainmenttt);
                 


            case "The Lonewolf" : return (R.drawable.lonewolftt);
                 


            case "The Vices Quiz" : return (R.drawable.vicequiztt);
                 


            case "Rubik's Challenge" : return (R.drawable.rubikstt);
                 


            case "Time Lapse" : return (R.drawable.timelapsett);
                 


            case "Wallstreet Fête" : return (R.drawable.wallstreetfetett);
                 


            case "Waves Open Quiz" : return (R.drawable.wavesopentt);
                 


            case "Waves Poetry Slam" : return (R.drawable.poetryslamtt);
                 


            case "Word Games" : return (R.drawable.wordgamestt);
                 
            
            default: return (R.drawable.waves);

        }}



}

