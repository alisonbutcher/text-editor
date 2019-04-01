package com.company;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.*;

public class GuiApplication implements ActionListener, ItemListener{

    /**
     * Title of the text editor
     */
    private static final String APP_NAME = "Text Editor";

    /**
     * Main application frame
     */
    private JFrame app;

    /**
     * Main text editor area
     */
    private JTextPane editor;

    /**
     * File Handler
     */
    private FileIO io;


    /**
     * Generates and displays GUI for the application
     */
    public GuiApplication() {

        // Main App
        app = new JFrame(APP_NAME);

        // Editor window
        editor = new JTextPane();
        editor.setContentType("text/plain");
        editor.setDocument(new DefaultStyledDocument());
        editor.setFont(new Font("Unifont", Font.PLAIN, 16));

        // Scrolling Pane
        JScrollPane editorScrollPane = new JScrollPane(editor);

        // Attach editor/scrollbar to the application
        app.add(editorScrollPane, BorderLayout.CENTER);

        // Add top menu bar to App
        app.setJMenuBar(TopMenu());


        // Configure App
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(990, 480);
        app.setLocation(150, 90);

        // Display
        app.setVisible(true);

        // Get FileIO Handler
        io = new FileIO();

    }


    /**
     *  Configures and creates the menu system for the main app
     *
     * @return JMenuBar containing all the menu items
     */
    private JMenuBar TopMenu() {


        /**
         * Main Menu bar for the application
         */
        JMenuBar menuBar;


        /**
         * Top level items of menu bar
         */
        JMenu fileMenu, editMenu, viewMenu, helpMenu;


        /**
         * Menu items of the File menu
         */
        JMenuItem mnuOpen, mnuClose, mnuSave, mnuPrint, mnuExit;


        /**
         * Menu items of the Edit menu
         */
        JMenuItem mnuSearch;


        /**
         * Menu items of the View menu
         */
        JMenuItem mnuShowSymbols;


        /**
         * Menu items of the Help menu
         */
        JMenuItem mnuAbout;


        // Configure Top level menus
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        viewMenu = new JMenu("View");
        helpMenu = new JMenu("Help");


        // Open Menu Item
        mnuOpen = new JMenuItem("Open", KeyEvent.VK_O);
        mnuOpen.getAccessibleContext().setAccessibleDescription("Open a new file");
        mnuOpen.addActionListener(this);
        fileMenu.add(mnuOpen);


        // Close Menu Item
        mnuClose = new JMenuItem("Close", KeyEvent.VK_W);
        mnuClose.getAccessibleContext().setAccessibleDescription("Close the file");
        mnuClose.addActionListener(this);
        fileMenu.add(mnuClose);


        // Save Menu Item
        mnuSave = new JMenuItem("Save", KeyEvent.VK_S);
        mnuSave.getAccessibleContext().setAccessibleDescription("Save file");
        mnuSave.addActionListener(this);
        fileMenu.add(mnuSave);


        // Print Menu Item
        mnuPrint = new JMenuItem("Print", KeyEvent.VK_P);
        mnuPrint.getAccessibleContext().setAccessibleDescription("Print");
        mnuPrint.addActionListener(this);
        fileMenu.add(mnuPrint);


        // Quit Menu Item
        mnuExit = new JMenuItem("Quit", KeyEvent.VK_Q);
        mnuExit.getAccessibleContext().setAccessibleDescription("Quit");
        mnuExit.addActionListener(this);
        fileMenu.add(mnuExit);


        // Search Menu Item
        mnuSearch = new JMenuItem("Find/Replace", KeyEvent.VK_F);
        mnuSearch.getAccessibleContext().setAccessibleDescription("Find/Replace");
        mnuSearch.addActionListener(this);
        editMenu.add(mnuSearch);

        // Show / Hide special characters
        mnuShowSymbols = new JCheckBoxMenuItem("Show/Hide Symbols");
        mnuShowSymbols.setMnemonic(KeyEvent.VK_G);
        mnuShowSymbols.getAccessibleContext().setAccessibleDescription("Show/Hide Symbols");
        mnuShowSymbols.addItemListener(this);
        viewMenu.add(mnuShowSymbols);


        // About Menu Item
        mnuAbout = new JMenuItem("About");
        mnuAbout.getAccessibleContext().setAccessibleDescription("About");
        mnuAbout.addActionListener(this);
        helpMenu.add(mnuAbout);


        // Create menuBar
        menuBar = new JMenuBar();


        // Add Menus to Menu bar
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);
        menuBar.add(helpMenu);


        return menuBar;
    }


    /**
     * Displays the print dialog
     * @param editorWindow contains data to print from editor
     */
    private void printDialog(JTextPane editorWindow) {
        try {
            boolean tr = editorWindow.print();
        } catch (Exception pex){
            JOptionPane.showMessageDialog(app, "Print Error: " + pex);
        }
    }


    /**
     * Displays about dialog
     *
     * @param parentWindow of the dialog
     */
    private void aboutDialog(JFrame parentWindow) {
        String msg = "Text Editor\nVersion: 1.0\n";
        msg += "Author: Alison Butcher\n\n";
        msg += "A project for 1801ICT Programming Languages\n";

        JOptionPane.showMessageDialog(parentWindow, msg);
    }


    /**
     * Handler for open file menu option
     */
    private void openFile() {
        editor.setText(io.openDialog(app));
    }


    /**
     * Handler for save file menu option
     */
    private void saveFile() {
        io.saveDialog(app, editor.getText());
    }


    /**
     * Handler for editor close menu option
     */
    private void editorClose() {
        editor.setText("");
    }


    /**
     * Handler for application close menu option
     */
    private void appClose() {
        System.exit(0);
    }


    /**
     * Handler for Find / Replace menu option
     */
    private void findReplace() {
        Search s = new Search(editor);
    }


    /**
     *
     * Responds to menu events
     *
     * @param e event
     */
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case"Open":
                openFile();
                break;
            case "Save":
                saveFile();
                break;
            case "Print":
                printDialog(editor);
                break;
            case "Quit":
                appClose();
                break;
            case "Close":
                editorClose();
                break;
            case "Find/Replace":
                findReplace();
                break;
            case "About":
                aboutDialog(app);
                break;
            default:
                break;

        }
    }


    /**
     * Responds to Show/Hide Symbols menu
     * @param e event
     */
    public void itemStateChanged(ItemEvent e) {
        Unicode u = new Unicode();

        if (e.getStateChange() == 2) {
            editor.setText(u.hideUnicode(editor.getText()));
        } else {
            editor.setText(u.showUnicode(editor.getText()));
        }
    }

}
