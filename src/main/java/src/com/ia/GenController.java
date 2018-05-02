package src.com.ia;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenController {

    private GenView theView;
    private GenModel theModel;

    public GenController(GenView theView, GenModel theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.addSetButtonListener(new ButtonListener());
    }

    class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String filePath = "";

            filePath = theView.getFilePath();
            if (!filePath.equals("")){
                theModel.setFilePath(filePath);

                theView.setFilePath(theModel.getFilePath());
            }
            else {
                theView.displayErrorMessage("Filepath is empty");
            }

        }

    }

}