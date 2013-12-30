package ucup.tech.utoggle;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

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
		ButtonLayout v = new ButtonLayout(mContext);
		ToggleButton tgl = model.get(arg0);
		v.setButtonDrawable(tgl.getIcon());
		if(tgl.getStatus().equals("Battery"))
			v.setButtonLevel(tgl.buttonExtra);
		v.setButtonText(tgl.getStatus());
		return v;
	}

}
