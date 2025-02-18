package com.goys.android.app;

import java.util.Locale;

import com.goys.android.app.application_linking.AppLinkingActivity;
import com.goys.android.app.contactus.ContactUsActivity;
import com.goys.android.app.db.DBAccess;
import com.goys.android.app.eservices.EServiceActivity;
import com.goys.android.app.eventcalendar.EventCalendarActivity;
import com.goys.android.app.news.NewsActivity;
import com.goys.android.app.push_notification.GcmHelper;
import com.goys.android.app.settings.SettingsActivity;
import com.goys.android.app.tvstream.TvStreamActivity;
import com.goys.android.app.webview.happyform;
import com.goys.android.app.webview.myslocator;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity {

	private Toolbar toolbar;
	private DrawerLayout drawerLayout;
	private ActionBarDrawerToggle drawerToggle;
	private ListView leftDrawerList;
	private ArrayAdapter<String> navigationDrawerAdapter;
	private String navdrawerEvent, navdrawerAugmented, navdrawerChatbot, navdrawerSettings, navTV, navApp4u, navContactus, navNews;
	
	
	int mScreenWidth;
	int mScreenHeight;
	HorizontalScrollView hsv;
	
	//Services
	ImageButton btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10;
	//Events
	ImageButton btn11, btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,btn21,btn22,btn23;
	//EventsR
	ImageButton btnR1,btnR2,btnR3,btnR4,btnR5,btnR6,btnR7,btnDummy;
	
	  
	String language=Locale.getDefault().getLanguage();
	public static DBAccess dbAccessObj;
	GcmHelper gcmHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_main);
	    nitView();
	    if (toolbar != null) {
	        toolbar.setTitle("");
	        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
	        setSupportActionBar(toolbar);
	    }
	    initDrawer();
	    
	    if(language.equals("ar")){
	    	 hsv = (HorizontalScrollView)findViewById(R.id.scroll1);
	    	 hsv.postDelayed(new Runnable() {
	    		    public void run() {
	    		        hsv.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
	    		        for(int i = 1; i < 11; i++){
	    		        	int d = 0;
							try {
								d = R.id.class.getField("menu_event" + i).getInt(0);
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (NoSuchFieldException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    		        	btnDummy = (ImageButton)findViewById(d);
	    		        	btnDummy.setVisibility(View.GONE);
	    		        }    
	    		    }
	    		}, 100L);
	    }else{
	    	for(int i = 1; i < 11; i++){
	        	int d = 0;
				try {
					d = R.id.class.getField("menu_eventR" + i).getInt(0);
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	btnDummy = (ImageButton)findViewById(d);
	        	btnDummy.setVisibility(View.GONE);
	        }    
	    }
	    
	    mScreenWidth = this.getWindowManager().getDefaultDisplay().getWidth();
		mScreenHeight = this.getWindowManager().getDefaultDisplay().getHeight();

		dbAccessObj = new DBAccess(this);
		
		gcmHelper = new GcmHelper(this,this);
		
		gcmHelper.register();
		
		//Buttons
		 btn1 = (ImageButton)findViewById(R.id.btneservices);
		    
		 btn1.setOnClickListener(new View.OnClickListener() {
            @Override
         public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), UnityPlayerActivity.class);
                view.getContext().startActivity(Intent);}
         });

		 btn2 = (ImageButton)findViewById(R.id.btnlivestreaming);
		 btn2.setOnClickListener(new View.OnClickListener() {
	        @Override
	        public void onClick(View view) {
	            Intent Intent = new Intent(view.getContext(), TvStreamActivity.class);
	            view.getContext().startActivity(Intent);}
	        });

		 btn3 = (ImageButton)findViewById(R.id.btncontactus);  
		 btn3.setOnClickListener(new View.OnClickListener() {
	            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), ContactUsActivity.class);
                view.getContext().startActivity(Intent);}
            });

		 btn4 = (ImageButton)findViewById(R.id.btnnews); 
		 btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), NewsActivity.class);
                view.getContext().startActivity(Intent);}
            });
		    
	    btn5 = (ImageButton)findViewById(R.id.btnmyslocator);
	    btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), myslocator.class);
                view.getContext().startActivity(Intent);}
            });
		
	    btn6 = (ImageButton)findViewById(R.id.btnappsforyou);
	    btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), AppLinkingActivity.class);
                view.getContext().startActivity(Intent);}
            });
		
	    //Social Media
	    btn7 = (ImageButton)findViewById(R.id.menu_twitter);
	    btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {Uri uri = Uri.parse("https://twitter.com/mysbhr"); 
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);}
            });
	    
	    btn8 = (ImageButton)findViewById(R.id.menu_instagram);
	    btn8.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {Uri uri = Uri.parse("https://www.instagram.com/mysbhr/"); 
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);}
            });
	    
	    
	    btn9 = (ImageButton)findViewById(R.id.menu_youtube);
	    btn9.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {Uri uri = Uri.parse("https://www.youtube.com/user/goysbhr/"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);}
            });
	    
	    
	    btn10 = (ImageButton)findViewById(R.id.menu_facebook);
	    btn10.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {Uri uri = Uri.parse("https://www.facebook.com/Mysbhr-901054683323063/"); // missing 'http://' will cause crashed
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);}
            });
	    
	    
	    btn23 = (ImageButton)findViewById(R.id.menu_happiness);
	    btn23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(view.getContext(), happyform.class);
                view.getContext().startActivity(Intent);}
            });
	    
	    //Events Click Events
	    
	    //--International Youth Conference --//
	    btn11 = (ImageButton)findViewById(R.id.menu_event1);
	    btn11.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		   
	    	    if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("ØªÙ†Ø¸Ù… ÙˆØ²Ø§Ø±Ø© Ø´Ø¦ÙˆÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø© Ù�ÙŠ ÙƒÙ„ Ø¹Ø§Ù… Ù…Ø¤ØªÙ…Ø± Ø§Ù„Ø´Ø¨Ø§Ø¨ Ø§Ù„Ø¯ÙˆÙ„ÙŠ ÙˆØ§Ù„Ø°ÙŠ ÙŠØ¹Ø¯ Ù†Ù‚Ø·Ø© Ø§Ù„ØªÙ‚Ø§Ø¡ Ø¨ÙŠÙ† Ø´Ø¨Ø§Ø¨ Ø§Ù„Ù…Ù…Ù„ÙƒØ© ÙˆØ£Ù‚Ø±Ø§Ù†Ù‡Ù… Ù…Ù† Ø¯ÙˆÙ„ Ø§Ù„Ø¹Ø§Ù„Ù… Ù„Ù„Ø­ÙˆØ§Ø± ÙˆØ§Ù„Ù†Ù‚Ø§Ø´ Ù�ÙŠ ÙƒÙ„ Ù…Ø§ ÙŠØªØ¹Ù„Ù‚ Ø¨Ø§Ù„Ø´Ø£Ù† Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ Ù�ÙŠ Ù…ÙˆØ§Ø¶ÙŠØ¹ Ù…Ø®ØªÙ„Ù�Ø© Ù�ÙŠ ÙƒÙ„ Ø¹Ø§Ù… ÙˆÙŠØªØ¶Ù…Ù† Ø§Ù„Ù…Ø¤ØªÙ…Ø± Ø¹Ù„Ù‰ Ø¬Ù„Ø³Ø§Øª Ø¹Ø§Ù…Ø© Ù…Ù�ØªÙˆØ­Ø© Ù„Ù„Ø¬Ù…ÙŠØ¹ ÙŠØ­Ø§Ø¶Ø± Ù�ÙŠÙ‡Ø§ Ù†Ø®Ø¨Ø© Ù…Ù† Ø£Ø´Ù‡Ø± Ø§Ù„Ù…ØªØ­Ø¯Ø«ÙŠÙ† Ø§Ù„Ø¹Ø§Ù„Ù…ÙŠÙŠÙ† ÙˆÙˆØ±Ø´ Ø¹Ù…Ù„ Ù�ÙŠÙ…Ø§ ÙŠØªØ¹Ù„Ù‚ Ø¨Ù…ÙˆØ¶ÙˆØ¹ ÙƒÙ„ Ø¹Ø§Ù… Ø¨Ø§Ù„Ø¥Ø¶Ø§Ù�Ø© Ø¥Ù„Ù‰ Ù…Ø¬Ù…ÙˆØ¹Ø© Ù…Ù† Ø§Ù„Ø£Ù†Ø´Ø·Ø© Ø§Ù„Ù…ØµØ§Ø­Ø¨Ø© ÙˆØ§Ù„ØªØ¹Ø±ÙŠÙ�ÙŠØ© Ø¨Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ù…Ø³ØªÙ‡Ø¯Ù�ÙŠÙ† Ø¨Ù‡Ø§ Ø§Ù„Ø¯ÙˆÙ„ Ø§Ù„Ù…Ø´Ø§Ø±ÙƒØ© Ù…Ù† Ù…Ø®ØªÙ„Ù� Ø£Ø±Ø¬Ø§Ø¡ Ø§Ù„Ø¹Ø§Ù„Ù….");
		    		builder1.setTitle("Ù…Ø¤ØªÙ…Ø± Ø§Ù„Ø´Ø¨Ø§Ø¨ Ø§Ù„Ø¯ÙˆÙ„ÙŠ");
		    		builder1.setCancelable(true);
		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		 });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("The Ministry of Youth and Sport Affairs annually organizes the International Youth Conference. This conference is considered a point of contact between the youth in the Kingdom of Bahrain and their peers from around the world to engage in dialogue and discuss everything that is relevant to the youth with different topics presented each year. The conference includes public open sessions for all to attend which are led by a high caliber of well-known international speakers, workshops covering the yearâ€™s topics of the conference as well as accompanying activities that aim to introduce and Kingdom of Bahrain to the visiting delegations from around the world.");
		    		builder1.setTitle("The International Youth Conference");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    		
	    		
	    		
            }
            });
	    
	  //--King Hamad Youth Empowerment Award--//
	    btn12 = (ImageButton)findViewById(R.id.menu_event2);
	    btn12.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		 if(language.equals("ar")){
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
			    		builder1.setMessage(" ØªØ£ØªÙŠ Ø¬Ø§Ø¦Ø²Ø© Ø§Ù„Ù…Ù„Ùƒ Ø­Ù…Ø¯ Ù„ØªÙ…ÙƒÙŠÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙƒÙ…Ø¨Ø§Ø¯Ø±Ø© Ù…Ù† ÙˆØ²Ø§Ø±Ø© Ø´Ø¦ÙˆÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø© Ù�ÙŠ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ù„ØªÙ…ÙƒÙŠÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„ØªØ±ÙƒÙŠÙ€Ø² Ø¹Ù„Ù‰ Ø§Ù„Ø§Ù‡ØªÙ…Ø§Ù… Ø¨Ù‡Ù…ØŒ ÙˆÙ‡Ø°Ù‡ Ø§Ù„Ø¬Ø§Ø¦Ø²Ø© Ù‡ÙŠ Ø§Ù„Ø£ÙˆÙ„Ù‰ Ù…Ù† Ù†ÙˆØ¹Ù‡Ø§ Ù„Ø¬Ù…ÙŠØ¹ Ø§Ù„Ø¯ÙˆÙ„ Ø¨Ù‡Ø¯Ù� Ø­Ø«Ù‡Ù… Ø¹Ù„Ù‰ Ø§Ù„Ø§Ù‡ØªÙ…Ø§Ù… ÙˆØ§Ø­ØªÙˆØ§Ø¡ Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØªØ´Ø¬ÙŠØ¹Ø§Ù‹ Ù„ØªØ­Ø³ÙŠÙ† Ø§Ù„Ø¨Ù†ÙŠØ© Ø§Ù„ØªØ­ØªÙŠØ© Ø§Ù„Ù…ØªØ¹Ù„Ù‚Ø© Ø¨Ø§Ù„Ø´Ø¨Ø§Ø¨ Ù„ØªØ¹ÙˆØ¯ Ø¨Ø§Ù„Ù†Ù�Ø¹ Ø¹Ù„Ù‰ ÙƒØ§Ù�Ø© Ø§Ù„Ø´Ø±Ø§Ø¦Ø­ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆØ§Ù„Ø¯Ø§Ø¹Ù…Ø© Ù„Ù„Ø´Ø¨Ø§Ø¨. ÙˆØªØ£ØªÙŠ Ù‡Ø°Ù‡ Ø§Ù„Ù…Ø¨Ø§Ø¯Ø±Ø© Ù…Ø¹Ø²Ø²Ø© Ù„Ø¯ÙˆØ± Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ù�ÙŠ ØªÙ†Ù…ÙŠØ© Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ­Ø±ØµØ§Ù‹ Ù…Ù†Ù‡Ø§ Ø¹Ù„Ù‰ Ø§Ù„Ø§Ø³ØªØ¯Ø§Ù…Ø© Ù…Ù† Ø®Ù„Ø§Ù„ Ø¥Ø´Ø±Ø§Ùƒ Ø¬Ù…ÙŠØ¹ Ù�Ø¦Ø§Øª Ø§Ù„Ù…Ø¬ØªÙ…Ø¹ Ù�ÙŠ Ø°Ù„Ùƒ ÙˆØ¨Ø§Ù„Ø£Ø®Øµ Ø§Ù„Ø´Ø¨Ø§Ø¨ØŒ Ù„Ù…Ø§ ÙŠÙ…ØªÙ„ÙƒÙˆÙ†Ù‡ Ù…Ù† Ù‚Ø¯Ø±Ø§Øª ÙˆØ·Ø§Ù‚Ø§Øª Ù‡Ø§Ø¦Ù„Ø© Ù…Ù† Ø´Ø£Ù†Ù‡Ø§ ØªØ­Ù‚ÙŠÙ‚ ØªØ·Ù„Ø¹Ø§ØªÙ‡Ù…ØŒ ÙƒÙ…Ø§ ØªÙ‡Ø¯Ù� Ù‡Ø°Ù‡ Ø§Ù„Ù…Ø¨Ø§Ø¯Ø±Ø© Ø£ÙŠØ¶Ø§ Ø¥Ù„Ù‰ ØªØ­Ù‚ÙŠÙ‚ Ø£Ù‡Ø¯Ø§Ù� Ø§Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ù…Ø³ØªØ¯Ø§Ù…Ø©ØŒ ÙˆØ§Ù„ØªÙŠ ØªÙ‡Ø¯Ù� Ø¥Ù„Ù‰ ØªÙˆÙ�ÙŠØ± Ù…Ø³ØªÙ‚Ø¨Ù„ Ø£Ù�Ø¶Ù„ Ù„Ù„Ø´Ø¨Ø§Ø¨ØŒ ÙƒÙ…Ø§ Ø£Ù†Ù‡ Ù…Ù† Ø®Ù„Ø§Ù„ Ù‡Ø°Ù‡ Ø§Ù„Ø¬Ø§Ø¦Ø²Ø© Ù�Ø¥Ù† Ø¬Ù…ÙŠØ¹ Ø§Ù„Ù…ØªÙ‚Ø¯Ù…ÙŠÙ† Ø³ÙˆÙ� ÙŠØ­Ù‚Ù‚ÙˆÙ† Ù…Ø¤Ø´Ø±Ø§Ù‹ Ù…Ù† Ù…Ø¤Ø´Ø±Ø§Øª Ø£Ù‡Ø¯Ø§Ù� Ø§Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ù…Ø³ØªØ¯Ø§Ù…Ø©. Ø¥Ù† Ø§Ù„Ø¬Ø§Ø¦Ø²Ø© Ø¨Ù…Ø§ ØªØ­Ù…Ù„Ù‡ Ù…Ù† Ø£Ù‡Ø¯Ø§Ù� Ø³Ø§Ù…ÙŠØ© Ù…Ù† Ø´Ø£Ù†Ù‡Ø§ Ø£Ù† ØªØ±ØªÙ‚ÙŠ Ø¨Ø§Ù„Ø´Ø±ÙŠØ­Ø© Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© Ø¥Ø°Ø§ Ù…Ø§ ØªÙ… Ø§Ù„Ù†Ø¸Ø± Ø¥Ù„ÙŠÙ‡Ø§ Ù…Ù† Ø¬Ø§Ù†Ø¨ Ø§Ù„Ø¬Ù‡Ø§Øª Ø§Ù„Ø­ÙƒÙˆÙ…ÙŠØ© ÙˆØ§Ù„Ù‚Ø·Ø§Ø¹ Ø§Ù„Ø®Ø§Øµ ÙˆØ§Ù„Ù‚Ø·Ø§Ø¹ Ø§Ù„Ø£Ù‡Ù„ÙŠ ÙˆØ§Ù„Ø£Ù�Ø±Ø§Ø¯ Ø­ÙŠØ« ØªØ´ÙƒÙ„ Ø§Ù„Ù�Ø¦Ø§Øª Ø§Ù„Ø³Ø§Ø¨Ù‚Ø© Ø§Ù„Ø£Ù�Ø±Ø¹ Ø§Ù„Ø£Ø³Ø§Ø³ÙŠØ© Ø§Ù„Ù…Ø­Ø±ÙƒØ© Ù„Ù„Ø¹Ù…Ù„ Ù�ÙŠ Ø§Ù„Ù‚Ø·Ø§Ø¹ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ. Ø³ØªÙ…Ù†Ø­ Ù‡Ø°Ù‡ Ø§Ù„Ø¬Ø§Ø¦Ø²Ø© Ø¥Ù„Ù‰ Ø£Ø±Ø¨Ø¹ Ù�Ø¦Ø§Øª Ù…Ø®ØªÙ„Ù�Ø© ÙˆÙ‡Ù…: Ø§Ù„Ù…Ø¤Ø³Ø³Ø§Øª Ø§Ù„Ø­ÙƒÙˆÙ…ÙŠØ©ØŒ Ø§Ù„Ù…Ø¤Ø³Ø³Ø§Øª Ø§Ù„Ø®Ø§ØµØ©ØŒ Ø§Ù„Ù…Ù†Ø¸Ù…Ø§Øª ØºÙŠØ± Ø§Ù„Ø±Ø¨Ø­ÙŠØ© ÙˆØ§Ù„Ù…Ù†Ø¸Ù…Ø§Øª Ø§Ù„Ø¯ÙˆÙ„ÙŠØ© ÙˆØ§Ù„Ø£Ù�Ø±Ø§Ø¯ØŒ ÙˆÙŠÙ„Ø²Ù… Ø¬Ù…ÙŠØ¹ Ø§Ù„Ù�Ø¦Ø§Øª Ù…Ø§Ø¹Ø¯Ø§ Ø§Ù„Ù…Ø¤Ø³Ø³Ø§Øª Ø§Ù„Ø­ÙƒÙˆÙ…ÙŠØ© Ø£Ù† ØªÙƒÙˆÙ† Ù…Ø¹ØªØ±Ù� Ø¨Ù‡Ø§ Ù…Ù† Ù‚Ø¨Ù„ Ø§Ù„Ø¯ÙˆÙ„Ø© Ø§Ù„ØªÙŠ ÙŠØªÙ… Ø§Ù„ØªÙ‚Ø¯ÙŠÙ… Ù…Ù† Ø®Ù„Ø§Ù„Ù‡Ø§ØŒ ÙˆØ³ØªØªÙƒÙ�Ù„ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ø¨Ø¬Ù…ÙŠØ¹ Ø§Ù„Ø§Ù„ØªØ²Ø§Ù…Ø§Øª Ø§Ù„Ù…Ø§Ù„ÙŠØ© Ù„Ù„Ø¬Ø§Ø¦Ø²Ø©");
			    		builder1.setTitle("Ø¬Ø§Ø¦Ø²Ø© Ø§Ù„Ù…Ù„Ùƒ Ø­Ù…Ø¯ Ù„ØªÙ…ÙƒÙŠÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ Ù„ØªØ­Ù‚ÙŠÙ‚ Ø£Ù‡Ø¯Ø§Ù� Ø§Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ù…Ø³ØªØ¯Ø§Ù…Ø©");
			    		builder1.setCancelable(true);
			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
		    	    if(language.equals("en")){
		    	        //do nothing
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
			    		builder1.setMessage("The King Hamad Youth Empowerment award comes as an initiative from the Ministry of Youth and Sport Affairs in the Kingdom of Bahrain, as the first award of its kind that will be open to all Member States. This initiative aims to empower young people and give them the attention, encouragement and motivation needed. It will encourage all eligible participants to improve the enabling environment and infrastructure for youth to allow them to be effective and productive citizens contributing to their communities. This initiative enhances the role of the Kingdom of Bahrain in youth development and its commitment to sustainability, through the involvement of all segments of society in it, particularly young people, as they own the abilities and energies that will achieve this. The initiative also aims to achieve the Sustainable Development Goals that aim to provide the best for young people's future and through this award; all applicants will have achieved SDG targets. This prestigious award including all that it will stand for and its goals, will get all sectors of the community involved and bring us a step forward in not only raising awareness for the importance of the youth but take us a step forward to achieving many goals that will help us create a better future. ");
			    		builder1.setTitle("King Hamad Youth Empowerment Award to Achieve the SDGs");
			    		builder1.setCancelable(true);
			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
		    		
	    		
	    		
            }
            });

	  //--Ajyal 2030--//
	    btn13 = (ImageButton)findViewById(R.id.menu_event3);
	    btn13.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		 if(language.equals("ar")){
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
			    		builder1.setMessage("ÙŠÙ‚Ø¯Ù… Ù…Ø±ÙƒØ² Ø³Ù„Ù…Ø§Ù† Ø§Ù„Ø«Ù‚Ø§Ù�ÙŠ Ù�ÙŠ ÙˆØ²Ø§Ø±Ø© Ø´Ø¤ÙˆÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø© Ù„Ù„Ù†Ø§Ø´Ø¦Ø© Ø®Ù„Ø§Ù„ Ù�ØªØ±Ø© Ø§Ù„Ø¥Ø¬Ø§Ø²Ø© Ø§Ù„ØµÙŠÙ�ÙŠØ© Ø¨Ø±Ù†Ø§Ù…Ø¬ Ø£Ø¬ÙŠØ§Ù„ 2030  ÙˆÙŠØ´ØªÙ…Ù„ Ø¹Ù„Ù‰ Ø§Ù„ÙƒØ«ÙŠØ± Ù…Ù† Ø§Ù„Ø£Ù†Ø´Ø·Ø© Ø§Ù„Ù…ØªÙ†ÙˆØ¹Ø© Ø§Ù„ØªÙŠ ØªÙ„Ø¨ÙŠ Ø±ØºØ¨Ø§Øª Ø§Ù„Ù…Ø´Ø§Ø±ÙƒÙŠÙ† Ù„ØµÙ‚Ù„ Ù…ÙˆØ§Ù‡Ø¨Ù‡Ù… ÙˆÙ…Ù‡Ø§Ø±Ø§ØªÙ‡Ù… Ù†Ø­Ùˆ ØªØ·ÙˆÙŠØ± Ø§Ù„Ù…Ø³ØªÙˆÙ‰ Ø§Ù„Ø¥Ø¨Ø¯Ø§Ø¹ÙŠ Ù„Ø¯ÙŠÙ‡Ù…ØŒ Ø­ÙŠØ« ÙŠÙ‚Ø¯Ù… Ø§Ù„Ù…Ø±ÙƒØ² Ø¨Ø§Ù‚Ø© Ù…ØªÙƒØ§Ù…Ù„Ø© Ù…Ù† Ø§Ù„Ø£Ù†Ø´Ø·Ø© Ø¨Ø±Ø¤ÙŠØ© Ø¹ØµØ±ÙŠØ© Ù…ØªÙˆØ§ÙƒØ¨Ø© Ù…Ø¹ Ù…Ø§ ØªØ±Ù†Ùˆ Ø¥Ù„ÙŠÙ‡ Ø§Ù„Ù‚ÙŠØ§Ø¯Ø© Ø§Ù„Ø­ÙƒÙŠÙ…Ø© Ù�ÙŠ ØªÙ‡ÙŠØ¦Ø© Ø£Ø¬ÙŠØ§Ù„ Ù‚Ø§Ø¯Ø±Ø© Ø¹Ù„Ù‰ Ù…ÙˆØ§ÙƒØ¨Ø© Ø§Ù„Ø­Ø§Ø¶Ø± ÙˆØµÙˆÙ„Ø§Ù‹ Ù„Ù„Ù…Ø³ØªÙ‚Ø¨Ù„ØŒ Ù�ÙŠ Ø¹Ø¯Ø© Ù…Ø¬Ø§Ù„Ø§Øª ÙƒØ§Ù„Ù�Ù†ÙˆÙ† ÙˆØ§Ù„Ù‚ÙŠØ§Ø¯Ø© ÙˆØ§Ù„Ø¹Ù„ÙˆÙ… ÙˆØ§Ù„ØªÙƒÙ†Ù„ÙˆØ¬ÙŠØ§ ÙˆØ§Ù„Ø¥Ø¹Ù„Ø§Ù… ÙˆØ§Ù„ØµØ­Ø© ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø©");
			    		builder1.setTitle("Ø£Ø¬ÙŠØ§Ù„ 2030");
			    		builder1.setCancelable(true);

			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
		    	    if(language.equals("en")){
		    	        //do nothing
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
			    		builder1.setMessage("During the summer holidays, Salman Cultural Center at the Ministry of youth and Sport Affairs offers a program called 'Ajyal 2030'. It includes many activities that meet the participants' needs to enhance their talents and skills towards developing their creative skills. This program comes from our wise leadershipâ€™s perspective in creating generations capable of keeping up with the present and being ready for the future in various fields such as arts, leadership, science, technology, media, health and sports.");
			    		builder1.setTitle("Ajyal 2030");
			    		builder1.setCancelable(true);

			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();

		    	    }
		    	    
	    		
            }
            });
	  
	  //-- Nasser Bin Hamad International Youth Creativity Award --//
	    btn13 = (ImageButton)findViewById(R.id.menu_event4);
	    btn13.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		 if(language.equals("ar")){
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
			    		builder1.setMessage("ØªØ£ØªÙŠ Ø¬Ø§Ø¦Ø²Ø© Ù†Ø§ØµØ± Ø¨Ù† Ø­Ù…Ø¯ Ø§Ù„Ø¹Ø§Ù„Ù…ÙŠØ© Ù„Ù„Ø¥Ø¨Ø¯Ø§Ø¹ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ù‚Ø·Ø§Ø¹ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØŒ ÙˆØ¥ÙŠØ¬Ø§Ø¯ Ø§Ù„Ø­Ø§Ù�Ø² Ù„Ù„Ø´Ø¨Ø§Ø¨ Ù„Ø¥Ø·Ù„Ø§Ù‚ Ø§Ù„Ø¹Ù†Ø§Ù† Ø£Ù…Ø§Ù… Ø§Ø¨Ø¯Ø§Ø¹Ø§ØªÙ‡Ù… ÙˆÙ…Ù‡Ø§Ø±Ø§ØªÙ‡Ù… ÙˆÙ‚Ø¯Ø±Ø§ØªÙ‡Ù… Ø§Ù„Ø§Ø¨ØªÙƒØ§Ø±ÙŠØ© Ø§Ù„ØªÙŠ ÙŠØªÙ…ÙŠØ²ÙˆÙ† Ø¨Ù‡Ø§ Ù�ÙŠ Ù…Ø¬Ø§Ù„Ø§Øª Ù…Ø®ØªÙ„Ù�Ø© Ù…Ù†Ù‡Ø§: Ø§Ù„Ø§Ø¨Ø¯Ø§Ø¹ Ø§Ù„Ø¹Ù„Ù…ÙŠØŒ Ø§Ù„ØªØµÙ…ÙŠÙ… Ø§Ù„Ø¬Ø±Ø§Ù�ÙŠÙƒÙŠØŒ Ø§Ù„ØªØµÙˆÙŠØ± Ø§Ù„Ù�ÙˆØªÙˆØºØ±Ø§Ù�ÙŠØŒ Ø¥Ù†ØªØ§Ø¬ Ø§Ù„Ø£Ù�Ù„Ø§Ù…ØŒ Ø§Ù„ØªØµÙ…ÙŠÙ… Ø§Ù„Ù…Ø¹Ù…Ø§Ø±ÙŠØŒ Ø§Ù„Ø±Ø³Ù… ÙˆØ§Ù„ØªØ´ÙƒÙŠÙ„.");
			    		builder1.setTitle("Ø¬Ø§Ø¦Ø²Ø© Ù†Ø§ØµØ± Ø¨Ù† Ø­Ù…Ø¯ Ø§Ù„Ø¹Ø§Ù„Ù…ÙŠØ© Ù„Ù„Ø¥Ø¨Ø¯Ø§Ø¹ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ");
			    		builder1.setCancelable(true);

			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
		    	    if(language.equals("en")){
		    	        //do nothing
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
			    		builder1.setMessage("The Nasser Bin Hamad International Youth Creativity award stresses on the development of the Youth Sector and works as an incentive for young people to unleash their creativity, skills and innovative abilities that are distinguished in six different fields including: Science, graphic design, photography, film production, architecture and art.");
			    		builder1.setTitle("Nasser Bin Hamad International Youth Creativity Award");
			    		builder1.setCancelable(true);

			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();

		    	    }
            }
            });
	    
	  //-- Youth City 2030--//
	    btn14 = (ImageButton)findViewById(R.id.menu_event5);
	    btn14.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("  ØªØ¹Ø¯ Ù…Ø¯ÙŠÙ†Ø© Ø´Ø¨Ø§Ø¨ 2030 Ù…Ø¨Ø§Ø¯Ø±Ø© Ø´Ø¨Ø§Ø¨ÙŠØ© ØºÙŠØ± Ù…Ø³Ø¨ÙˆÙ‚Ø© Ù�ÙŠ Ø§Ù„Ù…Ù…Ù„ÙƒØ© ØªÙ‡Ø¯Ù� Ù„ØªÙˆÙ�ÙŠØ± Ø£Ù�Ø¶Ù„ Ø¸Ø±ÙˆÙ� Ø§Ù„Ø±Ø¹Ø§ÙŠØ© Ù„Ù„Ø´Ø¨Ø§Ø¨ ÙˆØªÙ…ÙƒÙŠÙ†Ù‡Ù… ÙˆØ­Ø´Ø¯ Ø·Ø§Ù‚Ø§ØªÙ‡Ù… Ù„ÙŠØªÙ… ØªÙˆØ¬ÙŠÙ‡Ù‡Ø§ Ø§Ù„ØªÙˆØ¬ÙŠÙ‡ Ø§Ù„Ø³Ù„ÙŠÙ… ÙˆØªØ²ÙˆÙŠØ¯Ù‡Ù… Ø¨Ø§Ù„Ù…Ø¹Ø§Ø±Ù� Ø§Ù„Ù„Ø§Ø²Ù…Ø© Ø§Ù„ØªÙŠ ØªÙ†Ù…ÙŠ Ø§Ù„Ù…Ù‡Ø§Ø±Ø§Øª ÙˆØ§Ù„Ø³Ù…Ø§Øª Ø§Ù„Ù‚ÙŠØ§Ø¯ÙŠØ© Ù„Ø¯ÙŠÙ‡Ù… ÙˆÙ‡Ùˆ Ù…Ø´Ø±ÙˆØ¹ ÙŠØªÙ�Ø§Ø¹Ù„ Ø¥ÙŠØ¬Ø§Ø¨ÙŠØ§ Ù…Ø¹ Ø§Ù„Ø±Ø¤ÙŠØ© Ø§Ù„Ø§Ù‚ØªØµØ§Ø¯ÙŠØ© 2030  ÙˆØ§Ù„ØªÙŠ Ø±ÙƒØ²Øª Ø¹Ù„Ù‰ ØªÙ�Ø¹ÙŠÙ„ Ø¯ÙˆØ± Ø§Ù„Ø´Ø¨Ø§Ø¨ Ù�ÙŠ ØµÙŠØ§ØºØ© Ù…Ù�Ø±Ø¯Ø§Øª Ø§Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ø´Ø§Ù…Ù„Ø© Ù„Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ø¹Ù„Ù‰ Ù…Ø®ØªÙ„Ù� Ø§Ù„Ø£ØµØ¹Ø¯Ø©.");
		    		builder1.setTitle("Ù…Ø¯ÙŠÙ†Ø© Ø´Ø¨Ø§Ø¨ 2030");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("Youth City 2030 is an unprecedented youth initiative in the Kingdom aimed at providing the best conditions for caring for young people, empowering them and mobilizing their energies to provide them with the necessary knowledge and skills and to develop their leadership skills. This project positively interacts with the Economic Vision 2030, which focuses on activating the role of young people in the development of the Kingdom on various levels.");
		    		builder1.setTitle("Youth City 2030");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
	    	    }
            }
            });
	    
	  //-- 13th Bahrain Universities Model United Nations --//
	    btn15 = (ImageButton)findViewById(R.id.menu_event6);
	    btn15.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("ØªØ¹Ø¯ Ø¬Ù„Ø³Ø§Øª Ø§Ù„Ù…Ø¤ØªÙ…Ø± Ù…Ø­Ø§ÙƒØ§Ø© Ù„Ø¬Ù„Ø³Ø§Øª Ù…Ù†Ø¸Ù…Ø© Ø§Ù„Ø£Ù…Ù… Ø§Ù„Ù…ØªØ­Ø¯Ø©ØŒ ÙˆÙŠÙ‡Ø¯Ù� Ø§Ù„Ø¨Ø±Ù†Ø§Ù…Ø¬ Ø¥Ù„Ù‰ ØªØ±Ø³ÙŠØ® Ù�ÙƒØ±Ø© Ø£Ù† Ø§Ù„Ø­ÙˆØ§Ø± Ø§Ù„Ø¨Ù†Ø§Ø¡ Ø§Ù„ÙŠÙˆÙ… ÙŠÙ…ÙƒÙ† Ø£Ù† ÙŠÙˆØµÙ„Ù†Ø§ Ù„Ù„Ø­Ù„ÙˆÙ„ ØºØ¯Ø§Ù‹ØŒ ÙˆÙŠØ³Ø§Ù‡Ù… Ù�ÙŠ ØªØ£Ø³ÙŠØ³ 'Ù…ÙˆØ§Ø·Ù†ÙŠÙ† Ø¹Ø§Ù„Ù…ÙŠÙŠÙ†' Global Citizen ÙˆØ§Ù„Ù…ÙˆØ§Ø·Ù† Ø§Ù„Ø¹Ø§Ù„Ù…ÙŠ Ù‡Ùˆ Ù…Ù† ÙŠØ³ØªØ·ÙŠØ¹ Ø§Ù„ØªÙ�Ø§Ø¹Ù„ Ø¹Ù„Ù‰ Ù…Ø³ØªÙˆÙ‰ Ø¹Ø§Ù„Ù…ÙŠ Ù…Ø¹ Ø£ÙŠ Ø´Ø®Øµ Ù…Ù‡Ù…Ø§ Ø§Ø®ØªÙ„Ù�Øª Ø«Ù‚Ø§Ù�ØªÙ‡ ÙˆÙ…ÙˆØ·Ù†Ù‡ Ø¨Ø­ÙŠØ« ØªÙƒÙˆÙ† Ù„Ø¯ÙŠÙ‡ Ù…Ù‡Ø§Ø±Ø§Øª Ø§Ù„Ù…Ø´Ø§Ø±ÙƒØ© Ø§Ù„Ù…Ø¯Ù†ÙŠØ© ÙˆØ§Ù„Ù�Ø¹Ø§Ù„ÙŠØ© Ø§Ù„Ø³ÙŠØ§Ø³ÙŠØ©ØŒ Ø§Ù„ØªØ¹Ø§Ø·Ù� Ø§Ù„Ø«Ù‚Ø§Ù�ÙŠØŒ Ø§Ø­ØªØ±Ø§Ù… Ø§Ù„ØªÙ†ÙˆØ¹ØŒ ÙˆØ§Ù„Ù‚Ø¯Ø±Ø© Ø¹Ù„Ù‰ Ø§Ù„ØªÙˆÙ�ÙŠÙ‚ Ø¨ÙŠÙ† Ø§Ù„ØµØ±Ø§Ø¹Ø§Øª ÙˆØ§Ù„ØªÙˆØµÙ„ Ø¥Ù„Ù‰ ØªÙˆØ§Ù�Ù‚ Ù�ÙŠ Ø§Ù„Ø¢Ø±Ø§Ø¡ Ù…Ù† Ø®Ù„Ø§Ù„ ÙˆØ³Ø§Ø¦Ù„ Ø³Ù„Ù…ÙŠØ©.Ø±");
		    		builder1.setTitle("Ø¬Ù„Ø³Ø§Øª Ø§Ù„Ø£Ù…Ù… Ø§Ù„Ù…ØªØ­Ø¯Ø© Ù„Ù„Ø¬Ø§Ù…Ø¹Ø§Øª");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("The sessions of the conference are a simulation of the UN sessions. The program aims to instill the idea that constructive dialogue today can lead to solutions tomorrow, contribute to the establishment of a global citizen and the global citizen is able to interact on a global level with anyone, regardless of its culture and homeland. Have the skills of civic engagement and political effectiveness, cultural empathy, respect for diversity, the ability to reconcile conflicts and reach consensus through peaceful means.");
		    		builder1.setTitle("Bahrain Universities Model United Nations");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
		    		
	    	    }
	    		
	    				
            }
            });
	    
	  //-- Emtiyaz award for Youth Centers and National Sports Clubs --//
	    btn16 = (ImageButton)findViewById(R.id.menu_event7);
	    btn16.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("Ù…Ù† Ù…Ù†Ø·Ù„Ù‚ Ø§Ù„ØªØ·ÙˆÙŠØ± Ø§Ù„Ø´Ø§Ù…Ù„ Ù„Ù„Ø£Ù†Ø¯ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© Ù�ÙŠ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ†ØŒ Ø£Ø·Ù„Ù‚Øª ÙˆØ²Ø§Ø±Ø© Ø´Ø¤ÙˆÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø© (Ø¬Ø§Ø¦Ø²Ø© Ø§Ù…ØªÙŠØ§Ø²) Ù„Ù„Ø£Ù†Ø¯ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ØªÙ‡Ø¯Ù� Ù„Ù„ØªØºÙŠÙŠØ± Ø§Ù„Ù†ÙˆØ¹ÙŠ Ù�ÙŠ Ù…Ù†Ù‡Ø¬ÙŠØ§Øª Ø§Ù„Ø¹Ù…Ù„ Ø§Ù„Ù…Ø·Ø¨Ù‚Ø© Ù�ÙŠÙ‡Ù…Ø§ Ù„ØªØ­Ù‚ÙŠÙ‚ Ø£Ù�Ø¶Ù„ Ø§Ù„Ù†ØªØ§Ø¦Ø¬ Ù…Ù† Ø®Ù„Ø§Ù„ Ø®Ù„Ù‚ Ø¬Ùˆ ØªÙ†Ø§Ù�Ø³ÙŠ Ø´Ø±ÙŠÙ� Ù�ÙŠ Ù‚Ø·Ø§Ø¹ÙŠ Ø§Ù„Ø£Ù†Ø¯ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© Ù„Ù„ÙˆØµÙˆÙ„ Ø¥Ù„Ù‰ Ù…Ø³ØªÙˆÙ‰ Ø§Ù„ØªÙ…ÙŠØ².");
		    		builder1.setTitle("Ø¬Ø§Ø¦Ø²Ø© Ø§Ù…ØªÙŠØ§Ø²");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("As part of the overall development of the clubs and youth centers in the Kingdom of Bahrain, the Ministry of Youth and Sports has launched an excellence award â€œIMTIYAZâ€�  for clubs and youth centers aiming at qualitative changes in the working methodologies applied in them. This is  to achieve the best results by creating a competitive atmosphere in the clubs and youth centers to reach and sustain the highest levels of excellence.");
		    		builder1.setTitle("Emtiyaz");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
		    		
	    	    }
	    		
	    				
            }
            });
	   
	  //-- Khalid Bin Hamad Youth Theatre Award for National Sports Clubs and Youth Centers and Special Needs â€“Third Version --//
	    btn17 = (ImageButton)findViewById(R.id.menu_event8);
	    btn17.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("ØªÙ†Ø¸ÙŠÙ… Ù…Ù‡Ø±Ø¬Ø§Ù† Ù…Ø³Ø±Ø­ÙŠ Ø´Ø¨Ø§Ø¨ÙŠ Ù„ØªÙ†Ø´ÙŠØ· ÙˆØªØ·ÙˆÙŠØ± Ø§Ù„Ø­Ø±ÙƒØ© Ø§Ù„Ù…Ø³Ø±Ø­ÙŠØ© Ù�ÙŠ Ø§Ù„Ø£Ù†Ø¯ÙŠØ© Ø§Ù„ÙˆØ·Ù†ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ©.");
		    		builder1.setTitle("Ø¬Ø§Ø¦Ø²Ø© Ø®Ø§Ù„Ø¯ Ø¨Ù† Ø­Ù…Ø¯ Ù„Ù„Ù…Ø³Ø±Ø­ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ Ù„Ù„Ø£Ù†Ø¯ÙŠØ© Ø§Ù„ÙˆØ·Ù†ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆÙ„Ø°ÙˆÙŠ Ø§Ù„Ø§Ø¹Ø§Ù‚Ø©");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("Organizing a youth theatrical festival to revive and develop the theatrical movement of national clubs and youth centers.");
		    		builder1.setTitle("Khalid Bin Hamad Youth Theatre Award for National Sports Clubs and Youth Centers and Special Needs");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
		    		
	    	    }
		
            }
            });
	    
	  //-- Khalid Bin Hamad Futsal Championship  for Youth Centers and Special Needs- Fourth Version --//
	    btn18 = (ImageButton)findViewById(R.id.menu_event9);
	    btn18.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("Ø¥Ù‚Ø§Ù…Ø© Ø¯ÙˆØ±ÙŠ Ù„ÙƒØ±Ø© Ù‚Ø¯Ù… Ø§Ù„ØµØ§Ù„Ø§Øª Ù„Ø´Ø¨Ø§Ø¨ Ø§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆÙ„Ø°ÙˆÙŠ Ø§Ù„Ø§Ø¹Ø§Ù‚Ø©");
		    		builder1.setTitle("Ø¯ÙˆØ±ÙŠ Ø®Ø§Ù„Ø¯ Ø¨Ù† Ø­Ù…Ø¯ Ù„ÙƒØ±Ø© Ù‚Ø¯Ù… Ø§Ù„ØµØ§Ù„Ø§Øª Ù„Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆÙ„Ø°ÙˆÙŠ Ø§Ù„Ø¥Ø¹Ø§Ù‚Ø©.");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("Organizing Futsal Championship directed to Youth Centers and Special Needs to engage them in to sports activities and build the spirit of team work and unity.");
		    		builder1.setTitle("Khalid Bin Hamad Futsal Championship  for Youth Centers and Special Needs.");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
		    		
	    	    }
	    		
	    				
            }
            });

	  //-- Bahrain sports talent identification program --//
	    btn19 = (ImageButton)findViewById(R.id.menu_event10);
	    btn19.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("  Ø§ÙƒØªØ´Ø§Ù� Ø§Ù„Ù…ÙˆØ§Ù‡Ø¨ Ø§Ù„Ø±ÙŠØ§Ø¶ÙŠØ© Ù�ÙŠ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆØµÙ‚Ù„Ù‡Ø§ Ù„ØªÙƒÙˆÙ† Ø¬Ø§Ù‡Ø²Ø© Ù„Ù„Ù…Ø´Ø§Ø±ÙƒØ© Ù�ÙŠ Ø§Ù„Ø¯ÙˆØ±ÙŠØ§Øª Ùˆ Ø§Ù„Ø¨Ø·ÙˆÙ„Ø§Øª Ø§Ù„Ù…Ø­Ù„ÙŠØ© ÙˆØ§Ù„Ø¹Ø±Ø¨ÙŠØ© ÙˆØ§Ù„Ø¹Ø§Ù„Ù…ÙŠØ© Ù…Ù† Ø®Ù„Ø§Ù„ Ø²ÙŠØ§Ø±Ø§Øª Ù…ÙŠØ¯Ø§Ù†ÙŠØ©ØŒ Ù…Ù‡Ø±Ø¬Ø§Ù† Ø§Ù„Ø§Ù†ØªÙ‚Ø§Ø¡ØŒ Ø¨Ø±Ù†Ø§Ù…Ø¬ ØªØ¯Ø±ÙŠØ¨ÙŠ ØµÙŠÙ�ÙŠ Ù…ÙƒØ«Ù� Ø¨Ø§Ù„Ø¥Ø¶Ø§Ù�Ø© Ø§Ù„ Ø§Ù„Ø¨Ø±Ù†Ø§Ù…Ø¬ Ø§Ù„ØªØ¯Ø±ÙŠØ¨ÙŠ Ø§Ù„Ù…Ø³ØªÙ…Ø±.");
		    		builder1.setTitle("Ø¨Ø±Ù†Ø§Ù…Ø¬ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ù„Ø§ÙƒØªØ´Ø§Ù� Ø§Ù„Ù…ÙˆØ§Ù‡Ø¨ Ø§Ù„Ø±ÙŠØ§Ø¶ÙŠØ©");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    		builder1.setMessage("The Talent program has a major role it structures  a coherent sports platform. It insures the release of continuous generations of promising players of which are the base of an aspiring bright future for the sports industry in the Kingdom of Bahrain the programs aims to Choose promising aspiring sports talents in the various individual and group games it also thrives to Develop sports talents physically  through constant training by Providing professional training to the players all through the year by coordinating  with national clubs and sports associations not forgetting the Constant liaising with professionals to bench mark the players in a high rank of performance, training that would guarantee their optimum readiness to compete and gain success     ");
		    		builder1.setTitle("Bahrain sports talent identification program ");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
	    	    }		
            }
            });
	    
	    
	    
	   //######## RIGTH TO LEFT #########
	  //--International Youth Conference --//
	    btn20 = (ImageButton)findViewById(R.id.menu_eventR8);
	    btn20.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		   
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("ØªÙ†Ø¸Ù… ÙˆØ²Ø§Ø±Ø© Ø´Ø¦ÙˆÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø© Ù�ÙŠ ÙƒÙ„ Ø¹Ø§Ù… Ù…Ø¤ØªÙ…Ø± Ø§Ù„Ø´Ø¨Ø§Ø¨ Ø§Ù„Ø¯ÙˆÙ„ÙŠ ÙˆØ§Ù„Ø°ÙŠ ÙŠØ¹Ø¯ Ù†Ù‚Ø·Ø© Ø§Ù„ØªÙ‚Ø§Ø¡ Ø¨ÙŠÙ† Ø´Ø¨Ø§Ø¨ Ø§Ù„Ù…Ù…Ù„ÙƒØ© ÙˆØ£Ù‚Ø±Ø§Ù†Ù‡Ù… Ù…Ù† Ø¯ÙˆÙ„ Ø§Ù„Ø¹Ø§Ù„Ù… Ù„Ù„Ø­ÙˆØ§Ø± ÙˆØ§Ù„Ù†Ù‚Ø§Ø´ Ù�ÙŠ ÙƒÙ„ Ù…Ø§ ÙŠØªØ¹Ù„Ù‚ Ø¨Ø§Ù„Ø´Ø£Ù† Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ Ù�ÙŠ Ù…ÙˆØ§Ø¶ÙŠØ¹ Ù…Ø®ØªÙ„Ù�Ø© Ù�ÙŠ ÙƒÙ„ Ø¹Ø§Ù… ÙˆÙŠØªØ¶Ù…Ù† Ø§Ù„Ù…Ø¤ØªÙ…Ø± Ø¹Ù„Ù‰ Ø¬Ù„Ø³Ø§Øª Ø¹Ø§Ù…Ø© Ù…Ù�ØªÙˆØ­Ø© Ù„Ù„Ø¬Ù…ÙŠØ¹ ÙŠØ­Ø§Ø¶Ø± Ù�ÙŠÙ‡Ø§ Ù†Ø®Ø¨Ø© Ù…Ù† Ø£Ø´Ù‡Ø± Ø§Ù„Ù…ØªØ­Ø¯Ø«ÙŠÙ† Ø§Ù„Ø¹Ø§Ù„Ù…ÙŠÙŠÙ† ÙˆÙˆØ±Ø´ Ø¹Ù…Ù„ Ù�ÙŠÙ…Ø§ ÙŠØªØ¹Ù„Ù‚ Ø¨Ù…ÙˆØ¶ÙˆØ¹ ÙƒÙ„ Ø¹Ø§Ù… Ø¨Ø§Ù„Ø¥Ø¶Ø§Ù�Ø© Ø¥Ù„Ù‰ Ù…Ø¬Ù…ÙˆØ¹Ø© Ù…Ù† Ø§Ù„Ø£Ù†Ø´Ø·Ø© Ø§Ù„Ù…ØµØ§Ø­Ø¨Ø© ÙˆØ§Ù„ØªØ¹Ø±ÙŠÙ�ÙŠØ© Ø¨Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ù…Ø³ØªÙ‡Ø¯Ù�ÙŠÙ† Ø¨Ù‡Ø§ Ø§Ù„Ø¯ÙˆÙ„ Ø§Ù„Ù…Ø´Ø§Ø±ÙƒØ© Ù…Ù† Ù…Ø®ØªÙ„Ù� Ø£Ø±Ø¬Ø§Ø¡ Ø§Ù„Ø¹Ø§Ù„Ù….");
		    		builder1.setTitle("Ù…Ø¤ØªÙ…Ø± Ø§Ù„Ø´Ø¨Ø§Ø¨ Ø§Ù„Ø¯ÙˆÙ„ÙŠ");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("The Ministry of Youth and Sport Affairs annually organizes the International Youth Conference. This conference is considered a point of contact between the youth in the Kingdom of Bahrain and their peers from around the world to engage in dialogue and discuss everything that is relevant to the youth with different topics presented each year. The conference includes public open sessions for all to attend which are led by a high caliber of well-known international speakers, workshops covering the yearâ€™s topics of the conference as well as accompanying activities that aim to introduce and Kingdom of Bahrain to the visiting delegations from around the world.");
		    		builder1.setTitle("The International Youth Conference");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();

	    	    }
	    	    
            }
            });
	    
	  //--King Hamad Youth Empowerment Award--//
	    btn21 = (ImageButton)findViewById(R.id.menu_eventR9);
	    btn21.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		 if(language.equals("ar")){
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    	    	builder1.setMessage(" ØªØ£ØªÙŠ Ø¬Ø§Ø¦Ø²Ø© Ø§Ù„Ù…Ù„Ùƒ Ø­Ù…Ø¯ Ù„ØªÙ…ÙƒÙŠÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙƒÙ…Ø¨Ø§Ø¯Ø±Ø© Ù…Ù† ÙˆØ²Ø§Ø±Ø© Ø´Ø¦ÙˆÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø© Ù�ÙŠ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ù„ØªÙ…ÙƒÙŠÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„ØªØ±ÙƒÙŠÙ€Ø² Ø¹Ù„Ù‰ Ø§Ù„Ø§Ù‡ØªÙ…Ø§Ù… Ø¨Ù‡Ù…ØŒ ÙˆÙ‡Ø°Ù‡ Ø§Ù„Ø¬Ø§Ø¦Ø²Ø© Ù‡ÙŠ Ø§Ù„Ø£ÙˆÙ„Ù‰ Ù…Ù† Ù†ÙˆØ¹Ù‡Ø§ Ù„Ø¬Ù…ÙŠØ¹ Ø§Ù„Ø¯ÙˆÙ„ Ø¨Ù‡Ø¯Ù� Ø­Ø«Ù‡Ù… Ø¹Ù„Ù‰ Ø§Ù„Ø§Ù‡ØªÙ…Ø§Ù… ÙˆØ§Ø­ØªÙˆØ§Ø¡ Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØªØ´Ø¬ÙŠØ¹Ø§Ù‹ Ù„ØªØ­Ø³ÙŠÙ† Ø§Ù„Ø¨Ù†ÙŠØ© Ø§Ù„ØªØ­ØªÙŠØ© Ø§Ù„Ù…ØªØ¹Ù„Ù‚Ø© Ø¨Ø§Ù„Ø´Ø¨Ø§Ø¨ Ù„ØªØ¹ÙˆØ¯ Ø¨Ø§Ù„Ù†Ù�Ø¹ Ø¹Ù„Ù‰ ÙƒØ§Ù�Ø© Ø§Ù„Ø´Ø±Ø§Ø¦Ø­ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆØ§Ù„Ø¯Ø§Ø¹Ù…Ø© Ù„Ù„Ø´Ø¨Ø§Ø¨. ÙˆØªØ£ØªÙŠ Ù‡Ø°Ù‡ Ø§Ù„Ù…Ø¨Ø§Ø¯Ø±Ø© Ù…Ø¹Ø²Ø²Ø© Ù„Ø¯ÙˆØ± Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ù�ÙŠ ØªÙ†Ù…ÙŠØ© Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ­Ø±ØµØ§Ù‹ Ù…Ù†Ù‡Ø§ Ø¹Ù„Ù‰ Ø§Ù„Ø§Ø³ØªØ¯Ø§Ù…Ø© Ù…Ù† Ø®Ù„Ø§Ù„ Ø¥Ø´Ø±Ø§Ùƒ Ø¬Ù…ÙŠØ¹ Ù�Ø¦Ø§Øª Ø§Ù„Ù…Ø¬ØªÙ…Ø¹ Ù�ÙŠ Ø°Ù„Ùƒ ÙˆØ¨Ø§Ù„Ø£Ø®Øµ Ø§Ù„Ø´Ø¨Ø§Ø¨ØŒ Ù„Ù…Ø§ ÙŠÙ…ØªÙ„ÙƒÙˆÙ†Ù‡ Ù…Ù† Ù‚Ø¯Ø±Ø§Øª ÙˆØ·Ø§Ù‚Ø§Øª Ù‡Ø§Ø¦Ù„Ø© Ù…Ù† Ø´Ø£Ù†Ù‡Ø§ ØªØ­Ù‚ÙŠÙ‚ ØªØ·Ù„Ø¹Ø§ØªÙ‡Ù…ØŒ ÙƒÙ…Ø§ ØªÙ‡Ø¯Ù� Ù‡Ø°Ù‡ Ø§Ù„Ù…Ø¨Ø§Ø¯Ø±Ø© Ø£ÙŠØ¶Ø§ Ø¥Ù„Ù‰ ØªØ­Ù‚ÙŠÙ‚ Ø£Ù‡Ø¯Ø§Ù� Ø§Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ù…Ø³ØªØ¯Ø§Ù…Ø©ØŒ ÙˆØ§Ù„ØªÙŠ ØªÙ‡Ø¯Ù� Ø¥Ù„Ù‰ ØªÙˆÙ�ÙŠØ± Ù…Ø³ØªÙ‚Ø¨Ù„ Ø£Ù�Ø¶Ù„ Ù„Ù„Ø´Ø¨Ø§Ø¨ØŒ ÙƒÙ…Ø§ Ø£Ù†Ù‡ Ù…Ù† Ø®Ù„Ø§Ù„ Ù‡Ø°Ù‡ Ø§Ù„Ø¬Ø§Ø¦Ø²Ø© Ù�Ø¥Ù† Ø¬Ù…ÙŠØ¹ Ø§Ù„Ù…ØªÙ‚Ø¯Ù…ÙŠÙ† Ø³ÙˆÙ� ÙŠØ­Ù‚Ù‚ÙˆÙ† Ù…Ø¤Ø´Ø±Ø§Ù‹ Ù…Ù† Ù…Ø¤Ø´Ø±Ø§Øª Ø£Ù‡Ø¯Ø§Ù� Ø§Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ù…Ø³ØªØ¯Ø§Ù…Ø©. Ø¥Ù† Ø§Ù„Ø¬Ø§Ø¦Ø²Ø© Ø¨Ù…Ø§ ØªØ­Ù…Ù„Ù‡ Ù…Ù† Ø£Ù‡Ø¯Ø§Ù� Ø³Ø§Ù…ÙŠØ© Ù…Ù† Ø´Ø£Ù†Ù‡Ø§ Ø£Ù† ØªØ±ØªÙ‚ÙŠ Ø¨Ø§Ù„Ø´Ø±ÙŠØ­Ø© Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© Ø¥Ø°Ø§ Ù…Ø§ ØªÙ… Ø§Ù„Ù†Ø¸Ø± Ø¥Ù„ÙŠÙ‡Ø§ Ù…Ù† Ø¬Ø§Ù†Ø¨ Ø§Ù„Ø¬Ù‡Ø§Øª Ø§Ù„Ø­ÙƒÙˆÙ…ÙŠØ© ÙˆØ§Ù„Ù‚Ø·Ø§Ø¹ Ø§Ù„Ø®Ø§Øµ ÙˆØ§Ù„Ù‚Ø·Ø§Ø¹ Ø§Ù„Ø£Ù‡Ù„ÙŠ ÙˆØ§Ù„Ø£Ù�Ø±Ø§Ø¯ Ø­ÙŠØ« ØªØ´ÙƒÙ„ Ø§Ù„Ù�Ø¦Ø§Øª Ø§Ù„Ø³Ø§Ø¨Ù‚Ø© Ø§Ù„Ø£Ù�Ø±Ø¹ Ø§Ù„Ø£Ø³Ø§Ø³ÙŠØ© Ø§Ù„Ù…Ø­Ø±ÙƒØ© Ù„Ù„Ø¹Ù…Ù„ Ù�ÙŠ Ø§Ù„Ù‚Ø·Ø§Ø¹ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ. Ø³ØªÙ…Ù†Ø­ Ù‡Ø°Ù‡ Ø§Ù„Ø¬Ø§Ø¦Ø²Ø© Ø¥Ù„Ù‰ Ø£Ø±Ø¨Ø¹ Ù�Ø¦Ø§Øª Ù…Ø®ØªÙ„Ù�Ø© ÙˆÙ‡Ù…: Ø§Ù„Ù…Ø¤Ø³Ø³Ø§Øª Ø§Ù„Ø­ÙƒÙˆÙ…ÙŠØ©ØŒ Ø§Ù„Ù…Ø¤Ø³Ø³Ø§Øª Ø§Ù„Ø®Ø§ØµØ©ØŒ Ø§Ù„Ù…Ù†Ø¸Ù…Ø§Øª ØºÙŠØ± Ø§Ù„Ø±Ø¨Ø­ÙŠØ© ÙˆØ§Ù„Ù…Ù†Ø¸Ù…Ø§Øª Ø§Ù„Ø¯ÙˆÙ„ÙŠØ© ÙˆØ§Ù„Ø£Ù�Ø±Ø§Ø¯ØŒ ÙˆÙŠÙ„Ø²Ù… Ø¬Ù…ÙŠØ¹ Ø§Ù„Ù�Ø¦Ø§Øª Ù…Ø§Ø¹Ø¯Ø§ Ø§Ù„Ù…Ø¤Ø³Ø³Ø§Øª Ø§Ù„Ø­ÙƒÙˆÙ…ÙŠØ© Ø£Ù† ØªÙƒÙˆÙ† Ù…Ø¹ØªØ±Ù� Ø¨Ù‡Ø§ Ù…Ù† Ù‚Ø¨Ù„ Ø§Ù„Ø¯ÙˆÙ„Ø© Ø§Ù„ØªÙŠ ÙŠØªÙ… Ø§Ù„ØªÙ‚Ø¯ÙŠÙ… Ù…Ù† Ø®Ù„Ø§Ù„Ù‡Ø§ØŒ ÙˆØ³ØªØªÙƒÙ�Ù„ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ø¨Ø¬Ù…ÙŠØ¹ Ø§Ù„Ø§Ù„ØªØ²Ø§Ù…Ø§Øª Ø§Ù„Ù…Ø§Ù„ÙŠØ© Ù„Ù„Ø¬Ø§Ø¦Ø²Ø©");
			    		builder1.setTitle("Ø¬Ø§Ø¦Ø²Ø© Ø§Ù„Ù…Ù„Ùƒ Ø­Ù…Ø¯ Ù„ØªÙ…ÙƒÙŠÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ Ù„ØªØ­Ù‚ÙŠÙ‚ Ø£Ù‡Ø¯Ø§Ù� Ø§Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ù…Ø³ØªØ¯Ø§Ù…Ø©");
			    		builder1.setCancelable(true);
			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
		    	    if(language.equals("en")){
		    	        //do nothing
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    	    	builder1.setMessage("The King Hamad Youth Empowerment award comes as an initiative from the Ministry of Youth and Sport Affairs in the Kingdom of Bahrain, as the first award of its kind that will be open to all Member States. This initiative aims to empower young people and give them the attention, encouragement and motivation needed. It will encourage all eligible participants to improve the enabling environment and infrastructure for youth to allow them to be effective and productive citizens contributing to their communities. This initiative enhances the role of the Kingdom of Bahrain in youth development and its commitment to sustainability, through the involvement of all segments of society in it, particularly young people, as they own the abilities and energies that will achieve this. The initiative also aims to achieve the Sustainable Development Goals that aim to provide the best for young people's future and through this award; all applicants will have achieved SDG targets. This prestigious award including all that it will stand for and its goals, will get all sectors of the community involved and bring us a step forward in not only raising awareness for the importance of the youth but take us a step forward to achieving many goals that will help us create a better future. ");
			    		builder1.setTitle("King Hamad Youth Empowerment Award to Achieve the SDGs");
			    		builder1.setCancelable(true);
			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
            }
            });

	  //--Ajyal 2030--//
	    btn22 = (ImageButton)findViewById(R.id.menu_eventR10);
	    btn22.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		
	    		 if(language.equals("ar")){
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    	    	builder1.setMessage("ÙŠÙ‚Ø¯Ù… Ù…Ø±ÙƒØ² Ø³Ù„Ù…Ø§Ù† Ø§Ù„Ø«Ù‚Ø§Ù�ÙŠ Ù�ÙŠ ÙˆØ²Ø§Ø±Ø© Ø´Ø¤ÙˆÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø© Ù„Ù„Ù†Ø§Ø´Ø¦Ø© Ø®Ù„Ø§Ù„ Ù�ØªØ±Ø© Ø§Ù„Ø¥Ø¬Ø§Ø²Ø© Ø§Ù„ØµÙŠÙ�ÙŠØ© Ø¨Ø±Ù†Ø§Ù…Ø¬ Ø£Ø¬ÙŠØ§Ù„ 2030  ÙˆÙŠØ´ØªÙ…Ù„ Ø¹Ù„Ù‰ Ø§Ù„ÙƒØ«ÙŠØ± Ù…Ù† Ø§Ù„Ø£Ù†Ø´Ø·Ø© Ø§Ù„Ù…ØªÙ†ÙˆØ¹Ø© Ø§Ù„ØªÙŠ ØªÙ„Ø¨ÙŠ Ø±ØºØ¨Ø§Øª Ø§Ù„Ù…Ø´Ø§Ø±ÙƒÙŠÙ† Ù„ØµÙ‚Ù„ Ù…ÙˆØ§Ù‡Ø¨Ù‡Ù… ÙˆÙ…Ù‡Ø§Ø±Ø§ØªÙ‡Ù… Ù†Ø­Ùˆ ØªØ·ÙˆÙŠØ± Ø§Ù„Ù…Ø³ØªÙˆÙ‰ Ø§Ù„Ø¥Ø¨Ø¯Ø§Ø¹ÙŠ Ù„Ø¯ÙŠÙ‡Ù…ØŒ Ø­ÙŠØ« ÙŠÙ‚Ø¯Ù… Ø§Ù„Ù…Ø±ÙƒØ² Ø¨Ø§Ù‚Ø© Ù…ØªÙƒØ§Ù…Ù„Ø© Ù…Ù† Ø§Ù„Ø£Ù†Ø´Ø·Ø© Ø¨Ø±Ø¤ÙŠØ© Ø¹ØµØ±ÙŠØ© Ù…ØªÙˆØ§ÙƒØ¨Ø© Ù…Ø¹ Ù…Ø§ ØªØ±Ù†Ùˆ Ø¥Ù„ÙŠÙ‡ Ø§Ù„Ù‚ÙŠØ§Ø¯Ø© Ø§Ù„Ø­ÙƒÙŠÙ…Ø© Ù�ÙŠ ØªÙ‡ÙŠØ¦Ø© Ø£Ø¬ÙŠØ§Ù„ Ù‚Ø§Ø¯Ø±Ø© Ø¹Ù„Ù‰ Ù…ÙˆØ§ÙƒØ¨Ø© Ø§Ù„Ø­Ø§Ø¶Ø± ÙˆØµÙˆÙ„Ø§Ù‹ Ù„Ù„Ù…Ø³ØªÙ‚Ø¨Ù„ØŒ Ù�ÙŠ Ø¹Ø¯Ø© Ù…Ø¬Ø§Ù„Ø§Øª ÙƒØ§Ù„Ù�Ù†ÙˆÙ† ÙˆØ§Ù„Ù‚ÙŠØ§Ø¯Ø© ÙˆØ§Ù„Ø¹Ù„ÙˆÙ… ÙˆØ§Ù„ØªÙƒÙ†Ù„ÙˆØ¬ÙŠØ§ ÙˆØ§Ù„Ø¥Ø¹Ù„Ø§Ù… ÙˆØ§Ù„ØµØ­Ø© ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø©");
			    		builder1.setTitle("Ø£Ø¬ÙŠØ§Ù„ 2030");
			    		builder1.setCancelable(true);
			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		 });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
		    	    if(language.equals("en")){
		    	        //do nothing
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    	    	builder1.setMessage("During the summer holidays, Salman Cultural Center at the Ministry of youth and Sport Affairs offers a program called 'Ajyal 2030'. It includes many activities that meet the participants' needs to enhance their talents and skills towards developing their creative skills. This program comes from our wise leadershipâ€™s perspective in creating generations capable of keeping up with the present and being ready for the future in various fields such as arts, leadership, science, technology, media, health and sports.");
			    		builder1.setTitle("Ajyal 2030");
			    		builder1.setCancelable(true);

			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
            }
            });
	    
	    
		//-- Nasser Bin Hamad International Youth Creativity Award --//
	    btnR1 = (ImageButton)findViewById(R.id.menu_eventR1);
	    btnR1.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		 if(language.equals("ar")){
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    	    	builder1.setMessage("ØªØ£ØªÙŠ Ø¬Ø§Ø¦Ø²Ø© Ù†Ø§ØµØ± Ø¨Ù† Ø­Ù…Ø¯ Ø§Ù„Ø¹Ø§Ù„Ù…ÙŠØ© Ù„Ù„Ø¥Ø¨Ø¯Ø§Ø¹ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ù‚Ø·Ø§Ø¹ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØŒ ÙˆØ¥ÙŠØ¬Ø§Ø¯ Ø§Ù„Ø­Ø§Ù�Ø² Ù„Ù„Ø´Ø¨Ø§Ø¨ Ù„Ø¥Ø·Ù„Ø§Ù‚ Ø§Ù„Ø¹Ù†Ø§Ù† Ø£Ù…Ø§Ù… Ø§Ø¨Ø¯Ø§Ø¹Ø§ØªÙ‡Ù… ÙˆÙ…Ù‡Ø§Ø±Ø§ØªÙ‡Ù… ÙˆÙ‚Ø¯Ø±Ø§ØªÙ‡Ù… Ø§Ù„Ø§Ø¨ØªÙƒØ§Ø±ÙŠØ© Ø§Ù„ØªÙŠ ÙŠØªÙ…ÙŠØ²ÙˆÙ† Ø¨Ù‡Ø§ Ù�ÙŠ Ù…Ø¬Ø§Ù„Ø§Øª Ù…Ø®ØªÙ„Ù�Ø© Ù…Ù†Ù‡Ø§: Ø§Ù„Ø§Ø¨Ø¯Ø§Ø¹ Ø§Ù„Ø¹Ù„Ù…ÙŠØŒ Ø§Ù„ØªØµÙ…ÙŠÙ… Ø§Ù„Ø¬Ø±Ø§Ù�ÙŠÙƒÙŠØŒ Ø§Ù„ØªØµÙˆÙŠØ± Ø§Ù„Ù�ÙˆØªÙˆØºØ±Ø§Ù�ÙŠØŒ Ø¥Ù†ØªØ§Ø¬ Ø§Ù„Ø£Ù�Ù„Ø§Ù…ØŒ Ø§Ù„ØªØµÙ…ÙŠÙ… Ø§Ù„Ù…Ø¹Ù…Ø§Ø±ÙŠØŒ Ø§Ù„Ø±Ø³Ù… ÙˆØ§Ù„ØªØ´ÙƒÙŠÙ„.");
			    		builder1.setTitle("Ø¬Ø§Ø¦Ø²Ø© Ù†Ø§ØµØ± Ø¨Ù† Ø­Ù…Ø¯ Ø§Ù„Ø¹Ø§Ù„Ù…ÙŠØ© Ù„Ù„Ø¥Ø¨Ø¯Ø§Ø¹ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ");
			    		builder1.setCancelable(true);

			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();
		    	    }
		    	    if(language.equals("en")){
		    	        //do nothing
		    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
		    	    	builder1.setMessage("The Nasser Bin Hamad International Youth Creativity award stresses on the development of the Youth Sector and works as an incentive for young people to unleash their creativity, skills and innovative abilities that are distinguished in six different fields including: Science, graphic design, photography, film production, architecture and art.");
			    		builder1.setTitle("Nasser Bin Hamad International Youth Creativity Award");
			    		builder1.setCancelable(true);

			    		builder1.setPositiveButton(
			    		    "Ok",
			    		    new DialogInterface.OnClickListener() {
			    		        public void onClick(DialogInterface dialog, int id) {
			    		            dialog.cancel();
			    		        }
			    		    });
			    		AlertDialog alert11 = builder1.create();
			    		alert11.show();

		    	    }
            }
            });
	    
	  //-- Youth City 2030--//
	    btnR2 = (ImageButton)findViewById(R.id.menu_eventR2);
	    btnR2.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("  ØªØ¹Ø¯ Ù…Ø¯ÙŠÙ†Ø© Ø´Ø¨Ø§Ø¨ 2030 Ù…Ø¨Ø§Ø¯Ø±Ø© Ø´Ø¨Ø§Ø¨ÙŠØ© ØºÙŠØ± Ù…Ø³Ø¨ÙˆÙ‚Ø© Ù�ÙŠ Ø§Ù„Ù…Ù…Ù„ÙƒØ© ØªÙ‡Ø¯Ù� Ù„ØªÙˆÙ�ÙŠØ± Ø£Ù�Ø¶Ù„ Ø¸Ø±ÙˆÙ� Ø§Ù„Ø±Ø¹Ø§ÙŠØ© Ù„Ù„Ø´Ø¨Ø§Ø¨ ÙˆØªÙ…ÙƒÙŠÙ†Ù‡Ù… ÙˆØ­Ø´Ø¯ Ø·Ø§Ù‚Ø§ØªÙ‡Ù… Ù„ÙŠØªÙ… ØªÙˆØ¬ÙŠÙ‡Ù‡Ø§ Ø§Ù„ØªÙˆØ¬ÙŠÙ‡ Ø§Ù„Ø³Ù„ÙŠÙ… ÙˆØªØ²ÙˆÙŠØ¯Ù‡Ù… Ø¨Ø§Ù„Ù…Ø¹Ø§Ø±Ù� Ø§Ù„Ù„Ø§Ø²Ù…Ø© Ø§Ù„ØªÙŠ ØªÙ†Ù…ÙŠ Ø§Ù„Ù…Ù‡Ø§Ø±Ø§Øª ÙˆØ§Ù„Ø³Ù…Ø§Øª Ø§Ù„Ù‚ÙŠØ§Ø¯ÙŠØ© Ù„Ø¯ÙŠÙ‡Ù… ÙˆÙ‡Ùˆ Ù…Ø´Ø±ÙˆØ¹ ÙŠØªÙ�Ø§Ø¹Ù„ Ø¥ÙŠØ¬Ø§Ø¨ÙŠØ§ Ù…Ø¹ Ø§Ù„Ø±Ø¤ÙŠØ© Ø§Ù„Ø§Ù‚ØªØµØ§Ø¯ÙŠØ© 2030  ÙˆØ§Ù„ØªÙŠ Ø±ÙƒØ²Øª Ø¹Ù„Ù‰ ØªÙ�Ø¹ÙŠÙ„ Ø¯ÙˆØ± Ø§Ù„Ø´Ø¨Ø§Ø¨ Ù�ÙŠ ØµÙŠØ§ØºØ© Ù…Ù�Ø±Ø¯Ø§Øª Ø§Ù„ØªÙ†Ù…ÙŠØ© Ø§Ù„Ø´Ø§Ù…Ù„Ø© Ù„Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ø¹Ù„Ù‰ Ù…Ø®ØªÙ„Ù� Ø§Ù„Ø£ØµØ¹Ø¯Ø©.");
		    		builder1.setTitle("Ù…Ø¯ÙŠÙ†Ø© Ø´Ø¨Ø§Ø¨ 2030");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("Youth City 2030 is an unprecedented youth initiative in the Kingdom aimed at providing the best conditions for caring for young people, empowering them and mobilizing their energies to provide them with the necessary knowledge and skills and to develop their leadership skills. This project positively interacts with the Economic Vision 2030, which focuses on activating the role of young people in the development of the Kingdom on various levels.");
		    		builder1.setTitle("Youth City 2030");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
	    	    }
            }
            });
	    
	  //-- 13th Bahrain Universities Model United Nations --//
	    btnR3 = (ImageButton)findViewById(R.id.menu_eventR3);
	    btnR3.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("ØªØ¹Ø¯ Ø¬Ù„Ø³Ø§Øª Ø§Ù„Ù…Ø¤ØªÙ…Ø± Ù…Ø­Ø§ÙƒØ§Ø© Ù„Ø¬Ù„Ø³Ø§Øª Ù…Ù†Ø¸Ù…Ø© Ø§Ù„Ø£Ù…Ù… Ø§Ù„Ù…ØªØ­Ø¯Ø©ØŒ ÙˆÙŠÙ‡Ø¯Ù� Ø§Ù„Ø¨Ø±Ù†Ø§Ù…Ø¬ Ø¥Ù„Ù‰ ØªØ±Ø³ÙŠØ® Ù�ÙƒØ±Ø© Ø£Ù† Ø§Ù„Ø­ÙˆØ§Ø± Ø§Ù„Ø¨Ù†Ø§Ø¡ Ø§Ù„ÙŠÙˆÙ… ÙŠÙ…ÙƒÙ† Ø£Ù† ÙŠÙˆØµÙ„Ù†Ø§ Ù„Ù„Ø­Ù„ÙˆÙ„ ØºØ¯Ø§Ù‹ØŒ ÙˆÙŠØ³Ø§Ù‡Ù… Ù�ÙŠ ØªØ£Ø³ÙŠØ³ 'Ù…ÙˆØ§Ø·Ù†ÙŠÙ† Ø¹Ø§Ù„Ù…ÙŠÙŠÙ†' Global Citizen ÙˆØ§Ù„Ù…ÙˆØ§Ø·Ù† Ø§Ù„Ø¹Ø§Ù„Ù…ÙŠ Ù‡Ùˆ Ù…Ù† ÙŠØ³ØªØ·ÙŠØ¹ Ø§Ù„ØªÙ�Ø§Ø¹Ù„ Ø¹Ù„Ù‰ Ù…Ø³ØªÙˆÙ‰ Ø¹Ø§Ù„Ù…ÙŠ Ù…Ø¹ Ø£ÙŠ Ø´Ø®Øµ Ù…Ù‡Ù…Ø§ Ø§Ø®ØªÙ„Ù�Øª Ø«Ù‚Ø§Ù�ØªÙ‡ ÙˆÙ…ÙˆØ·Ù†Ù‡ Ø¨Ø­ÙŠØ« ØªÙƒÙˆÙ† Ù„Ø¯ÙŠÙ‡ Ù…Ù‡Ø§Ø±Ø§Øª Ø§Ù„Ù…Ø´Ø§Ø±ÙƒØ© Ø§Ù„Ù…Ø¯Ù†ÙŠØ© ÙˆØ§Ù„Ù�Ø¹Ø§Ù„ÙŠØ© Ø§Ù„Ø³ÙŠØ§Ø³ÙŠØ©ØŒ Ø§Ù„ØªØ¹Ø§Ø·Ù� Ø§Ù„Ø«Ù‚Ø§Ù�ÙŠØŒ Ø§Ø­ØªØ±Ø§Ù… Ø§Ù„ØªÙ†ÙˆØ¹ØŒ ÙˆØ§Ù„Ù‚Ø¯Ø±Ø© Ø¹Ù„Ù‰ Ø§Ù„ØªÙˆÙ�ÙŠÙ‚ Ø¨ÙŠÙ† Ø§Ù„ØµØ±Ø§Ø¹Ø§Øª ÙˆØ§Ù„ØªÙˆØµÙ„ Ø¥Ù„Ù‰ ØªÙˆØ§Ù�Ù‚ Ù�ÙŠ Ø§Ù„Ø¢Ø±Ø§Ø¡ Ù…Ù† Ø®Ù„Ø§Ù„ ÙˆØ³Ø§Ø¦Ù„ Ø³Ù„Ù…ÙŠØ©.Ø±");
		    		builder1.setTitle("Ø¬Ù„Ø³Ø§Øª Ø§Ù„Ø£Ù…Ù… Ø§Ù„Ù…ØªØ­Ø¯Ø© Ù„Ù„Ø¬Ø§Ù…Ø¹Ø§Øª");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("The sessions of the conference are a simulation of the UN sessions. The program aims to instill the idea that constructive dialogue today can lead to solutions tomorrow, contribute to the establishment of a global citizen and the global citizen is able to interact on a global level with anyone, regardless of its culture and homeland. Have the skills of civic engagement and political effectiveness, cultural empathy, respect for diversity, the ability to reconcile conflicts and reach consensus through peaceful means.");
		    		builder1.setTitle("Bahrain Universities Model United Nations");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
		    		
	    	    }		
            }
            });
	    
	  //-- Emtiyaz award for Youth Centers and National Sports Clubs --//
	    btnR4 = (ImageButton)findViewById(R.id.menu_eventR4);
	    btnR4.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("Ù…Ù† Ù…Ù†Ø·Ù„Ù‚ Ø§Ù„ØªØ·ÙˆÙŠØ± Ø§Ù„Ø´Ø§Ù…Ù„ Ù„Ù„Ø£Ù†Ø¯ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© Ù�ÙŠ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ†ØŒ Ø£Ø·Ù„Ù‚Øª ÙˆØ²Ø§Ø±Ø© Ø´Ø¤ÙˆÙ† Ø§Ù„Ø´Ø¨Ø§Ø¨ ÙˆØ§Ù„Ø±ÙŠØ§Ø¶Ø© (Ø¬Ø§Ø¦Ø²Ø© Ø§Ù…ØªÙŠØ§Ø²) Ù„Ù„Ø£Ù†Ø¯ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ØªÙ‡Ø¯Ù� Ù„Ù„ØªØºÙŠÙŠØ± Ø§Ù„Ù†ÙˆØ¹ÙŠ Ù�ÙŠ Ù…Ù†Ù‡Ø¬ÙŠØ§Øª Ø§Ù„Ø¹Ù…Ù„ Ø§Ù„Ù…Ø·Ø¨Ù‚Ø© Ù�ÙŠÙ‡Ù…Ø§ Ù„ØªØ­Ù‚ÙŠÙ‚ Ø£Ù�Ø¶Ù„ Ø§Ù„Ù†ØªØ§Ø¦Ø¬ Ù…Ù† Ø®Ù„Ø§Ù„ Ø®Ù„Ù‚ Ø¬Ùˆ ØªÙ†Ø§Ù�Ø³ÙŠ Ø´Ø±ÙŠÙ� Ù�ÙŠ Ù‚Ø·Ø§Ø¹ÙŠ Ø§Ù„Ø£Ù†Ø¯ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© Ù„Ù„ÙˆØµÙˆÙ„ Ø¥Ù„Ù‰ Ù…Ø³ØªÙˆÙ‰ Ø§Ù„ØªÙ…ÙŠØ².");
		    		builder1.setTitle("Ø¬Ø§Ø¦Ø²Ø© Ø§Ù…ØªÙŠØ§Ø²");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("As part of the overall development of the clubs and youth centers in the Kingdom of Bahrain, the Ministry of Youth and Sports has launched an excellence award â€œIMTIYAZâ€�  for clubs and youth centers aiming at qualitative changes in the working methodologies applied in them. This is  to achieve the best results by creating a competitive atmosphere in the clubs and youth centers to reach and sustain the highest levels of excellence.");
		    		builder1.setTitle("Emtiyaz");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
		    		
	    	    }
	    		
	    				
            }
            });
	   
	  //-- Khalid Bin Hamad Youth Theatre Award for National Sports Clubs and Youth Centers and Special Needs â€“Third Version --//
	    btnR5 = (ImageButton)findViewById(R.id.menu_eventR5);
	    btnR5.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("ØªÙ†Ø¸ÙŠÙ… Ù…Ù‡Ø±Ø¬Ø§Ù† Ù…Ø³Ø±Ø­ÙŠ Ø´Ø¨Ø§Ø¨ÙŠ Ù„ØªÙ†Ø´ÙŠØ· ÙˆØªØ·ÙˆÙŠØ± Ø§Ù„Ø­Ø±ÙƒØ© Ø§Ù„Ù…Ø³Ø±Ø­ÙŠØ© Ù�ÙŠ Ø§Ù„Ø£Ù†Ø¯ÙŠØ© Ø§Ù„ÙˆØ·Ù†ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ©.");
		    		builder1.setTitle("Ø¬Ø§Ø¦Ø²Ø© Ø®Ø§Ù„Ø¯ Ø¨Ù† Ø­Ù…Ø¯ Ù„Ù„Ù…Ø³Ø±Ø­ Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠ Ù„Ù„Ø£Ù†Ø¯ÙŠØ© Ø§Ù„ÙˆØ·Ù†ÙŠØ© ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆÙ„Ø°ÙˆÙŠ Ø§Ù„Ø§Ø¹Ø§Ù‚Ø©");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("Organizing a youth theatrical festival to revive and develop the theatrical movement of national clubs and youth centers.");
		    		builder1.setTitle("Khalid Bin Hamad Youth Theatre Award for National Sports Clubs and Youth Centers and Special Needs");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
		    		
	    	    }
		
            }
            });
	    
	  //-- Khalid Bin Hamad Futsal Championship  for Youth Centers and Special Needs- Fourth Version --//
	    btnR6 = (ImageButton)findViewById(R.id.menu_eventR6);
	    btnR6.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("Ø¥Ù‚Ø§Ù…Ø© Ø¯ÙˆØ±ÙŠ Ù„ÙƒØ±Ø© Ù‚Ø¯Ù… Ø§Ù„ØµØ§Ù„Ø§Øª Ù„Ø´Ø¨Ø§Ø¨ Ø§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆÙ„Ø°ÙˆÙŠ Ø§Ù„Ø§Ø¹Ø§Ù‚Ø©");
		    		builder1.setTitle("Ø¯ÙˆØ±ÙŠ Ø®Ø§Ù„Ø¯ Ø¨Ù† Ø­Ù…Ø¯ Ù„ÙƒØ±Ø© Ù‚Ø¯Ù… Ø§Ù„ØµØ§Ù„Ø§Øª Ù„Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆÙ„Ø°ÙˆÙŠ Ø§Ù„Ø¥Ø¹Ø§Ù‚Ø©.");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("Organizing Futsal Championship directed to Youth Centers and Special Needs to engage them in to sports activities and build the spirit of team work and unity.");
		    		builder1.setTitle("Khalid Bin Hamad Futsal Championship  for Youth Centers and Special Needs.");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
		    		
	    	    }
	    		
	    				
            }
            });

	  //-- Bahrain sports talent identification program --//
	    btnR7 = (ImageButton)findViewById(R.id.menu_eventR7);
	    btnR7.setOnClickListener(new View.OnClickListener() {
	    	@Override
            public void onClick(View view) {
	    		if(language.equals("ar")){
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("  Ø§ÙƒØªØ´Ø§Ù� Ø§Ù„Ù…ÙˆØ§Ù‡Ø¨ Ø§Ù„Ø±ÙŠØ§Ø¶ÙŠØ© Ù�ÙŠ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† ÙˆØ§Ù„Ù…Ø±Ø§ÙƒØ² Ø§Ù„Ø´Ø¨Ø§Ø¨ÙŠØ© ÙˆØµÙ‚Ù„Ù‡Ø§ Ù„ØªÙƒÙˆÙ† Ø¬Ø§Ù‡Ø²Ø© Ù„Ù„Ù…Ø´Ø§Ø±ÙƒØ© Ù�ÙŠ Ø§Ù„Ø¯ÙˆØ±ÙŠØ§Øª Ùˆ Ø§Ù„Ø¨Ø·ÙˆÙ„Ø§Øª Ø§Ù„Ù…Ø­Ù„ÙŠØ© ÙˆØ§Ù„Ø¹Ø±Ø¨ÙŠØ© ÙˆØ§Ù„Ø¹Ø§Ù„Ù…ÙŠØ© Ù…Ù† Ø®Ù„Ø§Ù„ Ø²ÙŠØ§Ø±Ø§Øª Ù…ÙŠØ¯Ø§Ù†ÙŠØ©ØŒ Ù…Ù‡Ø±Ø¬Ø§Ù† Ø§Ù„Ø§Ù†ØªÙ‚Ø§Ø¡ØŒ Ø¨Ø±Ù†Ø§Ù…Ø¬ ØªØ¯Ø±ÙŠØ¨ÙŠ ØµÙŠÙ�ÙŠ Ù…ÙƒØ«Ù� Ø¨Ø§Ù„Ø¥Ø¶Ø§Ù�Ø© Ø§Ù„ Ø§Ù„Ø¨Ø±Ù†Ø§Ù…Ø¬ Ø§Ù„ØªØ¯Ø±ÙŠØ¨ÙŠ Ø§Ù„Ù…Ø³ØªÙ…Ø±.");
		    		builder1.setTitle("Ø¨Ø±Ù†Ø§Ù…Ø¬ Ù…Ù…Ù„ÙƒØ© Ø§Ù„Ø¨Ø­Ø±ÙŠÙ† Ù„Ø§ÙƒØªØ´Ø§Ù� Ø§Ù„Ù…ÙˆØ§Ù‡Ø¨ Ø§Ù„Ø±ÙŠØ§Ø¶ÙŠØ©");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });
		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();
	    	    }
	    	    if(language.equals("en")){
	    	        //do nothing
	    	    	AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
	    	    	builder1.setMessage("The Talent program has a major role it structures  a coherent sports platform. It insures the release of continuous generations of promising players of which are the base of an aspiring bright future for the sports industry in the Kingdom of Bahrain the programs aims to Choose promising aspiring sports talents in the various individual and group games it also thrives to Develop sports talents physically  through constant training by Providing professional training to the players all through the year by coordinating  with national clubs and sports associations not forgetting the Constant liaising with professionals to bench mark the players in a high rank of performance, training that would guarantee their optimum readiness to compete and gain success     ");
		    		builder1.setTitle("Bahrain sports talent identification program ");
		    		builder1.setCancelable(true);

		    		builder1.setPositiveButton(
		    		    "Ok",
		    		    new DialogInterface.OnClickListener() {
		    		        public void onClick(DialogInterface dialog, int id) {
		    		            dialog.cancel();
		    		        }
		    		    });

		    		AlertDialog alert11 = builder1.create();
		    		alert11.show();		
	    	    }		
            }
            });
	     
	    
	}

	private void nitView() {
		Resources res = getResources();
		navdrawerEvent = res.getString(R.string.navigation_drawer_eventcalendar);
		navdrawerAugmented = res.getString(R.string.navigation_drawer_augmentedreality);
		navdrawerChatbot = res.getString(R.string.navigation_drawer_chatbot);
		navdrawerSettings = res.getString(R.string.navigation_drawer_settings);
		navTV = res.getString(R.string.navigation_drawer_navtv);
		navApp4u  = res.getString(R.string.navigation_drawer_appsforu);
		navContactus = res.getString(R.string.navigation_drawer_contactus);
		navNews = res.getString(R.string.navigation_drawer_news);
		
	    leftDrawerList = (ListView) findViewById(R.id.left_drawer);
	    toolbar = (Toolbar) findViewById(R.id.toolbar);
	    drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
	     
	    DataModel[] drawerItem = new DataModel[4];
        drawerItem[0] = new DataModel(R.drawable.icn_stng_eventcalendar, navNews);
        drawerItem[1] = new DataModel(R.drawable.icn_stng_augmented, navApp4u);
        drawerItem[2] = new DataModel(R.drawable.icn_stng_chatbot, navContactus);
        drawerItem[3] = new DataModel(R.drawable.icn_stng_settings, navdrawerSettings);
        
        
        
        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        leftDrawerList.setAdapter(adapter);
        leftDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        
	}

	private void initDrawer() {

	    drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

	        @Override
	        public void onDrawerClosed(View drawerView) {
	            super.onDrawerClosed(drawerView);

	        }

	        @Override
	        public void onDrawerOpened(View drawerView) {
	            super.onDrawerOpened(drawerView);

	        }
	    };
	    drawerLayout.setDrawerListener(drawerToggle);
	}
	
	private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }
	
	private void selectItem(int position) {

		Intent Intent = new Intent();
		
        switch (position) {
        
            case 0:
            	 Intent = new Intent(MainActivity.this, NewsActivity.class);
                 startActivity(Intent);
                break;
            case 1:
            	 Intent = new Intent(MainActivity.this, EventCalendarActivity.class);
                 startActivity(Intent);
                break;
            case 2:
            	 Intent = new Intent(MainActivity.this, ContactUsActivity.class);
                 startActivity(Intent);
                break;
            case 3:
           	 Intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(Intent);
               break;    
            

            default:
                break;
        }
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
	    super.onPostCreate(savedInstanceState);
	    drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    drawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    //getMenuInflater().inflate(R.menu.my, menu);
	    return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    int id = item.getItemId();
	    if (id == R.id.action_settings) {
	        return true;
	    }
	    if (drawerToggle.onOptionsItemSelected(item)) {
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	}
