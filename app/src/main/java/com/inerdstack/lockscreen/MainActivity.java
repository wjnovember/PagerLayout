package com.inerdstack.lockscreen;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class MainActivity extends AppCompatActivity {

    private XRecyclerView mRecyclerView;
    private PagerLayout myLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (XRecyclerView) findViewById(R.id.xrecyclerview);
        myLinearLayout = (PagerLayout) findViewById(R.id.lock_view);

        myLinearLayout.setLayout(this, R.layout.slide_layout);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyListAdapter(this));

        mRecyclerView.setPullRefreshEnabled(true);
        mRecyclerView.setLoadingMoreEnabled(true);
        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        myLinearLayout.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        }, 500);
                        mRecyclerView.refreshComplete();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore() {new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mRecyclerView.loadMoreComplete();
                }
            }, 500);
            }
        });


        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final float[] downY = {0};
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downY[0] = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        float curY = event.getY();
                        float delta = curY - downY[0];
                        int screen = DensityUtil.getWindowHeight(MainActivity.this);
                        if (delta > screen - DensityUtil.dip2px(MainActivity.this, 240)) {
                            myLinearLayout.show();
                        }
                        break;
                }
                return false;
            }
        });
    }
}
