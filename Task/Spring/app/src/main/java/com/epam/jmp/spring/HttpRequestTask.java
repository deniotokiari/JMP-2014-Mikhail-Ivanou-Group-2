package com.epam.jmp.spring;

import android.os.AsyncTask;
import android.util.Log;

import com.epam.jmp.spring.model.Book;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by sergey on 14.12.2014.
 */
public class HttpRequestTask extends AsyncTask<Object, Void, String> {

    public static interface Callback {

        void sucess(String resp);
    }

    private Callback mCallback;

    @Override
    protected String doInBackground(Object... params) {
        try {
            final String url = (String) params[0];
            final Class cls = (Class) params[1];
            mCallback = (Callback) params[2];
            RestTemplate restTemplate = new RestTemplate();

            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
            String object = restTemplate.getForObject(url, String.class);
            return object;
        } catch (Exception e) {
            Log.e("SPRING", "Error " + e);
        }
        return null;
    }

    @Override
    protected void onPostExecute(String resp) {
        if (mCallback != null) {
            mCallback.sucess(resp);
        }
    }

}