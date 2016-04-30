package com.coolshow.slidingmenu.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class SlidingMenu extends HorizontalScrollView {
	private LinearLayout mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private boolean once = true;
	private int mScreenWidth;
	private int mMenuWidth;
	// menu与屏幕右侧距离 dp
	private int menuRightPadding = 50;

	public SlidingMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		// 获取屏幕的尺寸
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		mScreenWidth = outMetrics.widthPixels;
		// 将dp转换为PX
		TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());

	}

	/**
	 * 子VIew的宽和高
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		if (once) {
			mWapper = (LinearLayout) getChildAt(0);
			mMenu = (ViewGroup) mWapper.getChildAt(0);
			mContent = (ViewGroup) mWapper.getChildAt(1);
			mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - menuRightPadding;
			mContent.getLayoutParams().width = mScreenWidth;
			once = false;
		}
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	/**
	 * 布局位置 设置偏移量让菜单栏隐藏
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			this.scrollTo(mMenuWidth, 0);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			// 隐藏在左边区域的宽度
			int scrollx = getScrollX();
			if (scrollx >= mMenuWidth / 2) {
				this.smoothScrollTo(mMenuWidth, 0);
			} else {
				this.smoothScrollTo(0, 0);
			}

			return true;

		default:
			break;
		}

		return super.onTouchEvent(ev);
	}

	/**
	 * 动画效果 区别1：内容区域1.0~0.7 缩放的效果 scale : 1.0~0.0 0.7 + 0.3 * scale
	 * 
	 * 区别2：菜单的偏移量需要修改
	 * 
	 * 区别3：菜单的显示时有缩放以及透明度变化 缩放：0.7 ~1.0 1.0 - scale * 0.3 透明度 0.6 ~ 1.0 0.6+ 0.4
	 * * (1- scale) ;
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
	}
}
