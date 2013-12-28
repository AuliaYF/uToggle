package ucup.tech.utoggle;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.provider.Settings;

public class GpsButton extends ToggleButton {
	ContentResolver resolver;
	public GpsButton(Context context) {
		super(context);
		resolver = context.getContentResolver();
	}

	@Override
	public String getStatus() {
		return getStatus(mContext)?"GPS On":"GPS Off";
	}
	@Override
	public Drawable getIcon() {
		if(getStatus(mContext)){
			return mTheme.getIcon(mTheme.icon_gps_on);
		}else{
			return mTheme.getIcon(mTheme.icon_gps_off);
		}
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}
	public void setState(Context context){
		boolean enabled = getStatus(context);        
		String provider = Settings.Secure.getString(resolver, Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
		
			if (enabled?provider.contains("gps"):!provider.contains("gps")) {
				final Intent poke = new Intent();
				poke.setClassName("com.android.settings",
				"com.android.settings.widget.SettingsAppWidgetProvider");
				poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
				poke.setData(Uri.parse("3"));
				context.sendBroadcast(poke);
			}
	}

	public boolean getStatus(Context context) {
		return Settings.Secure.isLocationProviderEnabled(resolver,LocationManager.GPS_PROVIDER);
	}
	
	private class MyObserver extends ContentObserver {

		public MyObserver(Handler handler) {
			super(handler);
		}

		private void observe() {
			ContentResolver resolver = mContext.getContentResolver();
			resolver.registerContentObserver(Settings.System
					.getUriFor(Settings.System.LOCATION_PROVIDERS_ALLOWED), false, this);
		}

		@Override
		public void onChange(boolean bool) {
			super.onChange(bool);
			GpsButton.this.updateView();
		}
	}
	@Override
	public void startListen() {
		MyObserver observer = new MyObserver(new Handler());
		observer.observe();
	}
}
