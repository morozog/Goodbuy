package com.spotlight.Goodbuy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.spotlight.Goodbuy.Entities.*;
import com.spotlight.Goodbuy.Parsers.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.graphics.drawable.BitmapDrawable;

public class ProductsActivity extends Activity {
	public final static String PRODUCT_ID = "prodId";
	public final static String PRODUCT_NAME = "prodName";
	public final static String PRODUCT_ICON = "prodIcon";
	public final static String PRODUCT_PRICE = "prodPrice";
	
	
	private final static String PRODUCTS_XML_URL="http://shop.spotlight.kz/frontend_dev.php/xml/products?catid=%s&shopid=%s";
	private ProductsArrayAdapter productsAdapter;
	
	private String shopId;
	private String categoryId;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
				
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_products);
        
        Bundle bundle = this.getIntent().getExtras();
		shopId = bundle.getString("shopId");
		categoryId = bundle.getString("categoryId");
        
        // will replace with xml parser
        Parser p = null;
        p = new ProductParser(String.format(PRODUCTS_XML_URL,categoryId, shopId));
	
		Collection<Entity> products = p.populateCol();
        
		if(products==null || products.isEmpty()){
			ProductEntity prod = new ProductEntity();
			prod.setId(-1);
			prod.setName("Empty");
			prod.setPrice("No products available in this category");
			prod.setAvail(false);
			products = new ArrayList<Entity>();
			products.add(prod);
		}
		
        // Initialize array adapter for the shops.
        productsAdapter =  new ProductsArrayAdapter(getApplicationContext(), 
        R.layout.product_list_item, (List<Entity>)products);

        // Find and set up the ListView for products
        ListView productsListView = (ListView) findViewById(R.id.productsListView);
        productsListView.setAdapter(productsAdapter);
        productsListView.setOnItemClickListener(prodsClickListener);
         
    }
    
	private OnItemClickListener prodsClickListener = new OnItemClickListener() {
        public void onItemClick(AdapterView<?> av, View v, int arg2, long arg3) {
        	TextView prodId = (TextView) v.findViewById(R.id.product_id);
        	TextView prodName = (TextView) v.findViewById(R.id.product_name);
        	TextView prodPrice = (TextView) v.findViewById(R.id.product_cost);
        	ImageView prodIcon = (ImageView) v.findViewById(R.id.product_icon);
        	Bundle bundle = new Bundle();
        	
        	bundle.putInt(PRODUCT_ID, Integer.parseInt(prodId.getText().toString()));
        	bundle.putString(PRODUCT_NAME, prodName.getText().toString());
        	bundle.putParcelable(PRODUCT_ICON, ((BitmapDrawable) prodIcon.getDrawable()).getBitmap());
        	bundle.putInt(PRODUCT_PRICE, Integer.parseInt(prodPrice.getText().toString()));
        	Intent myIntent = new Intent(v.getContext(), OrderActivity.class);
        	myIntent.putExtras(bundle);
        	
            startActivity(myIntent);
        }
    };

	
    @Override
    protected void onDestroy() {
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
