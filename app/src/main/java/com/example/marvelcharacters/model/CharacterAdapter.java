package com.example.marvelcharacters.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.marvelcharacters.R;

import java.util.List;

public class CharacterAdapter extends ArrayAdapter<Character> {

    public CharacterAdapter(Context context, List<Character> characters) {
        super(context, 0, characters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Character character = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_char, parent, false);
        }

        TextView tvNome = (TextView) convertView.findViewById(R.id.tvName);
        TextView tvData = (TextView) convertView.findViewById(R.id.tvLastModifiedDate);
        TextView tvDescricao = (TextView) convertView.findViewById(R.id.tvDescription);

        // Populate the data into the template view using the data object
        tvNome.setText(character.getName());
        tvData.setText(character.getDate());
        tvDescricao.setText(character.getDescription());


        return convertView;
    }
}
