package com.waspar.falert;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.mikhaellopez.circularimageview.CircularImageView;

@SuppressLint("ValidFragment")
public class Falert extends DialogFragment implements View.OnClickListener {

    private Context context;
    private int buttonType;
    private View roottt , view , icon , buttonRoot;
    private TextView positiveSingleButton, negativeButton, positiveButton;
    private FrameLayout frameLayout;
    private CircularImageView imageView;

    private GradientDrawable positiveButtonBackground;
    private GradientDrawable negativeButtonBackground;
    private GradientDrawable positiveSingleButtonBackground;
    private SingleButtonListener singleButtonListener;
    private DoubleButtonListener doubleButtonListener;
    private boolean autoDismiss = true;
    private boolean iconEnable = true;
    private boolean buttonEnable = true;
    private boolean cancelable = true;
    private String positiveText = null;
    private String negativeText = null;
    private int alertRadius = 40;
    private int buttonRadius = 80;
    private View customView;
    private Drawable iconDrawable = null;
    private int positiveButtonColor = 0;
    private int negativeButtonColor = 0;
    private int singleButtonColor = 0;
    private int PositiveButtonTextColor = 0;
    private int NegativeButtonTextColor = 0;
    private int SingleButtonTextColor = 0;
    private int strokeButtonsSize = 2;
    private int strokePositiveButtonColor = 0;
    private int strokeNegativeButtonColor = 0;
    private int strokeSingleButtonColor = 0;
    private float buttonTextSize = 13;
    private Typeface typeFace;

