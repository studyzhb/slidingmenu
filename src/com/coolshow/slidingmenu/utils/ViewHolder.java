package com.coolshow.slidingmenu.utils;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	/**
	 * 用一个Map来存储如果Key为Int值时使用SparseArray
	 */
	
	private SparseArray<View> mViews;
	private int position;
	private View mconvertView;
	public ViewHolder(Context context,int layoutID,int position,ViewGroup parent) {
		this.position=position;
		this.mViews=new SparseArray<View>();
		this.mconvertView=LayoutInflater.from(context).inflate(layoutID, parent, false);
		mconvertView.setTag(this);
	}
	
	public static ViewHolder get(Context context,View convertView,int layoutID,int position,ViewGroup parent){
		if(convertView==null){
			return new ViewHolder(context,layoutID,position,parent);
		}else{
			ViewHolder holder=(ViewHolder) convertView.getTag();
			holder.position=position;
			return holder;
		}
	}
	
	public <T extends View> T getView(int viewId){
		View view=mViews.get(viewId);
		if(view==null){
			view=mconvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T)view;
	}

	public View getMconvertView() {
		return mconvertView;
	}

	public ViewHolder setText(int viewId,String text){
		TextView tv=getView(viewId);
		tv.setText(text);
		return this;	
	}
	public ViewHolder setImageResouceId(int viewId,int resId){
		ImageView iv=getView(viewId);
		iv.setImageResource(resId);
		return this;
	}

	
}
