package com.amrdeveloper.reactbutton;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

@SuppressLint("AppCompatCustomView")
public class ReactButton extends Button implements View.OnClickListener , View.OnLongClickListener{

    //Alert Dialog To Show Emojis
    private AlertDialog alertDialog;
    //Current React Button
    private Button reactButton = this;
    //Layout Of Emojis
    private LinearLayout emojiLayout;
    //Emojis ImageButtons
    private ImageButton likeFace;
    private ImageButton loveFace;
    private ImageButton smileFace;
    private ImageButton wowFace;
    private ImageButton sadFace;
    private ImageButton angryFace;
    //User onCLick Implementation
    private OnClickListener onClickListener;
    //User onLongCLick Implementation
    private OnLongClickListener onDismissListener;

    //Check React Button State
    private boolean reactState = false;
    //Return Type Of Current Emoji
    private String emojiType = "Default";

    //Emojis Type as String
    public static final String DEFAULT = "Default";
    public static final String LIKE = "Like";
    public static final String LOVE = "Love";
    public static final String SMILE = "Smile";
    public static final String WOW = "Wow";
    public static final String SAD = "Sad";
    public static final String ANGRY = "Angry";

    //Default Emoji Dialog Color and Developer can change it using Method
    private int layoutColor = 0xfff;

    public ReactButton(Context context) {
        super(context);
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        reactButton.setText(LIKE);
        reactButton.setOnClickListener(this);
        reactButton.setOnLongClickListener(this);
    }

    public ReactButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        reactButton.setText(LIKE);
        reactButton.setOnClickListener(this);
        reactButton.setOnLongClickListener(this);
    }

    public ReactButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        reactButton.setText(LIKE);
        reactButton.setOnClickListener(this);
        reactButton.setOnLongClickListener(this);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReactButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
        reactButton.setText(layoutColor);
        reactButton.setOnClickListener(this);
        reactButton.setOnLongClickListener(this);
    }

    //Method To Make Button Like if it default and dislike if state is true
    private void onClickLikeAndDisLike(){
        //Code When User Click On Button
        //If State is true , dislike The Button And Return To Default State
        if(reactState){
            reactButtonDefaultState();
        } else {
            reactButtonLikeState();
        }
    }

    //Method To Show Emoji Dialog When User Click Long
    private void onLongClickDialog(){
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
                reactButtonLikeState();
                alertDialog.dismiss();
            }
        });

        loveFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonLoveState();
                alertDialog.dismiss();
            }
        });


        smileFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonSmileState();
                alertDialog.dismiss();

            }
        });

        wowFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonWowState();
                alertDialog.dismiss();
            }
        });


        sadFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonSadState();
                alertDialog.dismiss();
            }
        });

        angryFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonAngryState();
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

    //Set The Current Emoji Text And Reaction
    public void setCurrentEmojiType(String react){
        switch (react){
            case DEFAULT:
                reactButtonDefaultState();
                break;

            case LIKE:
                reactButtonLikeState();
                break;

            case LOVE:
                reactButtonLoveState();
                break;

            case SMILE:
                reactButtonSmileState();
                break;

            case WOW:
                reactButtonWowState();
                break;

            case SAD:
                reactButtonSadState();
                break;

            case ANGRY:
                reactButtonAngryState();
                break;
        }
    }

    //Private Method to Change Button state and set Reaction
    //React Button Default State
    private void reactButtonDefaultState(){
        reactState = false;
        emojiType = DEFAULT;
        reactButton.setText(LIKE);
        reactButton.setTextColor(ReactConstance.getColor(ReactConstance.DEFAULT));
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default,0,0,0);
    }

    //React Button Like State
    private void reactButtonLikeState(){
        reactState = true;
        emojiType = LIKE;
        reactButton.setText(LIKE);
        reactButton.setTextColor(ReactConstance.getColor(ReactConstance.BLUE));
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_like,0,0,0);
    }

    //React Button Love State
    private void reactButtonLoveState(){
        reactState = true;
        emojiType = LOVE;
        reactButton.setText(emojiType);
        reactButton.setTextColor(ReactConstance.getColor(ReactConstance.RED_LOVE));
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_love,0,0,0);
    }

    //React Button Smile State
    private void reactButtonSmileState(){
        reactState = true;
        emojiType = SMILE;
        reactButton.setText(emojiType);
        reactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_HAHA));
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_haha,0,0,0);
    }

    //React Button Wow State
    private void reactButtonWowState(){
        reactState = true;
        emojiType = WOW;
        reactButton.setText(emojiType);
        reactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_WOW));
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_wow,0,0,0);
    }

    //React Button Sad State
    private void reactButtonSadState(){
        reactState = true;
        emojiType = SAD;
        reactButton.setText(emojiType);
        reactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_HAHA));
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_sad,0,0,0);
    }

    //React Button Angry State
    private void reactButtonAngryState(){
        reactState = true;
        emojiType = ANGRY;
        reactButton.setText(emojiType);
        reactButton.setTextColor(ReactConstance.getColor(ReactConstance.RED_ANGRY));
        reactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_angry,0,0,0);
    }


    //Get ReactButton OnClick From User
    public void setReactClickListener(OnClickListener onClickListener){
        this.onClickListener = onClickListener;
    }

    //Get ReactButton OnLongClick From User
    public void setReactDismissListener(OnLongClickListener onDismisslListener){
        this.onDismissListener = onDismisslListener;
    }

    @Override
    public void onClick(View view) {
        //The Library OnClick
        onClickLikeAndDisLike();
        //If User Set OnClick Using it After Native Library OnClick
        if(onClickListener != null){
            onClickListener.onClick(view);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        final View currentView = view;
        //First Using My Native OnLongClick
        onLongClickDialog();
        //Implement on Dismiss Listener to call Developer Method
        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(onDismissListener != null)
                {
                    //User OnLongClick Implementation
                    onDismissListener.onLongClick(currentView);
                }
            }
        });
        return true;
    }
}
