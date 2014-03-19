package com.hp.tipcalculator;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class CustomSeekBarListener implements OnSeekBarChangeListener{

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		seekBar.setProgress(progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

}
