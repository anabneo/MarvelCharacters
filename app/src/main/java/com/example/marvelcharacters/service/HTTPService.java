package com.example.marvelcharacters.service;

import android.os.AsyncTask;

import com.example.marvelcharacters.model.Character;
import com.example.marvelcharacters.model.MainContent;
import com.example.marvelcharacters.model.Result;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTTPService extends AsyncTask<Void, Void, List<Character>> {

    @Override
    protected List<Character> doInBackground(Void... voids) {
        BufferedReader reader = null;

        try {
            URL marvelEndpoint = new URL("http://gateway.marvel.com/v1/public/characters?ts=1&apikey=3a1760c8ae550da92c5609772087d3f5&hash=db650c0ca5c1fa80d8391c5c8b6fd186");
            HttpURLConnection connection = (HttpURLConnection) marvelEndpoint.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");
            connection.setConnectTimeout(15000);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
                buffer.append("\n");

            }


            String ApiResult = buffer.toString();

            MainContent temporaria = new Gson().fromJson(ApiResult, MainContent.class);
            List<Result> resultList = temporaria.getData().getResults();

            List<Character> characterList = new ArrayList();

            for (Result res : resultList) {

                Character c = new Character(res.getName(), res.getModified(), res.getDescription());

                characterList.add(c);
            }

            return characterList;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
}
