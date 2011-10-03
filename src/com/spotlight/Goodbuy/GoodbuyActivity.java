package com.spotlight.Goodbuy;

import com.spotlight.Goodbuy.Parsers.*;

import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.spotlight.Goodbuy.Entities.*;

public class GoodbuyActivity extends Activity {
	private final static String SHOPS_XML_URL="http://shop.spotlight.kz/frontend_dev.php/xml/shops";
	
	// Member fields
    private ShopArrayAdapter shopsAdapter;
	List<ShopEntity> shops;;
    
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // will replace with xml parser
        Parser p = null;
        p = new ShopParser(SHOPS_XML_URL);
	
		Collection<Entity> shops = p.populateCol();
        		
        // Initialize array adapter for the shops.
        shopsAdapter =  new ShopArrayAdapter(getApplicationContext(), 
        		R.layout.shop_list_item, (List<Entity>)shops);

        // Find and set up the ListView for paired devices
        ListView shopsListView = (ListView) findViewById(R.id.shopsListView);
        shopsListView.setAdapter(shopsAdapter);
        shopsListView.setOnItemClickListener(shopClickListener);
         
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    // The on-click listener for all shops in the ListView
    private OnItemClickListener shopClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
        	Bundle bundle = new Bundle();
        	TextView shopId = (TextView) v.findViewById(R.id.shop_id);
        	bundle.putString("shopId", shopId.getText().toString());
        	Intent myIntent = new Intent(v.getContext(), CategoriesActivity.class);
        	myIntent.putExtras(bundle);
            startActivity(myIntent);
        }
    };
    
    
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
            //return true;
        case R.id.cart:
            serverIntent = new Intent(this, CartActivity.class);
            startActivity(serverIntent);
            return true;
        case R.id.settings:
            //return true;
        }
        return false;
    }
}