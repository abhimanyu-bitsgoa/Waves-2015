package bits.mobileappclub.waves;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ProgressDialog dialog=ProgressDialog.show(GalleryActivity.this,"Loading","Please wait");
        ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("Gallery");
        try {
            List<ParseObject> gallery=parseQuery.find();
            int[] imageResourceId = { R.drawable.waves,R.drawable.salim, R.drawable.tvf, R.drawable.aking};
            Log.d("Size of gallery",String.valueOf(gallery.size()));
            mAdapter=new RVAdapter(gallery,GalleryActivity.this);
            dialog.dismiss();
        } catch (ParseException e) {
            e.printStackTrace();
            dialog.dismiss();
            //TODO make a slack to show internet not available
        }
        RecyclerView rv = (RecyclerView)findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        rv.setAdapter(mAdapter);

    }

}
