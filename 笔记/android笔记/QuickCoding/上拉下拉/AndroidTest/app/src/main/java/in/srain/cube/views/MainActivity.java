package in.srain.cube.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.vgaw.androidtest.R;

import java.util.ArrayList;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

/**
 * 下拉参考：https://github.com/liaohuqiu/android-Ultra-Pull-To-Refresh
 * 上拉参考：https://github.com/liaohuqiu/android-cube-app
 */
public class MainActivity extends AppCompatActivity {

    private ListView load_more_small_image_list_view;

    final String[] mStringList = {"Loading", "Welcome"};
    private ArrayList<String> dataList = new ArrayList<>();
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addData();
        load_more_small_image_list_view = (ListView) findViewById(R.id.load_more_small_image_list_view);
        adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.test_list_item, dataList);

        final StoreHouseHeader header = new StoreHouseHeader(MainActivity.this);
        header.setPadding(0, 30, 0, 0);
        header.initWithString(mStringList[0]);
        final PtrFrameLayout frame = (PtrFrameLayout) findViewById(R.id.store_house_ptr_frame);
        frame.addPtrUIHandler(new PtrUIHandler() {

            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {
                mLoadTime++;
                String string = mStringList[mLoadTime % mStringList.length];
                header.initWithString(string);
            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {
            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });

        frame.setDurationToCloseHeader(3000);
        frame.setHeaderView(header);
        frame.addPtrUIHandler(header);
        frame.postDelayed(new Runnable() {
            @Override
            public void run() {
                frame.autoRefresh(false);
            }
        }, 100);

        // load more container
        final LoadMoreListViewContainer loadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
        loadMoreListViewContainer.useDefaultFooter();
        load_more_small_image_list_view.setAdapter(adapter);
        loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
            @Override
            public void onLoadMore(LoadMoreContainer loadMoreContainer) {
                addData();

                // load more
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreListViewContainer.loadMoreFinish(false, true);
                        adapter.notifyDataSetChanged();
                    }
                }, 1000);
            }
        });

        frame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, load_more_small_image_list_view, header);
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        frame.refreshComplete();
                        loadMoreListViewContainer.loadMoreFinish(false, true);
                    }
                }, 2000);
            }
        });
    }

    private void addData(){
        for (int i = 0;i < 27; i++) {
            dataList.add(String.valueOf(i));
        }
    }

}
