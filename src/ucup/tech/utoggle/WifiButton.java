package ucup.tech.utoggle;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiManager;

public class WifiButton extends ToggleButton {
	private static WifiManager wifi;
	public WifiButton(Context context) {
		super(context);
		wifi = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
	}

	@Override
	public String getStatus() {
		return getStatus(mContext)?"Wifi On":"Wifi Off";
	}
	@Override
	public Drawable getIcon() {
		if(getStatus(mContext)){
			return mTheme.getIcon(mTheme.icon_wifi_on);
		}else{
			return mTheme.getIcon(mTheme.icon_wifi_off);
		}
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}
	public static void setState(Context context){
		boolean onoff = getStatus(context);
		wifi.setWifiEnabled(onoff?false:true);
	}

	private static boolean getStatus(Context mContext) {
		if (wifi.isWifiEnabled())
			return true;
		else
			return false;
	}
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context contx, Intent intent) {
			String action = intent.getAction();
			if (action.equals(WifiManager.WIFI_STATE_CHANGED_ACTION)) {
				WifiButton.this.updateView();
			}
		}
	};
	@Override
	public void startListen() {
		mContext.registerReceiver(myReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
	}
}
