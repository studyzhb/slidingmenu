package com.coolshow.slidingmenu.utils;

import java.util.List;

import com.coolshow.slidingmenu.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter {
	
	protected List<T> list;
	protected Context context;
	protected LayoutInflater inflater;

	public CommonAdapter(Context context,List<T> listData) {
		this.list=listData;
		this.context=context;
		inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder=ViewHolder.get(context, convertView, R.layout.content_item, position, parent);
		convert(holder, getItem(position));
		return holder.getMconvertView();
	}
	
	public abstract void convert(ViewHolder holder,T t);
}
