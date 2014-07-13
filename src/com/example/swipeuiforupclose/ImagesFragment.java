package com.example.swipeuiforupclose;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ImagesFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.profile_images, container,
				false);
		ImageView wordCloudImage = (ImageView) rootView
				.findViewById(R.id.iv_wordcloud);
		int no = (int) (Math.random() * 4) + 1;
		switch (no) {
		case 0:
			wordCloudImage.setBackgroundResource(R.drawable.wc1);
			break;
		case 1:
			wordCloudImage.setBackgroundResource(R.drawable.wc2);
			break;
		case 2:
			wordCloudImage.setBackgroundResource(R.drawable.wc3);
			break;
		case 3:
			wordCloudImage.setBackgroundResource(R.drawable.wc4);
			break;
		default:
			wordCloudImage.setBackgroundResource(R.drawable.wc2);
		}
		return rootView;
	}

}