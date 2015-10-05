package bits.mobileappclub.waves;

/**
 * Created by HP1 on 03-Oct-15.
 */

        import android.content.Intent;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import java.util.ArrayList;

public class EventCategoryRecyclerViewAdapter extends RecyclerView
        .Adapter<EventCategoryRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "EventCategoryRecyclerViewAdapter";
    private ArrayList<EventDataObjectCardMainActivity> mDataset;


    public static class DataObjectHolder extends RecyclerView.ViewHolder
            {
        TextView eventName;
        ImageView eventImageThumbnail;

        public DataObjectHolder(View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventNameCardView);
            eventImageThumbnail = (ImageView) itemView.findViewById(R.id.imgThumbnail_cardView);
            Log.i(LOG_TAG, "Adding Listener");

        }


    }



    public EventCategoryRecyclerViewAdapter(ArrayList<EventDataObjectCardMainActivity> myDataset) {

        mDataset = myDataset;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_category_list_card_view, parent, false);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(v.getContext(),EventDescription.class);
                intent.putExtra("eventName", ((TextView) v.findViewById(R.id.eventNameCardView)).getText().toString());
                v.getContext().startActivity(intent);
            }
        });
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.eventName.setText(mDataset.get(position).getEventName());
        holder.eventImageThumbnail.setImageResource(mDataset.get(position).getImageID());
    }

    public void addItem(EventDataObjectCardMainActivity dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }



}
