package com.spotlight.Goodbuy;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.spotlight.Goodbuy.Entities.*;

public class ShopArrayAdapter extends ArrayAdapter<Entity> {
	private static final String tag = "ShopArrayAdapter";
	private ImageView shopIcon;
	private TextView shopName;
	private TextView shopDesc;
	private TextView shopContacts;
	private TextView shopId;
	
	private List<Entity> shops = new ArrayList<Entity>();

	public ShopArrayAdapter(Context context, int textViewResourceId,
			List<Entity> objects) {
		super(context, textViewResourceId, objects);
		this.shops = objects;
	}

	public int getCount() {
		return this.shops.size();
	}

	public ShopEntity getItem(int index) {
		return (ShopEntity)this.shops.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.shop_list_item, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		ShopEntity shop = getItem(position);

		// Get reference to ImageView
		shopIcon = (ImageView) row.findViewById(R.id.shop_icon);

		// Get reference to TextView - country_name
		shopName = (TextView) row.findViewById(R.id.shop_name);
		
		shopDesc = (TextView) row.findViewById(R.id.shop_desc);
		shopContacts = (TextView) row.findViewById(R.id.shop_contacts);
		shopId = (TextView) row.findViewById(R.id.shop_id);

		//Set country name
		shopName.setText(shop.getName());
		shopDesc.setText(shop.getDescription());
		shopContacts.setText(shop.getAddress()+" "+shop.getTelephone());
		shopId.setText(Integer.toString(shop.getId()));

		
		// Set country icon using File path
		String imgFilePath = shop.getImage();
		try {
			URL imageURL = new URL(imgFilePath);
			Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
			shopIcon.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return row;

	}
}