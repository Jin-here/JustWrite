package com.hchl.financeteam.ui.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.TimePicker;

import com.hchl.financeteam.utils.Utils;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public abstract class TimeDialog {
    private Dialog dialog;

    public TimeDialog(Context context, int hour, int minute){
        dialog = new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                onPositiveButton(Utils.addZero(hourOfDay) + ":" + Utils.addZero(minute) + ":00", hourOfDay, minute);
            }
        }, hour, minute, true);
    }

    public void show(){
        dialog.show();
    }

    public abstract void onPositiveButton(String formatTime, int hour, int minute);
}
