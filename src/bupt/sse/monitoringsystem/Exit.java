package bupt.sse.monitoringsystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Exit extends Activity implements OnClickListener{
	
	private Button exitYesBtn;
	private Button exitNoBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exit_dialog);
		
		exitYesBtn = (Button) findViewById(R.id.exitYesBtn);
		exitNoBtn = (Button) findViewById(R.id.exitNoBtn);
		exitYesBtn.setOnClickListener(this);
		exitNoBtn.setOnClickListener(this);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		this.finish();
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0.getId() == R.id.exitYesBtn) {
			this.finish();
			Main.instance.finish();
		} else if(arg0.getId() == R.id.exitNoBtn) {
			this.finish();
		}
	}

}
