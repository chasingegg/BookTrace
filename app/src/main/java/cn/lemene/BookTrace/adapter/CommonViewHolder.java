package cn.lemene.BookTrace.adapter;

import android.view.View;


public abstract class CommonViewHolder<T> {
	protected View view;

	/**
     * 初始化 UI 组件
     */
    public abstract void initView();
    
    /**
     * 初始化事件回调，比如注册点击事件
     */
    public abstract void initListener(int position, T item);
    
    /**
     * 更新 UI 组件
     * @param position Item 的位置
     * @param item
     */
    public abstract void updateView(int position, T item);
}
