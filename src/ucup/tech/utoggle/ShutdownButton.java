package ucup.tech.utoggle;

import com.android.internal.app.ShutdownThread;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;

public class ShutdownButton extends ToggleButton {

	public ShutdownButton(Context context) {
		super(context);
	}

	@Override
	public String getStatus() {
		return "Shutdown";
	}
	@Override
	public Drawable getIcon() {
		return mTheme.getIcon(mTheme.icon_shutdown);
	}
	@Override
	public void updateButton() {
		setState(mContext);
		super.updateButton();
	}
	public static void setState(final Context context){
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
		alertDialog.setTitle("Shutdown");
		alertDialog.setMessage("Your phone will shutdown");

		alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int which) {
				ShutdownThread.shutdown(context, false);
			}
		});

		alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,	int which) {
				dialog.cancel();
			}
		});
		alertDialog.show();
	}

	@Override
	public void startListen() {
		// lalalala
	}
}
