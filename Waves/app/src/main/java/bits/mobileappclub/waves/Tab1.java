package bits.mobileappclub.waves;

/**
 * Created by Aronzxxx on 03-10-2015.
 */
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edwin on 15/02/2015.
 */


public class Tab1 extends Fragment {

    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;



    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab_1,container,false);

        mRecyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new CardAdapter(getCardInfo());


        mRecyclerView.setAdapter(mAdapter);

        return v;
    }
    private ArrayList<CardInfo> getCardInfo() {
        List<ParseObject> pObj0;

        ArrayList  results = new ArrayList<CardInfo>();
        try {


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

            pObj0 = query0.find();





                    for(int i=0;i<pObj0.size();i++) {



                        results.add(new CardInfo(pObj0.get(i).getString("title").toString(),pObj0.get(i).getString("stage").toString(),pObj0.get(i).getString("category").toString(),pObj0.get(i).getString("time").toString(),pObj0.get(i).getString("venue").toString(),getThumbnail(pObj0.get(i).getString("title").toString())));
                    }




        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return results;
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

            case "Mocktalk Show" : return (R.drawable.mocktalktt);


            default: return (R.drawable.wavestt);

        }}
}
