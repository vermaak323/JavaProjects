import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Calculator 
{
    JFrame frame = new JFrame();
    JPanel buttonPanel= new JPanel();
    JPanel displayPanel = new JPanel();
    JLabel displayLabel = new JLabel();

    String[] buttonValues = {
        "AC", "+/-", "%", "÷", 
        "7", "8", "9", "×", 
        "4", "5", "6", "-",
        "1", "2", "3", "+",
        "0", ".", "√", "="
    };
    String[] rightSymbols = {"÷", "×", "-", "+", "="};
    String[] topSymbols = {"AC", "+/-", "%"};
    String A= "0";
    String B= null;
    String operator=null;

    Color customLightGray = new Color(212, 212, 210);
    Color customDarkGray = new Color(80, 80, 80);
    Color customBlack = new Color(28,28,28);
    Color customOrange = new Color(255, 149, 0);

    Calculator()
    {
        frame.setTitle("Simple Calculator");
        frame.setSize(360, 540);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        displayLabel.setBackground(customDarkGray);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.PLAIN, 80));
        displayLabel.setHorizontalAlignment(JLabel.RIGHT);
        displayLabel.setText("0");
        displayLabel.setOpaque(true);
       
        displayPanel.setLayout(new BorderLayout());
        displayPanel.add(displayLabel);
        frame.add(displayPanel, BorderLayout.NORTH);

        buttonPanel.setLayout(new GridLayout(5,4));
        buttonPanel.setBackground(customBlack);
        frame.add(buttonPanel);

        for(int i=0; i<buttonValues.length; i++)
        {
            JButton button= new JButton();
            String buttonValue = buttonValues[i];
            button.setFont(new Font("Arial", Font.PLAIN, 30));
            button.setFocusable(false);
            button.setBorder(new LineBorder(customBlack));
            button.setText(buttonValue);

            if(Arrays.asList(topSymbols).contains(buttonValue))
            {
                button.setBackground(customLightGray);
                button.setForeground(customBlack);
            }
            else if(Arrays.asList(rightSymbols).contains(buttonValue))
            {
                button.setBackground(customOrange);
                button.setForeground(customBlack);
            }
            else
            {
                button.setBackground(customDarkGray);
                button.setForeground(customLightGray);
            }

            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton button = (JButton) e.getSource();
                    String buttonValue = button.getText();

                    if(Arrays.asList(topSymbols).contains(buttonValue))
                    {
                        if(buttonValue=="AC")
                        {
                            clearAll();
                            displayLabel.setText("0");
                        }
                        else if(buttonValue=="+/-")
                        {
                            double numDisplay= Double.parseDouble(displayLabel.getText());
                            numDisplay*=-1;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }
                        else if(buttonValue=="%")
                        {
                            double numDisplay= Double.parseDouble(displayLabel.getText());
                            numDisplay/=100;
                            displayLabel.setText(removeZeroDecimal(numDisplay));

                        }

                    }
                    else if(Arrays.asList(rightSymbols).contains(buttonValue))
                    {
                        if(buttonValue=="=")
                        {
                            if(A!=null)
                            {
                                B=displayLabel.getText();
                                double numA= Double.parseDouble(A);
                                double numB= Double.parseDouble(B);

                                if(operator=="+")
                                {
                                    displayLabel.setText(removeZeroDecimal(numA+numB));
                                }
                                if(operator=="-")
                                {
                                    displayLabel.setText(removeZeroDecimal(numA-numB));
                                }
                                if(operator=="×")
                                {
                                    displayLabel.setText(removeZeroDecimal(numA*numB));
                                }
                                if(operator=="÷")
                                {
                                    displayLabel.setText(removeZeroDecimal(numA/numB));
                                }

                            }

                        }
                        else if("+-×÷".contains(buttonValue))
                        {
                            if(operator==null)
                            {
                                A=displayLabel.getText();
                                displayLabel.setText("0");
                                B="0";

                            }
                            operator=buttonValue;                        }

                    }
                    else
                    {
                        if(buttonValue==".")
                        {
                            if(!displayLabel.getText().contains(buttonValue))
                            {
                                displayLabel.setText(displayLabel.getText()+buttonValue);
                            }
                        }
                        else if("0123456789".contains(buttonValue))
                        {
                            if(displayLabel.getText()=="0")
                            {
                                displayLabel.setText(buttonValue);
                            }
                            else
                            {
                                displayLabel.setText(displayLabel.getText()+buttonValue);
                            }
                        }
                    }
                }
            }
            );

            buttonPanel.add(button);
        }

        frame.setVisible(true);
    }

    void clearAll()
    {
        A="0";
        operator=null;
        B=null;         
    }
    String removeZeroDecimal(double numDisplay)
    {
        if (numDisplay%1==0)
        {
            return Integer.toString((int) numDisplay);
        } 
        return Double.toString(numDisplay);
    }
    
}


