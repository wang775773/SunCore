package bupt.sse.monitoringsystem;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class Login extends Activity {
	
	private EditText username;
	private EditText pwd;
	private ImageView login_btn;
	
	private static int LOGIN_FAILED = 250;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		username = (EditText) findViewById(R.id.login_username_edit);
		pwd = (EditText) findViewById(R.id.login_pwd_edit);
		login_btn = (ImageView) findViewById(R.id.login_login_btn);
		
		login_btn.setOnClickListener(new LoginBtnOnClickListener());
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		if(resultCode == LOGIN_FAILED) {
			new AlertDialog.Builder(Login.this)
			.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
			.setTitle("登录失败")
			.setMessage("帐号或者密码不正确，\n请检查后再输入！")
			.create().show();
			username.setText("");
			pwd.setText("");
		}
	}

	class LoginBtnOnClickListener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			login_btn.setImageDrawable(getResources().getDrawable(R.drawable.login_pressed));
			String user = username.getText().toString();
			String password = pwd.getText().toString();
			if(user.equals("") || password.equals("")) {
				new AlertDialog.Builder(Login.this)
				.setIcon(getResources().getDrawable(R.drawable.login_error_icon))
				.setTitle("登录错误")
				.setMessage("帐号或者密码不能为空，\n请输入后再登录！")
				.create().show();
			} else {
				Intent intent = new Intent();
				Bundle bundle = new Bundle();
	            bundle.putString("username", user);
	            bundle.putString("password", password);
	            intent.putExtras(bundle);
	            intent.setClass(Login.this,Loading.class);
	            startActivityForResult(intent,0);
			}
		}
		
	}

}
