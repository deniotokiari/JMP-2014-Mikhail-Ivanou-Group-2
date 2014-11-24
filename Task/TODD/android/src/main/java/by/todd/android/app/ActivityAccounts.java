package by.todd.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import by.todd.android.R;
import by.todd.android.ToddApplication;

/**
 * Created by sergey on 23.11.2014.
 */
public class ActivityAccounts extends Activity {
    private EditText mEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmail = (EditText) findViewById(R.id.email);
        mEmail.setText(ToddApplication.getOwner());

        findViewById(R.id.enter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToddApplication.setOwner(mEmail.getEditableText().toString());
                Intent intent = new Intent(getApplicationContext(), ActivityHome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

}
