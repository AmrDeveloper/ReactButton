package com.amrdeveloper.reactbutton;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReactionAdapter extends ArrayAdapter<Reaction> {

    public ReactionAdapter(@NonNull Context context, @NonNull List<Reaction> reactions) {
        super(context, R.layout.react_dialog_item, reactions);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.react_dialog_item, parent, false);
        }

        Reaction reaction = getItem(position);
        if(reaction != null) {
            ImageView imageView = (ImageView) view;
            imageView.setImageResource(reaction.getReactIconId());
        }
        return view;
    }
}
