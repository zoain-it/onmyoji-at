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
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.ikrka.common.Config;
import com.ikrka.run.service.impl.SoulService;
import com.ikrka.run.service.impl.WakeUpService;
import com.ikrka.util.ADBUtil;
import com.ikrka.util.StringUtil;

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
                        null, Config.GAUNTLET_PARAM, Config.GAUNTLET_PARAM[0]);
                if (tier != null && !"".equals(tier)) {
                    statusLabel.setText("runing gauntlet.");
                    SoulService soulService = new SoulService(StringUtil.tierToInt(tier));
                    soulService.setName("soulService");
                    soulService.setDaemon(true);
                    ThreadUtil.execute(soulService);
                }
            } else if (command.equals("wakeUp")) {
                String model = (String) JOptionPane.showInputDialog(null, "请选择模式:\n", "模式",
                        JOptionPane.QUESTION_MESSAGE, null, Config.MODEL_PARAM, Config.MODEL_PARAM[0]);
                String tier = (String) JOptionPane.showInputDialog(null, "请选择层数:\n", "层数", JOptionPane.QUESTION_MESSAGE,
                        null, Config.GAUNTLET_PARAM, Config.GAUNTLET_PARAM[0]);
                if (model != null && !"".equals(model) && tier != null && !"".equals(tier)) {
                    statusLabel.setText("runing wakeUp.");
                    WakeUpService wakeUpService = new WakeUpService(StringUtil.wakeUpModelToInt(model),
                            StringUtil.tierToInt(tier));
                    wakeUpService.setName("wakeUpService");
                    wakeUpService.setDaemon(true);
                    ThreadUtil.execute(wakeUpService);
                }
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
                WakeUpService wakeUpService = new WakeUpService(0, 6);
                ThreadUtil.execute(wakeUpService);
                statusLabel.setText("test Button clicked.");
            }
        }
    }

}