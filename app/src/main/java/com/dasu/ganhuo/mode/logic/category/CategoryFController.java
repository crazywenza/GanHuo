package com.dasu.ganhuo.mode.logic.category;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.dasu.ganhuo.mode.okhttp.GankController;
import com.dasu.ganhuo.mode.okhttp.RetrofitListener;
import com.dasu.ganhuo.ui.category.CategoryFragment;
import com.dasu.ganhuo.utils.LogUtils;

import java.util.List;

/**
 * Created by dasu on 2017/4/21.
 *
 * CategoryFragment对应的Controller
 */

public class CategoryFController {

    private static final String TAG = CategoryFController.class.getSimpleName();

    private Context mContext;
    private CategoryFragment mCategoryFragment;
    private String mCategoryType;

    public CategoryFController(Fragment fragment) {
        if (!(fragment instanceof CategoryFragment)) {
            LogUtils.e(TAG, TAG + "绑定错误的Fragment");
            throw new UnsupportedOperationException(TAG + "绑定错误的Fragment");
        }
        mContext = fragment.getContext();
        mCategoryFragment = (CategoryFragment) fragment;
        mCategoryType = mCategoryFragment.getCategoryType();
        if (TextUtils.isEmpty(mCategoryType)) {
            LogUtils.e(TAG, "CategoryFragment 必须实现ICategoryType接口，指定返回某一type类型");
            throw new UnsupportedOperationException("CategoryFragment 必须实现ICategoryType接口，指定返回某一type类型");
        }
    }

    public void loadBaseData() {
        GankController.getSpecifyGanHuo(mCategoryType, 1, new RetrofitListener<List<GanHuoEntity>>() {
            @Override
            public void onSuccess(List<GanHuoEntity> data) {
                mCategoryFragment.updateGanHuo(data);
            }

            @Override
            public void onError(String description) {
                mCategoryFragment.onLoadFailed();
            }
        });
    }

}