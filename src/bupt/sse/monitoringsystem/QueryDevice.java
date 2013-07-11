package bupt.sse.monitoringsystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import bupt.sse.monitoringsystem.msg.GetMsg;
import bupt.sse.monitoringsystem.msg.util.Alarm;

public class QueryDevice extends Activity implements OnItemClickListener {

	private static final int WHAT_DID_LOAD_DATA = 0;
	private static final int WHAT_DID_REFRESH = 1;
	private static final int WHAT_DID_MORE = 2;

	private Button checkDeviceBtn;
	private ListView checkDeviceList;
	private BaseAdapter checkDeviceListAdapter;
	private List<Alarm> checkDeviceData = new ArrayList<Alarm>();
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private Spinner searchSpinner;
	private static final String[] m = { "按时间", "按级别", "按编号" };
	private ArrayAdapter<String> adapter;
	private EditText input;
	private int searchType;
	private List<String> deviceData = new ArrayList<String>();;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_device);

		input = (EditText) findViewById(R.id.device_no);
		searchSpinner = (Spinner) findViewById(R.id.spinner);
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		searchSpinner.setAdapter(adapter);
		searchSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				String type = m[arg2];
				if (type.equals("按级别")) {
					searchType = 3;
				} else if (type.equals("按时间")) {
					searchType = 2;
				} else {
					searchType = 1;
				}
				compare(checkDeviceData, searchType);
				updateView();
				// loadDeviceInfo();

			}

			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});

		checkDeviceBtn = (Button) findViewById(R.id.check_device_btn);
		checkDeviceBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String dn = input.getText().toString();
				Bundle bundle = new Bundle();
				bundle.putString("dn", dn);
				Intent intent = new Intent(QueryDevice.this, DeviceDetail.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}

		});

		checkDeviceList = (ListView) findViewById(R.id.check_device_result);
		checkDeviceListAdapter = new BaseAdapter() {

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return checkDeviceData.size();
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return checkDeviceData.get(arg0);
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup arg2) {
				// TODO Auto-generated method stub
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.device_item, null);
				TextView textView1 = (TextView) convertView
						.findViewById(R.id.device_warning_no);
				textView1.setText("编号："
						+ checkDeviceData.get(position).getAlarmObjDN());

				TextView textView2 = (TextView) convertView
						.findViewById(R.id.device_warning_time);
				textView2.setText("开始时间："
						+ dateFormat.format(checkDeviceData.get(position)
								.getBeginTime()));

				TextView textView3 = (TextView) convertView
						.findViewById(R.id.device_warning_level);
				textView3.setText("级别："
						+ checkDeviceData.get(position).getAlarmLevel());

				ImageView img = (ImageView) convertView.findViewById(R.id.head);
				if (checkDeviceData.get(position).getAlarmLevel() == 2) {
					img.setImageResource(R.drawable.login_error_icon1);
				} else if (checkDeviceData.get(position).getAlarmLevel() == 3) {
					img.setImageResource(R.drawable.login_error_icon2);
				} else if (checkDeviceData.get(position).getAlarmLevel() == 4) {
					img.setImageResource(R.drawable.login_error_icon3);
				}
				return convertView;
			}

		};
		checkDeviceList.setAdapter(checkDeviceListAdapter);
		// checkDeviceList.setOnItemClickListener(QueryDevice.this);
		loadDeviceInfo();
	}

	private void updateView() {
		checkDeviceList.setAdapter(checkDeviceListAdapter);
	}

	private Handler weatherUIHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if (msg.obj != null) {
				checkDeviceData.clear();
				checkDeviceData = (List<Alarm>) msg.obj;
				checkDeviceListAdapter.notifyDataSetChanged();
			} else {
				Toast.makeText(getApplicationContext(), "服务器出错，获取失败",
						Toast.LENGTH_SHORT);
			}
		}

	};

	private void loadDeviceInfo() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				List<Alarm> temp = new ArrayList<Alarm>();
				List<Alarm> alarms = GetMsg.getAlarms();
				// System.out.println(alarms);
				for (Alarm alarm : alarms) {
					temp.add(alarm);
					deviceData.add(alarm.getAlarmObjDN());
					// System.out.println(alarm.getAlarmLevel()+alarm.getAlarmObjDN()+alarm.getUnitid());
				}
				Message msg = weatherUIHandler
						.obtainMessage(WHAT_DID_LOAD_DATA);
				msg.obj = temp;
				msg.sendToTarget();
			}
		}).start();
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		/**
		 * 此处需传送选中条目的所有信息
		 */
		Toast.makeText(this, "啊，你点中我了 " + position, Toast.LENGTH_SHORT).show();
		Bundle bundle = new Bundle();
		bundle.putString("deviceName", deviceData.get(position));
		Intent intent = new Intent(QueryDevice.this, DeviceDetail.class);
		intent.putExtras(bundle);
		startActivity(intent);

	}

	@SuppressWarnings("unchecked")
	public void compare(List<Alarm> alarms, int type) {
		switch (type) {
		case 1:
			Comparator comp = new Comparator() {
				public int compare(Object o1, Object o2) {
					Alarm p1 = (Alarm) o1;
					Alarm p2 = (Alarm) o2;
					if (p1.getAlarmObjDN().compareTo(p2.getAlarmObjDN()) < 0)
						return -1;
					else if (p1.getAlarmObjDN().compareTo(p2.getAlarmObjDN()) == 0)
						return 0;
					else if (p1.getAlarmObjDN().compareTo(p2.getAlarmObjDN()) > 0)
						return 1;
					return 0;
				}
			};
			Collections.sort(alarms, comp);
			break;
		case 2:
			Comparator comp1 = new Comparator() {
				public int compare(Object o1, Object o2) {
					Alarm p1 = (Alarm) o1;
					Alarm p2 = (Alarm) o2;
					if (p1.getBeginTime().compareTo(p2.getBeginTime()) < 0)
						return -1;
					else if (p1.getBeginTime().compareTo(p2.getBeginTime()) == 0)
						return 0;
					else if (p1.getBeginTime().compareTo(p2.getBeginTime()) > 0)
						return 1;
					return 0;
				}
			};
			Collections.sort(alarms, comp1);
			break;
		case 3:
			Comparator comp2 = new Comparator() {
				public int compare(Object o1, Object o2) {
					Alarm p1 = (Alarm) o1;
					Alarm p2 = (Alarm) o2;
					if (p1.getAlarmLevel() < p2.getAlarmLevel())
						return -1;
					else if (p1.getAlarmLevel() == p2.getAlarmLevel())
						return 0;
					else if (p1.getAlarmLevel() > p2.getAlarmLevel())
						return 1;
					return 0;
				}
			};
			Collections.sort(alarms, comp2);
			break;

		}
		;
	}

}
