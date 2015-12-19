package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;

import com.example.alexanderst.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Alexander on 12/10/2015.
 */
class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {
    private static MyApi myApiService = null;
    private Context mContext;

    //used by unit-test
    private CountDownLatch mLatchJokeRetrieved;
    //used by unit-test
    private String mResult;
    //used by unit-test
    private boolean isTestMode() {
        return mContext == null;
    }

    public EndpointsAsyncTask(Context context) {
        mContext = context;
        if (isTestMode())
            mLatchJokeRetrieved = new CountDownLatch(1);
    }

    //used by unit-test
    public boolean waitForCompletion() {
        if (mLatchJokeRetrieved == null)
            return false;
        try {
            mLatchJokeRetrieved.await();
        } catch (InterruptedException e) {
            return false;
        }
        return true;
    }

    //used by unit-test
    public String getResult() {
        return mResult;
    }

    @Override
    protected String doInBackground(Void... params) {
        if(myApiService == null) {// Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null);

            if (mContext != null) {
                String appId = mContext.getString(R.string.backend_app_id);
                builder = builder.setRootUrl("https://" + appId + ".appspot.com/_ah/api/");
            } else {
                // options for running against local devappserver
                // - 10.0.2.2 is localhost's IP address in Android emulator
                // - turn off compression when running against local devappserver
                builder = builder.setRootUrl("http://10.0.2.2:8080/_ah/api/")
                        .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                            @Override
                            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
                                abstractGoogleClientRequest.setDisableGZipContent(true);
                            }
                        });
            }
            myApiService = builder.build();
        }

        mResult = null;
        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String result) {
        mResult = result;

        // If somebody is waiting on the latch (unit test),
        // let them know that joke is available
        if (mLatchJokeRetrieved != null)
            mLatchJokeRetrieved.countDown();

        // If we have a context (calling activity), pass the result to it
        if (mContext != null)
            ((JokeRetrievedCallback)mContext).onJokeRetrieved(result);
    }
}
