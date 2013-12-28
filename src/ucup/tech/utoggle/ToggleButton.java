package ucup.tech.utoggle;

import android.content.Context;
import android.graphics.drawable.Drawable;

import ucup.tech.utoggle.utils.uTheme;

public abstract class ToggleButton {
	protected Context mContext;
	protected uTheme mTheme;
	private String status;
	private Drawable icon;
	
	public ToggleButton(Context context) {
		this.mContext = context;
		this.mTheme = new uTheme(context);
	}
	public void setStatus(String state) {
		this.status = state;
	}
	public String getStatus() {
		return status;
	}
	public void updateView() {
		getStatus();
		getIcon();
	}
	public void updateButton() {
		getStatus();
		setStatus(status);
		getIcon();
		setIcon(icon);

	}
	public void setIcon(Drawable icon) {
		this.icon = icon;
	}
	public Drawable getIcon() {
		return icon;
	}
	public String getText() {
		return null;
	}
	public abstract void startListen();
}
