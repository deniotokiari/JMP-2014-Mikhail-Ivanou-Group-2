package com.epam.jmp.spring;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.epam.jmp.spring.model.Book;
import com.epam.jmp.spring.model.Message;

public class MessageActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.find).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ((EditText) findViewById(R.id.edit)).getText().toString();
                if (id == null || id.isEmpty()) {
                    return;
                }
                new HttpRequestTask().execute("http://10.0.2.2:8080/messages/" + id, Message.class, new HttpRequestTask.Callback() {

                    @Override
                    public void sucess(String resp) {
                        if (resp != null) {
                            StringBuilder out = new StringBuilder(resp);
                            ((TextView) findViewById(R.id.response)).setText(out.toString());
                        }
                    }
                });
            }
        });
    }
}
