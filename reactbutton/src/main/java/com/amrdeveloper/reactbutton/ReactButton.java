package com.amrdeveloper.reactbutton;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class ReactButton extends Button
                         implements View.OnClickListener ,
                                    View.OnLongClickListener{

    private AlertDialog alertDialog;
    //Current React Button
    private Button reactButton = this;
    //Check React Button State
    private boolean reactState = false;
    //Return Type Of Current Emoji
    private String emojiType = "Default";

    //Layout Of Emojis
    private LinearLayout emojiLayout;
    //Emojis ImageButtons
    private ImageButton likeFace;
    private ImageButton loveFace;
    private ImageButton smileFace;
    private ImageButton wowFace;
    private ImageButton sadFace;
    private ImageButton angryFace;

    //Default Emoji Dialog Color and Developer can change it using Method
    private int layoutColor = 0xfff;

    public ReactButton(Context context) {
        super(context);
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        reactButton.setText(ReactConstance.LIKE_TEXT);
    }

    public ReactButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        reactButton.setText(ReactConstance.LIKE_TEXT);
    }

    public ReactButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        reactButton.setText(ReactConstance.LIKE_TEXT);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReactButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.setOnClickListener(this);
        this.setOnLongClickListener(this);
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        reactButton.setText(ReactConstance.LIKE_TEXT);
    }

    @Override
    public void onClick(View view) {
        //Code When User Click On Button
        //If State is true , dislike The Button And Return To Default State
        if(reactState){
            reactState = false;
            reactButton.setText(ReactConstance.LIKE_TEXT);
            reactButton.setTextColor(ReactConstance.getColor(ReactConstance.DEFAULT));
            reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        }
        else
        {
            reactState = true;
            reactButton.setText(ReactConstance.LIKE_TEXT);
            reactButton.setTextColor(ReactConstance.getColor(ReactConstance.BLUE));
            reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_like,0,0,0);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        //Code When User Click Long on Button
        //Show Dialog With 6 Emoji
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        //Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.emojis_dialog, null);

        //Initializing LinearLayout For Emojis and Change Attributes
        emojiLayout = dialogView.findViewById(R.id.emojiLayout);
        emojiLayout.setBackgroundColor(layoutColor);

        initializingTextViews(dialogView);
        onClickTextViews();

        dialogBuilder.setView(dialogView);
        alertDialog = dialogBuilder.create();

        Window window = alertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        alertDialog.show();
        return true;
    }

    //Initializing Every TextView Using id and View Object
    private void initializingTextViews(View currentView){
        likeFace = (ImageButton) currentView.findViewById(R.id.likeFace);
        loveFace = (ImageButton) currentView.findViewById(R.id.loveFace);
        smileFace = (ImageButton) currentView.findViewById(R.id.smileFace);
        wowFace = (ImageButton) currentView.findViewById(R.id.wowFace);
        sadFace = (ImageButton) currentView.findViewById(R.id.sadFace);
        angryFace = (ImageButton) currentView.findViewById(R.id.angryFace);
    }

    //Set OnClick Method For Every TextView
    private void onClickTextViews(){

        likeFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactState = true;
                emojiType = ReactConstance.LIKE_TEXT;
                reactButton.setText(emojiType);
                reactButton.setTextColor(ReactConstance.getColor(ReactConstance.BLUE));
                reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_like,0,0,0);
                alertDialog.dismiss();
            }
        });

        loveFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactState = true;
                emojiType = ReactConstance.LOVE_TEXT;
                reactButton.setText(emojiType);
                reactButton.setTextColor(ReactConstance.getColor(ReactConstance.RED_LOVE));
                reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_love,0,0,0);
                alertDialog.dismiss();
            }
        });


        smileFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactState = true;
                emojiType = ReactConstance.SMILE_TEXT;
                reactButton.setText(emojiType);
                reactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_HAHA));
                reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_haha,0,0,0);
                alertDialog.dismiss();

            }
        });

        wowFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactState = true;
                emojiType = ReactConstance.WOW_TEXT;
                reactButton.setText(emojiType);
                reactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_WOW));
                reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_wow,0,0,0);
                alertDialog.dismiss();
            }
        });


        sadFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactState = true;
                emojiType = ReactConstance.SAD_TEXT;
                reactButton.setText(emojiType);
                reactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_HAHA));
                reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_sad,0,0,0);
                alertDialog.dismiss();
            }
        });

        angryFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactState = true;
                emojiType = ReactConstance.ANGRY_TEXT;
                reactButton.setText(emojiType);
                reactButton.setTextColor(ReactConstance.getColor(ReactConstance.RED_ANGRY));
                reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_angry,0,0,0);
                alertDialog.dismiss();
            }
        });

    }

    //Set Custom Color For Emoji Dialog
    public void dialogBackgroundColor(int color){
        //Set Color For EmojiDialog
        this.layoutColor = color;
    }

    //Return Type Of Current Emoji
    public String getCurrentEmojiType(){
        return emojiType;
    }
}
