package com.amrdeveloper.reactbutton;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressLint("AppCompatCustomView")
public class ReactButton
        extends Button
        implements View.OnClickListener, View.OnLongClickListener {

    /**
     * ReactButton custom view object to make easy to change attribute
     */
    private final ReactButton mReactButton = this;

    /**
     * Reaction Alert Dialog to show Reaction layout with 6 Reactions
     */
    private AlertDialog mReactAlertDialog;

    /**
     * True only if current reaction is not the default reaction
     */
    private boolean isReactButtonUpdated;

    /**
     * Reaction Object to save default Reaction
     */
    private Reaction mDefaultReaction;

    /**
     * Reaction Object to save the current Reaction
     */
    private Reaction mCurrentReaction;

    /**
     * List of Reaction to show them in the dialog
     */
    private final List<Reaction> mReactions = new ArrayList<>();

    /**
     * The number of reactions in the same row,
     * default value will be reactions size
     */
    private int mDialogColumnsNumber;

    /**
     * The dim amount for the reactions dialog
     * 0 for no dim to 1 for full dim, default is 0
     */
    private float mDialogDimAmount = 0;

    /**
     * Integer variable to change react dialog shape
     * Default value is react_dialog_shape
     */
    private int mReactDialogShape = R.drawable.react_dialog_shape;

    /**
     * The size of reaction icon in dp
     * Icon size + icon padding * 2
     */
    private static final int ICON_SIZE_WITH_PADDING = 55;

    /**
     * Full reaction icon size converted from dp
     */
    private final int REACTION_ICON_SIZE = (ICON_SIZE_WITH_PADDING *
            getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);

    public interface OnReactionDialogStateListener {
        void onDialogOpened();

        void onDialogDismiss();
    }

    private OnReactionDialogStateListener mOnReactionDialogStateListener;

    public interface OnReactionChangeListener {
        void onReactionChange(Reaction reaction);
    }

    private OnReactionChangeListener mOnReactionChangeListener;

    public ReactButton(Context context) {
        super(context);
        setupReactButtonDefaultSettings();
    }

    public ReactButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupReactButtonDefaultSettings();
    }

    public ReactButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupReactButtonDefaultSettings();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public ReactButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setupReactButtonDefaultSettings();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

        // Handle Screen rotation, should dismiss the dialog
        if (mReactAlertDialog != null && mReactAlertDialog.isShowing()) mReactAlertDialog.cancel();
    }

    /**
     * Setup default settings for ReactButton Constructors
     * - Set ReactButton Listeners
     */
    private void setupReactButtonDefaultSettings() {
        mReactButton.setOnClickListener(this);
        mReactButton.setOnLongClickListener(this);
    }

    /**
     * Method with 2 state set first React or back to default state
     */
    private void onReactionButtonClick() {
        Reaction reaction = isReactButtonUpdated ? mDefaultReaction : mReactions.get(0);
        updateReactButtonByReaction(reaction);
    }

    /**
     * Show Reaction dialog when user long click on react button
     */
    private void showReactionsDialog() {
        final Context context = getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View dialogView = inflater.inflate(R.layout.react_dialog_layout, null);

        GridView reactionsGrid = dialogView.findViewById(R.id.reactionsList);
        ReactionAdapter adapter = new ReactionAdapter(context, mReactions);
        reactionsGrid.setAdapter(adapter);

        // Setup the columns number
        if (mDialogColumnsNumber == 0) mDialogColumnsNumber = mReactions.size();
        reactionsGrid.setNumColumns(mDialogColumnsNumber);

        reactionsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Reaction currentReaction = mReactions.get(position);
                updateReactButtonByReaction(currentReaction);
                mReactAlertDialog.cancel();
            }
        });

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(dialogView);
        mReactAlertDialog = dialogBuilder.create();
        mReactAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = mReactAlertDialog.getWindow();
        window.setBackgroundDrawableResource(mReactDialogShape);
        window.setDimAmount(mDialogDimAmount);

        // Setup dialog gravity and dynamic position
        WindowManager.LayoutParams windowManagerAttributes = window.getAttributes();
        windowManagerAttributes.gravity = Gravity.TOP | Gravity.START;
        windowManagerAttributes.x = (int) getX() + (getWidth() / 2);
        windowManagerAttributes.y = (int) getY() + (getHeight() / 2);

        mReactAlertDialog.show();
        if (mOnReactionDialogStateListener != null) mOnReactionDialogStateListener.onDialogOpened();

        int dialogWidth = REACTION_ICON_SIZE * mDialogColumnsNumber;
        window.setLayout(dialogWidth, WindowManager.LayoutParams.WRAP_CONTENT);

        mReactAlertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (mOnReactionDialogStateListener != null) {
                    mOnReactionDialogStateListener.onDialogDismiss();
                }
            }
        });
    }

    /**
     * @param react Reaction to update UI by take attribute from it
     */
    private void updateReactButtonByReaction(Reaction react) {
        mCurrentReaction = react;
        mReactButton.setText(react.getReactText());
        mReactButton.setTextColor(Color.parseColor(react.getReactTextColor()));
        mReactButton.setCompoundDrawablesWithIntrinsicBounds(react.getReactIconId(), 0, 0, 0);
        isReactButtonUpdated = !react.equals(mDefaultReaction);
        if (mOnReactionChangeListener != null) mOnReactionChangeListener.onReactionChange(react);
    }

    /**
     * @param drawableShape set xml Shape for react dialog layout
     */
    public void setReactionDialogShape(@DrawableRes int drawableShape) {
        this.mReactDialogShape = drawableShape;
    }

    /**
     * @param reactions Array of six Reactions to update default six Reactions
     */
    public void setReactions(Reaction... reactions) {
        mReactions.addAll(Arrays.asList(reactions));
        if (mDialogColumnsNumber == 0) mDialogColumnsNumber = mReactions.size();
    }

    /**
     * @param reaction set This Reaction as current Reaction
     */
    public void setCurrentReaction(Reaction reaction) {
        updateReactButtonByReaction(reaction);
    }

    /**
     * @return The Current reaction Object
     */
    public Reaction getCurrentReaction() {
        return mCurrentReaction;
    }

    /**
     * @param reaction Update library default Reaction by other Reaction
     */
    public void setDefaultReaction(Reaction reaction) {
        mDefaultReaction = reaction;
        mCurrentReaction = mDefaultReaction;
        updateReactButtonByReaction(mDefaultReaction);
    }

    /**
     * @return The current default Reaction object
     */
    public Reaction getDefaultReaction() {
        return mDefaultReaction;
    }

    /**
     * @param number the number of reactions dialog columns
     */
    public void setDialogColumnsNumber(int number) {
        if (number > 0) mDialogColumnsNumber = number;
    }

    /**
     * @param amount The new dim amount, from 0 for no dim to 1 for full dim.
     */
    public void setDimAmount(float amount) {
        mDialogDimAmount = amount;
    }

    /**
     * @param listener OnReactionChangeListener to listen when user click on ReactButton
     */
    public void setOnReactionChangeListener(OnReactionChangeListener listener) {
        mOnReactionChangeListener = listener;
    }

    /**
     * @param listener OnReactionDialogStateListener to listen when reaction dialog open or closed
     */
    public void setOnReactionDialogStateListener(OnReactionDialogStateListener listener) {
        mOnReactionDialogStateListener = listener;
    }

    /**
     * @return true if current reaction type is default
     */
    public boolean isDefaultReaction() {
        return !isReactButtonUpdated;
    }

    @Override
    public void onClick(View view) {
        onReactionButtonClick();
    }

    @Override
    public boolean onLongClick(View view) {
        showReactionsDialog();
        return true;
    }
}
