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

@SuppressLint("AppCompatCustomView")
public class ReactButton
        extends Button
        implements View.OnClickListener, View.OnLongClickListener {

    /**
     * ReactButton custom view object to make easy to change attribute
     */
    private ReactButton mReactButton = this;

    /**
     * Reaction Alert Dialog to show Reaction layout with 6 Reactions
     */
    private AlertDialog mReactAlertDialog;


    /**
     * react current state as boolean variable
     * is false in default state and true in all other states
     */
    private boolean mCurrentReactState;

    /**
     * ImagesButton one for every Reaction
     */
    private ImageButton mImgButtonOne;
    private ImageButton mImgButtonTwo;
    private ImageButton mImgButtonThree;
    private ImageButton mImgButtonFour;
    private ImageButton mImgButtonFive;
    private ImageButton mImgButtonSix;

    /**
     * Number of Valid Reactions
     */
    private static final int REACTIONS_NUMBER = 6;

    /**
     * Array of ImagesButton to set any action for all
     */
    private final ImageButton[] mReactImgArray = new ImageButton[REACTIONS_NUMBER];

    /**
     * Reaction Object to save default Reaction
     */
    private Reaction mDefaultReaction = FbReactions.getDefaultReact();

    /**
     * Reaction Object to save the current Reaction
     */
    private Reaction mCurrentReaction = mDefaultReaction;

    /**
     * Array of six Reaction one for every ImageButton Icon
     */
    private Reaction[] mReactionPack = FbReactions.getReactions();

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

    /**
     * Method with 2 state set first React or back to default state
     */
    private void onClickLikeAndDisLike() {
        //Code When User Click On Button
        //If State is true , dislike The Button And Return To Default State
        if (mCurrentReactState) {
            updateReactButtonByReaction(mDefaultReaction);
        } else {
            updateReactButtonByReaction(mReactionPack[0]);
        }
    }

    /**
     * Show Reaction dialog when user long click on react button
     */
    private void onLongClickDialog() {
        //Show Dialog With 6 React
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());

        //Irrelevant code for customizing the buttons and title
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.react_dialog_layout, null);

        initializingReactImages(dialogView);
        setReactionsArray();
        resetReactionsIcons();
        onClickImageButtons();

        dialogBuilder.setView(dialogView);
        mReactAlertDialog = dialogBuilder.create();
        mReactAlertDialog.getWindow().setBackgroundDrawableResource(mReactDialogShape);

        Window window = mReactAlertDialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

        mReactAlertDialog.show();
    }

    /**
     * @param view : View Object to initialize all ImagesButton
     */
    private void initializingReactImages(View view) {
        mImgButtonOne = view.findViewById(R.id.imgButtonOne);
        mImgButtonTwo = view.findViewById(R.id.imgButtonTwo);
        mImgButtonThree = view.findViewById(R.id.imgButtonThree);
        mImgButtonFour = view.findViewById(R.id.imgButtonFour);
        mImgButtonFive = view.findViewById(R.id.imgButtonFive);
        mImgButtonSix = view.findViewById(R.id.imgButtonSix);
    }

    /**
     * Put all ImagesButton on Array
     */
    private void setReactionsArray() {
        mReactImgArray[0] = mImgButtonOne;
        mReactImgArray[1] = mImgButtonTwo;
        mReactImgArray[2] = mImgButtonThree;
        mReactImgArray[3] = mImgButtonFour;
        mReactImgArray[4] = mImgButtonFive;
        mReactImgArray[5] = mImgButtonSix;
    }

    /**
     * Set onClickListener For every Image Buttons on Reaction Dialog
     */
    private void onClickImageButtons() {
        imgButtonSetListener(mImgButtonOne, 0);
        imgButtonSetListener(mImgButtonTwo, 1);
        imgButtonSetListener(mImgButtonThree, 2);
        imgButtonSetListener(mImgButtonFour, 3);
        imgButtonSetListener(mImgButtonFive, 4);
        imgButtonSetListener(mImgButtonSix, 5);
    }

    /**
     * @param imgButton  : ImageButton view to set onClickListener
     * @param reactIndex : Index of Reaction to take it from ReactionPack
     */
    private void imgButtonSetListener(ImageButton imgButton, final int reactIndex) {
        imgButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                updateReactButtonByReaction(mReactionPack[reactIndex]);
                mReactAlertDialog.dismiss();
            }
        });
    }

    /**
     * Update All Reaction ImageButton one by one from Reactions array
     */
    private void resetReactionsIcons() {
        for (int index = 0; index < REACTIONS_NUMBER; index++) {
            mReactImgArray[index].setImageResource(mReactionPack[index].getReactIconId());
        }
    }

    /**
     * Simple Method to set default settings for ReactButton Constructors
     * - Default Text Is Like
     * - set onClick And onLongClick
     * - set Default image is Dark Like
     */
    private void reactButtonDefaultSetting() {
        mReactButton.setText(mDefaultReaction.getReactText());
        mReactButton.setOnClickListener(this);
        mReactButton.setOnLongClickListener(this);
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(mDefaultReaction.getReactIconId(), 0, 0, 0);
    }

    /**
     * @param shapeId : Get xml Shape for react dialog layout
     */
    public void setReactionDialogShape(int shapeId) {
        //Set Shape for react dialog layout
        this.mReactDialogShape = shapeId;
    }

    /**
     * @param react : Reaction to update UI by take attribute from it
     */
    private void updateReactButtonByReaction(Reaction react) {
        mCurrentReaction = react;
        mReactButton.setText(react.getReactText());
        mReactButton.setTextColor(Color.parseColor(react.getReactTextColor()));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(react.getReactIconId(), 0, 0, 0);
        mCurrentReactState = !react.getReactType().equals(mDefaultReaction.getReactType());
    }

    /**
     * @param reactions : Array of six Reactions to update default six Reactions
     */
    public void setReactions(Reaction... reactions) {
        //Assert that Reactions number is six
        if (reactions.length != REACTIONS_NUMBER)
            return;
        //Update array of library default reactions
        mReactionPack = reactions;
    }

    /**
     * @param reaction : set This Reaction as current Reaction
     */
    public void setCurrentReaction(Reaction reaction) {
        updateReactButtonByReaction(reaction);
    }

    /**
     * @return : The Current reaction Object
     */
    public Reaction getCurrentReaction() {
        return mCurrentReaction;
    }

    /**
     * @param reaction : Update library default Reaction by other Reaction
     */
    public void setDefaultReaction(Reaction reaction) {
        mDefaultReaction = reaction;
        updateReactButtonByReaction(mDefaultReaction);
    }

    /**
     * @return : The current default Reaction object
     */
    public Reaction getDefaultReaction() {
        return mDefaultReaction;
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

    /**
     * @return : true if current reaction type is default
     */
    public boolean isDefaultReaction() {
        return mCurrentReaction.equals(mDefaultReaction);
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
}
