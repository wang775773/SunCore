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

public class OrderDetail extends Activity implements OnClickListener {

	private static String orderNameStr;

	private Button dealOrderBtn;
	private Button completeOrderBtn;
	private TextView orderName;
	private TextView orderDesc;
	private String instanceName;
	private String tenantName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_detail);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		orderNameStr = bundle.getString("orderName");
		

		orderName = (TextView) findViewById(R.id.order_detail_title);
		orderDesc = (TextView) findViewById(R.id.order_detail_desc);

		dealOrderBtn = (Button) findViewById(R.id.order_detail_exit_btn);
		completeOrderBtn = (Button) findViewById(R.id.order_detail_complete_btn);
		dealOrderBtn.setOnClickListener(this);
		//completeOrderBtn.setOnClickListener(this);
		completeOrderBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
				bundle.putString("instanceName",instanceName );
				bundle.putString("tenantName", tenantName);
				intent.setClass(getApplicationContext(), CompleteOrder.class);
				intent.putExtras(bundle);
				startActivity(intent);
			}
			
		});
		LoadOrderDetailInfo();
	}

	public void LoadOrderDetailInfo() {

		String[] tmp = orderNameStr.split("_");
		//Order order = new Order(tmp[0], tmp[1]);
		instanceName=tmp[0];
		tenantName=tmp[1];
		System.out.println(instanceName+" "+tenantName);
		bupt.sse.monitoringsystem.msg.util.OrderDetail detail = GetMsg
				.getOrderDetail(instanceName,tenantName);
		String desc = "";// 从服务器拉取信息
		if (detail == null) {
			desc = "server error!";
		} else {
			String status;
			if(detail.getStatus().equals("0"))status="未完成";
			else status="完成";
			desc = "状态:" + status + "\n"
					+ "--------------------------\n" + "工单相关角色人员:\n";

			List<OrderRoleOperator> orderRoleOperators = detail
					.getOrderRoleOperators();
			for (OrderRoleOperator operator : orderRoleOperators) {
				desc += operator.getRoleName() + ":"
						+ operator.getOperatorName() + "\n";
			}
			desc += "--------------------------\n" + "工单相关属性:\n";
			List<OrderProperty> orderProperties = detail.getOrderProperties();
			for (OrderProperty property : orderProperties) {
				if (property.getPropertyValue().trim().equals(""))
					continue;
				if (property.getPropertyName().trim().equals("workorderlevel")
						|| property.getPropertyName().trim()
								.equals("chinese_name")
						|| property.getPropertyName().trim()
								.equals("instance_type")
						|| property.getPropertyName().trim()
								.equals("create_time")
						|| property.getPropertyName().trim()
								.equals("description"))
					desc += property.getPropertyName() + ":"
							+ property.getPropertyValue() + "\n";
			}
		}

		orderName.setText(orderNameStr);
		orderDesc.setText(desc);

	}

	@Override
	public void onClick(View arg0) {
		if (arg0.getId() == R.id.order_detail_exit_btn) {
			this.finish();
		} else if (arg0.getId() == R.id.order_detail_complete_btn) {
			this.finish();
		}
	}

}
