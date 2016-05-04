package com.coolshow.slidingmenu;

import java.util.ArrayList;
import java.util.List;

import com.coolshow.slidingmenu.adapter.MenuAdapter;
import com.coolshow.slidingmenu.bean.Bean;
import com.coolshow.slidingmenu.fragment.HomeFragment;
import com.coolshow.slidingmenu.view.LeftMenu;
import com.coolshow.slidingmenu.view.LeftMenu.LeftMenuListener;
import com.coolshow.slidingmenu.view.SlidingMenu;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private SlidingMenu mSlidingMenu;
	private FragmentManager manager;
	private FragmentTransaction transaction;
	private List<Bean> list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initData();
		mSlidingMenu=(SlidingMenu) findViewById(R.id.menu_id);
		ListView listView_left=(ListView)findViewById(R.id.leftmenu_listview);
		listView_left.setAdapter(new MenuAdapter(this,list));
		listView_left.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(MainActivity.this, position+"click", Toast.LENGTH_SHORT).show();
				
			}
			
		});
	}

	private void initData() {
		
		list=new ArrayList<Bean>();
		list.add(new Bean(R.drawable.img_1,"1"));
		list.add(new Bean(R.drawable.img_2,"2"));
		list.add(new Bean(R.drawable.img_3,"3"));
		list.add(new Bean(R.drawable.img_4,"4"));
		list.add(new Bean(R.drawable.img_5,"5"));
	}

	public void toogleMenu(View view){
		mSlidingMenu.toggle();
	}
}
