package bits.mobileappclub.waves;

/**
 * Created by HP1 on 03-Oct-15.
 */

import android.animation.ValueAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class EventDescriptionRecyclerViewAdapter extends RecyclerView
        .Adapter<EventDescriptionRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "EventDescriptionRecyclerViewAdapter";
    private EventDetails mDataset;
    private static MyClickListener myClickListener;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView cardViewHeader;
       TextView cardViewContent;
        private int originalHeight = 0;
        private boolean mIsViewExpanded = false;


        public DataObjectHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardViewHeader = (TextView) itemView.findViewById(R.id.descriptionHeader);
            cardViewContent = (TextView) itemView.findViewById(R.id.descriptionContent);
            Log.i(LOG_TAG, "Adding Listener");
            if (mIsViewExpanded == false) {
                // Set Views to View.GONE and .setEnabled(false)
                cardViewContent.setVisibility(View.GONE);
                cardViewContent.setEnabled(false);
            }
            itemView.setOnClickListener(this);
        }



            @Override
            public void onClick(final View view) {
                // If the originalHeight is 0 then find the height of the View being used
                // This would be the height of the cardview
                if (originalHeight == 0) {
                    originalHeight = view.getHeight();
                }

                // Declare a ValueAnimator object
                ValueAnimator valueAnimator;
                if (!mIsViewExpanded) {
                    cardViewContent.setVisibility(View.VISIBLE);
                    cardViewContent.setEnabled(true);
                    mIsViewExpanded = true;
                    valueAnimator = ValueAnimator.ofInt(originalHeight, originalHeight + (int) (originalHeight * 3.0)); // These values in this method can be changed to expand however much you like
                } else {
                    mIsViewExpanded = false;
                    valueAnimator = ValueAnimator.ofInt(originalHeight + (int) (originalHeight * 3.0), originalHeight);

                    Animation a = new AlphaAnimation(1.00f, 0.00f); // Fade out

                    a.setDuration(200);
                    // Set a listener to the animation and configure onAnimationEnd
                    a.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                           cardViewContent.setVisibility(View.INVISIBLE);
                            cardViewContent.setEnabled(false);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });

                    // Set the animation on the custom view
                   cardViewContent.startAnimation(a);
                }
                valueAnimator.setDuration(200);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    public void onAnimationUpdate(ValueAnimator animation) {
                        Integer value = (Integer) animation.getAnimatedValue();
                        view.getLayoutParams().height = value.intValue();
                        view.requestLayout();
                    }
                });


                valueAnimator.start();

            }
        }


    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public EventDescriptionRecyclerViewAdapter(EventDetails eventDetails) {

        mDataset = eventDetails;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event_description_card_view, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }



    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.cardViewHeader.setText(mDataset.header.get(position)
        );
        holder.cardViewContent.setText(mDataset.content.get(position));
    }


    @Override
    public int getItemCount() {
        return mDataset.getCardViewCount();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

}
