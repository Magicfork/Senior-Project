package com.company;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Dimension;

import javax.swing.*;

// This class can be used to convert Excel workbooks into a .csv file. This is useful
// for this project should Batch Predictions need to be done. As to do this a .csv
// file is required to be uploaded to Amazon S3, then using the Amazon Machine Learning
// SDK you can create a model or batch query from the .csv.

public class GUI extends JFrame{

    private static final String NAME = "Sepsis Real-time Predictionator";

    // Text fields for each attribute
    private JTextField t_BodyTemp = new JTextField(25);
    private JTextField t_HeartRate = new JTextField(25);
    private JTextField t_RespRate = new JTextField(25);
    private JTextField t_UrineOutput = new JTextField(25);
    private JTextField t_PCount = new JTextField(25);
    private JTextField t_DiffBreathe = new JTextField(25);
    private JTextField t_AbHeartP = new JTextField(25);
    private JTextField t_Age = new JTextField(25);
    private JTextField t_AbdominalPain = new JTextField(25);
    private JTextField t_WeakImmune = new JTextField(25);

    // Text labels for each attribute
    private JLabel l_BodyTemp = new JLabel("Body Temperature");
    private JLabel l_HeartRate = new JLabel("Heart Rate");
    private JLabel l_RespRate = new JLabel("Respiratory Rate");
    private JLabel l_UrineOutput = new JLabel("Urine Output");
    private JLabel l_PCount = new JLabel("Platelet Count");
    private JLabel l_DiffBreathe = new JLabel("Difficulty Breathing");
    private JLabel l_AbHeartP = new JLabel("Abnormal Heart Pump");
    private JLabel l_Age = new JLabel("Age");
    private JLabel l_AbdominalPain = new JLabel("Abdominal Pain");
    private JLabel l_WeakImmune = new JLabel("Weakened Immune System");

    // Text area used to display results
    private JTextArea t_Results = new JTextArea("Results Will Be Here", 30, 30);

    // Buttons for GUI features
    private JButton submit = new JButton("Submit");
    private JButton clear = new JButton("Clear Data");

    // Panels for each attribute
    private JPanel p_BodyTemp = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_HeartRate = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_RespRate = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_UrineOutput = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_PCount = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_DiffBreathe = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_AbHeartP = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_Age = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_AbdominalPain = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));
    private JPanel p_WeakImmune = new JPanel(new FlowLayout(FlowLayout.TRAILING, 75, 10));

    // Main container panels, directly added to window frame
    private JPanel p_Buttons = new JPanel(new FlowLayout());
    private JPanel p_SupahPanel = new JPanel(new GridLayout(10, 1));
    //private JPanel p_Results = new JPanel(new GridLayout(2, 1));
    private JPanel p_Results = new JPanel(new FlowLayout());

    // Parallel arrays for Attribute GUI elements
    private JPanel[] p_Array = {p_BodyTemp, p_HeartRate, p_RespRate, p_UrineOutput,
    p_PCount, p_DiffBreathe, p_AbHeartP, p_Age, p_AbdominalPain, p_WeakImmune};

    private JLabel[] l_Array = {l_BodyTemp, l_HeartRate, l_RespRate, l_UrineOutput,
            l_PCount, l_DiffBreathe, l_AbHeartP, l_Age, l_AbdominalPain, l_WeakImmune};

    private JTextField[] t_Array = {t_BodyTemp, t_HeartRate, t_RespRate, t_UrineOutput,
            t_PCount, t_DiffBreathe, t_AbHeartP, t_Age, t_AbdominalPain, t_WeakImmune};

    public GUI(){
        setLayout(new BorderLayout());

        for(int i = 0; i < p_Array.length; i++){
            p_Array[i].add(l_Array[i]);
            p_Array[i].add(t_Array[i]);
            p_Array[i].setMinimumSize(new Dimension(50, 50));
            p_SupahPanel.add(p_Array[i]);
        }

        add(p_SupahPanel, BorderLayout.CENTER);

        // Add button panel to the south
        p_Buttons.add(submit);
        p_Buttons.add(clear);
        add(p_Buttons, BorderLayout.SOUTH);
        
        t_Results.setEditable(false);
        p_Results.add(t_Results);
        add(p_Results, BorderLayout.EAST);


        setSize(new Dimension(1000, 600));
        setMinimumSize(new Dimension(1000, 600));
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle(NAME);
        setVisible(true);
    }

    public static void main(String[] args){
        GUI looky = new GUI();
        looky.show();
    }

}


