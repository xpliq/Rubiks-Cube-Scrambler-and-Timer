import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScramblerGUI implements ActionListener
{
    public static void main(String[] args)
    {
    	ScramblerGUI scramblerGUI = new ScramblerGUI();
    }

    //Attributes
    JFrame frame;
    JPanel scramblePanel;
    JPanel panel;
    JLabel scrambleLabel;
    JLabel timerLabel;
    JButton startTimer;
    JButton stopTimer;
    JButton resetTimer;
    Timer timer;
    JPanel buttonPanel;

    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d", minutes);
    String hours_string = String.format("%02d", hours);

    private boolean timerRunning = false;

    ScramblerGUI()
    {
        //Frame
        frame = new JFrame("Rubik's Cube Scrambler");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		frame.setLayout(new BorderLayout());
        frame.setResizable(true);

        
        //Wrapper Panel
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.X_AXIS));
        wrapperPanel.add(Box.createHorizontalGlue());

        //Panel
        scramblePanel = new JPanel();
        scramblePanel.setBackground(Color.gray);           
        scramblePanel.setPreferredSize(new Dimension(950, 100));
        scramblePanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));
        scramblePanel.setAlignmentX(JLabel.CENTER);

        //Button
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            //Start Timer
        startTimer = new JButton("START");
        startTimer.setBackground(Color.green);
        startTimer.setPreferredSize(new Dimension(100, 50));
        startTimer.addActionListener(this);
        
        buttonPanel.add(startTimer);

            //Stop Timer
        stopTimer = new JButton("STOP");
        stopTimer.setBackground(Color.pink);
        stopTimer.setPreferredSize(new Dimension(100, 50));
        stopTimer.addActionListener(this);

        buttonPanel.add(stopTimer);

            //Reset Timer
        resetTimer = new JButton("RESET");
        resetTimer.setBackground(Color.red);
        resetTimer.setPreferredSize(new Dimension(100, 50));
        resetTimer.addActionListener(this);

        buttonPanel.add(resetTimer);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        
        //Timer
            //Timer Label
        timerLabel = new JLabel("START");
        timerLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        timerLabel.setBounds(100,100,200,100);
        timerLabel.setFont(new Font("Verdana",Font.PLAIN,35));
        timerLabel.setBorder(BorderFactory.createBevelBorder(1));
        timerLabel.setOpaque(true);
        timerLabel.setHorizontalAlignment(JTextField.CENTER);
        frame.add(timerLabel);

        timer = new Timer(1000, new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
        {
            elapsedTime += 1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);
            timerLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
        }
        });
        
        //Scrambler to Text Label
        scramblePanel.setLayout(new BoxLayout(scramblePanel, BoxLayout.Y_AXIS));
        scramblePanel.add(Box.createVerticalGlue());

        scrambleLabel = new JLabel(Scrambler.scramble());

        scrambleLabel.setFont(new Font("Comic Sans", Font.BOLD, 24));
        scrambleLabel.setForeground(Color.black);
        scrambleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        scramblePanel.add(scrambleLabel);
        scramblePanel.add(Box.createVerticalGlue());

        wrapperPanel.add(scramblePanel);
        wrapperPanel.add(Box.createHorizontalGlue());
        
        frame.add(wrapperPanel, BorderLayout.NORTH);

        frame.setFocusable(true);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == startTimer || timerRunning == false)
        {
            timerRunning = true;
            start();
        }

        if(e.getSource() == stopTimer)
        {
            stop();
        }

        if(e.getSource() == resetTimer)
        {
            timerRunning = false;
            scrambleLabel.setText(Scrambler.scramble());
            reset();
        }
    }

    void start()
    {
        timer.start();
    }

    void stop()
    {
        timer.stop();
    }

    void reset()
    {
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);
        timerLabel.setText(hours_string + ":" + minutes_string + ":" + seconds_string);
    }
}