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
	private Random random;// ���ڲ��������

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		MyApplication.getInstance().addActivity(this);
		initPieChart();// ��ʼ����ͼ
	}

	private void initPieChart() {
		piechart=new PieChart(this);
		setContentView(piechart);
		
		piedata = getPieData(4, 100);    
        showChart(piechart, piedata);  		
	}

	private void showChart(PieChart piechart2, PieData piedata2) {
		piechart.setData(piedata);// ��PieChart�������
		piechart.getLegend().setPosition(LegendPosition.ABOVE_CHART_LEFT);
		piechart.getLegend().setForm(LegendForm.CIRCLE);// ����ע���λ�ú���״
		piechart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

			@Override
			public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
				Toast.makeText(getApplicationContext(), "Something selected value = " + e.getVal(), Toast.LENGTH_SHORT)
						.show();// ��������ʾһ��Toast
			}

			@Override
			public void onNothingSelected() {
				// TODO Auto-generated method stub

			}
		});

		piechart.setCenterText("��֧��ϸ");// �м�д������
		piechart.setCenterTextColor(Color.RED);// �����м����ֵ���ɫ
		piechart.setCenterTextRadiusPercent(0.5f);// ����������ʾ�ĽǶȣ�180���ţ�Ĭ��������
		piechart.setCenterTextSize(12f);// �����������ֵ������С
		piechart.setCenterTextTypeface(null);// ��������
		piechart.setDrawCenterText(true);// ������ʹ�ܿ��أ�falseʱ�м��޷���ʾ����

		piechart.setTransparentCircleAlpha(100);// ͸��Ȧ��͸���ȣ���3Ȧ��һ���������ֵ��Ȼ���������Ȼ�����������Ǹ�Hole
		piechart.setTransparentCircleColor(Color.RED); // ������ɫ
		piechart.setTransparentCircleRadius(50f);// ���ð뾶

		piechart.setDrawHoleEnabled(true);// ����ͬ��
		piechart.setHoleColor(Color.GREEN);
		piechart.setHoleRadius(30f);

		piechart.setDescription("No Deal");// ������������
		piechart.setDescriptionTextSize(20.f);// �����������ֵ�����
		piechart.animateXY(1000, 1000);
		
	}

	private PieData getPieData(int count, int range) {
		 ArrayList<String>  xVals = new ArrayList<String>();  //xVals������ʾÿ�������ϵ�����    
		    
	        for (int i = 0; i < count; i++) {    
	        	 xVals.add("��" + (i + 1));  //��������ʾ��Quarterly1, Quarterly2, Quarterly3, Quarterly4    
	        }    
	    
	        ArrayList<Entry> yValues = new ArrayList<Entry>();  //yVals������ʾ��װÿ�������ʵ������    
	    
	        // ��ͼ����    
	        /**  
	         * ��һ������ͼ�ֳ��Ĳ��֣� �Ĳ��ֵ���ֵ����Ϊ14:14:34:38  
	         * ���� 14����İٷֱȾ���14%   
	         */    
	        float quarterly1 = 14;    
	        float quarterly2 = 14;    
	        float quarterly3 = 34;    
	        float quarterly4 = 38;    
	    
	        yValues.add(new Entry(quarterly1, 0));    
	        yValues.add(new Entry(quarterly2, 1));    
	        yValues.add(new Entry(quarterly3, 2));    
	        yValues.add(new Entry(quarterly4, 3));    
	    
	        //y��ļ���    
	        PieDataSet pieDataSet = new PieDataSet(yValues, "С��ÿ��֧��");/*��ʾ�ڱ���ͼ��*/    
	        pieDataSet.setSliceSpace(0f); //���ø���״ͼ֮��ľ���    
	    
	        ArrayList<Integer> colors = new ArrayList<Integer>();    
	    
	        // ��ͼ��ɫ    
	        colors.add(Color.rgb(205, 205, 205));    
	        colors.add(Color.rgb(114, 188, 223));    
	        colors.add(Color.rgb(255, 123, 124));    
	        colors.add(Color.rgb(57, 135, 200));    
	    
	        pieDataSet.setColors(colors);    
	    
	        DisplayMetrics metrics = getResources().getDisplayMetrics();    
	        float px = 5 * (metrics.densityDpi / 160f);    
	        pieDataSet.setSelectionShift(px); // ѡ��̬����ĳ���    
	    
	        PieData pieData = new PieData(xVals, pieDataSet);    	            
	        return pieData;    
	}
}
