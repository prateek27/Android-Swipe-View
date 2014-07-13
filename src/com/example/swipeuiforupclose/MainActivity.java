/*
 * Copyright 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.swipeuiforupclose;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {

	private static final int REQUEST_CODE = 1234;
	
	AppSectionsPagerAdapter mAppSectionsPagerAdapter;

	ViewPager mViewPager;
	EditText AboutMeET;
	Fragment profileFragment;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_main);
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections
		// of the app.

		mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(
				getSupportFragmentManager());

		// Set up the action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home/Up button should not be enabled, since there is
		// no hierarchical
		// parent.
		actionBar.setHomeButtonEnabled(false);
		actionBar.setBackgroundDrawable(new ColorDrawable(0xFF46304e));
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(0xFF573d5d));
		actionBar.setTitle("My Profile");

		// Specify that we will be displaying tabs in the action bar.
		//actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
			actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		// Set up the ViewPager, attaching the adapter and setting up a listener
		// for when the
		// user swipes between sections.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(mAppSectionsPagerAdapter);
		mViewPager
				.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
					@Override
					public void onPageSelected(int position) {
						// When swiping between different app sections, select
						// the corresponding tab.
						// We can also use ActionBar.Tab#select() to do this if
						// we have a reference to the
						// Tab.
						actionBar.setSelectedNavigationItem(position);
					}
				});

		// For each of the sections in the app, add a tab to the action bar.
		for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
			// Create a tab with text corresponding to the page title defined by
			// the adapter.
			// Also specify this Activity object, which implements the
			// TabListener interface, as the
			// listener for when this tab is selected.
			actionBar.addTab(actionBar.newTab()
					.setText(mAppSectionsPagerAdapter.getPageTitle(i))
					.setTabListener(this));
		}
	}

	@Override
	public void onTabUnselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
		// When the given tab is selected, switch to the corresponding page in
		// the ViewPager.
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabReselected(ActionBar.Tab tab,
			FragmentTransaction fragmentTransaction) {
	}

	public static class AppSectionsPagerAdapter extends FragmentPagerAdapter {

		public AppSectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int i) {
			switch (i) {
			case 0:
				// The first section of the app is the most interesting -- it
				// offers
				// a	 launchpad into the other demonstrations in this example
				// application.
				Fragment profileFragment = new AboutFragment();
				return  profileFragment;
				
			case 1:
				Fragment voiceFragment = new VoiceFragment();
				return voiceFragment;
			default:
				Fragment imageFragment = new ImagesFragment();
				return imageFragment;
			}
		}

		@Override
		public int getCount() {
			return 6;
		}

		String[] pageTitles = { "About Me", "Voice Info", "Likes", "Status",
				"Groups", "Places" };

		@Override
		public CharSequence getPageTitle(int position) {
			return pageTitles[position];
		}
	}

	
	public static class DummySectionFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_section_dummy,
					container, false);
			Bundle args = getArguments();
			// ((TextView) rootView.findViewById(android.R.id.text1)).setText(
			// getString(R.string.dummy_section_text,
			// args.getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
		
		
	}
	
	
	//Process the result of intent from audio Fragment
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		
		if (resultCode == RESULT_OK && null != data
				&& requestCode == REQUEST_CODE) {
			ArrayList<String> text = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				Toast.makeText(getApplicationContext(), "Great Job !",Toast.LENGTH_SHORT).show();
				EditText et = (EditText)findViewById(R.id.et_about_me_text);
				et.setText(text.get(0));
		}
	
	
	}

}
