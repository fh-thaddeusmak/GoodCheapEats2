package com.thaddroid.apps.goodcheapeats2;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;

public class EnlargeActivity extends ActionBarActivity {
	private Intent intent;
	private Bundle bundle;
	private TouchImageView tiv;
	private AdView adView;
	private boolean firstTime=true;
	private LinearLayout ll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.image_enlarge);
		
		adView = new AdView(this);
		adView.setAdUnitId("ca-app-pub-3929736155927907/5768812675");
		adView.setAdSize(AdSize.SMART_BANNER);
		
		tiv = (TouchImageView)findViewById(R.id.enlarge_image);
		intent = this.getIntent();
		bundle = intent.getExtras();
		
		int imgid = bundle.getInt("Image");
		int title = bundle.getInt("title");
		
		if (title == 0) {
            setTitle(R.string.imagebutton1);
        } else {
            setTitle(R.string.imagebutton2);
        }
		
		tiv.setImageResource(imgid);
		
		ll = (LinearLayout)findViewById(R.id.layout_enlarge);
		
		AdRequest adRequest = new AdRequest.Builder().build();
		
		adView.setBackgroundColor(Color.TRANSPARENT);
		adView.loadAd(adRequest);
		
		adView.setAdListener(new AdListener()
	    {
	    	@Override
	    	public void onAdLoaded()
	    	{
	    		if(firstTime)
	    		{
	    			firstTime = false;
	    			ll.addView(adView);
	    		}
	    	}
	    });
	}
	
	public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        this.finish();
        super.onDestroy();
    }

    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }
}
