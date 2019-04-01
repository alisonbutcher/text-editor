package com.company;


import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;

/**
 * File input/output and file open/save dialog
 */
public class FileIO {

    private String fileName;
    private String filePath;


    public FileIO () {}

    /**
     * Reads string from a text based file on disk
     * @param filePath contains location of file
     * @return string containing file data
     */
    private String ReadFile(String filePath) {


        String data = "";
        byte[] bytes;


        // Perform File Open / Read (new Java 8 style)
        try {
            bytes = Files.readAllBytes(Paths.get(filePath));
            data = new String(bytes, "UTF-8");


        } catch (IOException ioe) {

            JOptionPane.showMessageDialog(null, "In Catch Block");

            // TODO: Error saving file message box

        }
        return data;
    }

    /**
     * Writes string to text file on disk
     * @param filePath location of where to save file
     * @param fileData is a string containing the data to save
     */
    private void WriteFile(String filePath, String fileData) {

        // Put string into byte array
        byte[] data = fileData.getBytes();


        // Perform File Save (new Java 8 style)
        try {

            Files.write(Paths.get(filePath), data);

        } catch (IOException ioe) {

            JOptionPane.showMessageDialog(null, "Error Writing file");
        }

    }

    /**
     * Gets file name
     * @return filename string
     */
    public String getFilename() {
        return fileName;
    }

    /**
     * Gets file path
     * @return filepath string
     */
    public String getFilePath() {
        return filePath;
    }



    /**
     * Provides an Open file Dialog
     * @param parentWindow is the main application window
     * @return String representing entire contents of file
     */
    public String openDialog(JFrame parentWindow) {
        JFileChooser chooser = new JFileChooser();
        String data = "";
        int result = chooser.showOpenDialog(parentWindow);

        if (result == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getName();
            filePath = chooser.getSelectedFile().getPath();
            FileIO in = new FileIO();
            data = in.ReadFile(filePath);
        }

        return data;
    }

    /**
     * Save file dialog
     *
     * @param parentWindow The window that is calling this method
     * @param data represents string to save to file
     * @return The file and path selected in the dialog
     */
    public boolean saveDialog(JFrame parentWindow, String data) {
        JFileChooser chooser = new JFileChooser();

        int result = chooser.showSaveDialog(parentWindow);

        if (result == JFileChooser.APPROVE_OPTION) {
            fileName = chooser.getSelectedFile().getName();
            filePath = chooser.getSelectedFile().getPath();
            FileIO out = new FileIO();
            out.WriteFile(filePath, data);
        }

        return true;  // TODO: Return based on what happens
    }

}
