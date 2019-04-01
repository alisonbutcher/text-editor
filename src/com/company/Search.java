package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Search/Replace functions and GUI
 */
public class Search implements ActionListener {

    private JButton btnFind, btnFindNext, btnReplace, btnReplaceAll;

    private JTextField txtFind, txtReplace;

    private JLabel lblFind, lblReplace;

    private JDialog search;

    /**
     * GUI for Search and Replace
     */
    private JTextPane searchUI;

    /**
     * JTextPane contains content of editor in main window
     */
    private JTextPane editorText;

    /**
     * Search Constructor
     * a
     * @param editor a JTextPane representing text editor content
     */
    public Search(JTextPane editor) {

        this.editorText = editor;

        search = new JDialog();
        search.setTitle("Find/Replace");

        searchUI = new JTextPane();
        searchUI.setBackground(Color.LIGHT_GRAY);

        // Configure Find / Replace UI
        search.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        search.setSize(350, 170);
        search.setLocation(350, 190);
        search.setResizable(false);

        // Configure Find Button
        btnFind = new JButton("Find");
        btnFind.setBounds(220, 10, 110, 25);
        btnFind.addActionListener(this);
        btnFind.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Configure Find Next Button
        btnFindNext = new JButton("Find Next");
        btnFindNext.setBounds(220, 40, 110, 25);
        btnFindNext.addActionListener(this);
        btnFindNext.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Configure Replace Button
        btnReplace = new JButton("Replace");
        btnReplace.setBounds(220, 70, 110, 25);
        btnReplace.addActionListener(this);
        btnReplace.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Configure Replace All Button
        btnReplaceAll = new JButton("Replace All");
        btnReplaceAll.setBounds(220, 100, 110, 25);
        btnReplaceAll.addActionListener(this);
        btnReplaceAll.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Configure Find Text field
        txtFind = new JTextField();
        txtFind.setBounds(65, 11, 150, 21);

        // Configure Replace Text field
        txtReplace = new JTextField();
        txtReplace.setBounds(65, 70, 150, 21);

        // Configure Find Label
        lblFind = new JLabel("Find:");
        lblFind.setBounds(25, 5, 60, 29);

        // Configure Replace Label
        lblReplace = new JLabel("Replace:");
        lblReplace.setBounds(5,72,102,20);

        // Add Components to Search UI
        searchUI.add(txtFind);
        searchUI.add(txtReplace);
        searchUI.add(lblFind);
        searchUI.add(lblReplace);
        searchUI.add(btnFind);
        searchUI.add(btnFindNext);
        searchUI.add(btnReplace);
        searchUI.add(btnReplaceAll);

        // Add Search UI to window
        search.add(searchUI);

        // Display
        search.setVisible(true);
    }


    /**
     * Searches for a substring within a string
     *
     */
    private void find() {

        String data = editorText.getText();
        int search = data.toLowerCase().indexOf(txtFind.getText().toLowerCase());


        if (search == -1) {

            JOptionPane.showMessageDialog(searchUI, "Not Found");

        } else if (search == data.toLowerCase().lastIndexOf(txtFind.getText().toLowerCase())) {

            editorText.select(search, search + txtFind.getText().length());
        }
    }

    /**
     * Finds the next occurrence of a string
     */
    private void findNext() {}


    /**
     * Replaces Next instance of substring
     */
    private void replace() {

    }


    /**
     * Replaces all instances of a substring with specified replace string
     */
    private void replaceAll(){
        String s = editorText.getText();
        editorText.setText(s.toLowerCase().replaceAll(txtFind.getText().toLowerCase(), txtReplace.getText()));
    }


    /**
     * Button Click handler
     *
     * @param e event
     */
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnFind) {

            find();

        } else if (e.getSource() == btnFindNext) {

            findNext();

        } else if (e.getSource() == btnReplace) {

            replace();

        } else if (e.getSource() == btnReplaceAll) {

            replaceAll();

        }
    }

}