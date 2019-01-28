package com.jdsv650.webviewtest;

import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.ScatterChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.ScatterData;
import com.github.mikephil.charting.data.ScatterDataSet;

import java.lang.reflect.Array;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ScatterChartTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scatter_chart_test);

        /*** bar chart
        HVAC h = new HVAC();
        h.date1 = "1";
        h.runT1 = 23.0;
        h.date3 = "3";
        h.runT3 = 56.9;
        graph(h);   ****/

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        graph();

    }


    private void graph()
    {
        int maxItems = 1440;

        ScatterChart scatterChartView = (ScatterChart) findViewById(R.id.chart);

        // misc chart settings
        scatterChartView.getLegend().setEnabled(true);
        scatterChartView.getLegend().setTextColor(Color.BLACK);

        scatterChartView.getDescription().setEnabled(false);

        // Legend
        LegendEntry le1 = new LegendEntry("label1", Legend.LegendForm.CIRCLE, 10, 10, new DashPathEffect(new float[10], 10), Color.BLUE);
        LegendEntry le2 = new LegendEntry("label2", Legend.LegendForm.CIRCLE, 10, 10, new DashPathEffect(new float[10], 10), Color.GREEN);
        LegendEntry le3 = new LegendEntry("label3", Legend.LegendForm.CIRCLE, 10, 10, new DashPathEffect(new float[10], 10), Color.RED);
        LegendEntry le4 = new LegendEntry("label4", Legend.LegendForm.CIRCLE, 10, 10, new DashPathEffect(new float[10], 10), Color.YELLOW);

        ArrayList<LegendEntry> legendEntries = new ArrayList<LegendEntry>();
        legendEntries.add(le1);
        legendEntries.add(le2);
        legendEntries.add(le3);
        legendEntries.add(le4);

        scatterChartView.getLegend().setCustom(legendEntries);

        // Data Entry
        List<Entry> dataArray1 = new ArrayList<Entry>();
        List<Entry> dataArray2 = new ArrayList<Entry>();

        //Entry p1 = new Entry(-1350, 35);
        // Entry p2 = new Entry(-20, 87);

        // dataArray1.add(p1);
        // dataArray1.add(p2);

        int[] colors1 = new int[maxItems];
        int[] colors2 = new int[maxItems];

        for (int i=0; i<maxItems; i++) {

            Entry point = new Entry(i-1440,  new Random().nextInt(170) + 30);
            Entry point2 = new Entry(i-1440,  new Random().nextInt(170) + 30);
            dataArray1.add(point);
            dataArray2.add(point2);

            int randColorNum = new Random().nextInt(4);

            if (randColorNum == 0)
            {
                colors1[i] = Color.YELLOW;
                colors2[i] = Color.YELLOW;
            }
            else if (randColorNum == 1)
            {
                colors1[i] = Color.BLUE;
                colors2[i] = Color.YELLOW;
            }
            else if (randColorNum == 2)
            {
                colors1[i] = Color.RED;
                colors2[i] = Color.YELLOW;
            }
            else if (randColorNum == 3)
            {
                colors1[i] = Color.GREEN;
                colors2[i] = Color.YELLOW;
            }

        }

        ScatterDataSet dataSet1 = new ScatterDataSet(dataArray1, "set1");
        ScatterDataSet dataSet2 = new ScatterDataSet(dataArray2, "set2");

        dataSet1.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        dataSet1.setScatterShapeSize(10.0f);
       // dataSet1.setColor(Color.RED);
        dataSet1.setColors(colors1);

        dataSet2.setScatterShape(ScatterChart.ScatterShape.CIRCLE);
        dataSet2.setScatterShapeSize(10.0f);
        dataSet2.setColors(colors1);

        ScatterDataSet[] dataSets = new ScatterDataSet[2];
        dataSets[0] = dataSet1;
        dataSets[1] = dataSet2;

        ScatterData data = new ScatterData(dataSets);

        scatterChartView.setData(data);

       // scatterChartView.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        scatterChartView.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(true);

        String[] xAxisLabels = new String[maxItems];

        for (int i=0; i<maxItems; i++)  {
            Log.d("xAxis items", "graph: ");
            xAxisLabels[i] = "!"; //String(i+1-1440);
        }

        // show labels on x axis from our data set
        XAxis xAxis = scatterChartView.getXAxis();

        xAxis.setGranularity(240);  // 4 hr - less than 200 just display 200 interval
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);

        xAxis.setAxisMinimum(-1440);
        xAxis.setAxisMaximum(0);

        //  xAxis.valueFormatter = AxisValueFormatter(values: [])

        // set min/max values for chart y-axis
        // for Daily Run Time set min-max to 0-100

        YAxis yAxis = scatterChartView.getAxis(YAxis.AxisDependency.LEFT);

        yAxis.setLabelCount(6);
        yAxis.setGranularity(1);


        yAxis.setValueFormatter(new YAxisValueFormatter());

        yAxis.setAxisMaximum(210);
        yAxis.setAxisMinimum(30);

        scatterChartView.getAxis(YAxis.AxisDependency.RIGHT).setEnabled(false);

        // yAxis = scatterChartView.getAxis(.right)
        //yAxis.axisMaximum = 200
        //yAxis.axisMinimum = 30

        //scatterChartView.invalidate();
        scatterChartView.notifyDataSetChanged();
    }




    /**** bar chart stuff ****
    private void graph(HVAC sensor)
    {
        BarChart chart = (BarChart) findViewById(R.id.chart);
        ArrayList<Bar> bars = populateBars(sensor);

        ArrayList<BarEntry> dataEntry = new ArrayList<BarEntry>();
        ArrayList<String> xAxisLabels = new ArrayList<String>();

        // cycle through bars and grab label and value for each
        for (int i=0; i<bars.size(); i++)
        {
            Float xVal = new Float(i);
            dataEntry.add(new BarEntry(xVal, bars.get(i).value));
            xAxisLabels.add(bars.get(i).label);
        }

        BarDataSet dataSet = new BarDataSet(dataEntry, ""); // add entries to dataset

        // "hide" actual value displayed near top of bar by same color
        dataSet.setValueTextColor(Color.WHITE);

        BarData data = new BarData(dataSet);
        chart.setData(data);

         // create and place chart descrioption
         Description chartDescription = chart.getDescription();

         // get center x
         Display display = getWindowManager().getDefaultDisplay();
         final Point size = new Point();
         display.getSize(size);
         Integer width = size.x;
         float centerX = width/2;
         Integer x = Math.round(centerX);

         chartDescription.setPosition(x, 40);
         chartDescription.setText("Daily Run Time Percent");
         chartDescription.setTextAlign(Paint.Align.CENTER);
         chartDescription.setTextColor(getResources().getColor(R.color.colorBlack));
         chartDescription.setTextSize(14.0f);

        // misc chart settings
        chart.setDrawValueAboveBar(false);
        chart.getLegend().setEnabled(false);
        chart.setBackgroundColor(Color.WHITE);
        chart.setBorderColor(Color.BLACK);
        chart.setGridBackgroundColor(Color.WHITE);
        chart.setDrawGridBackground(true);
        chart.getDescription().setEnabled(false);

        // show labels on x axis from our data set
        XAxis xAxis = chart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(R.color.colorPrimary));
        xAxis.setDrawLabels(true);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(true);
        xAxis.setGranularity(1.0f);

        xAxis.setValueFormatter(new AxisValueFormatter(xAxisLabels));

        // set min/max values for chart y-axis
        // for Daily Run Time set min-max to 0-100
        // for all charts set min to 0

        AxisBase yAxis = chart.getAxisLeft();
        yAxis.setAxisMaximum(100.0f);
        yAxis.setAxisMinimum(0.0f);

        yAxis = chart.getAxisRight();
        yAxis.setAxisMaximum(100.0f);
        yAxis.setAxisMinimum(0.0f);

        //chart.invalidate(); // refresh
        chart.notifyDataSetChanged();

    }

    private ArrayList<Bar> populateBars(HVAC sensor)
    {
        ArrayList<Bar> barsRead = new ArrayList<Bar>();

        if(sensor.date1 != "" && sensor.runT1 != -99.0)
        {
            Bar b = new Bar(sensor.date1, sensor.runT1.floatValue());
            barsRead.add(b);
        }

        if(sensor.date2 != "" && sensor.runT2 != -99.0)
        {
            Bar b = new Bar(sensor.date2, sensor.runT2.floatValue());
            barsRead.add(b);
        }

        if(sensor.date3 != "" && sensor.runT3 != -99.0)
        {
            Bar b = new Bar(sensor.date3, sensor.runT3.floatValue());
            barsRead.add(b);
        }

        if(sensor.date4 != "" && sensor.runT4 != -99.0)
        {
            Bar b = new Bar(sensor.date4, sensor.runT4.floatValue());
            barsRead.add(b);
        }

        if(sensor.date5 != "" && sensor.runT5 != -99.0)
        {
            Bar b = new Bar(sensor.date5, sensor.runT5.floatValue());
            barsRead.add(b);
        }

        if(sensor.date6 != "" && sensor.runT6 != -99.0)
        {
            Bar b = new Bar(sensor.date6, sensor.runT6.floatValue());
            barsRead.add(b);
        }

        if(sensor.date7 != "" && sensor.runT7 != -99.0)
        {
            Bar b = new Bar(sensor.date7, sensor.runT7.floatValue());
            barsRead.add(b);
        }

        return barsRead;
    }
    **************/

}
