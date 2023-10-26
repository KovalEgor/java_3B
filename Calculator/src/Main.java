//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

class Calculator implements ActionListener {
    double number;
    double answer;
    int calculation;
    JFrame frame;
    JLabel label = new JLabel();
    JTextField textField = new JTextField();
    JRadioButton onRadioButton = new JRadioButton("usual");
    JRadioButton offRadioButton = new JRadioButton("utility");
    JButton buttonZero = new JButton("0");
    JButton buttonOne = new JButton("1");
    JButton buttonTwo = new JButton("2");
    JButton buttonThree = new JButton("3");
    JButton buttonFour = new JButton("4");
    JButton buttonFive = new JButton("5");
    JButton buttonSix = new JButton("6");
    JButton buttonSeven = new JButton("7");
    JButton buttonEight = new JButton("8");
    JButton buttonNine = new JButton("9");
    JButton buttonDot = new JButton(".");
    JButton buttonClear = new JButton("C");
    JButton buttonDelete = new JButton("DEL");
    JButton buttonEqual = new JButton("=");
    JButton buttonMul = new JButton("x");
    JButton buttonDiv = new JButton("/");
    JButton buttonPlus = new JButton("+");
    JButton buttonMinus = new JButton("-");
    JButton buttonSquare = new JButton("x²");
    JButton buttonReciprocal = new JButton("1/x");
    JButton buttonSqrt = new JButton("√");
    JButton buttonSin = new JButton("Sin");
    JButton buttonCos = new JButton("Cos");
    JButton buttonTan = new JButton("Tng");
    JButton buttonCtg = new JButton("Ctg");
    Color pink1 = new Color(204, 78, 132);
    Color pink2 = new Color(235, 141, 172);
    Color pink3 = new Color(232, 102, 158);
    Color pink4 = new Color(247, 161, 191);

    Calculator() {
        this.prepareGUI();
        this.addComponents();
        this.addActionEvent();
    }

    public void prepareGUI() {
        this.frame = new JFrame();
        this.frame.setTitle("Калькулятор By Коваль и Жуковец");
        this.frame.setSize(380, 520);
        this.frame.getContentPane().setLayout((LayoutManager)null);
        this.frame.getContentPane().setBackground(this.pink2);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo((Component)null);
        this.frame.setVisible(true);
        this.frame.setDefaultCloseOperation(3);
    }

