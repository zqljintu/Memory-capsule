package com.is.lib_util;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.StringRes;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.security.PublicKey;

/**
 * @author 夜斗
 * @date 2019/11/14
 */
public class ToolBarUtil {


    private Toolbar mToolbar;


    private ToolBarUtil() {

    }

    private ToolBarUtil(Toolbar toolbar) {
        mToolbar = toolbar;
    }

    public static ToolBarUtil Builder(Toolbar toolbar) {
        return new ToolBarUtil(toolbar);
    }

    /**
     * 设置toolBar中自定义的view
     *
     * @param viewId            viewId
     * @param toolBarCustomView 通过回调接口设置view的属性
     * @return
     */
    public ToolBarUtil setCustomView(@IdRes int viewId, ToolBarCustomView toolBarCustomView) {
        View customView = mToolbar.findViewById(viewId);
        if (toolBarCustomView != null && customView != null) {
            toolBarCustomView.customView(customView);
        }
        return this;
    }

    /**
     * @param title 设置标题
     */
    public ToolBarUtil setTitle(CharSequence title) {
        mToolbar.setTitle(title);
        return this;
    }

    /**
     * @param stringId 设置标题
     */
    public ToolBarUtil setTitle(@StringRes int stringId) {
        mToolbar.setTitle(stringId);
        return this;
    }

    /**
     * @param color 设置title文字颜色
     * @return
     */
    public ToolBarUtil setTitleColor(@ColorInt int color) {
        mToolbar.setTitleTextColor(color);
        return this;
    }


    /**
     * @param subtitle 设置副标题
     */
    public ToolBarUtil setSubtitle(CharSequence subtitle) {
        mToolbar.setSubtitle(subtitle);
        return this;
    }

    /**
     * @param color 设置副标题文字颜色
     * @return
     */
    public ToolBarUtil setSubTitleColor(@ColorInt int color) {
        mToolbar.setSubtitleTextColor(color);
        return this;
    }

    /**
     * @param navigationIcon 设置返回icon
     */
    public ToolBarUtil setNavigationIcon(@DrawableRes int navigationIcon) {
        mToolbar.setNavigationIcon(navigationIcon);
        return this;
    }

    /**
     * @param navigationIcon 设置返回icon
     */
    public ToolBarUtil setNavigationIcon(Drawable navigationIcon) {
        mToolbar.setNavigationIcon(navigationIcon);
        return this;
    }

    /**
     * @param background 设置背景
     */
    public ToolBarUtil setBackground(Drawable background) {
        mToolbar.setBackground(background);
        return this;
    }

    /**
     * @param color 设置背景颜色
     */
    public ToolBarUtil setBckgroundColor(@ColorInt int color) {
        mToolbar.setBackgroundColor(color);
        return this;
    }





    public interface ToolBarCustomView {
        void customView(View view);
    }

}
