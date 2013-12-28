package ucup.tech.utoggle;

import java.util.List;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ToggleAdapter extends BaseAdapter{
	private List<ToggleButton> model;
	private Context mContext;

	public ToggleAdapter(Context context, List<ToggleButton> toggles) {
		this.model = toggles;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return model.size();
	}

	@Override
	public Object getItem(int arg0) {
		return model.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		LinearLayout.LayoutParams txtparams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				40);
		ToggleHolder h = new ToggleHolder();
		LinearLayout v = new LinearLayout(mContext);
		
		h.tv = new TextView(mContext);
		h.iv = new ImageView(mContext);

		v.setOrientation(LinearLayout.VERTICAL);
		v.setGravity(Gravity.CENTER_HORIZONTAL);
		v.setBackgroundColor(0xff000000);
		v.setPadding(0, 20, 0, 0);
		
		h.tv.setLayoutParams(txtparams);
		h.tv.setGravity(Gravity.CENTER_HORIZONTAL);
		h.tv.setSingleLine(true);
		h.tv.setTextSize(10);

		h.tv.setTextColor(0xffffffff);

		ToggleButton tog = model.get(arg0);
		h.tv.setText(tog.getStatus());
		h.iv.setImageDrawable(tog.getIcon());//loadImage(tog.bmp, h.iv, tog.type);
		v.addView(h.iv, 0);
		v.addView(h.tv, 1);
		v.setTag(h);

		return v;
	}

}
