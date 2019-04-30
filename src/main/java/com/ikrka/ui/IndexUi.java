package com.ikrka.ui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ikrka.common.Config;
import com.ikrka.common.TempImgConfig;
import com.ikrka.core.ImgMatch;
import com.ikrka.run.service.impl.SoulService;
import com.ikrka.util.ADBUtil;
import com.ikrka.util.StringUtil;

import cn.hutool.core.lang.Console;
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
        JButton screenShotButton = new JButton("截图");
        JButton testButton = new JButton("测试");
        gauntletButton.setActionCommand("gauntlet");
        wakeUpButton.setActionCommand("wakeUp");
        breakButton.setActionCommand("break");
        screenShotButton.setActionCommand("screenShot");
        testButton.setActionCommand("test");
        gauntletButton.addActionListener(new ButtonClickListener());
        wakeUpButton.addActionListener(new ButtonClickListener());
        breakButton.addActionListener(new ButtonClickListener());
        screenShotButton.addActionListener(new ButtonClickListener());
        testButton.addActionListener(new ButtonClickListener());
        controlPanel.add(gauntletButton);
        controlPanel.add(wakeUpButton);
        controlPanel.add(breakButton);
        controlPanel.add(screenShotButton);
        controlPanel.add(testButton);
        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("gauntlet")) {
                String tier = (String) JOptionPane.showInputDialog(null, "请选择层数:\n", "层数", JOptionPane.QUESTION_MESSAGE,
                        null, Config.gauntletParam, Config.gauntletParam[0]);
                if (tier != null && !"".equals(tier)) {
                    statusLabel.setText("start gauntlet.");
                    ThreadUtil.execute(new SoulService(StringUtil.toInt(tier)));
                }
            } else if (command.equals("wakeUp")) {
                statusLabel.setText("wakeUp Button clicked.");
            } else if (command.equals("break")) {
                statusLabel.setText("break Button clicked.");
            } else if (command.equals("screenShot")) {
                try {
                    ADBUtil.screenShot();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                statusLabel.setText("screenShot Button clicked.");
            } else if (command.equals("test")) {
                try {
                    try {
                        ADBUtil.screenShot();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    Map<String, Integer> reward1 = ImgMatch.match(TempImgConfig.REWARD[0], Config.screenShotSavePath);
                    Map<String, Integer> reward2 = ImgMatch.match(TempImgConfig.REWARD[1], Config.screenShotSavePath);
                    Map<String, Integer> reward3 = ImgMatch.match(TempImgConfig.REWARD[2], Config.screenShotSavePath);
                    int x1Diff = reward2.get("x") - reward1.get("x");
                    int x2Diff = reward3.get("x") - reward2.get("x");
                    int x3Diff = reward3.get("x") - reward1.get("x");
                    int y1Diff = reward2.get("y") - reward1.get("y");
                    int y2Diff = reward3.get("y") - reward2.get("y");
                    int y3Diff = reward3.get("y") - reward1.get("y");
                    Console.print(x1Diff);
                    Console.print(x2Diff);
                    Console.print(x3Diff);
                    Console.print(y1Diff);
                    Console.print(y2Diff);
                    Console.print(y3Diff);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                statusLabel.setText("screenShot Button clicked.");
            }
        }
    }

}