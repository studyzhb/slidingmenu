package com.coolshow.slidingmenu.adapter;

import java.util.List;

import com.coolshow.slidingmenu.R;
import com.coolshow.slidingmenu.bean.Bean;
import com.coolshow.slidingmenu.utils.CommonAdapter;
import com.coolshow.slidingmenu.utils.ViewHolder;

import android.content.Context;

public class MenuAdapter extends CommonAdapter<Bean> {

	public MenuAdapter(Context context, List<Bean> listData) {
		super(context, listData);
	}

	@Override
	public void convert(ViewHolder holder, Bean t) {
		holder.setText(R.id.textView1,t.getCount() );
		holder.setImageResouceId(R.id.imageView1, t.getResourceId());
		
	}
	

}
