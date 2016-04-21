package com.pass.ann.autorization;

import android.app.Application;
import com.backendless.Backendless;

/**
 * Created by ivan.k on 15.04.2016.
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Backendless.initApp( this, "32334757-C59D-8E07-FFA9-7B5C79ED1200", "D43AE1E2-D82A-F815-FF7A-81AB28D87E00",
                "v1" );
    }
}
