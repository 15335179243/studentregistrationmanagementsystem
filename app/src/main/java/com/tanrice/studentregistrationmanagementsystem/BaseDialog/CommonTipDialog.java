package com.tanrice.studentregistrationmanagementsystem.BaseDialog;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.tanrice.studentregistrationmanagementsystem.R;

import androidx.annotation.ColorInt;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CommonTipDialog extends BaseDialogFragment {


    @BindView(R.id.dialog_tip_title_tv)
    TextView mTitleTv;
    @BindView(R.id.dialog_tip_content_tv)
    TextView mContentTv;
    @BindView(R.id.dialog_tip_negative_tv)
    TextView mNegativeTv;
    @BindView(R.id.dialog_tip_positive_tv)
    TextView mPositiveTv;
    @BindView(R.id.dialog_tip_single_btn_tv)
    TextView mSingleBtnTv;
    /**
     * 设置带有style的Text时使用此属性，比如Html.fromHtml转的text
     */
    private CharSequence mCharSequenceText;

    private OnTipDialogListener mListener;
    private Option mOption;

    public static void showTip(FragmentManager fragmentManager,
                               Option option,
                               OnTipDialogListener listener) {
        showTip(fragmentManager, option, null, listener);
    }

    public static void showTip(FragmentManager fragmentManager,
                               Option option,
                               CharSequence charSequence,
                               OnTipDialogListener listener) {
        CommonTipDialog dialog = new CommonTipDialog();
        dialog.setOnTipDialogListener(listener);
        dialog.setCharSequenceText(charSequence);
        Bundle bundle = new Bundle();
        if (option != null)
            bundle.putParcelable("option", option);
        dialog.setArguments(bundle);
        dialog.showAllowingStateLoss(fragmentManager);
    }

    private void setOnTipDialogListener(OnTipDialogListener onTipDialogListener) {
        this.mListener = onTipDialogListener;
    }

    @Override
    public int getTheme() {
        return R.style.CommonTipDialogStyle;
    }

    @Override
    protected float getWidthPercent() {
        return 0.8f;
    }

    @Override
    protected float getDimAmount() {
        return 0.5f;
    }

    private void setCharSequenceText(CharSequence charSequenceText) {
        this.mCharSequenceText = charSequenceText;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.dialog_tip;
    }

    @Override
    public boolean shouldCancelOnBackPressed() {
        if (mOption != null)
            return mOption.cancelOnBackPressed;
        return super.shouldCancelOnBackPressed();
    }

    @Override
    public boolean shouldCancelOnTouchOutside() {
        if (mOption != null)
            return mOption.cancelOnTouchOutside;
        return super.shouldCancelOnTouchOutside();
    }

    @Override
    public void bindView(View v) {
        ButterKnife.bind(this, v);
        Bundle arguments = getArguments();
        if (arguments == null) {
            dismiss();
            return;
        }
        mOption = arguments.getParcelable("option");
        if (mOption == null) {
            mOption = new Option();
        }
        if (TextUtils.isEmpty(mOption.content) && TextUtils.isEmpty(mCharSequenceText)) {
            dismiss();
            return;
        }
        mTitleTv.setVisibility(TextUtils.isEmpty(mOption.title) ? View.GONE : View.VISIBLE);
        if (mOption.titleTextColor != Integer.MIN_VALUE) {
            mTitleTv.setTextColor(mOption.titleTextColor);
        }
        if (!TextUtils.isEmpty(mOption.title)) {
            mTitleTv.setText(mOption.title);
        }
        if (mOption.contentTextColor != Integer.MIN_VALUE) {
            mContentTv.setTextColor(mOption.contentTextColor);
        }
        if (mOption.contentTextGravity != -1) {
            mContentTv.setGravity(mOption.contentTextGravity);
        }
        if (!TextUtils.isEmpty(mCharSequenceText)) {
            mContentTv.setText(mCharSequenceText);
        } else {
            if (!TextUtils.isEmpty(mOption.content)) {
                mContentTv.setText(mOption.content);
            }
        }
        mSingleBtnTv.setVisibility(mOption.singleButton ? View.VISIBLE : View.GONE);
        if (mOption.singleButton) {
            if (mOption.singleButtonTextColor != Integer.MIN_VALUE) {
                mSingleBtnTv.setTextColor(mOption.singleButtonTextColor);
            }
            if (!TextUtils.isEmpty(mOption.singleButtonText)) {
                mSingleBtnTv.setText(mOption.singleButtonText);
            }
        } else {
            if (mOption.negativeTextColor != Integer.MIN_VALUE) {
                getNegativeTextButton().setTextColor(mOption.negativeTextColor);
            }
            if (!TextUtils.isEmpty(mOption.negativeText)) {
                getNegativeTextButton().setText(mOption.negativeText);
            }
            if (mOption.positiveTextColor != Integer.MIN_VALUE) {
                getPositiveTextButton().setTextColor(mOption.positiveTextColor);
            }
            if (!TextUtils.isEmpty(mOption.positiveText)) {
                getPositiveTextButton().setText(mOption.positiveText);
            }
        }
    }

    private TextView getPositiveTextButton() {
        if (mOption.reversePositiveNegative) {
            return mNegativeTv;
        }
        return mPositiveTv;
    }

    private TextView getNegativeTextButton() {
        if (mOption.reversePositiveNegative) {
            return mPositiveTv;
        }
        return mNegativeTv;
    }

    @OnClick({
            R.id.dialog_tip_negative_tv,
            R.id.dialog_tip_positive_tv,
            R.id.dialog_tip_single_btn_tv})
    public void onViewClicked(View view) {
        int i = view.getId();
        if (i == R.id.dialog_tip_negative_tv) {
            dismiss();
            if (mListener != null) {
                if (mOption.reversePositiveNegative) {
                    mListener.onPositiveClick();
                } else {
                    mListener.onNegativeClick();
                }
            }
        } else if (i == R.id.dialog_tip_positive_tv) {
            dismiss();
            if (mListener != null) {
                if (mOption.reversePositiveNegative) {
                    mListener.onNegativeClick();
                } else {
                    mListener.onPositiveClick();
                }
            }
        } else if (i == R.id.dialog_tip_single_btn_tv) {
            dismiss();
            if (mListener != null)
                mListener.onSingleButtonClick();
        }
    }

    public static abstract class OnTipDialogListener {

        public void onNegativeClick() {

        }

        public void onPositiveClick() {

        }

        public void onSingleButtonClick() {

        }

    }

    public static class Option implements Parcelable {

        private String title;
        private int titleTextColor = Integer.MIN_VALUE;
        private String content;
        private int contentTextColor = Integer.MIN_VALUE;
        private String negativeText;
        private int negativeTextColor = Integer.MIN_VALUE;
        private String positiveText;
        private int positiveTextColor = Integer.MIN_VALUE;
        private boolean singleButton;
        private String singleButtonText;
        private int singleButtonTextColor = Integer.MIN_VALUE;
        private int contentTextGravity = -1;

        private boolean cancelOnTouchOutside = true;
        private boolean cancelOnBackPressed = true;

        private boolean reversePositiveNegative;

        public Option() {
        }

        protected Option(Parcel in) {
            title = in.readString();
            titleTextColor = in.readInt();
            content = in.readString();
            contentTextColor = in.readInt();
            negativeText = in.readString();
            negativeTextColor = in.readInt();
            positiveText = in.readString();
            positiveTextColor = in.readInt();
            singleButton = in.readByte() != 0;
            singleButtonText = in.readString();
            singleButtonTextColor = in.readInt();
            contentTextGravity = in.readInt();
            cancelOnTouchOutside = in.readByte() != 0;
            cancelOnBackPressed = in.readByte() != 0;
            reversePositiveNegative = in.readByte() != 0;
        }

        public static final Creator<Option> CREATOR = new Creator<Option>() {
            @Override
            public Option createFromParcel(Parcel in) {
                return new Option(in);
            }

            @Override
            public Option[] newArray(int size) {
                return new Option[size];
            }
        };

        public Option setTitle(String title) {
            this.title = title;
            return this;
        }

        public Option setTitleTextColor(@ColorInt int titleTextColor) {
            this.titleTextColor = titleTextColor;
            return this;
        }

        public Option setContent(String content) {
            this.content = content;
            return this;
        }

        public Option setContentTextColor(@ColorInt int contentTextColor) {
            this.contentTextColor = contentTextColor;
            return this;
        }

        public Option setNegativeText(String negativeText) {
            this.negativeText = negativeText;
            return this;
        }

        public Option setNegativeTextColor(@ColorInt int negativeTextColor) {
            this.negativeTextColor = negativeTextColor;
            return this;
        }

        public Option setPositiveText(String positiveText) {
            this.positiveText = positiveText;
            return this;
        }

        public Option setPositiveTextColor(@ColorInt int positiveTextColor) {
            this.positiveTextColor = positiveTextColor;
            return this;
        }

        public Option setSingleButton(boolean singleButton) {
            this.singleButton = singleButton;
            return this;
        }

        public Option setSingleButtonText(String singleButtonText) {
            this.singleButtonText = singleButtonText;
            return this;
        }

        public Option setSingleButtonTextColor(@ColorInt int singleButtonTextColor) {
            this.singleButtonTextColor = singleButtonTextColor;
            return this;
        }

        public Option setContentTextGravity(int contentTextGravity) {
            this.contentTextGravity = contentTextGravity;
            return this;
        }

        public Option setCancelOnTouchOutside(boolean cancelOnTouchOutside) {
            this.cancelOnTouchOutside = cancelOnTouchOutside;
            return this;
        }

        public Option setCancelOnBackPressed(boolean cancelOnBackPressed) {
            this.cancelOnBackPressed = cancelOnBackPressed;
            return this;
        }

        public Option reversePositiveNegative() {
            this.reversePositiveNegative = true;
            return this;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeInt(titleTextColor);
            dest.writeString(content);
            dest.writeInt(contentTextColor);
            dest.writeString(negativeText);
            dest.writeInt(negativeTextColor);
            dest.writeString(positiveText);
            dest.writeInt(positiveTextColor);
            dest.writeByte((byte) (singleButton ? 1 : 0));
            dest.writeString(singleButtonText);
            dest.writeInt(singleButtonTextColor);
            dest.writeInt(contentTextGravity);
            dest.writeByte((byte) (cancelOnTouchOutside ? 1 : 0));
            dest.writeByte((byte) (cancelOnBackPressed ? 1 : 0));
            dest.writeByte((byte) (reversePositiveNegative ? 1 : 0));
        }
    }

}
