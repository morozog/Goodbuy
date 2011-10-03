package com.spotlight.Goodbuy;

import java.util.ArrayList;
import java.util.List;

import com.spotlight.Goodbuy.Entities.CartItemEntity;
import com.spotlight.Goodbuy.Entities.Entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CartArrayAdapter extends ArrayAdapter<Entity>{
	//private ImageView shopIcon;
	private TextView cartItemName;
	private TextView cartItemQuantity;
	private TextView cartItemId; 
	
	private List<Entity> cartItems = new ArrayList<Entity>();
	
	public CartArrayAdapter(Context context, int textViewResourceId,
			List<Entity> objects) {
		super(context, textViewResourceId, objects);
		this.cartItems = objects;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public CartItemEntity getItem(int position) {
		return (CartItemEntity)this.cartItems.get(position);
	}
	@Override
	public int getCount() {
		return cartItems.size();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if(row == null)
		{
			// ROW INFLATION
			LayoutInflater inflater = (LayoutInflater) this.getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.cart_item, parent, false);
		}
		CartItemEntity cartItem = getItem(position);
		cartItemName = (TextView) row.findViewById(R.id.cart_item_name);
		cartItemQuantity = (TextView) row.findViewById(R.id.cart_item_quantity);
		cartItemId = (TextView) row.findViewById(R.id.cart_item_id);
		
		cartItemName.setText(cartItem.getProdName());
		cartItemQuantity.setText(Integer.toString(cartItem.getQuantity()));
		cartItemId.setText(Integer.toString(cartItem.getId()));
		
		return row;
	}
	
}

