package com.example.home_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private IntroViewPagerAdapter mPagerAdapter;
    private LinearLayout mDotsLayout;
    private TextView[] mDots;
    private int[] mLayouts;
    private Button mBtnPre;
    private Button mBtnNext;
    private Button mBtnCat1;
    private Button mBtnCat2;
    private Button mBtnCat3;
    private Button mBtnCat4;
    private Button mBtnCat5;
    private PrefManager mPrefManager;
    private Product[] _data;
    private ImageView img_1;
    private TextView text_1;
    private ImageView img_2;
    private TextView text_2;
    private ImageView img_3;
    private TextView text_3;
    private ImageView img_4;
    private TextView text_4;
    private ImageView img_5;
    private TextView text_5;
    private ImageView img_6;
    private TextView text_6;
    private ImageView img_7;
    private TextView text_7;
    private ImageView img_8;
    private TextView text_8;
    private ImageView img_9;
    private TextView text_9;
    private ImageView img_10;
    private TextView text_10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _data = data.products_1;
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mDotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        mBtnPre = (Button) findViewById(R.id.btn_pre);
        mBtnNext = (Button) findViewById(R.id.btn_next);

        //danh muc
        mBtnCat1 = (Button) findViewById(R.id.cat_1);
        mBtnCat2 = (Button) findViewById(R.id.cat_2);
        mBtnCat3 = (Button) findViewById(R.id.cat_3);
        mBtnCat4 = (Button) findViewById(R.id.cat_4);
        mBtnCat5 = (Button) findViewById(R.id.cat_5);

        //san pham
        img_1 = (ImageView) findViewById(R.id.img_1);
        text_1 = (TextView) findViewById(R.id.text_1);
        img_2 = (ImageView) findViewById(R.id.img_2);
        text_2 = (TextView) findViewById(R.id.text_2);
        img_3 = (ImageView) findViewById(R.id.img_3);
        text_3 = (TextView) findViewById(R.id.text_3);
        img_4 = (ImageView) findViewById(R.id.img_4);
        text_4 = (TextView) findViewById(R.id.text_4);
        img_5 = (ImageView) findViewById(R.id.img_5);
        text_5 = (TextView) findViewById(R.id.text_5);
        img_6 = (ImageView) findViewById(R.id.img_6);
        text_6 = (TextView) findViewById(R.id.text_6);
        img_7 = (ImageView) findViewById(R.id.img_7);
        text_7 = (TextView) findViewById(R.id.text_7);
        img_8 = (ImageView) findViewById(R.id.img_8);
        text_8 = (TextView) findViewById(R.id.text_8);
        img_9 = (ImageView) findViewById(R.id.img_9);
        text_9 = (TextView) findViewById(R.id.text_9);
        img_10 = (ImageView) findViewById(R.id.img_10);
        text_10 = (TextView) findViewById(R.id.text_10);

        //đổ dữ liệu
        loadData();

        /**
         * Layouts of all welcome slides
         * add few more layouts if you want
         */
        mLayouts = new int[]{R.layout.image_slide,R.layout.image_slide,R.layout.image_slide,R.layout.image_slide};
        addBottomDots(0);
        changeStatusBarColor();

        mPagerAdapter = new IntroViewPagerAdapter();
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(mViewPagerChangeListener);

        mBtnCat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _data = data.products_1;
                loadData();
            }
        });


        mBtnCat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _data = data.products_2;
                loadData();
            }
        });


        mBtnCat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _data = data.products_3;
                loadData();
            }
        });


        mBtnCat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _data = data.products_4;
                loadData();
            }
        });


        mBtnCat5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _data = data.products_5;
                loadData();
            }
        });

        mBtnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Checking for last page if last page home screen will be launched
                 */
                int current = mViewPager.getCurrentItem() - 1;
                if (current < 0) {
                    // move to nex screen
                    mViewPager.setCurrentItem(mLayouts.length-1);
                } else {
                    mViewPager.setCurrentItem(current);
                }
            }
        });

        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 * Checking for last page if last page home screen will be launched
                 */
                int current = mViewPager.getCurrentItem() + 1;
                if (current < mLayouts.length) {
                    // move to nex screen
                    mViewPager.setCurrentItem(current);
                } else {
                    mViewPager.setCurrentItem(0);
                }
            }
        });
    }
    private ViewPager.OnPageChangeListener mViewPagerChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);
            //Change the next button text 'NEXT'/'GOT IT'
            mBtnNext.setText(getString(R.string.next));
            mBtnPre.setText(getString(R.string.pre));
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private void addBottomDots(int currentPage) {
        mDots = new TextView[mLayouts.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInActive = getResources().getIntArray(R.array.array_dot_inactive);

        mDotsLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("•"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(colorsInActive[currentPage]);
            mDotsLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[currentPage].setTextColor(colorsActive[currentPage]);
        }
    }

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    private int getItem(int i) {
        return mViewPager.getCurrentItem() + i;
    }

    private void loadData(){
        Picasso.get().load(_data[0].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_1);
        text_1.setText(_data[0].name);
        Picasso.get().load(_data[1].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_2);
        text_2.setText(_data[1].name);
        Picasso.get().load(_data[2].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_3);
        text_3.setText(_data[2].name);
        Picasso.get().load(_data[3].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_4);
        text_4.setText(_data[3].name);
        Picasso.get().load(_data[4].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_5);
        text_5.setText(_data[4].name);
        Picasso.get().load(_data[5].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_6);
        text_6.setText(_data[5].name);
        Picasso.get().load(_data[6].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_7);
        text_7.setText(_data[6].name);
        Picasso.get().load(_data[7].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_8);
        text_8.setText(_data[7].name);
        Picasso.get().load(_data[8].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_9);
        text_9.setText(_data[8].name);
        Picasso.get().load(_data[9].image).resize(100,100).error(R.drawable.ic_launcher_background).into(img_10);
        text_10.setText(_data[9].name);
    }

    public class IntroViewPagerAdapter extends PagerAdapter {
        private LayoutInflater mInflater;

        public IntroViewPagerAdapter() {
            super();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            mInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(mLayouts[position], container, false);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mLayouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}