package ucup.tech.utoggle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

public class MobileDataButton extends ToggleButton {
	private TelephonyManager tm;
	private mPSListener psListener;
	public MobileDataButton(Context context) {
		super(context);
	}

	@Override
	public String getStatus() {
		return getStatus(mContext)?"Data On":"Data Off";
	}
	@Override
	public Drawable getIcon() {
		if(getStatus(mContext)){
			return mTheme.getIcon(mTheme.icon_data_on);
		}else{
			return mTheme.getIcon(mTheme.icon_data_off);
		}
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}

	public static void setState(Context context){
		ConnectivityManager con = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		con.setMobileDataEnabled(!getStatus(context));
	}


	private static boolean getStatus(Context mContext) {
		int stat = 0;
		TelephonyManager telephonyManager = (TelephonyManager) mContext
				.getSystemService(Context.TELEPHONY_SERVICE);
		switch (telephonyManager.getDataState()) {
		case TelephonyManager.DATA_CONNECTED:
			stat = 1;
			break;
		case TelephonyManager.DATA_DISCONNECTED:
			stat = 0;
			break;
		}

		return stat==1;
	}
	private class mPSListener extends PhoneStateListener {

		@Override
		public void onDataConnectionStateChanged(int arg0) {
			super.onDataConnectionStateChanged(arg0);
			MobileDataButton.this.updateView();
		}
	}
	@Override
	public void startListen() {
		tm = (TelephonyManager) mContext
				.getSystemService(Context.TELEPHONY_SERVICE);
		psListener = new mPSListener();
		tm.listen(psListener,
				PhoneStateListener.LISTEN_DATA_CONNECTION_STATE);
	}
}
