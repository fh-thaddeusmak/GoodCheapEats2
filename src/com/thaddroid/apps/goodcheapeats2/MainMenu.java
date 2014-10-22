package com.thaddroid.apps.goodcheapeats2;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.ads.*;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;



public class MainMenu extends ActionBarActivity {

    private ListView lv;
    private List<String> list;
    private ArrayAdapter<String> adapter;
    private Intent pintent;
    private Bundle pbundle;
    private int seriesNo=0;
    private boolean firstTime=true;
    private AdView adView;
    private String epidso[];
    private LinearLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        pintent = getIntent();
        pbundle = pintent.getExtras();
        seriesNo = pbundle.getInt("series");
        if(seriesNo==0){
        	setTitle(R.string.imagebutton1);
        	
        	String temp[] = {"第1集 - 一雞十吃", "第2集 - 20元愛心靚湯",
            	"第3集 - 簡單烹調工夫菜", "第4集 - 開胃有妙法",
            	"第5集 - 冷飯大翻身", "第6集 - 80元二人浪漫西餐",
            	"第7集 - 罐頭煮出真智慧", "第8集 - 150元四人海鮮餐",
            	"第9集 - 母親節滋補養顏菜色", "第10集 - 特色炒麵",
            	"第11集 - 混出醬滋味", "第12集 - 平食三文魚",
            	"第13集 - 進補食平D", "第14集 - 超筍價精緻派對美食",
            	"第15集 - 隔夜餸變矜貴美食", "第16集 - 韓國養生餐",
            	"第17集 - 薯仔五吃食到盡", "第18集 - 孕婦滋補餐",
            	"第19集 - 全菇宴", "第20集 - 冬瓜清暑妙法",
            	"第21集 - 自家製煲仔菜", "第22集 - 自家平製健康燒味",
            	"第23集 - 西式名菜食平D", "第24集 - 終極街坊食平D"};
        	epidso = temp;
        	temp = new String[temp.length];
        }else{
        	setTitle(R.string.imagebutton2);
        	
        	String temp[] = {"第1集 - 雞翼十吃無難度", "第2集 - 「日式豬骨湯叉燒拉麵」是這樣煉成的",
                "第3集 - 茶記可以再食平DD！", "第4集 - 蔗渣價錢　煮出四道海鮮真味",
                "第5集 - 肥媽下廚「Very骨」", "第6集 - 來自星星的韓國菜",
                "第7集 - 肥媽煮餸「泰」夠料", "第8集 - 自製台式鹵肉及超平「燕窩」",
                "第9集 - 學製葡國菜　在家燒乳豬", "第10集 - 點止飯煲咁簡單",
                "第11集 - 全豬宴發掘豬的「內臟」美", "第12集 - 日、台、西式牛任食",
                "第13集 - 肥媽全鴨宴　免焗梳乎厘", "第14集 - 印度 x 咖喱 x 薄餅",
                "第15集 - 羊肉夠「疆」靠香料", "第16集 - 肥媽呷醋有辦法",
                "第17集 - 肥媽煮餸心中有「素」", "第18集 - 素湯煲出肉味來？",
                "第19集 - 炮製「蝦蝦蝦」美味大餐", "第20集 - 學煮懷舊菜　慶祝母親節"};
        	epidso = temp;
        	temp = new String[temp.length];
        }

        setContentView(R.layout.mainmenu);
        
        adView = new AdView(this);
		adView.setAdUnitId("ca-app-pub-3929736155927907/5768812675");
		adView.setAdSize(AdSize.SMART_BANNER);
		
		ll = (LinearLayout)findViewById(R.id.layout_main);
		
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

        lv = (ListView)findViewById(R.id.listview);
        list = new ArrayList<String>();
        for(int i=0; i<epidso.length; i++){
            list.add(epidso[i]);
        }

        adapter = new ArrayAdapter<String>(MainMenu.this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new ListView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainMenu.this, RecipeActivity.class);

                Bundle bundle = new Bundle();
                bundle.putInt("whichEpidso", position);
                bundle.putInt("whichSeries", seriesNo);

                intent.putExtras(bundle);
                startActivityForResult(intent, 0);
            }

        });

    }
    
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
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
    */
    
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

