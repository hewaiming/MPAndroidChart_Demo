package com.example.mpandroidchart_demo;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class RadarChartActivity extends Activity {
	private RadarChart radarchart;
	public ArrayList<String> x = new ArrayList<String>();
	public ArrayList<Entry> y = new ArrayList<Entry>();
	private RadarData radarData = null;
	private ArrayList<RadarDataSet> radarDataSets = new ArrayList<RadarDataSet>();;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		radarchart = new RadarChart(this);
		setContentView(radarchart);
		RadarData resultData = getRadarData(40, 100);
		showChart();
	}

	/**
	 * 设置样式
	 */
	private void showChart() {

		radarchart.setDescription("有风险的数据");// 数据描述
		radarchart.setNoDataTextDescription("我需要数据");// 没数据显示
		radarchart.setBackgroundColor(Color.YELLOW);// 背景颜色
		radarchart.setData(radarData);// 设置数据
		Legend legend = radarchart.getLegend();// 设置比例图片标示，就是那一组Y的value
		legend.setForm(Legend.LegendForm.CIRCLE);// 样式
		legend.setFormSize(6f);// 字体
		legend.setTextColor(Color.WHITE);// 设置颜色
		radarchart.animateY(2000);// X轴的动画

	}

	/**
	 * gv 初始化数据 count 表示坐标点个数，range表示等下y值生成的范围
	 */
	private RadarData getRadarData(int count, int range) {
		for (int i = 0; i < count; i++) { // X轴显示的数据
			x.add(i + "");
		}
		for (int i = 0; i < count; i++) {// y轴的数据
			float result = (float) (Math.random() * range) + 3;
			y.add(new Entry(result, i));
		}
		RadarDataSet radarDataSet = new RadarDataSet(y, "雷达图");// y轴数据集合
		radarDataSet.setLineWidth(1f);// 线宽
		radarDataSet.setColor(Color.RED);// 现实颜色
		radarDataSet.setHighLightColor(Color.WHITE);// 高度线的颜色
		// radarDataSets.add(radarDataSet);
		radarData = new RadarData(x, radarDataSet);
		return radarData;
	}
}
