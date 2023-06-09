package controller;
import javafx.fxml.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.input.*;
import service.*;
import gui.MainScreen;
import service.implement.*;
import data.util.UrlContainer;


import java.util.*;

public class DetailController {
    private VBox pane;
    @FXML
    private ScrollPane detail;
    @FXML
    private VBox box;
    @FXML
    private Label name;
    private String url;
    private CharacterService characterService = CharacterServiceImp.getInstance();
    private DynastyService dynastyService = DynastyServiceImp.getInstance();
    private EventService eventService = EventServiceImp.getInstance();
    private SiteService siteService = SiteServiceImp.getInstance();
    private FestivalService festivalService = FestivalServiceImp.getInstance();
    @FXML
    public void pressBackBtn(MouseEvent event){
        MainScreen.back();
    }
    @FXML
    public void goHome(MouseEvent event) {
        MainScreen.callUrl(UrlContainer.HOME_URL);
    }
    @FXML
    public void test(MouseEvent event) {
        MainScreen.callUrl("/nhan-vat/An Dương Vương");
    }
    public DetailController(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/detail.fxml"));
        loader.setController(this);
        try {
            pane = loader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }
    public void loadInfo(){
        Map<String,Object> value = new HashMap<>();
        if(url.contains(UrlContainer.NHAN_VAT_URL)) value = characterService.getCharacterByName(name.getText());
        else if(url.contains(UrlContainer.SU_KIEN_URL)) value = eventService.getEventByName(name.getText());
        else if(url.contains(UrlContainer.THOI_KY_URL)) value = dynastyService.getDynastyByName(name.getText());
        else if(url.contains(UrlContainer.DIA_DANH_URL)) value = siteService.getSiteByName(name.getText());
        else if(url.contains(UrlContainer.LE_HOI_URL)) value = festivalService.getFestivalByName(name.getText());
        for(Map.Entry<String,Object> m : value.entrySet()){
            if(m.getKey().equals("name")) continue;
            InfoController controller = new InfoController();
            controller.add(box);
            String urlType = "";
            // No anchor link
            if(m.getKey().equals("namSinh")) controller.setTitle("Năm sinh");
            else if(m.getKey().equals("namMat")) controller.setTitle("Năm mất");
            else if(m.getKey().equals("desc")){
                if(url.contains(UrlContainer.NHAN_VAT_URL)) controller.setTitle("Thân thế và sự nghiệp");
                else if(url.contains(UrlContainer.SU_KIEN_URL)) controller.setTitle("Diễn biến lịch sử");
                else controller.setTitle("Tổng quan");
            }
            else if(m.getKey().equals("time")){
                if(url.contains(UrlContainer.THOI_KY_URL)) controller.setTitle("Niên đại");
                else if(url.contains(UrlContainer.SU_KIEN_URL) || url.contains(UrlContainer.LE_HOI_URL)) controller.setTitle("Thời gian diễn ra");
            }
            else if(m.getKey().equals("location"))controller.setTitle("Địa điểm");
            // Have anchor link
            if (m.getKey().equals("kings")) {
                controller.setTitle("Các đời vua");
                urlType = UrlContainer.NHAN_VAT_URL;
            } else if (m.getKey().equals("relativeChar")) {
                controller.setTitle("Nhân vật liên quan");
                urlType = UrlContainer.NHAN_VAT_URL;
            } else if (m.getKey().equals("relativeSite")) {
                controller.setTitle("Địa danh liên quan");
                urlType = UrlContainer.DIA_DANH_URL;
            } else if (m.getKey().equals("relatedEvents")) {
                controller.setTitle("Sự kiện liên quan");
                urlType = UrlContainer.SU_KIEN_URL;
            } else if (m.getKey().equals("relatedCharacters")) {
                controller.setTitle("Nhân vật liên quan");
                urlType = UrlContainer.NHAN_VAT_URL;
            }
            if(urlType.isEmpty()) controller.setValue(m.getValue());
            else controller.setValue(m.getValue(),urlType);
        }
    }
    public void add(VBox box){
        box.getChildren().add(pane);
    }
    public void setUrl(String url){
        this.url = url;
    }
    public void setName(String s){
        name.setText(s);
    }

}
