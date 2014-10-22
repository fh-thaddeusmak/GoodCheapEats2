package com.thaddroid.apps.goodcheapeats2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import com.purplebrain.adbuddiz.sdk.AdBuddiz;

public class StartUpActivity extends ActionBarActivity {
	private Bundle bundle;
	private Intent intent;
	private ImageButton ib1;
	private ImageButton ib2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.startup_layout);
		
		AdBuddiz.setPublisherKey("858700bf-6ba4-4830-a43f-8f1d05855e3b");
		//AdBuddiz.setTestModeActive();
		AdBuddiz.cacheAds(this);
		if(AdBuddiz.isReadyToShowAd(this)){
			AdBuddiz.showAd(this);
		}
		
		intent = new Intent();
		bundle = new Bundle();
		
		ib1 = (ImageButton)findViewById(R.id.ib1);
		ib2 = (ImageButton)findViewById(R.id.ib2);
		
		ib1.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				intent.setClass(StartUpActivity.this, MainMenu.class);
				bundle.putInt("series", 0);
				intent.putExtras(bundle);
				startActivityForResult(intent, 0);
			}
			
		});
		
		ib2.setOnClickListener(new ImageButton.OnClickListener(){

			@Override
			public void onClick(View v) {
				intent.setClass(StartUpActivity.this, MainMenu.class);
				bundle.putInt("series", 1);
				intent.putExtras(bundle);
				startActivityForResult(intent, 0);
			}
			
		});
		
	}
	
}
