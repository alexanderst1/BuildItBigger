package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.alexanderst.androidjokes.JokesActivity;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

interface JokeRetrievedCallback {
    void onJokeRetrieved(String joke);
}

public class MainActivity extends AppCompatActivity implements JokeRetrievedCallback {

    InterstitialAd mInterstitialAd;
    // These 2 variables below are used for synchronization between calls to function
    // onJokeRetrieved(...) and onAdClosed(). The functions are both called on UI thread so
    // there is no risk of concurrent access to these 2 variables from different threads
    // and no protection from concurrent access is required
    String mJoke;
    boolean mShowingAd;

    public void onJokeRetrieved(String joke) {
        if (mShowingAd) {
            //Ad is still shown, let's store joke text so that the joke
            //can be shown as soon as the ad is closed by the user
            mJoke = joke;
        } else {
            //Ad is already closed (or was never shown) and joke can be shown right now
            mJoke = null;
            showJoke(joke);
        }
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

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                mShowingAd = false;
                //If this is not null, then joke is already retrieved
                //and can be shown now as the ad was just closed
                if (mJoke != null)
                    showJoke(mJoke);
                //If mJoke is null, then joke is not yet retrieved and will be
                //shown by method onJokeRetrieved(String joke) when it gets retrieved
            }
        });

        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mInterstitialAd.loadAd(adRequest);
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
        mJoke = null;

        new EndpointsAsyncTask(this).execute();

        //If ad is loaded show the ad while joke is being retrieved
        mShowingAd = mInterstitialAd.isLoaded();
        if (mShowingAd)
            mInterstitialAd.show();
    }


}
