package com.example.listapp;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ListActivity extends ActionBarActivity {

	private EditText mEditText;
	private Button mButton;
	private ListView mListView;
	private ArrayList<String> mList;
	private MyAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		mEditText=(EditText)findViewById(R.id.editText1);
		mButton=(Button)findViewById(R.id.button1);
		mListView=(ListView)findViewById(R.id.listView1);
		
		
		mList=new ArrayList<String>();		
		mAdapter=new MyAdapter(mList);
		mListView.setAdapter(mAdapter);
		
		mButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(mEditText.getText().toString().length()>0){
					mList.add(mEditText.getText().toString());
					((MyAdapter)mListView.getAdapter()).notifyDataSetChanged();
				}
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private class MyAdapter extends ArrayAdapter<String>{
		
		private Button mButton;
		private TextView mTextView;
		public MyAdapter(ArrayList<String> list) {
			super(ListActivity.this, android.R.layout.simple_list_item_1,list);
			
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public View getView(int position,View convertView,ViewGroup parent){
			if(convertView==null){
				convertView=getLayoutInflater().inflate(R.layout.listitem, null);
			}
			mTextView=(TextView)convertView.findViewById(R.id.textView1);
			mButton=(Button)convertView.findViewById(R.id.button1);
			mButton.setTag(position);
			mButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					int pos=(Integer) arg0.getTag();
					mList.remove(pos);
					((MyAdapter)mListView.getAdapter()).notifyDataSetChanged();
				}
			});
			mTextView.setText(getItem(position));
			return convertView;
			
		}

	}
}
