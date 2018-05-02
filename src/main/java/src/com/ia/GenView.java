package src.com.ia;

import javax.swing.*;
import java.awt.*;

public class GenView extends JFrame{
    private JTextField filePathTF;
    private JButton fileReadB;
    private JTextField logsTF;
    private JTextField phraseTF;
    private JButton classifyB;

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
        logsTF = new JTextField();
        phraseTF = new JTextField();
        classifyB = new JButton("Classify");

        northPanel.add(BorderLayout.CENTER, filePathTF);
        northPanel.add(BorderLayout.EAST, fileReadB);
        centerPanel.add(BorderLayout.CENTER, logsTF);
        southPanel.add(BorderLayout.CENTER, phraseTF);
        southPanel.add(BorderLayout.EAST, classifyB);

        parentPanel.add(BorderLayout.NORTH, northPanel);
        parentPanel.add(BorderLayout.CENTER, centerPanel);
        parentPanel.add(BorderLayout.SOUTH, southPanel);

        this.add(parentPanel);
    }

    public String getFilePath() {
        return filePathTF.getText();
    }

    public void setFilePath(String filePath) {
        this.filePathTF.setText(filePath);
    }

    public void addSetButtonListener(GenController.ButtonListener buttonListener) {
        this.fileReadB.addActionListener(buttonListener);
    }


    public void displayErrorMessage(String errorMessage){
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
