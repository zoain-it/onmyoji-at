package com.ikrka.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.ikrka.run.service.impl.TenSoulService;

import cn.hutool.core.thread.ThreadUtil;

public class IndexUi {

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public IndexUi() {
        prepareGUI();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Onmyoji-AT");
        mainFrame.setSize(400, 400);
        mainFrame.setLayout(new GridLayout(3, 1));
        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());
        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public void showEvent() {
        headerLabel.setText("请选择模式");
        JButton gauntletButton = new JButton("御魂");
        JButton wakeUpButton = new JButton("觉醒");
        JButton breakButton = new JButton("结界突破");
        gauntletButton.setActionCommand("gauntlet");
        wakeUpButton.setActionCommand("wakeUp");
        breakButton.setActionCommand("break");
        gauntletButton.addActionListener(new ButtonClickListener());
        wakeUpButton.addActionListener(new ButtonClickListener());
        breakButton.addActionListener(new ButtonClickListener());
        controlPanel.add(gauntletButton);
        controlPanel.add(wakeUpButton);
        controlPanel.add(breakButton);
        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("gauntlet")) {
                ThreadUtil.execute(new TenSoulService());
                statusLabel.setText("gauntlet Button clicked.");
            } else if (command.equals("wakeUp")) {
                statusLabel.setText("wakeUp Button clicked.");
            } else if (command.equals("break")) {
                statusLabel.setText("break Button clicked.");
            }
        }
    }

}