package com.example.swipeuiforupclose;

import java.io.IOException;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

public class VoiceFragment extends Fragment {

	ImageView recordIV, playIV;
	Chronometer myChronometer;
	int mode = 0; // 0 for Normal 1 Recording 2 Playing
	public static final String audioFileName = "/test_file.mp3";
	public static String mediaFileName;
	private MediaRecorder mediaRecorder = null;
	private MediaPlayer mediaPlayer = null;
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mediaFileName = Environment.getExternalStorageDirectory().getAbsolutePath();
		mediaFileName += audioFileName;
		View rootView = inflater.inflate(R.layout.voice, container, false);
		recordIV = (ImageView) rootView.findViewById(R.id.iv_record);
		playIV = (ImageView) rootView.findViewById(R.id.iv_play);
		myChronometer = (Chronometer) rootView
				.findViewById(R.id.recordChronometer);
		myChronometer.setText("");
		recordIV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				recordIV.setEnabled(false);
				recordingMode();
				mode = 1;
			}

		});

		playIV.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mode == 0) {
					recordIV.setEnabled(true);
					myChronometer.stop();
					playingMode();
					myChronometer.setText("Playing...");
				} 
				else {  //Stop Recording and get into Normal Mode
					recordIV.setEnabled(true);
					normalMode();
					myChronometer.stop();
					myChronometer.setText("Voice Recorded");
				}
				
			}

		});

		return rootView;

	}

	private void playingMode() {
		mode = 2;
		playIV.setBackgroundResource(R.drawable.stop);
		recordIV.setBackgroundResource(R.drawable.record);
		try{
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setDataSource(mediaFileName);
			mediaPlayer.prepare();
		}
		catch(IOException e){
			Toast.makeText(getActivity(),"Sorry,Can't Play", Toast.LENGTH_SHORT).show();
		}
		mediaPlayer.start();
		
	}

	private void recordingMode() {
		mode = 1;
		recordIV.setBackgroundResource(R.drawable.recording);
		playIV.setBackgroundResource(R.drawable.stop);

		mediaRecorder = new MediaRecorder();
		mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
		mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
		mediaRecorder.setOutputFile(mediaFileName);
		mediaRecorder.setMaxDuration(20000);
		mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
		try{
			mediaRecorder.prepare();
			
		}
		catch(IOException e){
			Toast.makeText(getActivity(), "Sorry,Can't Record Audio ",Toast.LENGTH_SHORT).show();
		}
		mediaRecorder.start();

		Toast.makeText(getActivity(),"Max 20 seconds audio",Toast.LENGTH_SHORT);
		long startTime = SystemClock.elapsedRealtime();
		myChronometer.setBase(startTime);
		myChronometer.start();
		
	}

	private void normalMode() {
		if(mediaRecorder!=null){
			mediaRecorder.stop();
			mediaRecorder.release();
			mediaRecorder = null;
		}
		if(mediaPlayer!=null){
			mediaPlayer.stop();
			mediaPlayer.release();
			mediaPlayer=null;
		}
		mode = 0;
		playIV.setBackgroundResource(R.drawable.play);
		recordIV.setBackgroundResource(R.drawable.record);
	}
}
