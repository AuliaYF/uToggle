package ucup.tech.utoggle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.provider.Settings;

public class AirplaneButton extends ToggleButton {

	public AirplaneButton(Context context) {
		super(context);
	}

	@Override
	public String getStatus() {
		return getStatus(mContext)?"Airplane On":"Airplane Off";
	}
	@Override
	public Drawable getIcon() {
		if(getStatus(mContext)){
			return mTheme.getIcon(mTheme.icon_airplane_on);
		}else{
			return mTheme.getIcon(mTheme.icon_airplane_off);
		}
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}
	public static void setState(Context context){
		boolean state = getStatus(context);
		Settings.System.putInt(context.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, state ? 0 : 1);
		Intent intent = new Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED);
		intent.putExtra("state", !state);
		context.sendBroadcast(intent);
	}

	private static boolean getStatus(Context mContext) {
		return Settings.System.getInt(mContext.getContentResolver(),
				Settings.System.AIRPLANE_MODE_ON, 0) == 1;
	}
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context contx, Intent intent) {
			String action = intent.getAction();
			if (action.equals(Intent.ACTION_AIRPLANE_MODE_CHANGED)) {
				AirplaneButton.this.updateView();
			}
		}
	};
	@Override
	public void startListen() {
		mContext.registerReceiver(myReceiver, new IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED));
	}
}
