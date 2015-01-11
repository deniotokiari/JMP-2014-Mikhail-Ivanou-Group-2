package com.epam.vkfeed.task;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sergey on 11.01.2015.
 */
public abstract class HttpRequestTask<T> extends AsyncTask<Void, Void, T> {

    private static final String TAG = "HttpRequestTask";

    protected abstract String getUrl();

    protected abstract Class getModelClass();

    protected abstract void showLoadingProgressDialog();

    protected abstract void dismissProgressDialog();

    protected abstract void refreshData(T result);

    @Override
    protected void onPreExecute() {
        showLoadingProgressDialog();
    }

    @Override
    protected T doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
            return (T) restTemplate.getForObject(getUrl(), getModelClass());
        } catch (Exception e) {
            Log.e(TAG, "exception " + e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(T result) {
        dismissProgressDialog();
        refreshData(result);
    }

}