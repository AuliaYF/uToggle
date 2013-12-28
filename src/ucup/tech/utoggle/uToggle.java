package ucup.tech.utoggle;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.AdapterView.OnItemClickListener;

public class uToggle extends LinearLayout{
	private GridView mGrid;
	private final String TAG = "uToggle";
	private List<ToggleButton> toggles;
	private ToggleAdapter mAdapter;
	private Context mContext;
	private Handler handler;
	private Runnable r;
	
	private AirplaneButton airplane;
	private AutoRotationButton rotation;
	private BluetoothButton bt;
	private GpsButton gps;
	private MobileDataButton mobiledata;
	private RingerButton ringer;
	private WifiButton wifi;
	private RebootButton reboot;
	private ShutdownButton shutdown;
	
	public uToggle(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		
		setupWidget();
		
		handler = new Handler();
		r = new Runnable() {
			
			@Override
			public void run() {
				mAdapter.notifyDataSetChanged();
				handler.postDelayed(this, 1000);
			}
		};
		handler.post(r);
	}

	public void setupWidget(){
		Log.i(TAG, "setupWidget()");
		createButton();
		
		setupButton(mContext);
	}
	private void createButton(){
		Log.i(TAG, "createButton()");
		airplane = new AirplaneButton(mContext);
		rotation = new AutoRotationButton(mContext);
		bt = new BluetoothButton(mContext);
		gps = new GpsButton(mContext);
		mobiledata = new MobileDataButton(mContext);
		ringer = new RingerButton(mContext);
		wifi = new WifiButton(mContext);
		reboot = new RebootButton(mContext);
		shutdown = new ShutdownButton(mContext);
	}
	private void setupButton(Context context) {
		Log.i(TAG, "setupButton()");
		removeAllViews();

		setOrientation(LinearLayout.VERTICAL);
		mGrid = new GridView(context);
		mGrid.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		mGrid.setNumColumns(3);
		mGrid.setVerticalScrollBarEnabled(false);
		mGrid.buildDrawingCache(true);
		mGrid.setHorizontalSpacing(5);
		mGrid.setVerticalSpacing(5);
		mGrid.setDrawSelectorOnTop(true);
		updateGridView(context);

		addView(mGrid);
	}

	private void updateGridView(Context context) {
		Log.i(TAG, "updateGridView()");
		toggles = new ArrayList<ToggleButton>();
		toggles.add(airplane);
		toggles.add(rotation);
		toggles.add(bt);
		toggles.add(gps);
		toggles.add(mobiledata);
		toggles.add(ringer);
		toggles.add(wifi);
		toggles.add(reboot);
		toggles.add(shutdown);

		for (int i = 0; i < toggles.size(); i++) {
			toggles.get(i).startListen();
			toggles.get(i).updateView();
		}

		mAdapter = new ToggleAdapter(context, toggles);
		mGrid.setAdapter(mAdapter);

		mGrid.setOnItemClickListener(OnGridViewClickListener);
	}
	private OnItemClickListener OnGridViewClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> av, View v, int pos, long id) {
			toggles.get(pos).updateButton();
		}
	};
}
