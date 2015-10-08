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
            ParseQuery<ParseObject> query0 = ParseQuery.getQuery("Events").whereMatches("date","DAY 0");
            ParseQuery<ParseObject> query1 = ParseQuery.getQuery("Events").whereMatches("dateSemi","DAY 0");
            ParseQuery<ParseObject> query2 = ParseQuery.getQuery("Events").whereMatches("dateFinals","DAY 0");
            query0.fromLocalDatastore();
            query1.fromLocalDatastore();
            query2.fromLocalDatastore();
            List<ParseObject> parObjList0=query0.find();
            List<ParseObject> parObjList1=query1.find();
            List<ParseObject> parObjList2=query2.find();

            parObjList0.addAll(parObjList1);
            parObjList0.addAll(parObjList2);



            if (num == 1) {

                parObjList0=parseSort(parObjList0);

                for (int i=0;i<(parObjList0.size());i++)
                    System.out.println(parObjList0.get(i).get("title")+" has time  "+parObjList0.get(i).get("timings")+" "+i);



                /*for(ParseObject p:parObjList){
                    p
                }*/


               /* buildParObj("JCioxw0ky1",R.drawable.music1);
                buildParObj("jVZgssmdIC",R.drawable.quiz1);
                buildParObj("CRzAO5anix",R.drawable.dance1);*/

               //

                /*
                // Single block of parse card starts
                card = new CardInfo();
                try {
                    p0 = query.get("CRzAO5anix");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                card.setName(p0.getString("title").toString());
                card.setDes(p0.getString("category").toString());
                card.setThumbnail(R.drawable.literary1);
                mItems.add(card);
                //Single block of parse card ends


                // Single block of parse card starts
                card = new CardInfo();
                try {
                    p0 = query.get("jVZgssmdIC");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                card.setName(p0.getString("title").toString());
                card.setDes(p0.getString("category").toString());
                card.setThumbnail(R.drawable.dance1);
                mItems.add(card);
                //Single block of parse card ends

                // Single block of parse card starts
                card = new CardInfo();
                try {
                    p0 = query.get("JCioxw0ky1");

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                card.setName(p0.getString("title").toString());
                card.setDes(p0.getString("category").toString());
                card.setThumbnail(R.drawable.dance1);
                mItems.add(card);
                //Single block of parse card ends
*/

            }
            if (num == 2) {




            } else {


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

    public int compareParse(ParseObject p1,ParseObject p2){
        String time1=null,time2=null;
        if(p1.getString("date").equals("DAY 0"))
            time1 = "timings";



        if(p1.getString("dateSemi").equals("DAY 0"))
            time1="timeSemi";

        if(p1.getString("dateFinals").equals("DAY 0"))
            time1="timeFinals";

        //Objects 2
        if(p2.getString("date").equals("DAY 0"))
            time2="timings";

        if(p2.getString("dateSemi").equals("DAY 0"))
            time2="timeSemi";

        if(p2.getString("dateFinals").equals("DAY 0"))
            time2="timeFinals";

        /*
        String s1 = "ab";
        String s2 = "ab";
        String s3 = "qb";
        s1.compareTo(s2); // is 0
        s1.compareTo(s3); // is -16
        s3.compareTo(s1); // is 16*/

        if(p1.getString(time1).compareTo(p2.getString(time2))<0)
            return -1;
        else
            return 1;

    }

    public List parseSort(List<ParseObject> arr ){

        for (int i=0;i<(arr.size());i++)
            System.out.println("Before Sort "+arr.get(i).get("title")+" has time  "+arr.get(i).get("timings")+" "+i);

        ParseObject parseTemp=null;
        String title=null;
        for(int i=0;(i<arr.size()-1);i++){
            for(int j=(arr.size()-1);j>i;j--){

                System.out.println("This is return statement"+(compareParse(arr.get(j), arr.get(j - 1))));
                if(compareParse(arr.get(j),arr.get(j-1))==-1);

                title=arr.get(j).get("title").toString();
                ParseQuery<ParseObject> query7 = ParseQuery.getQuery("Events").whereMatches("title",title);
                query7.fromLocalDatastore();

                try {
                    parseTemp=query7.getFirst();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                arr.set(j, arr.get(j-1));
                arr.set(j-1,parseTemp);

            }

            for (int m=0;m<(arr.size());m++)
                System.out.println("Status "+arr.get(i).get("title")+" has time  "+arr.get(i).get("timings")+" "+m);
        }

        for (int i=0;i<(arr.size());i++)
            System.out.println("After Sort "+arr.get(i).get("title")+" has time  "+arr.get(i).get("timings")+" "+i);

        return arr;
    }


}

