package src.com.ia;

public class Main {

    public static void main(String[] args) {
        GenView genView = new GenView();
        GenModel genModel = new GenModel();
        GenController genController = new GenController(genView, genModel);
        genView.setVisible(true);
    }
}
