package src;

import javax.swing.*;
import java.awt.*;

public class GenView extends JFrame implements GenController.LogEntry {
    private JTextField filePathTF;
    private JButton fileReadB;
    private JTextArea logsTF;
    private JTextField inputTF;
    private JButton classifyB;
    private JMenuItem settingsItem;
    private JMenuItem xItem;

    GenView() {
        initComponents();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(800, 400);
    }

    private void initComponents(){
        JPanel parentPanel = new JPanel();
        JPanel northPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel southPanel = new JPanel();

        parentPanel.setLayout(new BorderLayout());
        northPanel.setLayout(new BorderLayout());
        centerPanel.setLayout(new BorderLayout());
        southPanel.setLayout(new BorderLayout());

        filePathTF = new JTextField();
        fileReadB = new JButton("Run");
        logsTF = new JTextArea();
        logsTF.setEditable(false);
        inputTF = new JTextField();
        classifyB = new JButton("Classify");

        northPanel.add(BorderLayout.CENTER, filePathTF);
        northPanel.add(BorderLayout.EAST, fileReadB);
        centerPanel.add(BorderLayout.CENTER, logsTF);
        southPanel.add(BorderLayout.CENTER, inputTF);
        southPanel.add(BorderLayout.EAST, classifyB);

        parentPanel.add(BorderLayout.NORTH, northPanel);
        parentPanel.add(BorderLayout.CENTER, centerPanel);
        parentPanel.add(BorderLayout.SOUTH, southPanel);

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Settings");
        settingsItem = new JMenuItem("Change app properties");
        xItem = new JMenuItem("X");
        menu.add(settingsItem);
        menu.add(xItem);
        menubar.add(menu);
        this.setJMenuBar(menubar);

        this.add(parentPanel);
    }

    String getFilePath() {
        return filePathTF.getText();
    }

    void setFilePath(String filePath) {
        this.filePathTF.setText(filePath);
    }

    void addButtonListener(GenController.ButtonListener buttonListener) {
        this.fileReadB.addActionListener(buttonListener);
    }

    void addMenuListener(GenController.SettingsListener settingsListener) {
        this.settingsItem.addActionListener(settingsListener);
    }

    @Override
    public void displayErrorMessage(String errorMessage){
        errorMessage = " - ERROR: ".concat(errorMessage);
        log(errorMessage);
    }

    @Override
    public void log(String logMessage) {
        logsTF.append("\n" + logMessage);
    }
}
