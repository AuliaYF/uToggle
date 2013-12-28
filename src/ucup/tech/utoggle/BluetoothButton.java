package ucup.tech.utoggle;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;

public class BluetoothButton extends ToggleButton {
	private BluetoothAdapter mBAdapter;
	public BluetoothButton(Context context) {
		super(context);
		mBAdapter = BluetoothAdapter.getDefaultAdapter();
	}

	@Override
	public String getStatus() {
		return getStatus(mContext)?"Bluetooth On":"Bluetooth Off";
	}
	@Override
	public Drawable getIcon() {
		if(getStatus(mContext)){
			return mTheme.getIcon(mTheme.icon_bt_on);
		}else{
			return mTheme.getIcon(mTheme.icon_bt_off);
		}
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}
	public void setState(Context context){
		boolean state = getStatus(context);
		if(state)
			mBAdapter.disable();
			
		else
			mBAdapter.enable();
	}

	public boolean getStatus(Context context) {
		return mBAdapter.isEnabled();
	}
	private BroadcastReceiver myReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context contx, Intent intent) {
			final String action = intent.getAction();
			if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
				BluetoothButton.this.updateView();
			}
		}
	};
	@Override
	public void startListen() {
		mContext.registerReceiver(myReceiver, new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
	}
}
