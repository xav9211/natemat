package main.java.pl.edu.agh.sius.util;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        try (InputStream is = new URL(url).openStream()) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            return new JSONObject(jsonText);
        }
    }

    public static void main(String[] args) throws IOException, JSONException {
        String url = "http://natemat.pl/141751,obnizenie-wieku-emerytalnego-to-glowny-postulat-dudy-kandydat-pis-nie-potrafi-wytlumaczyc-skad-wziac-na-to-pieniadze";

        JSONObject json1 = readJsonFromUrl("http://graph.facebook.com/comments?id=" + url);
        System.out.println(json1.getJSONArray("data"));
        //Wziecie przykladowego komentarza
        for (int i = 0; i < json1.getJSONArray("data").length(); ++i) {
            System.out.println("Comment no. " + i);
            System.out.println("Author: " + json1.getJSONArray("data").getJSONObject(i).getJSONObject("from").getString("name"));
            System.out.println("Created date: " + json1.getJSONArray("data").getJSONObject(i).getString("created_time"));
            System.out.println("Like counter: " + json1.getJSONArray("data").getJSONObject(i).getInt("like_count"));
            System.out.println("Content: ");
            System.out.println(json1.getJSONArray("data").getJSONObject(i).getString("message"));
        }
        //Liczba wszystkich komentarzy
        System.out.println("Number of comments: " + json1.getJSONArray("data").length());
        //Liczba wszystkich komentarzy + podkomentarzy
        JSONObject json2 = readJsonFromUrl("http://graph.facebook.com/" + url);
        System.out.println("Number of all comments: " + json2.getInt("comments"));
    }
}
