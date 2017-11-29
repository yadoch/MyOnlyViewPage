package tw.com.abc.myonlyviewpage;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ViewPager viewPager;
    ArrayList<View> views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);

        initViewPager();
    }

    private void initViewPager() {
//        浮動視窗-由此Activty 自己創造 inflater
        //之前 在 Fragment 中的F1 onCreateView 中傳入值 inflater 可以直接操作,但此範例為MainActivity 沒有 inflater 所以要自己創造
        //不用new ,因為 一個 Context 只能有一個LayoutInflater
        LayoutInflater inflater = LayoutInflater.from(this);

        views=new ArrayList<View>();
        View page1=inflater.inflate(R.layout.page1,null);
        View page2=inflater.inflate(R.layout.page2,null);
        View page3=inflater.inflate(R.layout.page3,null);

        views.add(page1);
        views.add(page2);
        views.add(page3);

        viewPager.setAdapter( new MyPagerAdapter());
       // View tv1=(View)page1.findViewById(R.id.tv1);
    }

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return false;
        }
        // 另外新加入
        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = views.get(position);
            viewPager.addView(view);
            return view;
            //return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //super.destroyItem(container, position, object);
            View view = views.get(position);
            viewPager.removeView(view);
        }
    }
}
