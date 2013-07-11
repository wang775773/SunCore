package bupt.sse.monitoringsystem;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import bupt.sse.monitoringsystem.msg.GetMsg;

public class CompleteOrder extends Activity{
	private String instanceName;
	private String tenantName;
	private String userName="ts";
	private String info;
	private TextView instance;
	private TextView tenant;
	private TextView user;
	private EditText infoText;
	private Button confirmBtn;
	private Button backBtn;
	@SuppressLint("ShowToast")
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_receipt);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		instanceName = bundle.getString("instanceName");
		tenantName = bundle.getString("tenantName");

		instance = (TextView) findViewById(R.id.instanceName);
		tenant = (TextView) findViewById(R.id.tenantName);
		user = (TextView) findViewById(R.id.user);
		infoText = (EditText)findViewById(R.id.order_infos);
		confirmBtn = (Button)findViewById(R.id.confirmBtn);
		backBtn = (Button)findViewById(R.id.backBtn);
		instance.setText(instanceName);
		tenant.setText(tenantName);
		user.setText(userName);
		info = infoText.getText().toString();
		
		confirmBtn.setOnClickListener(new OnClickListener(){
            @Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
                if(GetMsg.receipt(instanceName,tenantName,userName,info)==null)
                {
                	System.out.println("sucess!!!");
                	Toast.makeText(getApplicationContext(), "回单成功！ ", Toast.LENGTH_SHORT).show();
                	Intent intent = new Intent(CompleteOrder.this, Main.class);
            		startActivity(intent);
                }
                else System.out.println(GetMsg.receipt(instanceName, tenantName, userName, info));
                
            }	
           
            
		});
		
		
	}

}
