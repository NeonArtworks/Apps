package at.neonartworks.ytdownloader;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private String[] arraySpinner;
    private Context ct;


    protected boolean shouldAskPermissions() {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions() {
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.INTERNET"
        };
        int requestCode = 200;
        requestPermissions(permissions, requestCode);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        ct = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (shouldAskPermissions()) {
            askPermissions();
        }

        //Add Request
        MobileAds.initialize(getApplicationContext(), "ca-app-pub-8832628756576966~6207374739");
        AdView adView1 = (AdView) findViewById(R.id.adView);
        AdView adView2 = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView1.loadAd(adRequest);
        adView2.loadAd(adRequest);


        //Load Spinner Entities
        this.arraySpinner = new String[]{
                "AUDIO", "VIDEO (360p)"
        };
        Spinner s = (Spinner) findViewById(R.id._format);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        s.setAdapter(adapter);

        //Download Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()

                               {

                                   @Override
                                   public void onClick(View view) {

                                       final EditText LinkURL = (EditText) findViewById(R.id._link);
                                       final Spinner spn = (Spinner) findViewById(R.id._format);
                                       //System.out.println("Ckicked");
                                       Log.i("Main", "Clicked Download Button!");
                                       String l = LinkURL.getText().toString();
                                       if (l == null || l.equals("") || l.equals(" ")){
                                           Snackbar.make(view, "No Video Link!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                           return;
                                       }
                                       Snackbar.make(view, "Download Started!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                                       new Downloader(ct, spn.getSelectedItem().toString()).execute(l);
                                   }
                               }
        );
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
