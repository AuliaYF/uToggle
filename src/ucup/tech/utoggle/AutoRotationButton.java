package ucup.tech.utoggle;

import android.content.ContentResolver;
import android.content.Context;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.provider.Settings;

public class AutoRotationButton extends ToggleButton {

	public AutoRotationButton(Context context) {
		super(context);
	}

	@Override
	public String getStatus() {
		return getStatus(mContext)?"AutoRotation On":"AutoRotation Off";
	}
	@Override
	public Drawable getIcon() {
		if(getStatus(mContext)){
			return mTheme.getIcon(mTheme.icon_rotation_on);
		}else{
			return mTheme.getIcon(mTheme.icon_rotation_off);
		}
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}
	public static void setState(Context context){
		boolean bool = getStatus(context);
		if (bool)
			Settings.System.putInt(context.getContentResolver(),
					Settings.System.ACCELEROMETER_ROTATION, 0);
		else
			Settings.System.putInt(context.getContentResolver(),
					Settings.System.ACCELEROMETER_ROTATION, 1);
	}

	private static boolean getStatus(Context context) {
		String status = Settings.System.getString(context.getContentResolver(),
				Settings.System.ACCELEROMETER_ROTATION);
		if (status.equals("0"))
			return false;
		else
			return true;
	}
	private class MyObserver extends ContentObserver {

		public MyObserver(Handler handler) {
			super(handler);
		}

		private void observe() {
			ContentResolver resolver = mContext.getContentResolver();
			resolver.registerContentObserver(Settings.System
					.getUriFor(Settings.System.ACCELEROMETER_ROTATION), false, this);
		}

		@Override
		public void onChange(boolean bool) {
			super.onChange(bool);
			AutoRotationButton.this.updateView();
		}
	}
	@Override
	public void startListen() {
		MyObserver observer = new MyObserver(new Handler());
		observer.observe();
	}
}
