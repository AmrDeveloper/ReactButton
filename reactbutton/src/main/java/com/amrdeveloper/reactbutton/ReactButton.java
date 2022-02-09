/*
 * MIT License
 *
 * Copyright (c) 2018 AmrDeveloper (Amr Hesham)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.amrdeveloper.reactbutton;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ReactButton custom class based on Button View
 *
 * Provide like and unlike feature depend on use single click
 * and reactions dialog feature when user provide long click event
 */
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
     * The current gravity for the reactions dialog
     */
    private int currentDialogGravity = Gravity.TOP | Gravity.START;

    /**
     *  Enable/Disable showing the reactions dialog in relative position to the react button
     */
    private boolean enableDialogDynamicPosition = true;

    /**
     * Enable/Disable the reactions tooltip feature
     */
    private boolean enableReactionTooltip = false;

    /**
     * The offset between tooltip and the reaction icon
     */
    private int mTooltipOffsetFromReaction = 100;

    /**
     * The default color for React tooltip text
     */
    @ColorInt private int mReactTooltipTextColor = Color.WHITE;

    /**
     * The default drawable shape for tooltip text
     */
    @DrawableRes private int mReactTooltipShape = R.drawable.react_tooltip_shape;

    /**
     * The min height of tooltip
     */
    private static final int TOOLTIP_VIEW_MIN_HEIGHT = 50;

    /**
     * Reaction image view padding
     */
    private static final int ICON_PADDING = 10;

    /**
     * The size of reaction icon in dp
     * Icon size + icon padding * 2
     */
    private static final int ICON_SIZE_WITH_PADDING = 45 + ICON_PADDING;

    /**
     * Full reaction icon size converted from dp
     */
    private final int REACTION_ICON_SIZE = (ICON_SIZE_WITH_PADDING *
            getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);

    /**
     * The maximum width of the screen in pixels
     */
    private final int SCREEN_MAX_WIDTH = getResources().getDisplayMetrics().widthPixels;

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
        reactionsGrid.setNumColumns(mDialogColumnsNumber);

        reactionsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Reaction currentReaction = mReactions.get(position);
                updateReactButtonByReaction(currentReaction);
                mReactAlertDialog.cancel();
            }
        });

        if (enableReactionTooltip) {
            final PopupWindow[] popupWindow = new PopupWindow[1];
            reactionsGrid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                    Reaction currentReaction = mReactions.get(position);

                    View tooltipView = LayoutInflater.from(context).inflate(R.layout.react_tooltip_layout, null);
                    tooltipView.measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    tooltipView.setBackgroundResource(mReactTooltipShape);

                    TextView tooltipTextView = tooltipView.findViewById(R.id.react_tooltip_text);
                    tooltipTextView.setTextColor(mReactTooltipTextColor);
                    tooltipTextView.setText(currentReaction.getReactText());

                    popupWindow[0] = new PopupWindow(tooltipView, tooltipView.getMeasuredWidth(), tooltipView.getMeasuredHeight(), true);
                    popupWindow[0].setOutsideTouchable(true);

                    final int[] viewLocation = new int[2];
                    view.getLocationOnScreen(viewLocation);
                    float xOffset = viewLocation[0];
                    float yOffset = viewLocation[1] - mTooltipOffsetFromReaction;

                    if (yOffset <= TOOLTIP_VIEW_MIN_HEIGHT) yOffset += mTooltipOffsetFromReaction * 2 + TOOLTIP_VIEW_MIN_HEIGHT;

                    popupWindow[0].showAtLocation(tooltipView, Gravity.NO_GRAVITY, (int) xOffset, (int) yOffset);
                    return false;
                }
            });

            reactionsGrid.setOnTouchListener(new OnTouchListener() {

                @SuppressLint("ClickableViewAccessibility")
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                       if (popupWindow[0] != null) popupWindow[0].dismiss();
                    }
                    return false;
                }
            });
        }

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setView(dialogView);
        mReactAlertDialog = dialogBuilder.create();
        mReactAlertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window window = mReactAlertDialog.getWindow();
        window.setBackgroundDrawableResource(mReactDialogShape);
        window.setDimAmount(mDialogDimAmount);

        // Setup dialog gravity and dynamic position
        WindowManager.LayoutParams windowManagerAttributes = window.getAttributes();
        windowManagerAttributes.gravity = currentDialogGravity;

        int dialogWidth = REACTION_ICON_SIZE * mDialogColumnsNumber;
        if (dialogWidth > SCREEN_MAX_WIDTH) dialogWidth = SCREEN_MAX_WIDTH;

        if (enableDialogDynamicPosition) {
            // Calculate the x and y positions only if the flag is enable
            final Rect react = new Rect();
            getGlobalVisibleRect(react);

            // Can be optimized and calculated once and modified only when size changed
            // Calculate x and y from global visible position to work also in Jetpack Compose
            windowManagerAttributes.x = react.left + react.width() / 2 - dialogWidth / 2;
            windowManagerAttributes.y = react.top - react.height() * 2;
        }

        mReactAlertDialog.show();

        if (mOnReactionDialogStateListener != null) mOnReactionDialogStateListener.onDialogOpened();

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
     * Modify the reactions dialog layout shape
     * @param drawableShape the new xml shape
     */
    public void setReactionDialogShape(@DrawableRes int drawableShape) {
        this.mReactDialogShape = drawableShape;
    }

    /**
     * Modify the reactions dialog gravity value, by default it Top | Start
     * @param gravity the new gravity
     * @since 2.1.0
     */
    public void setReactionDialogGravity(int gravity) {
        currentDialogGravity = gravity;
    }

    /**
     * Enable/Disable the reactions dialog dynamic position
     * @param enable True to enable the relative position
     * @since 2.1.0
     */
    public void enableReactionDialogDynamicPosition(boolean enable) {
        enableDialogDynamicPosition = enable;
    }

    /**
     * Modify the tooltip offset from the reaction icon
     * @param offset the new tooltip offset value
     */
    public void setTooltipOffsetFromReaction(int offset) {
        mTooltipOffsetFromReaction = offset;
    }

    /**
     * Modify the tooltip text color
     * @param color the new text color value
     */
    public void setReactionTooltipTextColor(@ColorInt int color) {
        mReactTooltipTextColor = color;
    }

    /**
     * Modify the tooltip layout shape
     * @param drawableShape the new xml shape
     */
    public void setReactionTooltipShape(@DrawableRes int drawableShape) {
        mReactTooltipShape = drawableShape;
    }

    /**
     * Enable or disable the reactions tooltip feature
     * @param isEnable True to enable reactions tooltip feature
     */
    public void setEnableReactionTooltip(boolean isEnable) {
        enableReactionTooltip = isEnable;
    }

    /**
     * Set the reactions list to new reactions
     * @param reactions Array of Reactions
     */
    public void setReactions(Reaction... reactions) {
        mReactions.clear();
        addReactions(reactions);
    }

    /**
     * Append new reactions to the current reactions list
     * @param reactions Array of Reactions
     * @since 2.1.0
     */
    public void addReactions(Reaction... reactions) {
        mReactions.addAll(Arrays.asList(reactions));
        if (mDialogColumnsNumber == 0)
            mDialogColumnsNumber = mReactions.size();
    }

    /**
     * Modify the current reactions with new one
     * @param reaction new reactions to set as a current reaction
     */
    public void setCurrentReaction(Reaction reaction) {
        updateReactButtonByReaction(reaction);
    }

    /**
     * Return the current reaction object
     * @return The Current reaction
     */
    public Reaction getCurrentReaction() {
        return mCurrentReaction;
    }

    /**
     * Update the default Reaction by other Reaction
     * @param reaction the new reaction be to set as a default reaction
     */
    public void setDefaultReaction(Reaction reaction) {
        mDefaultReaction = reaction;
        mCurrentReaction = mDefaultReaction;
        updateReactButtonByReaction(mDefaultReaction);
    }

    /**
     * Return the default reaction object
     * @return The default Reaction
     */
    public Reaction getDefaultReaction() {
        return mDefaultReaction;
    }

    /**
     * Modify the columns number for the reactions dialog
     * @param number the new columns number valud
     */
    public void setDialogColumnsNumber(int number) {
        if (number > 0) mDialogColumnsNumber = number;
    }

    /**
     * Modify the reactions dialog dim amount value
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
