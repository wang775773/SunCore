package bupt.sse.monitoringsystem;

import monitoringsystem.second.PowerChartActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class Main extends Activity {
	
	public static Main instance = null;
	
	private static final String QueryType = "type";
	private static final String ARRAY = "array";
	private static final String UNIT = "unit";
	private static final String STATION = "station";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		instance = this;
	}
	
	public void directorClickListener(View v){
		
		Intent intent = new Intent();
		Bundle bundle = new Bundle();
		
		switch (v.getId()) {
		case R.id.main_weather:
			intent.setClass(getApplicationContext(), QueryWeather.class);
			startActivity(intent);
			break;
		case R.id.main_device:
			intent.setClass(getApplicationContext(), QueryDevice.class);
			startActivity(intent);
			break;
		case R.id.main_barChart:
			bundle.putInt(QueryType, 1);
			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(),  PowerChartActivity.class);
			startActivity(intent);
			break;
		case R.id.main_lineChart:
			bundle.putInt(QueryType, 0);
			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(),  PowerChartActivity.class);
			startActivity(intent);
			break;
		case R.id.main_order_unsolved:
			bundle.putBoolean("solved", false);
			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(), QueryOrders.class);
			startActivity(intent);
			break;
		case R.id.main_order_solved:
			bundle.putBoolean("solved", true);
			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(), QueryCompletedOrders.class);
			startActivity(intent);
			break;
		case R.id.main_help:
			bundle.putBoolean("solved", true);
			intent.putExtras(bundle);
			intent.setClass(getApplicationContext(), QueryOrders.class);
			startActivity(intent);
			break;
		case R.id.main_exit:
			intent.setClass(getApplicationContext(), Exit.class);
			startActivity(intent);
			break;
		}
		
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getApplicationContext(), Exit.class);
		startActivity(intent);
	}
	
	

}
