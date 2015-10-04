package bits.mobileappclub.waves;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by HP1 on 03-Oct-15.
 */
public class LiveViewPagerAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Activity activity;
    String[] eventName;
    String[] eventTime;
    String[] eventStage;
    int[] eventImageID;

    public LiveViewPagerAdapter(Context context, String[] name, String[] time, String[] stage, int[] imgArr) {
        this.context = context;
        eventName = name;
        eventTime = time;
        eventStage = stage;
        eventImageID = imgArr;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        // Declare Variables
        TextView eventNameTextView;
        TextView eventTimeTextView;
        TextView eventStageTextView;
        ImageView eventImageView;

        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.live_view_layout, container,
                false);

        // Locate the TextViews in viewpager_item.xml
        eventNameTextView = (TextView) itemView.findViewById(R.id.eventName);
        eventTimeTextView = (TextView) itemView.findViewById(R.id.eventTime);
        eventStageTextView = (TextView) itemView.findViewById(R.id.eventStage);

        // Capture position and set to the TextViews
        eventNameTextView.setText(eventName[position]);
        eventTimeTextView.setText(eventTime[position]);
        eventStageTextView.setText(eventStage[position]);

        // Locate the ImageView in viewpager_item.xml
        eventImageView = (ImageView) itemView.findViewById(R.id.eventImage);
        // Capture position and set to the ImageView
        eventImageView.setImageResource(eventImageID[position]);

        // Add viewpager_item.xml to ViewPager
        ((ViewPager) container).addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove viewpager_item.xml from ViewPager
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

    @Override
    public int getCount() {
        return eventName.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }
}



