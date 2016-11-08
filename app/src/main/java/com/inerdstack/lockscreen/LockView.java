package com.inerdstack.lockscreen;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;

/**
 * Created by wangjie on 2016/11/8.
 */
public class LockView extends LinearLayout {

    private float mLastY = 0;
    private float mCurY = 0;
    private Scroller mScroller;
    private int viewHeight;

    public LockView(Context context) {
        this(context, null);
    }

    public LockView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        View view = LayoutInflater.from(context).inflate(R.layout.lock_view, this, false);
        this.addView(view);
        mScroller = new Scroller(context);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight = MeasureSpec.getSize(heightMeasureSpec);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float deltaY = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastY = event.getY();
                Log.i("lockc", "init y--" + mLastY);
                break;
            case MotionEvent.ACTION_MOVE:
                float y = this.getY();
                mCurY = event.getY();
                deltaY = mCurY - mLastY;
                if (deltaY < 0 || getScrollY() > 0) {
                    this.scrollTo(0, (int) (-1 * deltaY));
                }
                break;
            case MotionEvent.ACTION_UP:
                int scrollY = getScrollY();
                int viewY = (int) this.getY();
                if (scrollY > 300) {
                    Log.i("lockc", "scrollUp");
                    smoothScrollTo(0, viewHeight - scrollY);

                } else {
                    Log.i("lockc", "revert");
                    smoothScrollTo(0, 0);
                }
                invalidate();
                break;
        }


        return true;
    }

    private void smoothScrollTo(int fx, int fy) {
        int dx = fx - mScroller.getFinalX();
        int dy = fy - mScroller.getFinalY();
        smoothScrollBy(dx, dy);
    }

    private void smoothScrollBy(int dx,int dy) {
        mScroller.startScroll(0, this.getScrollY(), dx, dy);
        invalidate();
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        //如果当前正在滚动
        if (mScroller.computeScrollOffset()) {
            //滚动到(x,y)处
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }

}
