package ucup.tech.utoggle;

import android.os.Bundle;
import android.app.Activity;

public class MainActivity extends Activity {
	uToggle toggle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		toggle = (uToggle)findViewById(R.id.toggle);
	}
}
