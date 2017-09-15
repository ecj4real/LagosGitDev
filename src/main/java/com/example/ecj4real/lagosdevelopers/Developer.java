package com.example.ecj4real.lagosdevelopers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Developer implements Serializable {
    private String login;
    private String avatar_url;
    private String html_url;

    public String getLogin() {
        return login;
    }

    public String getAvatar() {
        return avatar_url;
    }

    public String getHtml() {
        return html_url;
    }

    public static Developer fromJson(JSONObject jsonObject) {
        Developer developer = new Developer();
        try {
            developer.login = jsonObject.getString("login");
            developer.avatar_url = jsonObject.getString("avatar_url");
            developer.html_url = jsonObject.getString("html_url");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return developer;
    }

    public static ArrayList<Developer> fromJson(JSONArray jsonArray) {
        ArrayList<Developer> developersList = new ArrayList<Developer>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject developerJson = null;
            try {
                developerJson = jsonArray.getJSONObject(i);
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
            Developer developer = Developer.fromJson(developerJson);
            if (developer != null) {
                developersList.add(developer);
            }
        }
        return developersList;
    }
}
