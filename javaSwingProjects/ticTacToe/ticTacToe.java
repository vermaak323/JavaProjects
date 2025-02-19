import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ticTacToe 
{
    JFrame frame = new JFrame();
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JButton [][] board = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;
    boolean gameOver= false;
    int turns=0;


    ticTacToe()
    {
        frame.setSize(600,650);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Tic-Tac-Toe");
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        textLabel.setText("Tic-Tac-Toe");
        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3,3));
        // boardPanel.setForeground(Color.black);
        boardPanel.setBackground(Color.darkGray);
        frame.add(boardPanel);



        //for loop starts here for making tile 
        for(int r=0; r<3; r++)
        {
            for(int c=0; c<3; c++)
            {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);
                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e)
                    { 
                        if (gameOver) return;
                        JButton tile= (JButton) e.getSource();
                        if(tile.getText()=="")
                        {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if(!gameOver)
                            {
                                currentPlayer = currentPlayer== playerX ? playerO: playerX;
                                textLabel.setText(currentPlayer+"'s turn.");
                            }
                            
                        }
                        
                    }
                });
            }
        }
        // for loop for making tile ends here 

    }

    void checkWinner()
    {
        //horizontal 
        for(int r=0; r<3; r++)
        {
            if(board[r][0].getText()=="") continue;

            if(board[r][0].getText()==board[r][1].getText()&&
            board[r][1].getText()==board[r][2].getText())
            {
                for(int i=0;i<3;i++)
                {
                    setWinner(board[r][i]);
                }
                gameOver=true;
                return;
            }
            
        }

        //vertical
        for(int c=0; c<3; c++)
        {
            if(board[0][c].getText()=="") continue;

            if(board[0][c].getText()==board[1][c].getText()&&
            board[1][c].getText()==board[2][c].getText())
            {
                for(int i=0;i<3;i++)
                {
                    setWinner(board[i][c]);
                }
                gameOver=true;
                return;
            }
            
        }


        //diagonally 
        if(board[0][0].getText()==board[1][1].getText()&&
        board[1][1].getText()==board[2][2].getText()&& board[0][0].getText()!="")
        {
            for(int i=0;i<3;i++)
            {
                setWinner(board[i][i]);
            }
            gameOver=true;
            return;

        }



        // antidiagonal 
        if(board[0][2].getText()==board[1][1].getText()&&
        board[1][1].getText()==board[2][0].getText()&& board[0][2].getText()!="")
        {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver=true;
            return;

        }

        if(turns==9)
        {
            for(int r=0; r<3; r++)
            {
                for(int c=0; c<3; c++)
                {
                    setTie(board[r][c]);
                }
            }
            gameOver=true;
            
        }        
    }
    
    void setWinner(JButton tile)
    {
        tile.setBackground(Color.gray);
        tile.setForeground(Color.green);
        textLabel.setText(currentPlayer+" is the winner!");
    }

    void setTie(JButton tile)
    {
        tile.setBackground(Color.gray);
        tile.setForeground(Color.orange);
        textLabel.setText("Tie");
    }


}
