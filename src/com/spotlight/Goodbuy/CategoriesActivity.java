package com.spotlight.Goodbuy;

import android.app.Activity;
import com.spotlight.Goodbuy.Parsers.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.*;

import com.spotlight.Goodbuy.Entities.Entity;
import com.spotlight.Goodbuy.Parsers.Parser;

public class CategoriesActivity extends Activity{
	private final static String CATEGORY_XML_URL="http://shop.spotlight.kz/frontend_dev.php/xml/categories?shopid=%s";
	
	private CategoriesArrayAdapter catAdapter;
	private String shopId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		Bundle bundle = this.getIntent().getExtras();
		shopId = bundle.getString("shopId");
		
		
		setContentView(R.layout.main_cats);
		// will replace with xml parser
		Parser p = null;
        p = new CategoryParser(String.format(CATEGORY_XML_URL, shopId));
	
		Collection<Entity> cats = p.populateCol();

		// populate adapter
		catAdapter = new CategoriesArrayAdapter(getApplicationContext(), R.layout.shop_category, (List<Entity>)cats);
		// Find and set up the ListView for paired devices
        ListView catsListView = (ListView) findViewById(R.id.catListView);
        catsListView.setAdapter(catAdapter);
        catsListView.setOnItemClickListener(catsClickListener);
		
	}
	private OnItemClickListener catsClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
        	Bundle bundle = new Bundle();
        	TextView categoryId = (TextView) v.findViewById(R.id.category_id);
        	bundle.putString("shopId", shopId);
        	bundle.putString("categoryId", categoryId.getText().toString());
        	Intent myIntent = new Intent(v.getContext(), ProductsActivity.class);
        	myIntent.putExtras(bundle);
            startActivity(myIntent);
        }
    };
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
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
            serverIntent = new Intent(this, CartActivity.class);
            startActivity(serverIntent);
            return true;
        case R.id.settings:
            //return true;
        }
        return false;
    }
}