    public void addComponents() {
        this.label.setBounds(250, 0, 50, 50);
        this.label.setForeground(Color.black);
        this.frame.add(this.label);
        this.textField.setBounds(30, 40, 270, 40);
        this.textField.setFont(new Font("Arial", 1, 20));
        this.textField.setEditable(false);
        this.textField.setHorizontalAlignment(4);
        this.frame.add(this.textField);
        this.onRadioButton.setBounds(10, 95, 60, 40);
        this.onRadioButton.setSelected(true);
        this.onRadioButton.setFont(new Font("Arial", 1, 9));
        this.onRadioButton.setBackground(this.pink2);
        this.onRadioButton.setForeground(Color.WHITE);
        this.frame.add(this.onRadioButton);
        this.offRadioButton.setBounds(10, 120, 60, 40);
        this.offRadioButton.setSelected(false);
        this.offRadioButton.setFont(new Font("Arial", 1, 9));
        this.offRadioButton.setBackground(this.pink2);
        this.offRadioButton.setForeground(Color.WHITE);
        this.frame.add(this.offRadioButton);
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(this.onRadioButton);
        buttonGroup.add(this.offRadioButton);
        this.buttonOne.setBounds(10, 350, 60, 40);
        this.buttonOne.setFont(new Font("Arial", 1, 20));
        this.buttonOne.setBackground(this.pink4);
        this.buttonOne.setForeground(Color.white);
        this.frame.add(this.buttonOne);
        this.buttonTwo.setBounds(80, 350, 60, 40);
        this.buttonTwo.setFont(new Font("Arial", 1, 20));
        this.buttonTwo.setBackground(this.pink4);
        this.buttonTwo.setForeground(Color.white);
        this.frame.add(this.buttonTwo);
        this.buttonThree.setBounds(150, 350, 60, 40);
        this.buttonThree.setFont(new Font("Arial", 1, 20));
        this.buttonThree.setBackground(this.pink4);
        this.buttonThree.setForeground(Color.white);
        this.frame.add(this.buttonThree);
        this.buttonFour.setBounds(10, 290, 60, 40);
        this.buttonFour.setFont(new Font("Arial", 1, 20));
        this.buttonFour.setBackground(this.pink4);
        this.buttonFour.setForeground(Color.white);
        this.frame.add(this.buttonFour);
        this.buttonFive.setBounds(80, 290, 60, 40);
        this.buttonFive.setFont(new Font("Arial", 1, 20));
        this.buttonFive.setBackground(this.pink4);
        this.buttonFive.setForeground(Color.white);
        this.frame.add(this.buttonFive);
        this.buttonSix.setBounds(150, 290, 60, 40);
        this.buttonSix.setFont(new Font("Arial", 1, 20));
        this.buttonSix.setBackground(this.pink4);
        this.buttonSix.setForeground(Color.white);
        this.frame.add(this.buttonSix);
        this.buttonSeven.setBounds(10, 230, 60, 40);
        this.buttonSeven.setFont(new Font("Arial", 1, 20));
        this.buttonSeven.setBackground(this.pink4);
        this.buttonSeven.setForeground(Color.white);
        this.frame.add(this.buttonSeven);
        this.buttonEight.setBounds(80, 230, 60, 40);
        this.buttonEight.setFont(new Font("Arial", 1, 20));
        this.buttonEight.setBackground(this.pink4);
        this.buttonEight.setForeground(Color.white);
        this.frame.add(this.buttonEight);
        this.buttonNine.setBounds(150, 230, 60, 40);
        this.buttonNine.setFont(new Font("Arial", 1, 20));
        this.buttonNine.setBackground(this.pink4);
        this.buttonNine.setForeground(Color.white);
        this.frame.add(this.buttonNine);
        this.buttonDot.setBounds(150, 410, 60, 40);
        this.buttonDot.setFont(new Font("Arial", 1, 20));
        this.buttonDot.setBackground(this.pink4);
        this.buttonDot.setForeground(Color.white);
        this.frame.add(this.buttonDot);
        this.buttonZero.setBounds(10, 410, 130, 40);
        this.buttonZero.setFont(new Font("Arial", 1, 20));
        this.buttonZero.setBackground(this.pink4);
        this.buttonZero.setForeground(Color.white);
        this.frame.add(this.buttonZero);
        this.buttonEqual.setBounds(220, 350, 60, 100);
        this.buttonEqual.setFont(new Font("Arial", 1, 20));
        this.buttonEqual.setBackground(this.pink3);
        this.frame.add(this.buttonEqual);
        this.buttonDiv.setBounds(220, 110, 60, 40);
        this.buttonDiv.setFont(new Font("Arial", 1, 20));
        this.buttonDiv.setBackground(this.pink3);
        this.frame.add(this.buttonDiv);
        this.buttonSqrt.setBounds(10, 170, 60, 40);
        this.buttonSqrt.setFont(new Font("Arial", 1, 18));
        this.buttonSqrt.setBackground(this.pink3);
        this.frame.add(this.buttonSqrt);
        this.buttonMul.setBounds(220, 170, 60, 40);
        this.buttonMul.setFont(new Font("Arial", 1, 20));
        this.buttonMul.setBackground(this.pink3);
        this.frame.add(this.buttonMul);
        this.buttonMinus.setBounds(220, 230, 60, 40);
        this.buttonMinus.setFont(new Font("Arial", 1, 20));
        this.buttonMinus.setBackground(this.pink3);
        this.frame.add(this.buttonMinus);
        this.buttonPlus.setBounds(220, 290, 60, 40);
        this.buttonPlus.setFont(new Font("Arial", 1, 20));
        this.buttonPlus.setBackground(this.pink3);
        this.frame.add(this.buttonPlus);
        this.buttonSquare.setBounds(80, 170, 60, 40);
        this.buttonSquare.setFont(new Font("Arial", 1, 20));
        this.buttonSquare.setBackground(this.pink3);
        this.frame.add(this.buttonSquare);
        this.buttonReciprocal.setBounds(150, 170, 60, 40);
        this.buttonReciprocal.setFont(new Font("Arial", 1, 15));
        this.buttonReciprocal.setBackground(this.pink3);
        this.frame.add(this.buttonReciprocal);
        this.buttonDelete.setBounds(150, 110, 60, 40);
        this.buttonDelete.setFont(new Font("Arial", 1, 12));
        this.buttonDelete.setBackground(this.pink1);
        this.buttonDelete.setForeground(Color.white);
        this.frame.add(this.buttonDelete);
        this.buttonClear.setBounds(80, 110, 60, 40);
        this.buttonClear.setFont(new Font("Arial", 1, 12));
        this.buttonClear.setBackground(this.pink1);
        this.buttonClear.setForeground(Color.white);
        this.frame.add(this.buttonClear);
        this.buttonSin.setBounds(290, 110, 60, 70);
        this.buttonSin.setFont(new Font("Arial", 1, 12));
        this.buttonSin.setBackground(this.pink4);
        this.buttonSin.setForeground(Color.white);
        this.buttonSin.setEnabled(false);
        this.frame.add(this.buttonSin);
        this.buttonCos.setBounds(290, 200, 60, 70);
        this.buttonCos.setFont(new Font("Arial", 1, 12));
        this.buttonCos.setBackground(this.pink4);
        this.buttonCos.setForeground(Color.white);
        this.buttonCos.setEnabled(false);
        this.frame.add(this.buttonCos);
        this.buttonTan.setBounds(290, 290, 60, 70);
        this.buttonTan.setFont(new Font("Arial", 1, 12));
        this.buttonTan.setBackground(this.pink4);
        this.buttonTan.setForeground(Color.white);
        this.buttonTan.setEnabled(false);
        this.frame.add(this.buttonTan);
        this.buttonCtg.setBounds(290, 380, 60, 70);
        this.buttonCtg.setFont(new Font("Arial", 1, 12));
        this.buttonCtg.setBackground(this.pink4);
        this.buttonCtg.setForeground(Color.white);
        this.buttonCtg.setEnabled(false);
        this.frame.add(this.buttonCtg);
    }

