package com.example.movieapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.movieapp.Domain.SliderItems;
import com.example.movieapp.R;

import java.util.List;

public class SliderAdapters extends RecyclerView.Adapter<SliderAdapters.SliderViewHolder>{
    private List<SliderItems> sliderItems;
    private ViewPager2 viewPager2;
    Context context;

    // Khởi tạo Adapter(nhận dữ liệu từ danh sách SliderItems hiển thị lên ViewPager2)
    public SliderAdapters(List<SliderItems> sliderItems, ViewPager2 viewPager2) {
        this.sliderItems = sliderItems;
        this.viewPager2 = viewPager2;
    }

    // Tạo ViewHolder đại diện cho mỗi mục của danh sách slider
    @NonNull
    @Override
    public SliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.slide_item_container,parent, false
        ));
        // Tạo View từ XML layout được xác định bởi layout.slide_item_container
    }


    // Cấu hình hiển thị dữ liệu(Thiết lập cấu hình dữ liệu cho ViewHolder được tạo ở trên)
    @Override
    public void onBindViewHolder(@NonNull SliderAdapters.SliderViewHolder holder, int position) {
holder.setImage(sliderItems.get(position));
if(position==sliderItems.size()-2){
    viewPager2.post(runnable);
}
    }


    // Hiển thị dữ liệu - Trả về tổng số lượng mục trong danh sách slider

    //Xác định số lượng mục trong danh sách slider cần hiển thị
    @Override
    public int getItemCount() {
        return sliderItems.size();
    }


    public class SliderViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageSlide);
        }

        // Được gọi từ onBindViewHolder để thiết lập hình ảnh cho ImageView
        void setImage(SliderItems sliderItems){
            // Tạo đối tượng để cấu hình các yêu cầu cho thư viện Glide(được sử dụng để tải và hiển thị hình ảnh)
            RequestOptions requestOptions = new RequestOptions();
            requestOptions=requestOptions.transforms(new CenterCrop(), new RoundedCorners(60));

            //Sử dụng thư viện Glide để tải và hiển thị hình ảnh
            Glide.with(context)
                    .load(sliderItems.getImage())
                    .apply(requestOptions)
                    .into(imageView);
        }
    }

    // Tạo ra hiệu ứng vòng lặp vô hạn trong ViewPager2.
    private Runnable runnable = new Runnable() {
        @Override
        // Phương thức run được gọi khi Runnable được chạy
        public void run() {
            sliderItems.addAll(sliderItems);
            notifyDataSetChanged();
        }
    };
}
