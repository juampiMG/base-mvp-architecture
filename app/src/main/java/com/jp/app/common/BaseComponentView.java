package com.jp.app.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import butterknife.ButterKnife;

public abstract class BaseComponentView extends FrameLayout {

    public BaseComponentView(Context context) {

        super(context);
        init(context, null);
    }

    public BaseComponentView(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context, attrs);
    }

    public BaseComponentView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {

        if (!isInEditMode()) {
            loadView(context);
            loadAttributes(context, attrs);
            bindViews();
            loadData();
        }
    }

    protected void loadView(Context context) {

        View.inflate(context, getLayoutId(), this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        if (isInEditMode()) {
            loadView(getContext());
        }
    }

    protected void bindViews() {}

    protected abstract void loadAttributes(Context context, AttributeSet attrs);

    protected abstract void loadData();

    protected abstract int getLayoutId();


}
