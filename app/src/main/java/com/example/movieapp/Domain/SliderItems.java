package com.example.movieapp.Domain;

public class SliderItems {

    //Khai báo một trường chứa giá trị hình ảnh
    private int image;

    
    public SliderItems(int image) {
        this.image = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
