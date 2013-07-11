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
		String desc = "";// �ӷ�������ȡ��Ϣ
		if (detail == null) {
			desc = "server error!";
		} 
		else
		{
			desc = "�豸���:     " + detail.getDn() + "\n"
				 + "�豸������: " + detail.getPower() + "\n"
				 + "������Ԫ:     " + detail.getParentid() + "\n"
				 + "��ǰʱ��:     " + detail.getDate() + "\n"
				 +"--------------------------------"+ "\n"
				 + "�澯����:     " + detail.getAlarm().getAlarmLevel() + "\n"
				 + "�澯����:     " + detail.getAlarm().getAlarmObjType() + "\n"
				 + "�澯���:     " + detail.getAlarm().getAlarmObjDN() + "\n"
				 + "�澯��Ϣ:     " + detail.getAlarm().getAlarmtext() + "\n"
				 + "��ʼʱ��:     " + detail.getAlarm().getBeginTime();
				 
				 
		}
		orderName.setText(deviceNameStr);
		orderDesc.setText(desc);

	}

	

}
