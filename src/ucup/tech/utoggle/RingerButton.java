package ucup.tech.utoggle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;

public class RingerButton extends ToggleButton {
	AudioManager audioManager;
	public RingerButton(Context context) {
		super(context);
		audioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
	}

	@Override
	public String getStatus() {
		int status = getStatus(mContext);
		if (status == 0)
			return "Silent";
		else if (status == 1)
			return "Vibrate";
		else if (status == 2)
			return "Ring & Vibrate";
		else
			return "Unknown";
	}
	@Override
	public Drawable getIcon() {
		if(getStatus(mContext)==0)
			return mTheme.getIcon(mTheme.icon_silent);
		else if(getStatus(mContext)==1)
			return mTheme.getIcon(mTheme.icon_vibrate_off);
		else
			return mTheme.getIcon(mTheme.icon_vibrate_on);
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}
	public void setState(Context context){
		int status = getStatus(context);
		if (status == 0)
			audioManager.setRingerMode(1);
		else if (status == 1)
			audioManager.setRingerMode(2);
		else if (status == 2)
			audioManager.setRingerMode(0);
	}

	public int getStatus(Context context) {
		return audioManager.getRingerMode();
	}
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context contx, Intent intent) {
			RingerButton.this.updateView();
		}
	};
	@Override
	public void startListen() {
		IntentFilter filter = new IntentFilter();
		filter.addAction(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
		filter.addAction(AudioManager.RINGER_MODE_CHANGED_ACTION);
		mContext.registerReceiver(myReceiver, filter);
	}
}
