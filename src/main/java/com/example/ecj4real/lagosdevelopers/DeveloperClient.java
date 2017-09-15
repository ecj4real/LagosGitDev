package com.example.ecj4real.lagosdevelopers;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

public class DeveloperClient {
    private static final String API_BASE_URL = "https://api.github.com/search/users?q=location:lagos";
    private AsyncHttpClient client;

    public DeveloperClient() {
        this.client = new AsyncHttpClient();
    }

    public void getDevelopers(JsonHttpResponseHandler handler) {
        try {
            String url = API_BASE_URL;
            client.setUserAgent("UBrowser/7.0.6.1042");
            client.get(url, handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
