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
		scatterchart.setDrawBorders(false); //�Ƿ���ӱ߿�
		scatterchart.setDescription("�з��յ�����"); //��������
		scatterchart.setNoDataTextDescription("����Ҫ����");//û������ʾ
		scatterchart.setDrawGridBackground(true);//�Ƿ���ʾ�����ɫ
		scatterchart.setBackgroundColor(Color.YELLOW); //������ɫ
		scatterchart.setData(scatterData);  //��������
		Legend legend = scatterchart.getLegend();//���ñ���ͼƬ��ʾ��������һ��Y��value  
        legend.setForm(Legend.LegendForm.CIRCLE);//��ʽ  
        legend.setFormSize(6f);//����  
        legend.setTextColor(Color.WHITE);//������ɫ  
        scatterchart.animateX(2000);//X��Ķ���
	}

	/**  
     * gv  
     * ��ʼ������  
     * count ��ʾ����������range��ʾ����yֵ���ɵķ�Χ  
     */  
	private ScatterData getRadarData(int count, int range) {
		for(int i=0;i<count;i++){
			x.add(i+"");    //X����ʾ������
		}
		
		for (int i = 0; i < count; i++) {//y�������  
            float result = (float) (Math.random() * range) + 3;  
            y.add(new Entry(result, i));  
        }  
		ScatterDataSet scatterDataSet=new ScatterDataSet(y, "ɢ��ͼ");  //y�����ݼ���
		scatterDataSet.setColor(Color.RED); //��ʵ��ɫ
		scatterDataSet.setScatterShapeSize(6f);
		scatterDataSet.setDrawValues(true);
		scatterDataSet.setHighLightColor(Color.WHITE); //�߶��ߵ���ɫ
		//scatterDataSets.add(scatterDataSet);
		scatterData =new ScatterData(x,scatterDataSet);
		return scatterData;
	}
}
