package com.all.app.utils.views;
import android.view.View;
import android.view.ViewGroup;
public class CancelOnClickListener implements View.OnClickListener {

    public static final String TAG = "CancelOnClickListener";

    private View view;

    public CancelOnClickListener(View view) {
        this.view = view;
    }

    @Override
    public void onClick(View v) {

        ViewGroup viewGroup = (ViewGroup) view.getParent();
        viewGroup.removeView(view);

    }
}
