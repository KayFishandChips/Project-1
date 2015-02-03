package com.ajkayfishgmail.project_1;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.textservice.TextInfo;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends ActionBarActivity {
    Button rngBtn;
    Button qotdBtn;
    Button submitBtn;
    TextView points;
    TextView textLine;
    private final static  String URL1 =
            "http://api.theysaidso.com/qod";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        qotdBtn = (Button)findViewById(R.id.qotdBtn);
        rngBtn = (Button) findViewById(R.id.rngBtn);
        submitBtn = (Button)findViewById(R.id.submitBtn);
        points = (TextView)findViewById(R.id.points);
        textLine = (TextView)findViewById(R.id.textLine);

        qotdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                QotdTask t = new QotdTask();
                t.execute();
            }
        });
        rngBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //put rng code here
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //submit code here
            }
        });
    }

    private class QotdTask extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params)
        {
            HttpClient client = new DefaultHttpClient();
            HttpGet hget = new HttpGet(URL1);

            try
            {
                HttpResponse resp = client.execute(hget);
                InputStream stream = resp.getEntity().getContent();
                char[] buffer = new char[1024];
                InputStreamReader reader = new InputStreamReader(stream);
                int len;
                StringBuffer sb = new StringBuffer();
                len = reader.read(buffer,0,1024);

                while(len != -1)
                {
                    sb.append(buffer,0,1024);
                }
                return sb.toString();
            }
            catch (IOException e)
            {
                Log.e("OOPS", "There was an error " + e.getMessage() );
                return null;
            }
        }

        @Override
        protected  void onPostExecute(String sb)
        {

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
