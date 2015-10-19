package edu.usf.myweb.hpate.hannahresume;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class Resume extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        TextView txtName = (TextView) findViewById(R.id.name);

        txtName.setText("Hannah Pate");


        Button button = (Button) findViewById(R.id.linkedin);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri url = Uri.parse("https://www.linkedin.com/in/thehannah");
                Intent goToLinkedin = new Intent(Intent.ACTION_VIEW, url);
                startActivity(goToLinkedin);
            }
        });


        SharedPreferences sharedpreferences =
                getSharedPreferences("myPreferences", Context.MODE_PRIVATE);

        Boolean viewed = sharedpreferences.getBoolean("viewed", false);

        if(!viewed) {
            Toast.makeText(getApplicationContext(),
                    "Welcome to my resume app.", Toast.LENGTH_LONG).show();

            SharedPreferences.Editor edit = sharedpreferences.edit();
            edit.putBoolean("viewed", true);
        }

        addActivities();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_email) {
            sendEmail();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void sendEmail() {

        /* Create email intent with email address attached */
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, "hpate@mail.usf.edu");
        /* --- */

        /* Try to start email intent. If email not found, alert user */
        try {
            startActivity(Intent.createChooser(emailIntent, "Contact Me"));
        }
        catch (Exception ex) {
            Toast.makeText(getApplicationContext(), "An Error Occured",
                    Toast.LENGTH_LONG).show();
        }
        /* --- */
    }

    private void addActivities() {

        String[] activites = getResources().getStringArray(R.array.activities);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, activites);

        ListView lstActivities = (ListView) findViewById(R.id.activitiesList);
        lstActivities.setAdapter(itemsAdapter);

    }

}



