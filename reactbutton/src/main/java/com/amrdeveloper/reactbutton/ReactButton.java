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

@SuppressLint("AppCompatCustomView")
public class ReactButton
        extends Button
        implements View.OnClickListener, View.OnLongClickListener {

    /**
     * ReactButton custom view object to make easy to change attribute
     */
    private ReactButton mReactButton = this;

    /**
     * Emoji Alert Dialog to show emoji layout with 6 emoji faces
     */
    private AlertDialog mReactAlertDialog;

    /**
     * ImageButton Object for every React face
     */
    private ImageButton likeImgButton;
    private ImageButton loveImgButton;
    private ImageButton smileImgButton;
    private ImageButton wowImgButton;
    private ImageButton sadImgButton;
    private ImageButton angryImgButton;

    /**
     * Type of every React as String
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
    private boolean mCurrentReactState;

    /**
     * String variable to save current React Type
     * default type is 'Default'
     */
    private String mCurrentReactType = DEFAULT;

    /**
     * Integer variable to change react dialog shape
     * Default value is react_dialog_shape
     */
    private int mReactDialogShape = R.drawable.react_dialog_shape;

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
        if (mCurrentReactState) {
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
        //Show Dialog With 6 React
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        //Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.react_dialog_layout, null);

        initializingViews(dialogView);
        onClickImageButtons();

        dialogBuilder.setView(dialogView);
        mReactAlertDialog = dialogBuilder.create();
        mReactAlertDialog.getWindow().setBackgroundDrawableResource(mReactDialogShape);

        Window window = mReactAlertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        mReactAlertDialog.show();
    }

    /**
     * @param view : get current View
     *             and using id to initializing ImageButtons Using id and View Object
     */
    private void initializingViews(View view) {
        likeImgButton = view.findViewById(R.id.likeImgButton);
        loveImgButton = view.findViewById(R.id.loveImgButton);
        smileImgButton = view.findViewById(R.id.smileImgButton);
        wowImgButton = view.findViewById(R.id.wowImgButton);
        sadImgButton = view.findViewById(R.id.sadImgButton);
        angryImgButton = view.findViewById(R.id.angryImgButton);
    }

    /**
     * Set onClickListener For every Image Buttons on Emoji Dialog
     */
    private void onClickImageButtons() {
        likeImgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonLikeState();
                mReactAlertDialog.dismiss();
            }
        });

        loveImgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonLoveState();
                mReactAlertDialog.dismiss();
            }
        });

        smileImgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonSmileState();
                mReactAlertDialog.dismiss();
            }
        });

        wowImgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonWowState();
                mReactAlertDialog.dismiss();
            }
        });

        sadImgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonSadState();
                mReactAlertDialog.dismiss();
            }
        });

        angryImgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                reactButtonAngryState();
                mReactAlertDialog.dismiss();
            }
        });
    }

    /**
     * ReactButton default state settings
     */
    private void reactButtonDefaultState() {
        mCurrentReactState = false;
        mCurrentReactType = DEFAULT;
        mReactButton.setText(LIKE);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.DEFAULT));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gray_like, 0, 0, 0);
    }

    /**
     * ReactButton like state settings
     */
    private void reactButtonLikeState() {
        mCurrentReactState = true;
        mCurrentReactType = LIKE;
        mReactButton.setText(LIKE);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.BLUE));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gray_like, 0, 0, 0);
    }

    /**
     * ReactButton love state settings
     */
    private void reactButtonLoveState() {
        mCurrentReactState = true;
        mCurrentReactType = LOVE;
        mReactButton.setText(mCurrentReactType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.RED_LOVE));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_heart, 0, 0, 0);
    }

    /**
     * ReactButton smile state settings
     */
    private void reactButtonSmileState() {
        mCurrentReactState = true;
        mCurrentReactType = SMILE;
        mReactButton.setText(mCurrentReactType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_HAHA));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_happy, 0, 0, 0);
    }

    /**
     * ReactButton wow state settings
     */
    private void reactButtonWowState() {
        mCurrentReactState = true;
        mCurrentReactType = WOW;
        mReactButton.setText(mCurrentReactType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_WOW));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_surprise, 0, 0, 0);
    }

    /**
     * ReactButton sad state settings
     */
    private void reactButtonSadState() {
        mCurrentReactState = true;
        mCurrentReactType = SAD;
        mReactButton.setText(mCurrentReactType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.YELLOW_HAHA));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_sad, 0, 0, 0);
    }

    /**
     * ReactButton angry state settings
     */
    private void reactButtonAngryState() {
        mCurrentReactState = true;
        mCurrentReactType = ANGRY;
        mReactButton.setText(mCurrentReactType);
        mReactButton.setTextColor(ReactConstance.getColor(ReactConstance.RED_ANGRY));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_angry, 0, 0, 0);
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
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_gray_like, 0, 0, 0);
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
        mReactAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
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

    /**
     * @param shapeId : Get xml Shape for react dialog layout
     */
    public void setReactDialogShape(int shapeId) {
        //Set Shape for react dialog layout
        this.mReactDialogShape = shapeId;
    }

    /**
     * @return : Return type of current Emoji in Button
     */
    public String getCurrentReactType() {
        return this.mCurrentReactType;
    }

    /**
     * @param reactName : Emoji Type as String
     *                  put Setting for this emoji type using switch
     */
    public void setCurrentReactType(String reactName) {
        switch (reactName) {
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
}
