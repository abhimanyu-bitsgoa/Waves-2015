package bits.mobileappclub.waves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by HP1 on 03-Oct-15.
 */
public class LiveEventViewAdapter extends BaseAdapter {

    Context context;
    String[] eventName;
    String[] eventTime;
    String[] eventStage;
    int[] eventImageId;
    private static LayoutInflater inflater=null;
    public LiveEventViewAdapter(MainActivity mainActivity,String[] eventNameText,String[] eventTimeText,String[] eventStageText,int[] eventImageIdNumber)
    {
        eventName=eventNameText;
        eventTime=eventTimeText;
        eventStage=eventStageText;
        eventImageId=eventImageIdNumber;
        context=mainActivity;
        inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);



    }

    @Override
    public int getCount() {
        return eventName.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder=new Holder();
        View rowView;
        rowView=inflater.inflate(R.layout.live_view_layout,null);
        holder.eventImage=(ImageView)rowView.findViewById(R.id.eventImage);
        holder.eventNameTextView=(TextView)rowView.findViewById(R.id.eventName);
        holder.eventStageTextView=(TextView)rowView.findViewById(R.id.eventStage);
        holder.eventTimeTextView=(TextView)rowView.findViewById(R.id.eventTime);
        holder.eventImage.setImageResource(R.drawable.img1);
        holder.eventNameTextView.setText(eventName[position]);
        holder.eventTimeTextView.setText(eventTime[position]);
        holder.eventStageTextView.setText(eventStage[position]);
        holder.eventImage.setImageResource(eventImageId[position]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked " + eventName[position], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }
    public  class Holder
    {
        TextView eventNameTextView;
        ImageView eventImage;
        TextView eventTimeTextView;
        TextView eventStageTextView;


    }
}
