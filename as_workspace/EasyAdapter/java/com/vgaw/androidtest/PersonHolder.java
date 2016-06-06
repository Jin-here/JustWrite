package com.vgaw.androidtest;

import android.view.View;
import android.widget.TextView;

/**
 * from : Volodymyr
 * to : caojinmail@163.com
 * me : github.com/VolodymyrCj/
 */
public class PersonHolder extends EasyHolder {
    protected TextView tv_name;
    protected TextView tv_sex;

    @Override
    public int getLayout() {
        return R.layout.item_person;
    }

    @Override
    public View createView() {
        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_sex = (TextView) view.findViewById(R.id.tv_sex);
        return view;
    }

    @Override
    public void refreshView(Object item) {
        Person person = (Person) item;
        tv_name.setText(person.getName());
        tv_sex.setText(person.getSex());
    }
}
