package controller;

import javafx.fxml.*;
import javafx.scene.layout.*;
import util.UrlContainer;

import java.util.*;

public class Controller {
    @FXML
    private VBox layout;
    private Deque<String> urls = new ArrayDeque<>();
    public String getCurrentUrl(){
        return urls.getLast();
    }
    public void addHomePage(){
        MainScreenController controller = new MainScreenController();
        controller.add(layout);
    }
    public void addListPage(String title , String url){
        CustomListController controller = new CustomListController();
        controller.setTitle(title);
        controller.add(layout);
        controller.setUrl(url);
        controller.setOption();
        controller.addList();
    }
    public void addDetailPage(){
        DetailController controller = new DetailController();
        controller.add(layout);
    }
    public void resetScreen(){
        layout.getChildren().clear();
    }

    public void moveToURL(String url , boolean back){
        if(!back) urls.addLast(url);
        resetScreen();
        generateFromUrl(url, "");
    }
    public void back(){
        urls.removeLast();
        if(this.urls.size() > 0) moveToURL(urls.getLast(),true);
    }

    public void generateFromUrl(String url , String searchVal){
        // Home
        if(url.equalsIgnoreCase(UrlContainer.HOME_URL)) addHomePage();
        // List and Detail
        if(url.equalsIgnoreCase(UrlContainer.NHAN_VAT_URL)) addListPage("Nhân vật lịch sử",UrlContainer.NHAN_VAT_URL );
        else if(url.contains(UrlContainer.NHAN_VAT_URL)) addDetailPage();

        if(url.equalsIgnoreCase(UrlContainer.THOI_KY_URL)) addListPage("Thời kỳ lịch sử",UrlContainer.THOI_KY_URL);
        else if(url.contains(UrlContainer.THOI_KY_URL)) addDetailPage();

        if(url.equalsIgnoreCase(UrlContainer.SU_KIEN_URL)) addListPage("Sự kiện lịch sử",UrlContainer.SU_KIEN_URL);
        else if(url.contains(UrlContainer.SU_KIEN_URL)) addDetailPage();

        if(url.equalsIgnoreCase(UrlContainer.DIA_DANH_URL)) addListPage("Địa điểm lịch sử",UrlContainer.DIA_DANH_URL);
        else if(url.contains(UrlContainer.DIA_DANH_URL)) addDetailPage();

//        if(url.equalsIgnoreCase(UrlContainer.LE_HOI_URL)) addListPage("Lễ hội văn hóa");
//        else if(url.contains(UrlContainer.LE_HOI_URL)) addDetailPage();


    }

}