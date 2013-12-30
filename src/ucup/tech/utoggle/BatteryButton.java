package ucup.tech.utoggle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;

public class BatteryButton extends ToggleButton {
	private int type;
	private int level;
	public BatteryButton(Context context) {
		super(context);
	}

	@Override
	public String getStatus() {
		return "Battery";
	}
	@Override
	public Drawable getIcon() {
		if(type==2)
			return mTheme.getIcon(mTheme.battery_charging);
		else
			return mTheme.getIcon(mTheme.battery_normal);
	}
	@Override
	public void updateButton() {
		super.updateButton();
	}
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context contx, Intent intent) {
			BatteryButton.this.buttonExtra = intent.getIntExtra("level", 0);
			BatteryButton.this.type = intent.getIntExtra("status", 0);
			BatteryButton.this.updateView();
		}
	};
	@Override
	public void startListen() {
		mContext.registerReceiver(myReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
	}
}
