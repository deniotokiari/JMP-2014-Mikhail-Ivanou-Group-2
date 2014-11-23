package by.todd.android.app;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import by.todd.android.ToddApplication;
import by.todd.android.widget.RippleButton;
import by.todd.R;


public class ActivitySignIn extends ActionBarActivity {

    private Context mContext;
    private Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        if (ToddApplication.getAccount() != null) {
            AccountManager manager = (AccountManager) getSystemService(ACCOUNT_SERVICE);
            Account[] list = manager.getAccounts();
            String google = null;

            for (Account account : list) {
                if (account.type.equalsIgnoreCase("com.google")) {
                    google = account.name;
                    break;
                }
            }
            startActivity(new Intent(mContext, ActivityHome.class));
        } else {
            final RippleButton bSignIn = (RippleButton) findViewById(R.id.signIn);
            mContext = this;
            mHandler = new Handler(Looper.getMainLooper());
            bSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(mContext, ActivityHome.class));
                        }
                    }, RippleButton.DURATION);
                }
            });
        }
    }
}
