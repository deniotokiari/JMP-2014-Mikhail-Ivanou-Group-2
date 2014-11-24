package by.todd.android.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Arrays;

import by.todd.android.R;
import by.todd.android.ToddApplication;
import by.todd.android.app.adapter.AccountAdapter;

/**
 * Created by sergey on 23.11.2014.
 */
public class ActivityAccounts extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        AccountAdapter adapter = new AccountAdapter(Arrays.asList(ToddApplication.getAccounts()), R.layout.item_account, this, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToddApplication.setAccountId(recyclerView.getChildPosition(v));
                startActivity(new Intent(getApplicationContext(), ActivityHome.class));
            }
        });
        recyclerView.setAdapter(adapter);
    }

}
