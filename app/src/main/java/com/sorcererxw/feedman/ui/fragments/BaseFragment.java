package com.sorcererxw.feedman.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sorcererxw.feedman.ui.activities.BaseActivity;

import butterknife.ButterKnife;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/9/1
 */
public abstract class BaseFragment extends Fragment {
    protected abstract int getLayoutId();

    protected abstract void initView(View view, Bundle saveInstance);

    private BaseActivity mActivity;

    protected BaseActivity getHoldingActivity() {
        return mActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle saveInstance) {
        View view = inflater.inflate(getLayoutId(), parent, false);
        ButterKnife.bind(this, view);
        initView(view, saveInstance);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            mActivity = (BaseActivity) context;
        }
    }

    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getHoldingActivity().addFragment(fragment);
        }
    }

    protected void removeFragment() {
        getHoldingActivity().removeFragment();
    }

}