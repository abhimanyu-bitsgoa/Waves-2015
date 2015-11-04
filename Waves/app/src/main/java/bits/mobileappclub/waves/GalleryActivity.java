package bits.mobileappclub.waves;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id== android.R.id.home) {
            onBackPressed();
            return  true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_map) {
            Intent intent=new Intent(GalleryActivity.this,MapActivity.class);

            startActivity(intent);
            return true;
        }
        if (id== R.id.action_about) {
            Intent intent = new Intent(GalleryActivity.this,AboutActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            this.startActivity(intent);
            return  true;

        }


        return super.onOptionsItemSelected(item);
    }

}
