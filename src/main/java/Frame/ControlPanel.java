package Frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
public class ControlPanel extends JPanel {
    final MainFrame frame;

    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton resetBtn = new JButton("Reset");
    JButton exitBtn = new JButton("Exit");

    //create all buttons (Load, Reset, Exit)


    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }
    private void init() {
        this.add(loadBtn);
        this.add(saveBtn);
        this.add(resetBtn);
        this.add(exitBtn);

        //configure listeners for all buttons
        loadBtn.addActionListener( this::load );
        saveBtn.addActionListener(this::save);
        resetBtn.addActionListener(this::reset);
        exitBtn.addActionListener( this::exit );

    }


    private void load(ActionEvent e) {
        JFileChooser chooser;
        String chooserTitle = "Select File";
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(chooserTitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }


        try {
            frame.canvas.image = ImageIO.read(chooser.getSelectedFile());
        } catch (IOException ex) { System.err.println(ex); }
        frame.canvas.update(frame.canvas.graphics);
    }

    private void save(ActionEvent e) {
        JFileChooser chooser;
        String chooserTitle = "Select Save Location";
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(chooserTitle);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            System.out.println("getCurrentDirectory(): "
                    +  chooser.getCurrentDirectory());
            System.out.println("getSelectedFile() : "
                    +  chooser.getSelectedFile());
        }
        else {
            System.out.println("No Selection ");
        }

        try {
            ImageIO.write(frame.canvas.image, "PNG",
                    chooser.getSelectedFile());
        } catch (IOException ex) { System.err.println(ex); }

    }

    private void reset(ActionEvent e) {
        frame.dispose();
        new MainFrame().setVisible(true);
    }
    private void exit(ActionEvent e) {
        frame.dispose();
    }


}
