package Calculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Calculator extends JPanel {
	JPanel btPanel;
	JTextField jt;
	JTextField show;
	JButton[] btList;
	String currentInput = "";
	String operator = "";
	double operand1 = 0;

	String[] labels = { "C", "DEL", "(", ")", "<=", "1/x", "x²", "√", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2",
			"3", "-", "0", ".", "=", "+" };

	public Calculator() {
		btPanel = new JPanel();
		btList = new JButton[24];
		jt = new JTextField("");
		jt.setFont(new Font("Digital-7", Font.BOLD, 20));
		jt.setHorizontalAlignment(SwingConstants.RIGHT);
		jt.setEditable(false);
		this.setLayout(new BorderLayout());
		this.add(jt, BorderLayout.NORTH);
		
		btPanel.setLayout(new GridLayout(5, 5, 5, 5));
		for (int i = 0; i < labels.length; i++) {
			JButton b = new JButton(labels[i]);
			b.addActionListener(this::actionPerformed);
			if (labels[i].equals("C") || labels[i].equals("DEL")) b.setBackground(Color.RED);
			else if ("0123456789.".contains(labels[i])) b.setBackground(Color.GRAY);
			else if ("<=>1/x x²√+-*/".contains(labels[i])) b.setBackground(Color.LIGHT_GRAY);
			else b.setBackground(Color.GREEN);
			btPanel.add(b);
			btList[i] = b;
		}
		this.add(btPanel, BorderLayout.CENTER);
		
//		for (int i = 0; i < labels.length; i++) {
//			JButton b = new JButton(labels[i]);
//			if (labels.equals("C") || labels.equals("DEL")) {
//				b.setBackground(Color.RED);
//			}
//			if (labels.equals("0") || labels.equals("1") || labels.equals("2") || labels.equals("3")
//					|| labels.equals("4") || labels.equals("5") || labels.equals("6") || labels.equals("7")
//					|| labels.equals("8") || labels.equals("9") || labels.equals(".")) {
//				b.setBackground(Color.gray);
//			}
//			if (labels.equals("<=") || labels.equals("1/x") || labels.equals("x²") || labels.equals("√")
//					|| labels.equals("+") || labels.equals("-") || labels.equals("*") || labels.equals("/")) {
//				b.setBackground(Color.GRAY);
//			} else {
//				b.setBackground(Color.GREEN);
//			}
//			btPanel.add(b);
//			btList[i] = b;
//
//		}
	}

	public String evaluateExpression(String expression) {
		try {
			System.out.println("Evaluating: " + expression);
			ScriptEngineManager mg = new ScriptEngineManager();
			ScriptEngine eg = mg.getEngineByName("js");
			Object rs = eg.eval(expression);
			return rs.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "Error";
		}
	}

	public void actionPerformed(ActionEvent x) {
		String cm = x.getActionCommand();
		String ct = jt.getText();
		if (cm.equals("C")) {
			jt.setText("");
			currentInput = "";
			operand1 = 0;
			operator = "";
		} else if (cm.equals("DEL")) {
			if (!ct.isEmpty()) {
				jt.setText(ct.substring(0, ct.length() - 1));
			}
		} else if (cm.equals("=")) {
			try {
				System.out.println(jt.getText());
				String rs = evaluateExpression(jt.getText());
				jt.setText(rs);
			} catch (Exception ex) {
				jt.setText("Error");
			}
		} else jt.setText(jt.getText() + cm);
	}
}
