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
            // ViewPager 做一些狀態的改變時，為了確認其框架底下的子 View 是否是需被改變的對象，
            // 會一個一個取出現有的子 View 並和其內的 mItems 頁面陣列一個一個比對，
            // 其中會呼叫此方法來判斷子 View 是否為對應的頁面。
            // 如果覺得複雜較難理解，只需一直採用如下寫法就可以。
            return  view == object;
            //return false;
        }
        // 另外新加入
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //傳回畫面處理
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
