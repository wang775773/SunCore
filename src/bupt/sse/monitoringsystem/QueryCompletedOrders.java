package bupt.sse.monitoringsystem;

import java.util.ArrayList;
import java.util.List;

import bupt.sse.monitoringsystem.PullDownView.OnPullDownListener;
import bupt.sse.monitoringsystem.msg.GetMsg;
import bupt.sse.monitoringsystem.msg.util.Order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class QueryCompletedOrders extends Activity implements OnPullDownListener,
		OnItemClickListener {

	private static final int WHAT_DID_LOAD_DATA = 0;
	private static final int WHAT_DID_REFRESH = 1;
	private static final int WHAT_DID_MORE = 2;

	private ListView mListView;
	private BaseAdapter mAdapter;
    private TextView text;
	private PullDownView mPullDownView;
	private List<String> myOrderData = new ArrayList<String>();;

	private List<Order> orders;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pulldown);

		/*
		 * 1.使用PullDownView 2.设置OnPullDownListener 3.从mPullDownView里面获取ListView
		 */
		mPullDownView = (PullDownView) findViewById(R.id.pull_down_view);
		mPullDownView.setOnPullDownListener(this);
		mListView = mPullDownView.getListView();
        text=(TextView)findViewById(R.id.main_order_type);
        text.setText("已完成工单");
		mListView.setOnItemClickListener(this);
		mListView.setDivider(null);
		mAdapter = new MyOrderListAdapter();
		mListView.setAdapter(mAdapter);

		mPullDownView.enableAutoFetchMore(true, 1);

		loadData();
	}

	private void loadData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				orders = GetMsg.getOrders("ts");
				List<String> temp = new ArrayList<String>();
				for (Order order : orders) {
					if(GetMsg.getOrderDetail(order.getInstanceName(),order.getTenantName()).getStatus().equals("1"))
					{
						myOrderData.add(order.getInstanceName() + "_"
								+ order.getTenantName());;
					}	
				}
				Message msg = mUIHandler.obtainMessage(WHAT_DID_LOAD_DATA);
				msg.obj = temp;
				msg.sendToTarget();
			}
		}).start();
	}

	@Override
	public void onRefresh() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				orders = GetMsg.getOrders("ts");
				myOrderData.clear();
				for (Order order : orders) {
					if(GetMsg.getOrderDetail(order.getInstanceName(),order.getTenantName()).getStatus().equals("1"))
					{
						myOrderData.add(order.getInstanceName() + "_"
								+ order.getTenantName());;
					}	
				}
				Message msg = mUIHandler.obtainMessage(WHAT_DID_REFRESH);
				msg.obj = myOrderData;
				msg.sendToTarget();
			}
		}).start();
	}

	@Override
	public void onMore() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				// Message msg = mUIHandler.obtainMessage(WHAT_DID_MORE);
				// msg.obj = "After more " + System.currentTimeMillis();
				// msg.sendToTarget();
			}
		}).start();
	}

	private Handler mUIHandler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case WHAT_DID_LOAD_DATA: {
				if (msg.obj != null) {
					List<String> strings = (List<String>) msg.obj;
					if (!strings.isEmpty()) {
						myOrderData.addAll(strings);
						mAdapter.notifyDataSetChanged();
					}
				}
				// 诉它数据加载完毕;
				mPullDownView.notifyDidLoad();
				break;
			}
			case WHAT_DID_REFRESH: {
//				String body = (String) msg.obj;
//				myOrderData.add(0, body);
				mAdapter.notifyDataSetChanged();
				// 告诉它更新完毕
				mPullDownView.notifyDidRefresh();
				break;
			}

			case WHAT_DID_MORE: {
//				String body = (String) msg.obj;
//				myOrderData.add(body);
				mAdapter.notifyDataSetChanged();
				// 告诉它获取更多完毕
				mPullDownView.notifyDidMore();
				break;
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

		//Toast.makeText(this, "啊，你点中我了 " + position, Toast.LENGTH_SHORT).show();
		Bundle bundle = new Bundle();
		bundle.putString("orderName", myOrderData.get(position));
		Intent intent = new Intent(QueryCompletedOrders.this, OrderDetail.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}

	class MyOrderListAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return myOrderData.size();
		}

		@Override
		public Object getItem(int arg0) {
			return myOrderData.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			return arg0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = LayoutInflater.from(getApplicationContext()).inflate(
					R.layout.pulldown_item, null);
			TextView textView = (TextView) convertView
					.findViewById(R.id.order_item_name);
			textView.setText(myOrderData.get(position));
			return convertView;
		}

	}
}
