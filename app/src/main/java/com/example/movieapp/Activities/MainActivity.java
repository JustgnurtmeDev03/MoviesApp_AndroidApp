package com.example.movieapp.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
//Thư viện hỗ trợ danh sách cuộn trong ứng dụng
import androidx.recyclerview.widget.RecyclerView;
//Thư viện hỗ trợ các trang trượt trong ứng dụng
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.movieapp.Adapters.SliderAdapters;
import com.example.movieapp.Domain.SliderItems;
import com.example.movieapp.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
// Khai Báo Biến
private ViewPager2 viewPager2;
private Handler slideHandler= new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Gọi Phương Thức
        initView();
        banners();
    }

    private void banners() {
        // Khởi tạo danh sách các hình ảnh trong slider
        List<SliderItems> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItems(R.drawable.f));
        sliderItems.add(new SliderItems(R.drawable.f1));
        sliderItems.add(new SliderItems(R.drawable.f2));
        sliderItems.add(new SliderItems(R.drawable.f3));
        sliderItems.add(new SliderItems(R.drawable.f4));

     // Thiết lập Adapter cho ViewPager2
    viewPager2.setAdapter(new SliderAdapters(sliderItems, viewPager2));

    // Cấu hình các thuộc tính cho ViewPager2
    viewPager2.setClipToPadding(false);
    viewPager2.setOffscreenPageLimit(5);
    viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);

        //Tạo và thiết lập đối tượng chuyển trang
        CompositePageTransformer compositePageTransformer= new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r=1-Math.abs(position);
                page.setScaleY(0.85f+r*0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);
        viewPager2.setCurrentItem(1);
        // Đăng ký một callback để theo dõi sự kiện khi chuyển đổi trang
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                //Sẽ được gọi khi một trang mới được chọn
                super.onPageSelected(position);
                //Loại bỏ các callbacks đang chờ để chuyển đổi trang khi trang mới được chọn
                slideHandler.removeCallbacks(sliderRunnable);
            }
        });
    }

    // Khởi tạo đối tượng cho việc tự động chuyển đổi
    private Runnable sliderRunnable=new Runnable() {
        @Override
        public void run() {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem()+1);
        }
    };


    @Override
    protected void onPause() {
        super.onPause();
        // Loại bỏ callbacks khi Activity mất focus
        slideHandler.removeCallbacks(sliderRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
         // Đặt callback để tự động chuyển dổi slider sau 2 giây khi Activity có focus
        slideHandler.postDelayed(sliderRunnable,2000);
    }

    // Chắc chắn rằng viewPager2 được khởi tạo và tham chiếu đến đúng trong layout R.id.viewpagerSlider
    private void initView() {
        viewPager2=findViewById(R.id.viewpagerSlider);
    }
}