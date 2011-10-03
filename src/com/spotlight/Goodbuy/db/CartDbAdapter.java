package com.spotlight.Goodbuy.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class CartDbAdapter {

	// Database fields
	public static final String KEY_ROWID = "_id";
	public static final String KEY_PROD_ID = "prod_id";
	public static final String KEY_PROD_NAME = "prod_name";
	public static final String KEY_QUANTITY = "quantity";
	private static final String DATABASE_TABLE = "cart";
	private Context context;
	private SQLiteDatabase database;
	private CartDatabaseHelper dbHelper;

	public CartDbAdapter(Context context) {
		this.context = context;
	}

	public CartDbAdapter open() throws SQLException {
		dbHelper = new CartDatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		dbHelper.close();
	}

	public long createCartItem(int prodId, String prodName, int quantity) {
		ContentValues initialValues = createContentValues(prodId, prodName, quantity);
		return database.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean updateTodo(long rowId, int prodId, String prodName, int quantity) {
		ContentValues updateValues = createContentValues(prodId, prodName, quantity);
		return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "="
				+ rowId, null) > 0;
	}

	public boolean deleteTodo(long rowId) {
		return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
	}


	public Cursor fetchAllItems() {
		return database.query(DATABASE_TABLE, new String[] { KEY_ROWID,
				KEY_PROD_ID, KEY_PROD_NAME, KEY_QUANTITY }, null, null, null,
				null, null);
	}

	
/**
	 * Return a Cursor positioned at the defined ITEM
	 */

	public Cursor fetchTodo(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
				KEY_ROWID,KEY_PROD_ID, KEY_PROD_NAME, KEY_QUANTITY },
				KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private ContentValues createContentValues(int prodId, String prodName, int quantity) {
		ContentValues values = new ContentValues();
		values.put(KEY_PROD_ID, prodId);
		values.put(KEY_PROD_NAME, prodName);
		values.put(KEY_QUANTITY, quantity);
		return values;
	}
}