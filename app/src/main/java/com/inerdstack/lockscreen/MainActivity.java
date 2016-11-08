package com.inerdstack.lockscreen;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
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

        myLinearLayout.setLayout(this, R.layout.lock_view);
        myLinearLayout.setButtonClickListener(R.id.btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "您点击了按钮", Toast.LENGTH_SHORT).show();
            }
        });

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
                        myLinearLayout.show();
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
    }
}
