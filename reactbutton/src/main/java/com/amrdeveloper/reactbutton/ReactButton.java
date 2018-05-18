package com.amrdeveloper.reactbutton;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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
public class ReactButton extends Button implements View.OnClickListener, View.OnLongClickListener {

    /**
     * ReactButton custom view object to make easy to change attribute
     */
    private ReactButton mReactButton = this;

    /**
     * Emoji Alert Dialog to show emoji layout with 6 emoji faces
     */
    private AlertDialog emojisAlertDialog;

    /**
     * ImageButton Object for every emoji face
     */
    private ImageButton likeFace;
    private ImageButton loveFace;
    private ImageButton smileFace;
    private ImageButton wowFace;
    private ImageButton sadFace;
    private ImageButton angryFace;

    /**
     * Type of every emoji as String
     */
    public static final String DEFAULT = "Default";
    public static final String LIKE = "Like";
    public static final String LOVE = "Love";
    public static final String SMILE = "Smile";
    public static final String WOW = "Wow";
    public static final String SAD = "Sad";
    public static final String ANGRY = "Angry";

    /**
     * react current state as boolean variable
     * is false in default state and true in all other states
     */
    private boolean reactState = false;

    /**
     * String variable to save current Emoji Type
     * default type is 'Default'
     */
    private String currentEmojiType = "Default";

    /**
     * Integer variable to change Emoji Dialog layout color
     * Default value is White Color and change using public method by developer
     */
    private int emojiDialogColor = Color.WHITE;

    /**
     * onClickListener interface implementation object
     */
    private OnClickListener onClickListener;

    /**
     * OnLongClickListener interface implementation object
     */
    private OnLongClickListener onDismissListener;

    public ReactButton(Context context) {
        super(context);
        reactButtonDefaultSetting();
    }

