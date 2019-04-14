package com.my.calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FrameCalculator extends JFrame {

    private boolean isCalculated = false;
    private StringCalculator stringCalculator = new StringCalculator();
    private JTextField text;
    private JPanel keyboard;

    private FrameCalculator() {

        // 设置布局
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // 添加显示器
        add(Box.createVerticalStrut(20)); // 调制间距
        text = new JTextField(20);
        text.setFont(new Font("楷体",Font.BOLD,32));
        text.setHorizontalAlignment(SwingConstants.RIGHT);
        text.setMaximumSize(new Dimension(500, 80));
        text.addKeyListener(defaultKeyListener); // 监听回车事件
        add(text);

        // 添加键盘
        add(Box.createVerticalStrut(18)); // 调制间距
        keyboard = new JPanel();
        keyboard.setMaximumSize(new Dimension(500, 400));
        keyboard.setLayout(new GridLayout(5, 5, 10, 10));
        add(keyboard);

        // 添加按钮
        addButtons();

        // 显示窗口
        setTitle("MyCalculator");
        setBounds(400, 200, 540, 570);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void addButtons() {

        addButton("sin", event -> text.setText(text.getText() + "sin("));
        addButton("cos", event -> text.setText(text.getText() + "cos("));
        addButton("tan", event -> text.setText(text.getText() + "tan("));
        addButton("cot", event -> text.setText(text.getText() + "cot("));
        addButton("<", event -> {
            String t = text.getText();
            if (t.length() > 0) {
                text.setText(t.substring(0, t.length() - 1));
            }
        });

        addButton("9");
        addButton("8");
        addButton("7");
        addButton("(");
        addButton(")");

        addButton("6");
        addButton("5");
        addButton("4");
        addButton("+");
        addButton("-");

        addButton("3");
        addButton("2");
        addButton("1");
        addButton("*");
        addButton("/");

        addButton(".");
        addButton("0");
        addButton("PI", event -> text.setText(text.getText() + "PI"));
        addButton("^");
        addButton("=", event -> calculate());
    }
    private void addButton(String title) {
        addButton(title, event -> text.setText(text.getText() + title));
    }
    private void addButton(String title, ActionListener l) {
        JButton button = new JButton(title);
        button.setFont(new Font("楷体",Font.BOLD,32));
        button.addActionListener(l);
        button.addActionListener(defaultActionListener);
        keyboard.add(button);
    }

    private ActionListener defaultActionListener = event -> {
        if (isCalculated) {
            text.setText("");
            isCalculated = false;
        }
    };
    private KeyListener defaultKeyListener = new KeyListener() {
        public void keyPressed(KeyEvent e) {
            defaultActionListener.actionPerformed(null);
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                calculate();
            }
        }
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
    };

    private void calculate() {
        String input = text.getText();
        if (!"".equals(input)) {
            text.setText(stringCalculator.calculate(input));
            isCalculated = true;
        }
    }

    public static void main(String[] args) {

        new FrameCalculator();

//        String input = "(1.1 + 2)- 3.3 / 7.0 + sin(123) string";
//        String input = "1 * 2 + 3 ^ 4 / 5";
//        String input = "sin(3.1415926 / 2)";
//        String input = "(sin(3.1415926 / 2) * (123 + 2 / 2) ) / 2";
    }
}
