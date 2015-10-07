package bits.mobileappclub.waves;

/**
 * Created by Aronzxxx on 03-10-2015.
 */
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;


public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {






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
            query.fromLocalDatastore();

            if (num == 1) {
                ArrayList<ParseObject> pA1= new ArrayList<>();
                pA1.add(query.get("CRzAO5anix"));
                pA1.add(query.get("jVZgssmdIC"));
                pA1.add(query.get("JCioxw0ky1"));

                TreeMap<String,ParseObject> pT1=new TreeMap<String,ParseObject>();

                pT1.put(pA1.get(0).get("timings").toString(), pA1.get(0));
                pT1.put(pA1.get(1).get("timings").toString(), pA1.get(1));
                pT1.put(pA1.get(2).get("timings").toString(),pA1.get(2));


                System.out.println(pT1.firstKey());
                System.out.println(pT1.values().toArray()[1]);
                System.out.println(pT1.values().toArray()[2]);


                for(int i=0;i<3;i++){
                    card = new CardInfo();
                    card.setName(pT1.get(i).getString("title").toString());
                    card.setDes(pT1.get(i).getString("category").toString());
                    card.setThumbnail(R.drawable.literary1);
                    mItems.add(card);

                }


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


                // Single block of parse card starts
                card = new CardInfo();
                try {
                    p0 = query.get("f9YsW0pHLY");
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
                    p0 = query.get("clc3dZtIlf");
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
                    p0 = query.get("YXXhs5wi7W");
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

                // Single block of parse card starts
                card = new CardInfo();
                try {
                    p0 = query.get("M32VpiErz8");
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
                    p0 = query.get("l03K5Ji0al");
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
                    p0 = query.get("Zxmaxm92Hz");
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
                    p0 = query.get("xYUsPO9qCY");
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
                    p0 = query.get("BIksvXkmCz");
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
                    p0 = query.get("IlMeKRNgrn");
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
                    p0 = query.get("bDopmhS1c1");
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
                    p0 = query.get("D9ko0Yy8je");
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
                    p0 = query.get("h08tYWjIwm");
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
                    p0 = query.get("m17N1Sgdds");
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
                    p0 = query.get("inHUhSmJFM");
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
                    p0 = query.get("KcGLLKnPAi");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                card.setName(p0.getString("title").toString());
                card.setDes(p0.getString("category").toString());
                card.setThumbnail(R.drawable.literary1);
                mItems.add(card);
                //Single block of parse card ends


            } else {

                card = new CardInfo();
                try {
                    p0 = query.get("KcGLLKnPAi");
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                card.setName(p0.getString("title").toString());
                card.setDes(p0.getString("category").toString());
                card.setThumbnail(R.drawable.specials1);
                mItems.add(card);
                //Single block of parse card ends
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





}