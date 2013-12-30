package ucup.tech.utoggle;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ButtonLayout extends LinearLayout{

	private ImageView buttonView;
	private TextView textView;
	
	private final LayoutParams baseParam = new LayoutParams(
			LayoutParams.MATCH_PARENT, 40); 
	
	public ButtonLayout(Context context) {
		super(context);
		
		this.setOrientation(LinearLayout.VERTICAL);
		this.setGravity(Gravity.CENTER_HORIZONTAL);
		this.setBackgroundColor(0xff000000);
		this.setPadding(0, 20, 0, 0);
		
		this.buttonView = new ImageView(context);
		this.textView = new TextView(context);
		
		this.textView.setLayoutParams(baseParam);
		this.textView.setGravity(Gravity.CENTER_HORIZONTAL);
		this.textView.setSingleLine(true);
		this.textView.setTextSize(10);
		this.textView.setTextColor(0xffffffff);
		
		this.addView(buttonView);
		this.addView(textView);
	}
	
	public void setButtonDrawable(Drawable drw){
		this.buttonView.setImageDrawable(drw);
	}
	
	public void setButtonLevel(int level){
		this.buttonView.setImageLevel(level);
	}
	
	public void setButtonText(String text){
		this.textView.setText(text);
	}
}
