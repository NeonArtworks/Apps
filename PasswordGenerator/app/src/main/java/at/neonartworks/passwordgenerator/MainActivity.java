package at.neonartworks.passwordgenerator;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.SecureRandom;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText txLength;
    private EditText txPassword;
    private PasswordGenerator generator;
    private EditText txPasswordList;
    private EditText txName;
    private FileWriter outputStream;
    private BufferedReader inputStream;
    final String fileName = "password_sFile";
    private File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Initialize content
        txLength = (android.widget.EditText) findViewById(R.id._length);
        txPassword = (android.widget.EditText) findViewById(R.id._password);
        txName = (android.widget.EditText) findViewById(R.id._name);
        txPasswordList = (android.widget.EditText) findViewById(R.id._passwordList);
        file = new File(getApplicationContext().getFilesDir(), fileName);
        //Copy Button
        FloatingActionButton load = (FloatingActionButton) findViewById(R.id._update);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    inputStream = new BufferedReader(new FileReader(file));
                    String line;

                    StringBuilder sb = new StringBuilder();

                    while ((line = inputStream.readLine()) != null) {
                        String[] tmp = line.split(";");

                        sb.append(tmp[0] + ": " + tmp[1] + System.lineSeparator());
                    }
                    txPasswordList.setText(sb.toString());

                } catch (Exception e) {

                }


            }
        });

        //Copy Button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Password", generator.getPassword());
                clipboard.setPrimaryClip(clip);

                Snackbar.make(view, "Password Copied", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

    }

    //Generate Password
    public void onGenerate(View view) {
        generator = new PasswordGenerator(txLength.getText().toString());
        txPassword.setText(generator.generate());
    }

    //Save Password into File
    public void onSave(View view) {
        String name = txName.getText().toString();
        String pw = txPassword.getText().toString();


        StringBuilder sb = new StringBuilder();
        sb.append(name + ";" + pw + System.lineSeparator());

        try {
            outputStream = new FileWriter(file, true);
            outputStream.write(sb.toString());
            outputStream.close();
        } catch (Exception e) {

        }
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
