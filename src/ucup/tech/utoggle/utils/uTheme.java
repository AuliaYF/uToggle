package ucup.tech.utoggle.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class uTheme {
	private PackageManager pm;

	private String themePackage = "utoggle.theme.def";

	public String icon_airplane_on = "stat_airplane_on";
	public String icon_airplane_off = "stat_airplane_off";
	public String icon_bt_on = "stat_bt_on";
	public String icon_bt_off = "stat_bt_off";
	public String icon_rotation_on = "stat_rotate_on";
	public String icon_rotation_off = "stat_rotate_off";
	public String icon_gps_on = "stat_gps_on";
	public String icon_gps_off = "stat_gps_off";
	public String icon_data_on = "stat_data_on";
	public String icon_data_off = "stat_data_off";
	public String icon_wifi_on = "stat_wifi_on";
	public String icon_wifi_off = "stat_wifi_off";

	public String icon_silent = "stat_silent";
	public String icon_vibrate_on = "stat_vibrate_on";
	public String icon_vibrate_off = "stat_vibrate_off";
	
	public String icon_reboot = "stat_reboot";
	public String icon_shutdown = "stat_shutdown";
	
	public String battery_normal = "stat_sys_battery";
	public String battery_charging = "stat_sys_battery_charge";
	public uTheme(Context context){
		pm = context.getPackageManager();
	}
	public Drawable getIcon(String iconName) {
		Resources themeResources = null;
		Drawable d = null;
		try {

			themeResources = pm.getResourcesForApplication(themePackage);
		} catch (NameNotFoundException e) {
		} catch (NullPointerException e) {
		}

		if (themeResources != null) {
			int resource_id = themeResources.getIdentifier(iconName,
					"drawable", themePackage);
			if (resource_id != 0) {
				try {
					d = themeResources.getDrawable(resource_id);
				} catch (Resources.NotFoundException e) {
				}
			}
		}
		return d;
	}
}