    public Falert(Context context) {
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_falert, container, false);
        show();
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setCancelable(cancelable);
        return view;
    }

    private void init() {
        if (view == null){
            view = View.inflate(this.context, R.layout.layout_falert, (ViewGroup)null);
        }
        roottt = view.findViewById(R.id.root);
        positiveSingleButton = view.findViewById(R.id.falert_single_button);
        negativeButton = view.findViewById(R.id.falert_negative_button);
        positiveButton = view.findViewById(R.id.falert_positive_button);
        frameLayout = view.findViewById(R.id.frameLayoutFalert);
        imageView = view.findViewById(R.id.falert_icon);
        icon = view.findViewById(R.id.frameLayout2);
        buttonRoot = view.findViewById(R.id.button_root);


        if (typeFace != null){
            positiveButton.setTypeface(typeFace);
            negativeButton.setTypeface(typeFace);
            positiveSingleButton.setTypeface(typeFace);
        }

        positiveButton.setTextSize(buttonTextSize);
        negativeButton.setTextSize(buttonTextSize);
        positiveSingleButton.setTextSize(buttonTextSize);

        if (PositiveButtonTextColor != 0){
            positiveButton.setTextColor(PositiveButtonTextColor);
        }
        if (NegativeButtonTextColor != 0){
            negativeButton.setTextColor(NegativeButtonTextColor);
        }
        if (SingleButtonTextColor != 0){
            positiveSingleButton.setTextColor(SingleButtonTextColor);
        }
    }

    private void actionSetIcon() {
        imageView.setImageDrawable(iconDrawable);
    }

    private void actionSetCustomView() {
        frameLayout.addView(customView);
    }

    private void actionSingleButtun() {
        negativeButton.setVisibility(View.GONE);
        positiveButton.setVisibility(View.GONE);
        positiveSingleButton.setVisibility(View.VISIBLE);

        positiveSingleButton.setOnClickListener(this);

        if (positiveText != null){
            positiveSingleButton.setText(positiveText);
        }

        if (positiveSingleButtonBackground == null){
            positiveSingleButtonBackground = new GradientDrawable();
            positiveSingleButtonBackground.setShape(GradientDrawable.RECTANGLE);
            positiveSingleButtonBackground.setColor(context.getResources().getColor(R.color.falert_green));
            positiveSingleButtonBackground.setStroke(strokeButtonsSize, context.getResources().getColor(R.color.falert_white));
            if (singleButtonColor != 0){
                positiveSingleButtonBackground.setColor(singleButtonColor);
            }
            if (strokeSingleButtonColor != 0){
                positiveSingleButtonBackground.setStroke(strokeButtonsSize, strokeSingleButtonColor);
            }
            positiveSingleButtonBackground.setCornerRadius(buttonRadius);
        }
        positiveSingleButton.setBackground(positiveSingleButtonBackground);
    }

    private void actionDoubleButtun() {
        negativeButton.setVisibility(View.VISIBLE);
        positiveButton.setVisibility(View.VISIBLE);
        positiveSingleButton.setVisibility(View.GONE);

        negativeButton.setOnClickListener(this);
        positiveButton.setOnClickListener(this);

        if (positiveText != null){
            positiveButton.setText(positiveText);
        }

        if (negativeText != null){
            negativeButton.setText(negativeText);
        }


        if (negativeButtonBackground == null){
            negativeButtonBackground = new GradientDrawable();
            negativeButtonBackground.setShape(GradientDrawable.RECTANGLE);
            negativeButtonBackground.setColor(context.getResources().getColor(R.color.falert_red));
            negativeButtonBackground.setStroke(strokeButtonsSize, context.getResources().getColor(R.color.falert_white));
            if (negativeButtonColor != 0){
                negativeButtonBackground.setColor(negativeButtonColor);
            }
            if (strokeNegativeButtonColor != 0){
                negativeButtonBackground.setStroke(strokeButtonsSize, strokeNegativeButtonColor);
            }
            negativeButtonBackground.setCornerRadius(buttonRadius);
        }
        negativeButton.setBackground(negativeButtonBackground);


        if (positiveButtonBackground == null){
            positiveButtonBackground = new GradientDrawable();
            positiveButtonBackground.setShape(GradientDrawable.RECTANGLE);
            positiveButtonBackground.setColor(context.getResources().getColor(R.color.falert_green));
            positiveButtonBackground.setStroke(strokeButtonsSize, context.getResources().getColor(R.color.falert_white));
            if (positiveButtonColor != 0){
                positiveButtonBackground.setColor(positiveButtonColor);
            }
            if (strokePositiveButtonColor != 0){
                positiveButtonBackground.setStroke(strokeButtonsSize, strokePositiveButtonColor);
            }
            positiveButtonBackground.setCornerRadius(buttonRadius);
        }
        positiveButton.setBackground(positiveButtonBackground);
    }

    public void dismissAlert(){
        dismiss();
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.falert_negative_button) {
            doubleButtonListener.onClickNegative();
            if (autoDismiss){
                dismiss();
            }

        } else if (i == R.id.falert_positive_button) {
            doubleButtonListener.onClickPositive();
            if (autoDismiss){
                dismiss();
            }
        } else if (i == R.id.falert_single_button) {
            singleButtonListener.onClick();
            if (autoDismiss){
                dismiss();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT - 1;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        GradientDrawable bgShape = new GradientDrawable();

        bgShape.setCornerRadius(alertRadius);
        bgShape.setColor(getActivity().getResources().getColor(R.color.falert_white));
        roottt.setBackground(bgShape);
    }

    public Falert setButtonType(int buttonType) {
        this.buttonType = buttonType;
        return this;
    }

    public Falert setPositiveButtonBackground(GradientDrawable positiveButtonBackground) {
        this.positiveButtonBackground = positiveButtonBackground;
        return this;
    }

    public Falert setNegativeButtonBackground(GradientDrawable negativeButtonBackground) {
        this.negativeButtonBackground = negativeButtonBackground;
        return this;
    }

    public Falert setPositiveSingleButtonBackground(GradientDrawable positiveSingleButtonBackground) {
        this.positiveSingleButtonBackground = positiveSingleButtonBackground;
        return this;
    }

    public Falert setSingleButtonListener(SingleButtonListener singleButtonListener) {
        this.singleButtonListener = singleButtonListener;
        return this;
    }

    public Falert setDoubleButtonListener(DoubleButtonListener doubleButtonListener) {
        this.doubleButtonListener = doubleButtonListener;
        return this;
    }

    public Falert setAutoDismiss(boolean autoDismiss) {
        this.autoDismiss = autoDismiss;
        return this;
    }

    public Falert setPositiveText(String positiveText) {
        this.positiveText = positiveText;
        return this;
    }

    public Falert setNegativeText(String negativeText) {
        this.negativeText = negativeText;
        return this;
    }

    public Falert setAlertRadius(int alertRadius) {
        this.alertRadius = alertRadius;
        return this;
    }

    public Falert setButtonRadius(int buttonRadius) {
        this.buttonRadius = buttonRadius;
        return this;
    }

    public Falert customView(View customView) {
        this.customView = customView;
        return this;
    }

    public Falert setHeaderIcon(Drawable iconDrawable) {
        this.iconDrawable = iconDrawable;
        return this;
    }

    public Falert setPositiveButtonBackground(int positiveButtonColor) {
        this.positiveButtonColor = positiveButtonColor;
        return this;
    }

    public Falert setNegativeButtonBackground(int negativeButtonColor) {
        this.negativeButtonColor = negativeButtonColor;
        return this;
    }

    public Falert setSingleButtonBackground(int singleButtonColor) {
        this.singleButtonColor = singleButtonColor;
        return this;
    }

    public Falert setTypeFace(Typeface typeFace) {
        this.typeFace = typeFace;
        return this;
    }

    public Falert setHeaderIconEnable(boolean iconEnable) {
        this.iconEnable = iconEnable;
        return this;
    }

    public Falert setButtonEnable(boolean buttonEnable) {
        this.buttonEnable = buttonEnable;
        return this;
    }

    public Falert setButtonTextSize(float buttonTextSize) {
        this.buttonTextSize = buttonTextSize;
        return this;
    }

    public Falert setPositiveButtonTextColor(int PositiveButtonTextColor) {
        this.PositiveButtonTextColor = PositiveButtonTextColor;
        return this;
    }

    public Falert setNegativeButtonTextColor(int NegativeButtonTextColor) {
        this.NegativeButtonTextColor = NegativeButtonTextColor;
        return this;
    }

    public Falert setSingleButtonTextColor(int SingleButtonTextColor) {
        this.SingleButtonTextColor = SingleButtonTextColor;
        return this;
    }

    public Falert setStrokePositiveButtonColor(int strokePositiveButtonColor) {
        this.strokePositiveButtonColor = strokePositiveButtonColor;
        return this;
    }

    public Falert setStrokeNegativeButtonColor(int strokeNegativeButtonColor) {
        this.strokeNegativeButtonColor = strokeNegativeButtonColor;
        return this;
    }

    public Falert setstrokeButtonsSize(int strokeButtonsSize) {
        this.strokeButtonsSize = strokeButtonsSize;
        return this;
    }

    public Falert setStrokeSingleButtonColor(int strokeSingleButtonColor) {
        this.strokeSingleButtonColor = strokeSingleButtonColor;
        return this;
    }

    public Falert setCancelableTouchOutside(boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public Falert show() {

        //View v = View.inflate(this.context, R.layout.layout_falert, (ViewGroup)null);
        init();

        if (customView != null){
            actionSetCustomView();
        }

        if (iconDrawable != null){
            actionSetIcon();
        }

        if (buttonType == 1){
            actionSingleButtun();
        }else {
            actionDoubleButtun();
        }

        if (!iconEnable){
            icon.setVisibility(View.INVISIBLE);
        }

        if (!buttonEnable){
            positiveButton.setVisibility(View.GONE);
            negativeButton.setVisibility(View.GONE);
            positiveSingleButton.setVisibility(View.GONE);
            buttonRoot.setVisibility(View.GONE);
        }

        return this;
    }

}
