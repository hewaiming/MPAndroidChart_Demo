package com.example.mpandroidchart_demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {
	private Button btn_linechart;
	private Button btn_barchart, btn_horizonalbarchart;
	private Button btn_combinedchart;
	private Button btn_piechart;
	private Button btn_scatterchart;
	private Button btn_candlechart;
	private Button btn_radarchart;
	private Context mContext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyApplication.getInstance().addActivity(this);
		mContext = this;
		init_View();

	}

	private void init_View() {
		btn_linechart = (Button) findViewById(R.id.btn_linechart);
		btn_barchart = (Button) findViewById(R.id.btn_barchart);
		btn_horizonalbarchart = (Button) findViewById(R.id.btn_horizontalchart);

		btn_combinedchart = (Button) findViewById(R.id.btn_combinedchart);

		btn_piechart = (Button) findViewById(R.id.btn_piechart);
		btn_scatterchart = (Button) findViewById(R.id.btn_scatterchart);
		btn_candlechart = (Button) findViewById(R.id.btn_candlechart);
		btn_radarchart = (Button) findViewById(R.id.btn_radarchart);

		btn_linechart.setOnClickListener(this);

		btn_barchart.setOnClickListener(this);
		btn_horizonalbarchart.setOnClickListener(this);
		btn_combinedchart.setOnClickListener(this);
		btn_piechart.setOnClickListener(this);
		btn_scatterchart.setOnClickListener(this);
		btn_candlechart.setOnClickListener(this);
		btn_radarchart.setOnClickListener(this);

	}

	@Override
	public void onClick(View view) {
		Intent intent = null;
		switch (view.getId()) {
		case R.id.btn_linechart:
			intent = new Intent(MainActivity.this, LineChartActivity.class);
			break;
		case R.id.btn_barchart:
			intent = new Intent(MainActivity.this, BarChartActivity.class);
			break;
		case R.id.btn_horizontalchart:
			intent = new Intent(MainActivity.this, HorizontalChart.class);
			break;
		case R.id.btn_combinedchart:
			intent = new Intent(MainActivity.this, CombinedChartActivity.class);
			break;
		case R.id.btn_piechart:
			intent = new Intent(MainActivity.this, PieChartActivity.class);
			break;
		case R.id.btn_scatterchart:
			intent = new Intent(MainActivity.this, ScatterChartActivity.class);
			break;
		case R.id.btn_candlechart:
			intent = new Intent(MainActivity.this, CandleChartActivity.class);
			break;
		case R.id.btn_radarchart:
			intent = new Intent(MainActivity.this, RadarChartActivity.class);
			break;
		}
		startActivity(intent);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			dialog_exit();
			return true;
		}
		return true;
	}

	protected void dialog_exit() {
		AlertDialog.Builder builder = new Builder(mContext);
		builder.setMessage("确定要退出吗?");
		builder.setTitle("提示");
		builder.setPositiveButton("确认", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				MyApplication.getInstance().exit();

			}
		});
		builder.setNegativeButton("取消", new android.content.DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		builder.create().show();
	}
}