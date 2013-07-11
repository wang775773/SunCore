package bupt.sse.monitoringsystem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import bupt.sse.monitoringsystem.entity.PowerInfo;
import bupt.sse.monitoringsystem.msg.GetMsg;
import bupt.sse.monitoringsystem.msg.util.EquArray;
import bupt.sse.monitoringsystem.msg.util.Station;
import bupt.sse.monitoringsystem.msg.util.Unit;

public class QueryPower extends Activity implements OnItemClickListener {

	private static String QueryType;
	private static final String ARRAY = "array";
	private static final String UNIT = "unit";
	private static final String STATION = "station";

	private ListView mPowerListView;
	private BaseAdapter mPowerAdapter;

	private List<PowerInfo> myPowerData = new ArrayList<PowerInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_power_list);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		QueryType = bundle.getString("type");

		mPowerListView = (ListView) findViewById(R.id.power_list);

		mPowerListView.setOnItemClickListener(this);
		mPowerListView.setDivider(null);
		mPowerAdapter = new MyPowerListAdapter();
		mPowerListView.setAdapter(mPowerAdapter);

		loadData();
	}

	private void loadData() {
		new Thread(new Runnable() {

			@Override
			public void run() {

				List<PowerInfo> tempData = new ArrayList<PowerInfo>();
				// 拉取数据并存储在tempData中
				if (QueryType.equals(ARRAY)) {
					/*List<EquArray> arrays = GetMsg.getArrays(new Date());
					for (EquArray array : arrays) {
						tempData.add(new PowerInfo(array));
					}*/
				} else if (QueryType.equals(UNIT)) {
					/*List<Unit> units = GetMsg.getUnits(new Date());
					for (Unit unit : units) {
						tempData.add(new PowerInfo(unit));
					}*/
				} else if (QueryType.equals(STATION)) {
					List<Station> stations = GetMsg.getStations("glm",0);
					for (Station station : stations) {
						tempData.add(new PowerInfo(station));
					}
				}

				Message msg = mUIHandler.obtainMessage();
				msg.obj = tempData;
				msg.sendToTarget();

			}
		}).start();
	}
	

			

	private Handler mUIHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.obj != null) {
				List<PowerInfo> strings = (List<PowerInfo>) msg.obj;
				if (!strings.isEmpty()) {
					myPowerData.addAll(strings);
					mPowerAdapter.notifyDataSetChanged();
				}
			}
		}

	};

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		/**
		 * 此处需传送选中条目的所有信息
		 */

		Toast.makeText(this, "啊，你点中我了 " + position, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(QueryPower.this, OrderDetail.class);
		startActivity(intent);
	}

	class MyPowerListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return myPowerData.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return myPowerData.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			convertView = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.power_list_item, null);
			TextView textView1 = (TextView) convertView
					.findViewById(R.id.power_list_name);
			textView1.setText(myPowerData.get(position).getPowerName());
			TextView textView2 = (TextView) convertView
					.findViewById(R.id.power_list_capacity);
			textView2.setText(myPowerData.get(position).getCapacity() + "");
			// 图片更改操作

			return convertView;
		}

	}
}
