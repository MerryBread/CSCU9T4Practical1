// GUI and main program for the Training Record
package com.stir.cscu9t4practical1;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;

public class TrainingRecordGUI extends JFrame implements ActionListener {

    private JTextField name = new JTextField(30);
    private JTextField day = new JTextField(2);
    private JTextField month = new JTextField(2);
    private JTextField year = new JTextField(4);
    private JTextField hours = new JTextField(2);
    private JTextField mins = new JTextField(2);
    private JTextField secs = new JTextField(2);
    private JTextField dist = new JTextField(4);
    private JTextField terr = new JTextField(10);
    private JTextField temp = new JTextField(10);
    private JTextField rep = new JTextField(2);
    private JTextField rec = new JTextField(2);
    private JTextField where = new JTextField(15);
    private JLabel labn = new JLabel(" Name:");
    private JLabel labd = new JLabel(" Day:");
    private JLabel labm = new JLabel(" Month:");
    private JLabel laby = new JLabel(" Year:");
    private JLabel labh = new JLabel(" Hours:");
    private JLabel labmm = new JLabel(" Mins:");
    private JLabel labs = new JLabel(" Secs:");
    private JLabel labdist = new JLabel(" Distance (km):");
    private JLabel labterr = new JLabel(" Terrain:");
    private JLabel labtemp = new JLabel(" Tempo:");
    private JLabel labrep = new JLabel(" Repetitions:");
    private JLabel labrec = new JLabel(" Recovery:");
    private JLabel labwhere = new JLabel(" Location:");
    private JButton addR = new JButton("Add");
    private JButton lookUpByDate = new JButton("Look Up");
    private JButton findAllByDate = new JButton("Find All");
    private JButton remove = new JButton("Remove");
    private JRadioButton cycle = new JRadioButton("Cycle");
    private JRadioButton sprint = new JRadioButton("Sprint");
    private JRadioButton swim = new JRadioButton("Swim");

    private ButtonGroup sport = new ButtonGroup();

    private TrainingRecord myAthletes = new TrainingRecord();

    private JTextArea outputArea = new JTextArea(5, 50);

    public static void main(String[] args) {
        TrainingRecordGUI applic = new TrainingRecordGUI();
    } // main

    // set up the GUI 
    public TrainingRecordGUI() {
        super("Training Record");
        setLayout(new FlowLayout());
        add(labn);
        add(name);
        name.setEditable(true);
        add(labd);
        add(day);
        day.setEditable(true);
        add(labm);
        add(month);
        month.setEditable(true);
        add(laby);
        add(year);
        year.setEditable(true);
        add(labh);
        add(hours);
        hours.setEditable(true);
        add(labmm);
        add(mins);
        mins.setEditable(true);
        add(labs);
        add(secs);
        secs.setEditable(true);
        add(labdist);
        add(dist);
        dist.setEditable(true);

        add(labterr);
        add(terr);
        terr.setEditable(true);
        add(labtemp);
        add(temp);
        temp.setEditable(true);
        add(labrep);
        add(rep);
        rep.setEditable(true);
        add(labrec);
        add(rec);
        rec.setEditable(true);
        add(labwhere);
        add(where);
        where.setEditable(true);

        sport.add(cycle);
        sport.add(sprint);
        sport.add(swim);
        add(cycle);
        add(sprint);
        add(swim);
        cycle.setActionCommand(cycle.getText());
        sprint.setActionCommand(sprint.getText());
        swim.setActionCommand(swim.getText());

        add(addR);
        addR.addActionListener(this);
        add(lookUpByDate);
        lookUpByDate.addActionListener(this);
        add(findAllByDate);
        findAllByDate.addActionListener(this);
        add(remove);
        remove.addActionListener(this);
        add(outputArea);
        outputArea.setEditable(false);
        setSize(720, 200);
        setVisible(true);
        blankDisplay();

        // To save typing in new entries while testing, uncomment
        // the following lines (or add your own test cases)
        
    } // constructor

    // listen for and respond to GUI events 
    public void actionPerformed(ActionEvent event) {
        String message = "";
        if (event.getSource() == addR) {
            message = addEntry("generic");
        }
        if (event.getSource() == lookUpByDate) {
            message = lookupEntry();
        }
        if (event.getSource() == findAllByDate) {
            message = findAllByDate();
        }
        if (event.getSource() == remove) {
            message = remove();
        }
        outputArea.setText(message);
        blankDisplay();
    } // actionPerformed

    public String addEntry(String what) {
        String message = "Record added\n";
        System.out.println("Adding "+what+" entry to the records");
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        float km = java.lang.Float.parseFloat(dist.getText());
        int h = Integer.parseInt(hours.getText());
        int mm = Integer.parseInt(mins.getText());
        int s = Integer.parseInt(secs.getText());

        try {
            String selected = sport.getSelection().getActionCommand();
            switch(selected) {
                case "cycle":
                    String tr = terr.getText();
                    String tm = temp.getText();
                    Entry cyc = new CycleEntry(n, d, m, y, h, mm, s, km, tr, tm);
                    myAthletes.addEntry(cyc);
                case "sprint":
                    int rp = Integer.parseInt(rep.getText());
                    int rc = Integer.parseInt(rec.getText());
                    Entry spr = new SprintEntry(n, d, m, y, h, mm, s, km, rp, rc);
                    myAthletes.addEntry(spr);
                case "swim":
                    String wh = where.getText();
                    Entry swm = new SwimEntry(n, d, m, y, h, mm, s, km, wh);
                    myAthletes.addEntry(swm);
            }
        } catch(Exception exception) {
            System.out.println("Error: " + exception);
            Entry e = new Entry(n, d, m, y, h, mm, s, km);
            myAthletes.addEntry(e);
        }


        return message;
    }
    
    public String lookupEntry() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up record ...");
        String message = myAthletes.lookupEntry(d, m, y);
        return message;
    }

    public String findAllByDate() {
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("looking up all records ...");
        String message = myAthletes.findAllByDate(d, m, y);
        return message;
    }

    public String remove() {
        String n = name.getText();
        int m = Integer.parseInt(month.getText());
        int d = Integer.parseInt(day.getText());
        int y = Integer.parseInt(year.getText());
        outputArea.setText("Deleting record ...");
        String message = myAthletes.remove(n,m,d,y);
        return message;
    }

    public void blankDisplay() {
        name.setText("");
        day.setText("");
        month.setText("");
        year.setText("");
        hours.setText("");
        mins.setText("");
        secs.setText("");
        dist.setText("");
        terr.setText("");
        temp.setText("");
        rep.setText("");
        rec.setText("");
        where.setText("");

    }// blankDisplay
    // Fills the input fields on the display for testing purposes only
    public void fillDisplay(Entry ent) {
        name.setText(ent.getName());
        day.setText(String.valueOf(ent.getDay()));
        month.setText(String.valueOf(ent.getMonth()));
        year.setText(String.valueOf(ent.getYear()));
        hours.setText(String.valueOf(ent.getHour()));
        mins.setText(String.valueOf(ent.getMin()));
        secs.setText(String.valueOf(ent.getSec()));
        dist.setText(String.valueOf(ent.getDistance()));
        terr.setText(String.valueOf(ent.getDistance()));
        temp.setText(String.valueOf(ent.getDistance()));
        rep.setText(String.valueOf(ent.getDistance()));
        rec.setText(String.valueOf(ent.getDistance()));
        where.setText(String.valueOf(ent.getDistance()));
    }

} // TrainingRecordGUI

