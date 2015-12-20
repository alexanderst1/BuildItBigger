package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    // Test case will fail if 'backend endpoints' server is not started at http://localhost:8080.
    // Please start 'backend' prior to running unit test by selecting the 'backend' item in the
    // 'Select Run/Debug Configuration' drop down and hitting the 'Run' button on Android Studio
    // toolbar. Alternatively you can run task 'tieTogether' in [project_root]/build.gradle'
    // which will start 'backend', run unit tests, stop 'backend'.
    public void testEndpointsAsyncTask() {
        EndpointsAsyncTask task = new EndpointsAsyncTask(null);
        task.execute();
        assertTrue(task.waitForCompletion());
        String result = task.getResult();
        assertTrue(result != null && result.length() > 0 && !result.contains("failed to connect"));
    }
}