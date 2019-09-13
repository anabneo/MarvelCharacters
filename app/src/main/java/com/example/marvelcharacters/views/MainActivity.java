package com.example.marvelcharacters.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.marvelcharacters.R;
import com.example.marvelcharacters.model.Character;
import com.example.marvelcharacters.model.CharacterAdapter;
import com.example.marvelcharacters.service.HTTPService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Marvel Characters");
        actionBar.setDisplayShowHomeEnabled(true);


        HTTPService service = new HTTPService();

        List<Character> characterList = new ArrayList();

        try {
            characterList = service.execute().get();

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        CharacterAdapter adapter = new CharacterAdapter(this, characterList);
        ListView listView = findViewById(R.id.lvCharacters);
        listView.setAdapter(adapter);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_characterList:
                Toast.makeText(this, "Character List", Toast.LENGTH_LONG).show();
                break;

            case R.id.menu_logout:
                Intent intentLogin = new Intent(MainActivity.this, Login.class);
                startActivity(intentLogin);
                finish();
        }
        return true;
    }

}
