package bits.mobileappclub.waves;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
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
import android.widget.LinearLayout;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.squareup.picasso.Picasso;

import java.util.List;

public class GalleryActivity extends AppCompatActivity {
    private RecyclerView.Adapter mAdapter;
    RecyclerView rv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        rv = (RecyclerView)findViewById(R.id.rv);



    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        new AsyncCaller().execute();

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

    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        ProgressDialog pdLoading = new ProgressDialog(GalleryActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread
            pdLoading.setMessage("\tLoading (Internet required)");
            pdLoading.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                ParseQuery<ParseObject> parseQuery=ParseQuery.getQuery("Gallery");
                List<ParseObject> gallery=parseQuery.find();

                Log.d("Size of gallery",String.valueOf(gallery.size()));
                mAdapter=new RVAdapter(gallery,GalleryActivity.this);

            } catch (ParseException e) {
                final View coordinatorLayoutView = findViewById(R.id.snackbarPosition);

                Snackbar
                        .make(coordinatorLayoutView,"Please connect to the internet", Snackbar.LENGTH_LONG)

                        .show();
                e.printStackTrace();

                //TODO make a slack to show internet not available
            }

            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here


            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        addImages();
            //this method will be running on UI thread

            pdLoading.dismiss();
        }

    }
    public void addImages()
    {
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        if(mAdapter!=null){
        rv.setAdapter(mAdapter);}
        else{
            final View coordinatorLayoutView = findViewById(R.id.snackbarPosition);
            Snackbar
                    .make(coordinatorLayoutView,"Please connect to the internet", Snackbar.LENGTH_LONG)

                    .show();

        }

    }
}



