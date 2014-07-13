package com.example.swipeuiforupclose;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

public class AboutFragment extends Fragment {
	protected static final int REQUEST_CODE = 1234;
	EditText aboutMeTV;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.profile_about_me, container,
				false);

		// Demonstration of a collection-browsing activity.
		aboutMeTV = (EditText) rootView.findViewById(R.id.et_about_me_text);
		rootView.findViewById(R.id.iv_mic).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Toast.makeText(getActivity(),
								"Speak to type automatically..",
								Toast.LENGTH_SHORT).show();
						startVoiceRecognition();

					}

					private void startVoiceRecognition() {
						Intent intent = new Intent(
								RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
						intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
								RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
						getActivity().startActivityForResult(intent,
								REQUEST_CODE);
					}

				});
		
		return rootView;

	}
	
}
