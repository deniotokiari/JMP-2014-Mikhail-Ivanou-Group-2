package by.todd.android.app;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import by.todd.android.R;
import by.todd.android.ToddApplication;
import by.todd.android.app.fragment.FragmentCreateItem;
import by.todd.android.app.fragment.FragmentHome;

public class ActivityHome extends ActionBarActivity {

    private String LOG_TAG = "ActivityHome";
    private static final int USER_PERMISSION = 989;

    private boolean mExpireToken = false;
    private DefaultHttpClient mHttpClient;
    private boolean mGotCookies = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHttpClient = new DefaultHttpClient();
        ToddApplication.getAccountManager().getAuthToken(ToddApplication.getAccount(), "ah", null, false, new OnTokenAcquired(), null);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, FragmentHome.newInstance());
        fragmentTransaction.commit();

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container, FragmentCreateItem.newInstance());
                fragmentTransaction.addToBackStack("ADD");
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            //if the user approved the use of the account make another request
            //for the auth token else display a message
            case USER_PERMISSION:
                if (resultCode == RESULT_OK) {
                    ToddApplication.getAccountManager().getAuthToken(ToddApplication.getAccount(), "ah", null, false, new OnTokenAcquired(), null);
                } else if (resultCode == RESULT_CANCELED) {
                    Toast.makeText(getBaseContext(), "Permission denied by User!",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }

    }

    public void get(Success success) {
        new MyAuthenticatedRequest(success, "get", null).execute("http://exemplary-terra-772.appspot.com/get");
    }

    public void post(JSONObject jo, Success success) {
        new MyAuthenticatedRequest(success, "post", jo).execute("http://exemplary-terra-772.appspot.com/add");
    }

    private class OnTokenAcquired implements AccountManagerCallback<Bundle> {

        @Override
        public void run(AccountManagerFuture<Bundle> result) {
            Bundle bundle;
            try {
                bundle = (Bundle) result.getResult();
                if (bundle.containsKey(AccountManager.KEY_INTENT)) {
                    Intent intent = bundle.getParcelable(AccountManager.KEY_INTENT);
                    intent.setFlags(intent.getFlags() & ~Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivityForResult(intent, USER_PERMISSION);
                } else {
                    String authToken = bundle.getString(AccountManager.KEY_AUTHTOKEN);
                    if (mExpireToken) {
                        ToddApplication.getAccountManager().invalidateAuthToken("ah", authToken);
                    } else {
                        new GetCookie().execute(authToken);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class GetCookie extends AsyncTask<String, Void, Boolean> {

        HttpParams params = mHttpClient.getParams();
        private HttpResponse response;

        protected Boolean doInBackground(String... tokens) {
            try {
                params.setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false);

                HttpGet httpGet = new HttpGet("http://exemplary-terra-772.appspot.com/_ah/login?continue=http://localhost/&auth=" + tokens[0]);
                response = mHttpClient.execute(httpGet);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();

                if (response.getStatusLine().getStatusCode() != 302) {
                    Log.v(LOG_TAG, "No cookie");
                    return false;
                }

                for (Cookie cookie : mHttpClient.getCookieStore().getCookies()) {
                    Log.v(LOG_TAG, cookie.getName());
                    if (cookie.getName().equals("ACSID") || cookie.getName().equals("SACSID")) {
                        Log.v(LOG_TAG, "Got cookie");
                        return true;
                    }
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
                cancel(true);
            } catch (IOException e) {
                e.printStackTrace();
                cancel(true);
            } catch (Exception e) {
                e.printStackTrace();
                cancel(true);
            } finally {
                params.setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, true);
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            Log.v(LOG_TAG, "Done cookie");
            mGotCookies = true;
        }
    }

    private class MyAuthenticatedRequest extends AsyncTask<String, Void, Boolean> {

        private final Success mSuccess;
        private final String mMethod;
        private final JSONObject mJsonObject;

        private HttpResponse response;
        private String content = null;

        public MyAuthenticatedRequest(Success success, String method, JSONObject jsonObject) {
            mSuccess = success;
            mMethod = method;
            mJsonObject = jsonObject;
        }

        protected Boolean doInBackground(String... urls) {
            try {
                if (mMethod.equals("get")) {
                    HttpGet httpGet = new HttpGet(urls[0]);
                    response = mHttpClient.execute(httpGet);
                    StatusLine statusLine = response.getStatusLine();
                    Log.v(LOG_TAG, statusLine.getReasonPhrase());
                    if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        response.getEntity().writeTo(out);
                        out.close();
                        content = out.toString();
                        return true;
                    }
                } else if (mMethod.equals("post")) {
                    HttpPost httpPost = new HttpPost(urls[0]);
                    httpPost.setEntity(new StringEntity(mJsonObject.toString()));
                    response = mHttpClient.execute(httpPost);
                    StatusLine statusLine = response.getStatusLine();
                    if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
                        ByteArrayOutputStream out = new ByteArrayOutputStream();
                        response.getEntity().writeTo(out);
                        out.close();
                        content = out.toString();
                        return true;
                    }
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
                cancel(true);
            } catch (IOException e) {
                e.printStackTrace();
                cancel(true);
            } catch (Exception e) {
                e.printStackTrace();
                cancel(true);
            }
            return false;
        }


        //display the response from the request above
        protected void onPostExecute(Boolean result) {
            Log.v(LOG_TAG, content);
            //mSuccess.success(content);
            Toast.makeText(getBaseContext(), "Response from request: " + content,
                    Toast.LENGTH_LONG).show();
        }


    }

}
