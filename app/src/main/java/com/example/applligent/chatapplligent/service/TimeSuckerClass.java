package com.example.applligent.chatapplligent.service;

import android.app.TimePickerDialog;
import android.widget.TimePicker;

public class TimeSuckerClass implements TimePickerDialog.OnTimeSetListener {
    public int x;
    public int y;
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
           x= hourOfDay;
           y=minute;
    }
}
