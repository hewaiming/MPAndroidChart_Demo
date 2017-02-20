package com.example.mpandroidchart_demo;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class ScatterChartActivity extends Activity {
	private ScatterChart scatterchart;
	public ArrayList<String> x = new ArrayList<String>();  
	public ArrayList<Entry> y = new ArrayList<Entry>();
	public ArrayList<ScatterDataSet> scatterDataSets = new ArrayList<ScatterDataSet>();
	private ScatterData scatterData=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		scatterchart = new ScatterChart(this);
		setContentView(scatterchart);
		ScatterData resultScatterData = getRadarData(40, 100);
		showChart();
	}

	private void showChart() {
		scatterchart.setDrawBorders(false); //是否添加边框
		scatterchart.setDescription("有风险的数据"); //数据描述
		scatterchart.setNoDataTextDescription("我需要数据");//没数据显示
		scatterchart.setDrawGridBackground(true);//是否显示表格颜色
		scatterchart.setBackgroundColor(Color.YELLOW); //背景颜色
		scatterchart.setData(scatterData);  //设置数据
		Legend legend = scatterchart.getLegend();//设置比例图片标示，就是那一组Y的value  
        legend.setForm(Legend.LegendForm.CIRCLE);//样式  
        legend.setFormSize(6f);//字体  
        legend.setTextColor(Color.WHITE);//设置颜色  
        scatterchart.animateX(2000);//X轴的动画
	}

	/**  
     * gv  
     * 初始化数据  
     * count 表示坐标点个数，range表示等下y值生成的范围  
     */  
	private ScatterData getRadarData(int count, int range) {
		for(int i=0;i<count;i++){
			x.add(i+"");    //X轴显示的数据
		}
		
		for (int i = 0; i < count; i++) {//y轴的数据  
            float result = (float) (Math.random() * range) + 3;  
            y.add(new Entry(result, i));  
        }  
		ScatterDataSet scatterDataSet=new ScatterDataSet(y, "散列图");  //y轴数据集合
		scatterDataSet.setColor(Color.RED); //现实颜色
		scatterDataSet.setScatterShapeSize(6f);
		scatterDataSet.setDrawValues(true);
		scatterDataSet.setHighLightColor(Color.WHITE); //高度线的颜色
		//scatterDataSets.add(scatterDataSet);
		scatterData =new ScatterData(x,scatterDataSet);
		return scatterData;
	}
}
