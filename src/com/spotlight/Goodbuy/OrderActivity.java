package com.spotlight.Goodbuy;

import java.io.IOException;
import java.net.URL;

import com.spotlight.Goodbuy.db.CartDbAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends Activity {
	public static final String TOTAL_ORDER_KEY = "totalKey";
	private static final String PRODUCT_ID = "prodId";
	private int prodId;
	private String prodName;
	private Bitmap prodIcon;
	private int prodPrice;
	private int totalPriceToBascket;
	
	private CartDbAdapter dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_item);
		Bundle bundle = this.getIntent().getExtras();
		// get params
		prodId = bundle.getInt(ProductsActivity.PRODUCT_ID);
		prodName = bundle.getString(ProductsActivity.PRODUCT_NAME);
		prodIcon = bundle.getParcelable(ProductsActivity.PRODUCT_ICON);
		prodPrice = bundle.getInt(ProductsActivity.PRODUCT_PRICE);
		TextView productName = (TextView) findViewById(R.id.prodName);
		productName.setText(prodName);
		
		ImageView productIcon = (ImageView) findViewById(R.id.prodIcon);
		productIcon.setImageBitmap(prodIcon);
		
		final TextView tx = (TextView) findViewById(R.id.amountText);
		final TextView totalPrice = (TextView) findViewById(R.id.priceVal);
		final Button incrementor = (Button) findViewById(R.id.incr);
		totalPrice.setText(prodPrice + "");
		
		incrementor.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				int cur = Integer.parseInt(tx.getText().toString()) + 1;
				totalPrice.setText(calcTotal(cur) + "");
				tx.setText(cur +"");
			}
		});
		
		final Button decrementor = (Button) findViewById(R.id.decr);
		decrementor.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				TextView tx = (TextView) findViewById(R.id.amountText);
				int cur = Integer.parseInt(tx.getText().toString()) - 1;
				totalPrice.setText(calcTotal(cur) + "");
				tx.setText(cur +"");
			}
		});
		
		final Button doOrder = (Button) findViewById(R.id.order);
		doOrder.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				TextView priceValText = (TextView) findViewById(R.id.priceVal);
				totalPriceToBascket = Integer.parseInt(priceValText.getText().toString());
			
				dbHelper = new CartDbAdapter(getApplicationContext());
				dbHelper.open();
				dbHelper.createCartItem(prodId, prodName, totalPriceToBascket);
				//Bundle b = new Bundle();
				//b.putInt(TOTAL_ORDER_KEY, totalPriceToBascket);
				// correct intent
				//Intent myIntent = new Intent(v.getContext(), ProductsActivity.class);
				//myIntent.putExtras(b);
				//startActivity(myIntent);
				finish();
			}
		});
		
	}
	protected int calcTotal(int portions){
		return prodPrice * portions;
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
}
