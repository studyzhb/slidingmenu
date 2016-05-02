package com.coolshow.slidingmenu.view;

import com.coolshow.slidingmenu.R;
import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.content.res.TypedArray;
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
	
	private boolean isOpen;
	public SlidingMenu(Context context, AttributeSet attrs) {
		this(context, attrs,0);

	}

	public SlidingMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// 获取属性
		TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.slidingMenu, defStyle, 0);
		int sum = a.getIndexCount();// 获取自定义属性的数量
		for (int i = 0; i < sum; i++) {
			int attr = a.getIndex(i);
			switch (attr) {
			case R.styleable.slidingMenu_rightPadding:
				menuRightPadding=a.getDimensionPixelSize(attr, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics()));
				break;

			default:
				break;
			}
		}
		a.recycle();// 释放
		// 获取屏幕的尺寸
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);

		mScreenWidth = outMetrics.widthPixels;
		// 将dp转换为PX
//		TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
	}

	public SlidingMenu(Context context) {
		this(context, null);

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
				isOpen=false;
			} else {
				this.smoothScrollTo(0, 0);
				isOpen=true;
			}

			return true;

		default:
			break;
		}

		return super.onTouchEvent(ev);
	}

	
	/**
	 * 属性动画 translationx
	 * mMenu.setTranslationX(l);兼容到3.0，或者ViewHelper.setTransklationX(mMenu, l)；兼容到2.0，//抽屉
	 * 动画效果 区别1：内容区域1.0~0.7 缩放的效果 scale : 1.0~0.0 0.7 + 0.3 * scale
	 * 
	 * 区别2：菜单的偏移量需要修改0.0-1.0
	 * 
	 * 区别3：菜单的显示时有缩放以及透明度变化 缩放：0.7 ~1.0 1.0 - scale * 0.3 透明度0.6 ~ 1.0
	 *  0.6+ 0.4* (1- scale) ;
	 * 滚动发生时 触发此方法
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		// TODO Auto-generated method stub
		super.onScrollChanged(l, t, oldl, oldt);
		//l就是getscrollX,设置拉出来的偏移量
		float scale=l*1.0f/mMenuWidth;
		
		//内容区域偏移，但是由于移动的时候缩放就造成偏移完成后内容区域可能会消失
		//解决办法就是让其缩放的焦点放在左边的Y轴上就可以了，便宜后左侧距离不变
		float rightScale=0.7f+0.3f*scale;
		//左侧菜单区域缩放以及透明度偏移量
		float leftScale=1.0f-scale*0.3f;
		float leftAlpha=0.5f+0.4f*(1f-scale);
		ViewHelper.setTranslationX(mContent, rightScale);
		ViewHelper.setTranslationY(mContent, rightScale);
		//伸缩相对于x,y的位置(焦点在左侧高度的一半)
		ViewHelper.setPivotX(mContent, 0);
		ViewHelper.setPivotY(mContent, mContent.getHeight()/2);
		//左侧缩放
		ViewHelper.setTranslationX(mMenu, mMenuWidth*scale*0.7f);//偏移量
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		
		
	}
	
	public void openMenu(){
		if(isOpen){
			return;
		}
		this.smoothScrollTo(0, 0);
		isOpen=true;
	}
	public void closeMenu(){
		if(!isOpen){
			return;
		}
		this.smoothScrollTo(mMenuWidth, 0);
		isOpen=false;
	}
	/**
	 * 切换菜单
	 */
	public void toggle(){
		if(isOpen){
			closeMenu();
		}else{
			openMenu();
			}
	}
}
