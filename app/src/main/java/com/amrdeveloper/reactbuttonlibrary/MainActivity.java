package com.amrdeveloper.reactbuttonlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.amrdeveloper.reactbutton.ReactButton;
import com.amrdeveloper.reactbutton.Reaction;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ReactButton reactButton = findViewById(R.id.reactButton);
        reactButton.setReactions(FbReactions.reactions);
        reactButton.setDefaultReaction(FbReactions.defaultReact);

        reactButton.setOnReactionChangeListener(new ReactButton.OnReactionChangeListener() {
            @Override
            public void onReactionChange(Reaction reaction) {
                Log.d(TAG, "onReactionChange: " + reaction.getReactText());
            }
        });

        reactButton.setOnReactionDialogStateListener(new ReactButton.OnReactionDialogStateListener() {
            @Override
            public void onDialogOpened() {
                Log.d(TAG, "onDialogOpened");
            }

            @Override
            public void onDialogDismiss() {
                Log.d(TAG, "onDialogDismiss");
            }
        });
    }
}
