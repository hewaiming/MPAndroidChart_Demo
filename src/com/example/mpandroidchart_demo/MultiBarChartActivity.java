package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.Random;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class MultiBarChartActivity extends Activity {
	private Random random;// 用于产生随机数
	private BarChart multi_chart;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
	
		BarData resultData = getMultiBarData(12);
		showChart(resultData);
	}

	private void showChart(BarData resultData) {
		multi_chart = new BarChart(this);
		setContentView(multi_chart);

		multi_chart.setData(resultData);
		multi_chart.getLegend().setPosition(LegendPosition.BELOW_CHART_LEFT);// 设置注解的位置在左上方
		multi_chart.getLegend().setForm(LegendForm.CIRCLE);// 这是左边显示小图标的形状

		multi_chart.getXAxis().setPosition(XAxisPosition.BOTTOM);// 设置X轴的位置
		multi_chart.getXAxis().setDrawGridLines(false);// 不显示网格

		multi_chart.getAxisRight().setEnabled(false);// 右侧不显示Y轴
		multi_chart.getAxisLeft().setAxisMinValue(0.0f);// 设置Y轴显示最小值，不然0下面会有空隙
		multi_chart.getAxisLeft().setDrawGridLines(false);// 不设置Y轴网格
	
		multi_chart.setDescription("No Deal");// 设置描述
		multi_chart.setDescriptionTextSize(20.f);// 设置描述字体
		multi_chart.animateXY(1000, 2000);// 设置动画

	}

	private BarData getMultiBarData(int count) {
		ArrayList<BarEntry> yVals = new ArrayList<>();// Y轴方向第一组数组
		ArrayList<BarEntry> yVals2 = new ArrayList<>();// Y轴方向第二组数组
		ArrayList<BarEntry> yVals3 = new ArrayList<>();// Y轴方向第三组数组
		ArrayList<String> xVals = new ArrayList<>();// X轴数据
		random = new Random();
		
		for (int i = 0; i < count; i++) {// 添加数据源
			xVals.add((i + 1) + "月");
			yVals.add(new BarEntry(random.nextInt(10000), i));			
			yVals2.add(new BarEntry(random.nextInt(10000), i));
			if(i%2==0){
					yVals3.add(new BarEntry(500, i));
			}else{
				yVals3.add(new BarEntry(0, i));
			}		
			

		}

		BarDataSet barDataSet = new BarDataSet(yVals, "小明每月支出");
		barDataSet.setColor(Color.RED);// 设置第一组数据颜色
        barDataSet.setDrawValues(false); //不显示数值
		
		BarDataSet barDataSet2 = new BarDataSet(yVals2, "小花每月支出");
		barDataSet2.setColor(Color.GREEN);// 设置第二组数据颜色

		BarDataSet barDataSet3 = new BarDataSet(yVals3, "小蔡每月支出");
		barDataSet3.setColor(Color.YELLOW);// 设置第三组数据颜色
		barDataSet3.setDrawValues(false); //不显示数值
		
		ArrayList<IBarDataSet> threebardata = new ArrayList<>();// IBarDataSet
																// 接口很关键，是添加多组数据的关键结构，LineChart也是可以采用对应的接口类，也可以添加多组数据
		threebardata.add(barDataSet);
		threebardata.add(barDataSet2);
		threebardata.add(barDataSet3);

		BarData multi_bardata = new BarData(xVals, threebardata);
		return multi_bardata;
	}	
}
