GridView
1：ArrayAdapter
经典构造：new ArrayAdapter<String>(MainActivity.this, R.layout.grid_item, R.id.tv_icon, list)
如果要自定义布局，可以重写ArrayList中的item的toString()方法（重写该方法只是在item值和显示值之间添加了一层逻辑，item布局没有变），
或者如果要改变布局，可以重写getView()方法，代码如下：
        GridView gv_home = (GridView) findViewById(R.id.gv_home);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i < 9;i++){
            list.add("1");
        }
        gv_home.setAdapter(new MyAdapter<String>(MainActivity.this, R.layout.grid_item, R.id.tv_icon, list));
		
    private class MyAdapter<T> extends ArrayAdapter{
        public MyAdapter(Context context, int resource, int textViewResourceId, List objects) {
            super(context, resource, textViewResourceId, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            ((ImageView)view.findViewById(R.id.iv_icon)).setImageResource(R.mipmap.ic_launcher);
            return view;
        }
    }
	
	布局如下：
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <ImageView
        android:id="@+id/iv_icon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <TextView
        android:id="@+id/tv_icon"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" />
</LinearLayout>	
