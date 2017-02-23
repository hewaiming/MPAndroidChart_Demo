package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.Random;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.os.Bundle;

public class BarChartActivity extends Activity {
	private Random random;// 用于产生随机数
	private BarChart chart;
	private BarData data;
	private BarDataSet dataSet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		init_chart();

	}

	private void init_chart() {
		// 设置显示图表
		chart = new BarChart(this);
		setContentView(chart);
		/** 图表具体设置 */
		ArrayList<BarEntry> entries = new ArrayList<>();// 显示条目
		ArrayList<String> xVals = new ArrayList<String>();// 横坐标标签
		random = new Random();// 随机数
		for (int i = 0; i < 12; i++) {
			float profit = random.nextFloat() * 1000;
			// entries.add(BarEntry(float val,int positon);
			entries.add(new BarEntry(profit, i));
			xVals.add((i + 1) + "月");
		}

		dataSet = new BarDataSet(entries, "公司年利润报表");
		dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
		data = new BarData(xVals, dataSet);

		chart.setData(data);
		// 设置Y方向上动画animateY(int time);
		chart.animateY(3000);
		// 图表描述
		chart.setDescription("公司前半年财务报表(单位：万元)");

	}
}
