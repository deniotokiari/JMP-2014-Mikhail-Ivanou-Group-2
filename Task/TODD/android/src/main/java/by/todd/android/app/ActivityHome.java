package by.todd.android.app;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import by.todd.android.R;
import by.todd.android.app.fragment.FragmentCreateItem;
import by.todd.android.app.fragment.FragmentHome;

public class ActivityHome extends ActionBarActivity {
    private static final String GET = "get";
    private static final String POST = "post";
    private DefaultHttpClient mHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mHttpClient = new DefaultHttpClient();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.app_name));

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentByTag(FragmentHome.TAG) == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, FragmentHome.newInstance(), FragmentHome.TAG);
            fragmentTransaction.commit();
        }

        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentCreateItem fragmentCreate = (FragmentCreateItem) fragmentManager.findFragmentByTag(FragmentCreateItem.TAG);
                if (fragmentCreate == null) {
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.container, FragmentCreateItem.newInstance(), FragmentCreateItem.TAG);
                    fragmentTransaction.addToBackStack(FragmentCreateItem.TAG);
                    fragmentTransaction.commit();
                } else {
                    fragmentCreate.save();
                }
            }
        });
    }

    public void get(Success success, String path) {
        new Request(success, GET, null).execute(path);
    }

    public void post(JSONObject jo, Success success, String path) {
        new Request(success, POST, jo).execute(path);
    }

    private class Request extends AsyncTask<String, Void, Boolean> {

        private final Success mSuccess;
        private final String mMethod;
        private final JSONObject mJsonObject;

        private HttpResponse response;
        private String content = null;

        public Request(Success success, String method, JSONObject jsonObject) {
            mSuccess = success;
            mMethod = method;
            mJsonObject = jsonObject;
        }

        protected Boolean doInBackground(String... urls) {
            try {
                HttpRequestBase httpRequestBase = null;
                if (GET.equals(mMethod)) {
                    httpRequestBase = new HttpGet(urls[0]);
                } else if (POST.equals(mMethod)) {
                    httpRequestBase = new HttpPost(urls[0]);
                    ((HttpPost) httpRequestBase).setEntity(new StringEntity(mJsonObject.toString()));
                }
                response = mHttpClient.execute(httpRequestBase);
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                content = out.toString();
                return true;
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


        @Override
        protected void onPostExecute(Boolean result) {
            mSuccess.success(content);
        }


    }

}
