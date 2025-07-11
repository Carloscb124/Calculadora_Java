import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class Calculator {
    int boardWidth = 360;
    int boardHeigth = 540;

    Color customLigthGray = new Color(212, 212, 210);
    Color customDarkhGray = new Color(80, 80, 80);
    Color customBlack = new Color(28, 28, 28);
    Color customOrange = new Color(255, 149, 0);

    // Matrix Valores Dos Botões
    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};



    JFrame frame = new JFrame("Calculadora"); // Titulo
    JLabel displayLabel = new JLabel();
    JPanel displaypanel = new JPanel();
    JPanel buttonsPanel = new JPanel();

    // A+B, A-B, A*B, A/B 
    String A ="0";
    String operator = null;
    String B = null;

    // Construtor
    Calculator() {
        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeigth);
        frame.setLocationRelativeTo(null); //centraliza a janela
        frame.setResizable(false); //Não ajusta a janlea
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a janela no X
        frame.setLayout(new BorderLayout());

        //Style
        displayLabel.setBackground(customBlack);
        displayLabel.setForeground(Color.white); // Cor Texto
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80)); // Fonte
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);

        //Painel
        displaypanel.setLayout(new BorderLayout());
        displaypanel.add(displayLabel);
        frame.add(displaypanel, BorderLayout.NORTH);

        //Painel Botões
        buttonsPanel.setLayout(new GridLayout(5, 4));
        buttonsPanel.setBackground(customBlack);
        frame.add(buttonsPanel);

        for (int i = 0; i < buttonValues.length; i++){
            JButton button = new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN,30));
            button.setText(buttonValue);
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));
            
            if (Arrays.asList(topSymbols).contains(buttonValue)){
                button.setBackground(customLigthGray);
                button.setForeground(customBlack);
            }   
                else if (Arrays.asList(rightSymbols).contains(buttonValue)){
                    button.setBackground(customOrange);
                    button.setForeground(Color.WHITE);
            }  
                    else {
                    button.setBackground(customDarkhGray);
                    button.setForeground(Color.WHITE);
            }
            buttonsPanel.add(button);

            // Fazer os botões funcionarem
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();

                    if (Arrays.asList(rightSymbols).contains(buttonValue)){
                        if (buttonValue == "="){
                            if (A != null){
                                B = displayLabel.getText();
                                double numA = Double.parseDouble(A);
                                double numB = Double.parseDouble(B);

                                if (operator == "+"){
                                    displayLabel.setText(reomveZeroDecimal(numA+numB));
                                }
                                else if (operator == "-"){
                                    displayLabel.setText(reomveZeroDecimal(numA-numB));
                                }
                                else if (operator == "×"){
                                    displayLabel.setText(reomveZeroDecimal(numA*numB));
                                }
                                else if (operator == "÷"){
                                    displayLabel.setText(reomveZeroDecimal(numA/numB));
                                }
                            }
                        }
                        else if ("+-×÷".contains(buttonValue)){
                            if (operator == null){
                                A = displayLabel.getText();
                                displayLabel.setText("0");
                                B = "0";
                            }
                            operator = buttonValue;
                        }
                    }
                    else if (Arrays.asList(topSymbols).contains(buttonValue)){
                        if (buttonValue == "AC"){
                            clearAll();
                            displayLabel.setText("0");
                        }   
                        else if (buttonValue == "+/-"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay *= -1;
                            displayLabel.setText(reomveZeroDecimal(numDisplay));
                        }
                        else if (buttonValue == "%"){
                            double numDisplay = Double.parseDouble(displayLabel.getText());
                            numDisplay /= 100;
                            displayLabel.setText(reomveZeroDecimal(numDisplay));
                        }
                    }
                        else{ //digitou o .
                            if (buttonValue == "."){
                                if(!displayLabel.getText().contains(buttonValue)) {
                                    displayLabel.setText(displayLabel.getText() + buttonValue);
                                }
                            }
                                else if("0123456789".contains(buttonValue)){
                                        if (displayLabel.getText() == "0"){
                                        displayLabel.setText(buttonValue); // Inves de fazer 05 faz 5
                                        }
                                            else{
                                                displayLabel.setText(displayLabel.getText() + buttonValue);
                                            }
                                }
                        }
                }
            });
        }

    }

    // Funçao do AC
    void clearAll(){
        A = "0";
        operator = null;
        B = null;
    }

    String reomveZeroDecimal(double numDisplay){
        if (numDisplay % 1 == 0){
            return Integer.toString((int) numDisplay);
        }
        return Double.toString(numDisplay);
    }
}
