package com.coolshow.slidingmenu.view;

import java.util.ArrayList;
import java.util.List;

import com.coolshow.slidingmenu.R;
import com.coolshow.slidingmenu.adapter.MenuAdapter;
import com.coolshow.slidingmenu.bean.Bean;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class LeftMenu extends RelativeLayout implements OnItemClickListener{
	
	private ListView listView;
	private LeftMenuListener listener;
//	private List<Bean> list;
	public LeftMenu(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}
	public interface LeftMenuListener{
		public void clickWhich(int position);
	}
	public void setOnLeftMenuListener(LeftMenuListener listener){
		this.listener=listener;
	}
	public LeftMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.menu_sliding, this);
//		initData();
		listView=(ListView) findViewById(R.id.leftmenu_listview);
//		listView.setAdapter(new MenuAdapter(context, list));
//		listView.setOnItemClickListener(this);
	}

//	private void initData() {
//		list=new ArrayList<Bean>();
//		list.add(new Bean(R.drawable.img_1,"1"));
//		list.add(new Bean(R.drawable.img_2,"2"));
//		list.add(new Bean(R.drawable.img_3,"3"));
//		list.add(new Bean(R.drawable.img_4,"4"));
//		list.add(new Bean(R.drawable.img_5,"5"));
//		
//	}
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		listener.clickWhich(position);
		
	}



}