    public void addActionEvent() {
        this.onRadioButton.addActionListener(this);
        this.offRadioButton.addActionListener(this);
        this.buttonClear.addActionListener(this);
        this.buttonDelete.addActionListener(this);
        this.buttonDiv.addActionListener(this);
        this.buttonSqrt.addActionListener(this);
        this.buttonSquare.addActionListener(this);
        this.buttonReciprocal.addActionListener(this);
        this.buttonMinus.addActionListener(this);
        this.buttonSeven.addActionListener(this);
        this.buttonEight.addActionListener(this);
        this.buttonNine.addActionListener(this);
        this.buttonMul.addActionListener(this);
        this.buttonFour.addActionListener(this);
        this.buttonFive.addActionListener(this);
        this.buttonSix.addActionListener(this);
        this.buttonPlus.addActionListener(this);
        this.buttonOne.addActionListener(this);
        this.buttonTwo.addActionListener(this);
        this.buttonThree.addActionListener(this);
        this.buttonEqual.addActionListener(this);
        this.buttonZero.addActionListener(this);
        this.buttonDot.addActionListener(this);
        this.buttonSin.addActionListener(this);
        this.buttonCos.addActionListener(this);
        this.buttonTan.addActionListener(this);
        this.buttonCtg.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == this.onRadioButton) {
            this.enable();
        } else if (source == this.offRadioButton) {
            this.disable();
        } else if (source == this.buttonClear) {
            this.label.setText("");
            this.textField.setText("");
        } else if (source == this.buttonDelete) {
            int length = this.textField.getText().length();
            int number = length - 1;
            if (length > 0) {
                StringBuilder back = new StringBuilder(this.textField.getText());
                back.deleteCharAt(number);
                this.textField.setText(back.toString());
            }

            if (this.textField.getText().endsWith("")) {
                this.label.setText("");
            }
        } else if (source == this.buttonZero) {
            if (this.textField.getText().equals("0")) {
                return;
            }

            this.textField.setText(this.textField.getText() + "0");
        } else if (source == this.buttonOne) {
            this.textField.setText(this.textField.getText() + "1");
        } else if (source == this.buttonTwo) {
            this.textField.setText(this.textField.getText() + "2");
        } else if (source == this.buttonThree) {
            this.textField.setText(this.textField.getText() + "3");
        } else if (source == this.buttonFour) {
            this.textField.setText(this.textField.getText() + "4");
        } else if (source == this.buttonFive) {
            this.textField.setText(this.textField.getText() + "5");
        } else if (source == this.buttonSix) {
            this.textField.setText(this.textField.getText() + "6");
        } else if (source == this.buttonSeven) {
            this.textField.setText(this.textField.getText() + "7");
        } else if (source == this.buttonEight) {
            this.textField.setText(this.textField.getText() + "8");
        } else if (source == this.buttonNine) {
            this.textField.setText(this.textField.getText() + "9");
        } else if (source == this.buttonDot) {
            if (this.textField.getText().contains(".")) {
                return;
            }

            this.textField.setText(this.textField.getText() + ".");
        } else {
            String str;
            if (source == this.buttonPlus) {
                str = this.textField.getText();
                this.number = Double.parseDouble(this.textField.getText());
                this.textField.setText("");
                this.label.setText(str + "+");
                this.calculation = 1;
            } else if (source == this.buttonMinus) {
                str = this.textField.getText();
                this.number = Double.parseDouble(this.textField.getText());
                this.textField.setText("");
                this.label.setText(str + "-");
                this.calculation = 2;
            } else if (source == this.buttonMul) {
                str = this.textField.getText();
                this.number = Double.parseDouble(this.textField.getText());
                this.textField.setText("");
                this.label.setText(str + "X");
                this.calculation = 3;
            } else if (source == this.buttonDiv) {
                str = this.textField.getText();
                this.number = Double.parseDouble(this.textField.getText());
                this.textField.setText("");
                this.label.setText(str + "/");
                this.calculation = 4;
            } else {
                Double Ctg;
                if (source == this.buttonSqrt) {
                    this.number = Double.parseDouble(this.textField.getText());
                    Ctg = Math.sqrt(this.number);
                    this.textField.setText(Double.toString(Ctg));
                } else if (source == this.buttonSquare) {
                    str = this.textField.getText();
                    this.number = Double.parseDouble(this.textField.getText());
                    double square = Math.pow(this.number, 2.0);
                    String string = Double.toString(square);
                    if (string.endsWith(".0")) {
                        this.textField.setText(string.replace(".0", ""));
                    } else {
                        this.textField.setText(string);
                    }

                    this.label.setText("(sqr)" + str);
                } else if (source == this.buttonReciprocal) {
                    this.number = Double.parseDouble(this.textField.getText());
                    double reciprocal = 1.0 / this.number;
                    String string = Double.toString(reciprocal);
                    if (string.endsWith(".0")) {
                        this.textField.setText(string.replace(".0", ""));
                    } else {
                        this.textField.setText(string);
                    }
                } else if (source == this.buttonEqual) {
                    switch (this.calculation) {
                        case 1:
                            this.answer = this.number + Double.parseDouble(this.textField.getText());
                            if (Double.toString(this.answer).endsWith(".0")) {
                                this.textField.setText(Double.toString(this.answer).replace(".0", ""));
                            } else {
                                this.textField.setText(Double.toString(this.answer));
                            }

                            this.label.setText("");
                            break;
                        case 2:
                            this.answer = this.number - Double.parseDouble(this.textField.getText());
                            if (Double.toString(this.answer).endsWith(".0")) {
                                this.textField.setText(Double.toString(this.answer).replace(".0", ""));
                            } else {
                                this.textField.setText(Double.toString(this.answer));
                            }

                            this.label.setText("");
                            break;
                        case 3:
                            this.answer = this.number * Double.parseDouble(this.textField.getText());
                            if (Double.toString(this.answer).endsWith(".0")) {
                                this.textField.setText(Double.toString(this.answer).replace(".0", ""));
                            } else {
                                this.textField.setText(Double.toString(this.answer));
                            }

                            this.label.setText("");
                            break;
                        case 4:
                            this.answer = this.number / Double.parseDouble(this.textField.getText());
                            if (Double.toString(this.answer).endsWith(".0")) {
                                this.textField.setText(Double.toString(this.answer).replace(".0", ""));
                            } else {
                                this.textField.setText(Double.toString(this.answer));
                            }

                            this.label.setText("");
                    }
                } else if (source == this.buttonSin) {
                    this.number = Double.parseDouble(this.textField.getText());
                    Ctg = Math.sin(Math.toRadians(this.number));
                    this.label.setText("Sin " + this.number);
                    this.textField.setText(Double.toString(Ctg));
                } else if (source == this.buttonCos) {
                    this.number = Double.parseDouble(this.textField.getText());
                    Ctg = Math.sqrt(1 - Math.pow(Math.sin(Math.toRadians(this.number)), 2.0));
                    this.label.setText("Cos " + this.number);
                    this.textField.setText(Double.toString(Ctg));
                } else if (source == this.buttonTan) {
                    this.number = Double.parseDouble(this.textField.getText());
                    Ctg = Math.tan(Math.toRadians(this.number));
                    this.label.setText("Tan " + this.number);
                    this.textField.setText(Double.toString(Ctg));
                } else if (source == this.buttonCtg) {
                    this.number = Double.parseDouble(this.textField.getText());
                    Ctg = 1.0 / Math.tan(Math.toRadians(this.number));
                    this.label.setText("Ctg " + this.number);
                    this.textField.setText(Double.toString(Ctg));
                }
            }
        }

    }

    public void enable() {
        this.onRadioButton.setEnabled(false);
        this.offRadioButton.setEnabled(true);
        this.textField.setEnabled(true);
        this.label.setEnabled(true);
        this.buttonClear.setEnabled(true);
        this.buttonDelete.setEnabled(true);
        this.buttonDiv.setEnabled(true);
        this.buttonSqrt.setEnabled(true);
        this.buttonSquare.setEnabled(true);
        this.buttonReciprocal.setEnabled(true);
        this.buttonMinus.setEnabled(true);
        this.buttonSeven.setEnabled(true);
        this.buttonEight.setEnabled(true);
        this.buttonNine.setEnabled(true);
        this.buttonMul.setEnabled(true);
        this.buttonFour.setEnabled(true);
        this.buttonFive.setEnabled(true);
        this.buttonSix.setEnabled(true);
        this.buttonPlus.setEnabled(true);
        this.buttonOne.setEnabled(true);
        this.buttonTwo.setEnabled(true);
        this.buttonThree.setEnabled(true);
        this.buttonEqual.setEnabled(true);
        this.buttonZero.setEnabled(true);
        this.buttonDot.setEnabled(true);
        this.buttonSin.setEnabled(false);
        this.buttonCos.setEnabled(false);
        this.buttonTan.setEnabled(false);
        this.buttonCtg.setEnabled(false);
    }

    public void disable() {
        this.onRadioButton.setEnabled(true);
        this.offRadioButton.setEnabled(false);
        this.textField.setText("");
        this.label.setText(" ");
        this.buttonClear.setEnabled(true);
        this.buttonDelete.setEnabled(false);
        this.buttonDiv.setEnabled(false);
        this.buttonSqrt.setEnabled(false);
        this.buttonSquare.setEnabled(false);
        this.buttonReciprocal.setEnabled(false);
        this.buttonMinus.setEnabled(false);
        this.buttonSeven.setEnabled(true);
        this.buttonEight.setEnabled(true);
        this.buttonNine.setEnabled(true);
        this.buttonMul.setEnabled(false);
        this.buttonFour.setEnabled(true);
        this.buttonFive.setEnabled(true);
        this.buttonSix.setEnabled(true);
        this.buttonPlus.setEnabled(false);
        this.buttonOne.setEnabled(true);
        this.buttonTwo.setEnabled(true);
        this.buttonThree.setEnabled(true);
        this.buttonEqual.setEnabled(false);
        this.buttonZero.setEnabled(true);
        this.buttonDot.setEnabled(true);
        this.buttonSin.setEnabled(true);
        this.buttonCos.setEnabled(true);
        this.buttonTan.setEnabled(true);
        this.buttonCtg.setEnabled(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