    public ReactButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        reactButtonDefaultSetting();
    }

    public ReactButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        reactButtonDefaultSetting();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReactButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        reactButtonDefaultSetting();
    }

    //Method To Make Button Like if it default and dislike if state is true
    private void onClickLikeAndDisLike() {
        //Code When User Click On Button
        //If State is true , dislike The Button And Return To Default State
        if (reactState) {
            reactButtonDefaultState();
        } else {
            reactButtonLikeState();
        }
    }

    /**
     * Show emoji dialog when user long click on react button
     */
    private void onLongClickDialog() {
        //Code When User Click Long on Button
        //Show Dialog With 6 Emoji
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        //Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.emojis_dialog, null);

        //Initializing LinearLayout For Emojis and Change Attributes
        LinearLayout emojiLayout = dialogView.findViewById(R.id.emojiLayout);
        emojiLayout.setBackgroundColor(emojiDialogColor);

        initializingTextViews(dialogView);
        onClickImageButtons();

        dialogBuilder.setView(dialogView);
        emojisAlertDialog = dialogBuilder.create();

        Window window = emojisAlertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        emojisAlertDialog.show();
    }

    /**
     * @param currentView : get current View
     *                    and using id to initializing ImageButtons Using id and View Object
     */
    private void initializingTextViews(View currentView) {
        likeFace = currentView.findViewById(R.id.likeFace);
        loveFace = currentView.findViewById(R.id.loveFace);
        smileFace = currentView.findViewById(R.id.smileFace);
        wowFace = currentView.findViewById(R.id.wowFace);
        sadFace = currentView.findViewById(R.id.sadFace);
        angryFace = currentView.findViewById(R.id.angryFace);
    }

    /**
     * Set onClickListener For every Image Buttons on Emoji Dialog
     */
    private void onClickImageButtons() {
        likeFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonLikeState();
                emojisAlertDialog.dismiss();
            }
        });

        loveFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonLoveState();
                emojisAlertDialog.dismiss();
            }
        });

        smileFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonSmileState();
                emojisAlertDialog.dismiss();
            }
        });

        wowFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonWowState();
                emojisAlertDialog.dismiss();
            }
        });

        sadFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonSadState();
                emojisAlertDialog.dismiss();
            }
        });

        angryFace.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonAngryState();
                emojisAlertDialog.dismiss();
            }
        });
    }

    /**
     * @param color : Get Color Value from user and set it for dialog background
     */
    public void setDialogBackgroundColor(int color) {
        //Set Color For EmojiDialog
        this.emojiDialogColor = color;
    }

    /**
     * @return : Return type of current Emoji in Button
     */
    public String getCurrentEmojiType() {
        return this.currentEmojiType;
    }

    /**
     * @param emojiType : Emoji Type as String
     *                  put Setting for this emoji type using switch
     */
    public void setCurrentEmojiType(String emojiType) {
        switch (emojiType) {
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

    /**
     * ReactButton default state settings
     */
    private void reactButtonDefaultState() {
        reactState = false;
        currentEmojiType = DEFAULT;
        mReactButton.setText(LIKE);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.DEFAULT));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default, 0, 0, 0);
    }

    /**
     * ReactButton like state settings
     */
    private void reactButtonLikeState() {
        reactState = true;
        currentEmojiType = LIKE;
        mReactButton.setText(LIKE);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.BLUE));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_like, 0, 0, 0);
    }

    /**
     * ReactButton love state settings
     */
    private void reactButtonLoveState() {
        reactState = true;
        currentEmojiType = LOVE;
        mReactButton.setText(currentEmojiType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.RED_LOVE));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_love, 0, 0, 0);
    }

    /**
     * ReactButton smile state settings
     */
    private void reactButtonSmileState() {
        reactState = true;
        currentEmojiType = SMILE;
        mReactButton.setText(currentEmojiType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_HAHA));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_haha, 0, 0, 0);
    }

    /**
     * ReactButton wow state settings
     */
    private void reactButtonWowState() {
        reactState = true;
        currentEmojiType = WOW;
        mReactButton.setText(currentEmojiType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_WOW));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_wow, 0, 0, 0);
    }

    /**
     * ReactButton sad state settings
     */
    private void reactButtonSadState() {
        reactState = true;
        currentEmojiType = SAD;
        mReactButton.setText(currentEmojiType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_HAHA));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_sad, 0, 0, 0);
    }

    /**
     * ReactButton angry state settings
     */
    private void reactButtonAngryState() {
        reactState = true;
        currentEmojiType = ANGRY;
        mReactButton.setText(currentEmojiType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.RED_ANGRY));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_angry, 0, 0, 0);
    }

    /**
     * Simple Method to set default settings for ReactButton Constructors
     * - Default Text Is Like
     * - set onClick And onLongClick
     * - set Default image is Dark Like
     */
    private void reactButtonDefaultSetting() {
        mReactButton.setText(LIKE);
        mReactButton.setOnClickListener(this);
        mReactButton.setOnLongClickListener(this);
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reactbutton_default, 0, 0, 0);
    }

    /**
     * @param onClickListener : get OnClickListener implementation from developer
     *                        ans update onClickListener Value in this class
     */
    public void setReactClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    /**
     * @param onDismissListener : get OnLongClickListener implementation
     *                          and update onDismissListener value in this class
     */
    public void setReactDismissListener(OnLongClickListener onDismissListener) {
        this.onDismissListener = onDismissListener;
    }

    @Override
    public void onClick(View view) {
        //The Library OnClick
        onClickLikeAndDisLike();
        //If User Set OnClick Using it After Native Library OnClick
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    @Override
    public boolean onLongClick(View view) {
        final View currentView = view;
        //First Using My Native OnLongClick
        onLongClickDialog();
        //Implement on Dismiss Listener to call Developer Method
        emojisAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (onDismissListener != null) {
                    //User OnLongClick Implementation
                    onDismissListener.onLongClick(currentView);
                }
            }
        });
        return true;
    }
}
