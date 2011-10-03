package com.spotlight.Goodbuy;

import java.util.ArrayList;
import java.util.List;

import com.spotlight.Goodbuy.Entities.CategoryEntity;
import com.spotlight.Goodbuy.Entities.Entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoriesArrayAdapter extends ArrayAdapter<Entity>{
	//private ImageView shopIcon;
	private TextView catName;
	private TextView productAmount;
	private TextView categoryId; 
	
	private List<Entity> categories = new ArrayList<Entity>();
	
	public CategoriesArrayAdapter(Context context, int textViewResourceId,
			List<Entity> objects) {
		super(context, textViewResourceId, objects);
		this.categories = objects;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CategoryEntity getItem(int position) {
		return (CategoryEntity)this.categories.get(position);
	}
	@Override
	public int getCount() {
		return categories.size();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(row == null)
		{
			// ROW INFLATION
			LayoutInflater inflater = (LayoutInflater) this.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.shop_category, parent, false);
		}
		CategoryEntity catEnt = getItem(position);
		catName = (TextView) row.findViewById(R.id.catName);
		productAmount = (TextView) row.findViewById(R.id.prodAmount);
		categoryId = (TextView) row.findViewById(R.id.category_id);
		
		catName.setText(catEnt.getName());
		productAmount.setText(catEnt.getDescription());
		categoryId.setText(Integer.toString(catEnt.getId()));
		
		return row;
	}
	
}
