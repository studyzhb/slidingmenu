package com.coolshow.slidingmenu.fragment;

import java.util.ArrayList;
import java.util.List;

import com.coolshow.slidingmenu.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class HomeFragment extends Fragment implements OnItemClickListener{
	

	public HomeFragment() {
		
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.homefragment, null);
		return view;
	}
	@Override
	public void onPause() {
		
		super.onPause();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		
		
	}
}
