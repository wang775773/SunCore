package bupt.sse.monitoringsystem;

import java.util.List;

import monitoringsystem.second.WeatherChartActivity;

import bupt.sse.monitoringsystem.msg.GetMsg;
import bupt.sse.monitoringsystem.msg.util.Order;
import bupt.sse.monitoringsystem.msg.util.OrderProperty;
import bupt.sse.monitoringsystem.msg.util.OrderRoleOperator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class DeviceDetail extends Activity {

	private static String deviceNameStr;
	private Button completeOrderBtn;
	private TextView orderName;
	private TextView orderDesc;
	private String instanceName;
	private String tenantName;
    private String desc;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_device_detail);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		deviceNameStr = bundle.getString("dn");
		System.out.println(deviceNameStr);
		

		orderName = (TextView) findViewById(R.id.device_detail_title);
		orderDesc = (TextView) findViewById(R.id.device_detail_desc);

		completeOrderBtn = (Button) findViewById(R.id.devices_confirm_btn);
		
		completeOrderBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				DeviceDetail.this.finish();
			
			}

		});
		
		LoadOrderDetailInfo();
	}

	public void LoadOrderDetailInfo() {

		String dn = deviceNameStr;
		System.out.println(dn);
		bupt.sse.monitoringsystem.msg.util.DeviceDetail detail = GetMsg
				.getDeviceDetail(dn);
		String desc = "";// 从服务器拉取信息
		if (detail == null) {
			desc = "server error!";
		} 
		else
		{
			desc = "设备编号:     " + detail.getDn() + "\n"
				 + "设备发电量: " + detail.getPower() + "\n"
				 + "所属单元:     " + detail.getParentid() + "\n"
				 + "当前时间:     " + detail.getDate() + "\n"
				 +"--------------------------------"+ "\n"
				 + "告警级别:     " + detail.getAlarm().getAlarmLevel() + "\n"
				 + "告警类型:     " + detail.getAlarm().getAlarmObjType() + "\n"
				 + "告警编号:     " + detail.getAlarm().getAlarmObjDN() + "\n"
				 + "告警信息:     " + detail.getAlarm().getAlarmtext() + "\n"
				 + "开始时间:     " + detail.getAlarm().getBeginTime();
				 
				 
		}
		orderName.setText(deviceNameStr);
		orderDesc.setText(desc);

	}

	

}
