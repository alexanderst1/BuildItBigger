package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.alexanderst.androidjokes.JokesActivity;

interface JokeRetrievedCallback {
    void onJokeRetrieved(String joke);
}

public class MainActivity extends AppCompatActivity implements JokeRetrievedCallback {

    private ProgressBar mProgressBar;

    public void onJokeRetrieved(String joke) {
        mProgressBar.setVisibility(View.GONE);
        showJoke(joke);
    }

    private void showJoke(String joke) {
        Intent intent = new Intent(this, JokesActivity.class);
        intent.putExtra(JokesActivity.JOKES_KEY, joke);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar=(ProgressBar)findViewById(R.id.progressBar);
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jokes, menu);
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

    public void tellJoke(View view){
        mProgressBar.setVisibility(View.VISIBLE);
        new EndpointsAsyncTask(this).execute();
    }
}
