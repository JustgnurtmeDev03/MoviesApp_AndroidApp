package com.example.movieapp.Domain;

public class SliderItems {

    //Khai báo một trường chứa giá trị hình ảnh
    private int image;

    // Khai báo một constructor
    public SliderItems(int image) {
        this.image = image;
    }

    // Khai báo phương thức cho phép bên ngoài đọc giá trị của trường image
    public int getImage() {
        return image;
    }

    // Khai báo phương thức để thiết lập giá trị cho trường image
    public void setImage(int image) {
        this.image = image;
    }
}
