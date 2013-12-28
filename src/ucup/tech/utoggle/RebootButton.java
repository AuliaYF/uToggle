package ucup.tech.utoggle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.PowerManager;

public class RebootButton extends ToggleButton {
	private PowerManager pm;
	public RebootButton(Context context) {
		super(context);
		pm = (PowerManager) context
				.getSystemService(Context.POWER_SERVICE);
	}

	@Override
	public String getStatus() {
		return "Reboot";
	}
	@Override
	public Drawable getIcon() {
		return mTheme.getIcon(mTheme.icon_reboot);
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}
	public void setState(final Context context){
		final String items[] = {"Reboot", "Recovery"};
		AlertDialog.Builder ab=new AlertDialog.Builder(context);
		ab.setTitle("Reboot");
		ab.setItems(items, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface d, int choice) {
				if(choice == 0)
					pm.reboot("now");
				else
					pm.reboot("recovery");
			}
		});
		ab.show();
	}

	@Override
	public void startListen() {
		// lalalala
	}
}
