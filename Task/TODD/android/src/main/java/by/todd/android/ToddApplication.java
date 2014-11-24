package by.todd.android;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Application;

/**
 * Created by sergey on 15.11.2014.
 */
public class ToddApplication extends Application {

    private static AccountManager sAccountManager;
    private static Account[] sAccounts;
    private static int sAccountId;

    @Override
    public void onCreate() {
        super.onCreate();
        sAccountManager = AccountManager.get(getApplicationContext());
        sAccounts = sAccountManager.getAccountsByType("com.google");
    }

    public static AccountManager getAccountManager() {
        return sAccountManager;
    }

    public static Account[] getAccounts() {
        return sAccounts;
    }

    public static Account getAccount() {
        return sAccounts[ToddApplication.sAccountId];
    }

    public static void setAccountId(int id) {
        sAccountId = id;
    }
}
