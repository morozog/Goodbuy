package com.spotlight.Goodbuy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.spotlight.Goodbuy.Entities.CartItemEntity;
import com.spotlight.Goodbuy.Entities.Entity;
import com.spotlight.Goodbuy.db.CartDbAdapter;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class CartActivity extends Activity{
	
	private CartArrayAdapter cartItemsAdapter;
	private CartDbAdapter dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.cart);
        
        ArrayList<Entity> cartItems = new ArrayList<Entity>();
        CartItemEntity cartItem;
        
        dbHelper = new CartDbAdapter(getApplicationContext());
		dbHelper.open();
		Cursor cur = dbHelper.fetchAllItems();
		cur.moveToFirst();
		for(int i = 0; i<cur.getCount(); i++) {
			
			cartItem = new CartItemEntity();
			cartItem.setId(cur.getInt(0));
			cartItem.setProdId(cur.getInt(1));
        	cartItem.setProdName(cur.getString(2));
        	cartItem.setQuantity(cur.getInt(3));
        	cartItems.add(cartItem);
        	cur.moveToNext();
		}
        cur.close();
		
        if(cartItems==null || cartItems.isEmpty()){
        	cartItem = new CartItemEntity();
        	cartItem.setId(-1);
        	cartItem.setProdId(-1);
        	cartItem.setProdName("Your cart is empty");
        	cartItem.setQuantity(0);
        	cartItems.add(cartItem);
        }
		
        // Initialize array adapter for the shops.
        cartItemsAdapter =  new CartArrayAdapter(getApplicationContext(), 
        		R.layout.shop_list_item, (List<Entity>)cartItems);

        // Find and set up the ListView for paired devices
        ListView cartListView = (ListView) findViewById(R.id.cartListView);
        cartListView.setAdapter(cartItemsAdapter);
        
	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.option_menu, menu);
	        return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        Intent serverIntent = null;
	        switch (item.getItemId()) {
	        case R.id.order_pacement:
	        	serverIntent = new Intent(this, GoodbuyActivity.class);
	            startActivity(serverIntent);
	            return true;
	        case R.id.cart:
	        	//serverIntent = new Intent(this, CartActivity.class);
	            //startActivity(serverIntent);
	            //return true;
	        case R.id.settings:
	            //return true;
	        }
	        return false;
	    }

}
