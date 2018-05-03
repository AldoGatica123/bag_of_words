package src;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GenController {

    private LogEntry logger;
    private GenView theView;
    private GenModel theModel;

    GenController(GenView theView, GenModel theModel) {
        this.theView = theView;
        this.theModel = theModel;
        this.logger = theView;

        this.theView.addButtonListener(new ButtonListener());
        this.theView.addMenuListener(new SettingsListener());
        startProgram();
    }

    private void startProgram(){
        logger.log("Starting program...");
        theModel.loadProperties();
        MongoDB db = initMongo();
        MLMagic magic = new MLMagic(db);
    }

    private MongoDB initMongo(){
        logger.log("MongoClient connecting to: " + theModel.getDBName() + " database");
        return new MongoDB(theModel.getDBName());
    }




    public interface LogEntry{
        void log(String logMessage);

        void displayErrorMessage(String errorMessage);
    }

    class ButtonListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String filePath;

            filePath = theView.getFilePath();
            if (!filePath.equals("")){
                theModel.setFilePath(filePath);
                theView.setFilePath(theModel.getFilePath());
                logger.log("Filepath changed to: ".concat(filePath));
            }
            else {
                logger.displayErrorMessage("Filepath is empty");
            }
        }
    }

    class SettingsListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {

        }
    }

}