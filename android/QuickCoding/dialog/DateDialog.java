package com.hchl.financeteam.ui.dialog;

import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public abstract class DateDialog {
    private DatePickerDialog dialog;
    public DateDialog(Context mContext){
        init(mContext);
    }

    private void init(Context mContext){
        dialog = new DatePickerDialog(mContext,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar cal = Calendar.getInstance();
                        cal.set(year, monthOfYear, dayOfMonth);
                        onPositiveButton(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth, String.valueOf(cal.getTimeInMillis()));
                    }
                }, 2015,
                7, 1);
    }

    public void show(){
        dialog.show();
    }

    public abstract void onPositiveButton(String date, String timeInMillis);
}
