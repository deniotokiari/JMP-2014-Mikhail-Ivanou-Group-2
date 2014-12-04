package com.epam.realm;

import android.app.Activity;
import android.os.Bundle;

import com.epam.realm.model.Employee;
import com.epam.realm.model.Personal;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProviderImpl impl = new ProviderImpl(getApplicationContext());
        impl.createEmployee("");
    }

}
