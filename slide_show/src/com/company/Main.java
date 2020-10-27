package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame {

    JLabel pic;
    Timer tm;
    int x = 0;
    Font font = new Font("Helvetica", Font.BOLD, 40);
    String[] list = {
            "D:/Downloads/1.jpg",
            "D:/Downloads/2.jpg",
            "D:/Downloads/3.jpg",
            ""};

    public Main() {
        super("Images SlideShow");
        pic = new JLabel();
        pic.setBounds(40, 30, 700, 300);
        pic.setForeground(Color.white);
        pic.setFont(font);

        tm = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon ii = getResizedImage(x);
                pic.setText("File Not Found");
                x += 1;

                if (x >= list.length) {
                    x = 0;
                    pic.setText("The End");
                }

                pic.setIcon(ii);
            }
        });

        add(pic);
        tm.start();
        setLayout(null);
        setSize(800, 400);
        getContentPane().setBackground(Color.decode("#3c3a2a"));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    private ImageIcon getResizedImage(int i) {
        ImageIcon icon = new ImageIcon(list[i]);
        Image img = icon.getImage();

        Image newImg = img.getScaledInstance(pic.getWidth(), pic.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon newImc = new ImageIcon(newImg);
        return newImc;
    }

}