package com.example.mpandroidchart_demo;

import java.util.ArrayList;
import java.util.Random;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend.LegendForm;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Toast;

public class PieChartActivity extends Activity {
	private PieChart piechart;
	private PieDataSet pieDataSet;
	private PieData piedata;
	private Random random;// 用于产生随机数

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		initPieChart();// 初始化饼图
	}

	private void initPieChart() {
		piechart=new PieChart(this);
		setContentView(piechart);
		
		piedata = getPieData(4, 100);    
        showChart(piechart, piedata);  		
	}

	private void showChart(PieChart piechart2, PieData piedata2) {
		piechart.setData(piedata);// 给PieChart填充数据
		piechart.getLegend().setPosition(LegendPosition.ABOVE_CHART_LEFT);
		piechart.getLegend().setForm(LegendForm.CIRCLE);// 设置注解的位置和形状
		piechart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

			@Override
			public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
				Toast.makeText(getApplicationContext(), "Something selected value = " + e.getVal(), Toast.LENGTH_SHORT)
						.show();// 单纯地显示一个Toast
			}

			@Override
			public void onNothingSelected() {
				// TODO Auto-generated method stub

			}
		});

		piechart.setCenterText("收支明细");// 中间写的文字
		piechart.setCenterTextColor(Color.RED);// 设置中间文字的颜色
		piechart.setCenterTextRadiusPercent(0.5f);// 设置文字显示的角度，180横着，默认是竖着
		piechart.setCenterTextSize(12f);// 设置中心文字的字体大小
		piechart.setCenterTextTypeface(null);// 设置字体
		piechart.setDrawCenterText(true);// 中心字使能开关，false时中间无法显示文字

		piechart.setTransparentCircleAlpha(100);// 透明圈的透明度，分3圈，一个是外面的值，然后是这个，然后就是下面的那个Hole
		piechart.setTransparentCircleColor(Color.RED); // 设置颜色
		piechart.setTransparentCircleRadius(50f);// 设置半径

		piechart.setDrawHoleEnabled(true);// 基本同上
		piechart.setHoleColor(Color.GREEN);
		piechart.setHoleRadius(30f);

		piechart.setDescription("No Deal");// 设置描述文字
		piechart.setDescriptionTextSize(20.f);// 设置描述文字的字体
		piechart.animateXY(1000, 1000);
		
	}

	private PieData getPieData(int count, int range) {
		 ArrayList<String>  xVals = new ArrayList<String>();  //xVals用来表示每个饼块上的内容    
		    
	        for (int i = 0; i < count; i++) {    
	        	 xVals.add("月" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4    
	        }    
	    
	        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals用来表示封装每个饼块的实际数据    
	    
	        // 饼图数据    
	        /**  
	         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38  
	         * 所以 14代表的百分比就是14%   
	         */    
	        float quarterly1 = 14;    
	        float quarterly2 = 14;    
	        float quarterly3 = 34;    
	        float quarterly4 = 38;    
	    
	        yValues.add(new Entry(quarterly1, 0));    
	        yValues.add(new Entry(quarterly2, 1));    
	        yValues.add(new Entry(quarterly3, 2));    
	        yValues.add(new Entry(quarterly4, 3));    
	    
	        //y轴的集合    
	        PieDataSet pieDataSet = new PieDataSet(yValues, "小明每月支出");/*显示在比例图上*/    
	        pieDataSet.setSliceSpace(0f); //设置个饼状图之间的距离    
	    
	        ArrayList<Integer> colors = new ArrayList<Integer>();    
	    
	        // 饼图颜色    
	        colors.add(Color.rgb(205, 205, 205));    
	        colors.add(Color.rgb(114, 188, 223));    
	        colors.add(Color.rgb(255, 123, 124));    
	        colors.add(Color.rgb(57, 135, 200));    
	    
	        pieDataSet.setColors(colors);    
	    
	        DisplayMetrics metrics = getResources().getDisplayMetrics();    
	        float px = 5 * (metrics.densityDpi / 160f);    
	        pieDataSet.setSelectionShift(px); // 选中态多出的长度    
	    
	        PieData pieData = new PieData(xVals, pieDataSet);    	            
	        return pieData;    
	}
}
