package com.jookershop.linefriend.place;



import java.util.HashMap;

import com.jookershop.linefriend.Constants;
import com.jookershop.linefriend.MainActivity;
import com.jookershop.linefriend3buy.R;
import com.jookershop.linefriend3buy.R.drawable;
import com.jookershop.linefriend3buy.R.id;
import com.jookershop.linefriend3buy.R.layout;
import com.jookershop.linefriend.friend.AllFriendFragment;
import com.jookershop.linefriend.friend.FriendFragment;
import com.jookershop.linefriend.subcategory.SubCategoryFragment;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;


public class PlaceAdapter extends BaseAdapter {
	    private Context mContext;
	    private HashMap<String, Integer> count = new HashMap<String, Integer>();
	    
	    public PlaceAdapter(Context c){
	        mContext = c;
	    }
	 
	    @Override
	    public int getCount() {
	        return Constants.PLACE_THUMBNAIL_IDS.length;
	    }
	 
	    @Override
	    public Object getItem(int position) {
	        return Constants.PLACE_THUMBNAIL_IDS[position];
	    }
	 
	    @Override
	    public long getItemId(int position) {
	        return 0;
	    }
	 
	    @Override
	    public View getView(final int position, View convertView, ViewGroup parent) {
	    	View row =convertView;
	    	
//	    	if(row == null) {
	    		LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
          		row = inflater.inflate(R.layout.category_item, parent, false);
//	    	}
	    	
          	ImageView iv = (ImageView) row.findViewById(R.id.imageView1);
//          	iv.setImageResource(Constants.PLACE_THUMBNAIL_IDS[position]);
	    	MainActivity.imageLoader.displayImage("drawable://" + Constants.PLACE_THUMBNAIL_IDS[position], 
	    			iv, MainActivity.options, new SimpleImageLoadingListener(), null);
	    	
          	TextView title = (TextView) row.findViewById(R.id.title);
          	title.setText(Constants.PLACE_TITLES[position]);

          	if(count.containsKey(Constants.PLACE_IDS[position])) {
          		TextView countTv = (TextView) row.findViewById(R.id.counttv);
          		countTv.setText("線友人數:" + count.get(Constants.PLACE_IDS[position]) + "人");
          	}

          	
          	final String detailTitle = mContext.getString(R.string.title_section2) + "/" + Constants.PLACE_TITLES[position];
          	row.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					((FragmentActivity)mContext).getSupportFragmentManager()
					.beginTransaction()
					.replace(R.id.container,
							SubCategoryFragment.newInstance(Constants.TYPE_PLACE, Constants.PLACE_IDS[position]))
							.addToBackStack("place").commit();
				}
			});          	
//          	row.setBackgroundColor(Color.parseColor(Constants.PLACE_COLOR));
	        return row;
	    }
	    
	 public void addCount(String categoryId, int count)  {
		 this.count.put(categoryId, count);
	 }
	 
}

