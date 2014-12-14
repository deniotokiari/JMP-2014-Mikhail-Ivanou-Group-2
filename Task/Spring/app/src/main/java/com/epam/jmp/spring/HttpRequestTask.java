package com.epam.jmp.spring;

import android.os.AsyncTask;
import android.util.Log;

import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by sergey on 14.12.2014.
 */
public class HttpRequestTask<T> extends AsyncTask<Object, Void, T> {

    public static interface Callback<T> {

        void sucess(T resp);
    }

    private Callback mCallback;

    @Override
    protected T doInBackground(Object... params) {
        try {
            final String url = (String) params[0];
            final Class cls = (Class) params[1];
            mCallback = (Callback) params[2];
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());

            return (T) restTemplate.getForObject(url, cls);
        } catch (Exception e) {
            Log.e("SPRING", "Error " + e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(T resp) {
        if (mCallback != null) {
            mCallback.sucess(resp);
        }
    }

}