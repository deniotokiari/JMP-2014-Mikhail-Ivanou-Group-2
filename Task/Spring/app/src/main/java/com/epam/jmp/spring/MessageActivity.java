package com.epam.jmp.spring;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
                final TextView tv = ((TextView) findViewById(R.id.response));
                tv.setVisibility(View.GONE);
                new HttpRequestTask().execute("http://10.0.2.2:8080/messages/" + id, Message.class, new HttpRequestTask.Callback<Message>() {

                    @Override
                    public void sucess(Message resp) {
                        tv.setVisibility(View.VISIBLE);
                        if (resp != null) {
                            StringBuilder out = new StringBuilder();
                            out.append("id: ");
                            out.append(resp.getId());
                            out.append("\ncontent: ");
                            out.append(resp.getContent());
                            tv.setText(out.toString());
                        }else{
                            tv.setText("плохо...");
                        }
                    }
                });
            }
        });
    }
}
