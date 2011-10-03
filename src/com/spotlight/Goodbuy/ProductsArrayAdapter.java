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

import com.spotlight.Goodbuy.Entities.Entity;
import com.spotlight.Goodbuy.Entities.ProductEntity;

public class ProductsArrayAdapter extends ArrayAdapter<Entity> {
	private static final String tag = "ProductArrayAdapter";
	private ImageView productIcon;
	private TextView productName;
	private TextView productCost;
	private TextView productAvailability;
	private TextView productId;
	
	private List<Entity> products = new ArrayList<Entity>();

	public ProductsArrayAdapter(Context context, int textViewResourceId,
			List<Entity> objects) {
		super(context, textViewResourceId, objects);
		this.products = objects;
	}

	public int getCount() {
		return this.products.size();
	}

	public ProductEntity getItem(int index) {
		return (ProductEntity)this.products.get(index);
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		if (row == null) {
			// ROW INFLATION
			Log.d(tag, "Starting XML Row Inflation ... ");
			LayoutInflater inflater = (LayoutInflater) this.getContext()
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.product_list_item, parent, false);
			Log.d(tag, "Successfully completed XML Row Inflation!");
		}

		// Get item
		ProductEntity product = getItem(position);

		// Get reference to ImageView
		productIcon = (ImageView) row.findViewById(R.id.product_icon);

		// Get reference to TextView - country_name
		productName = (TextView) row.findViewById(R.id.product_name);
		
		productCost = (TextView) row.findViewById(R.id.product_cost);
		productAvailability = (TextView) row.findViewById(R.id.product_availability);
		productId = (TextView) row.findViewById(R.id.product_id);

		//Set country name
		productName.setText(product.getName());
		productCost.setText(/*"KZT"+*/product.getPrice());
		productAvailability.setText(product.getAvail() ? "Available" : "Not available");
		productId.setText(Integer.toString(product.getId()));

		
		// Set country icon using File path
		String imgFilePath = product.getImage();
		try {
			URL imageURL = new URL(imgFilePath);
			Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
			productIcon.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return row;

	}
	
	
}
