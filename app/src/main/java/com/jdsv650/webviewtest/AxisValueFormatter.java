package com.jdsv650.webviewtest;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;

public class AxisValueFormatter implements IAxisValueFormatter {

    private ArrayList<String> mValues;

    public AxisValueFormatter(ArrayList<String> values) {
        mValues = values;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)

        Integer posAsInt = Math.round(value);
        return mValues.get(posAsInt);
    }
}
