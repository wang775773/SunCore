package bupt.sse.monitoringsystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class Loading extends Activity {

	private static String username;
	private static String password;

	private static int LOGIN_FAILED = 250;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);

		Intent intent = this.getIntent();
		Bundle bundle = intent.getExtras();
		username = bundle.getString("username");
		password = bundle.getString("password");

		new Handler().post(new Runnable() {
			@Override
			public void run() {
				if (checkUserInfo(Loading.username, Loading.password)) {

					/**
					 * 获取天气数据,并存储在weather1，weather2中
					 */
//					Bundle bundle = new Bundle();
//					boolean flag = false;
//					String[] weather1 = { "6-2", "30", "23", "34", "东南", "100" };
//					String[] weather2 = { "6-3", "30", "23", "34", "东南", "100" };
//					Weather weather = GetMsg.getWeather();
//					if (weather != null) {
//						flag = true;
//						SimpleDateFormat dateFormat = new SimpleDateFormat(
//								"yyyy-MM-dd HH:mm:ss");
//						weather2 = weather1 = new String[] {
//								dateFormat.format(weather.getDate()),
//								weather.getTemperature() + "",
//								weather.getHumidity() + "",
//								weather.getWindSpeed() + "",
//								weather.getWindDirection() + "",
//								weather.getAirPressure() + "" };
//					}
//					System.out.println(Arrays.toString(weather1) + "\n" + Arrays.toString(weather2));
//					bundle.putStringArray("weather1", weather1);
//					bundle.putStringArray("weather2", weather2);
//					bundle.putBoolean("flag", flag);
					Intent intent = new Intent(Loading.this, Main.class);
					//intent.putExtras(bundle);

					startActivity(intent);
					Loading.this.finish();
					Toast.makeText(getApplicationContext(), "登录成功",
							Toast.LENGTH_SHORT).show();

				} else {
					Intent intent = new Intent(Loading.this, Login.class);
					Loading.this.setResult(LOGIN_FAILED, intent);
					Loading.this.finish();
				}
			}
		});
	}

	public boolean checkUserInfo(String username, String pwd) {
//		String result = GetMsg.checkUser(new User(username, pwd));
//		if (result != null) {
//			Log.i("login", result);
//			return false;
//		}
		return true;
	}

}
