package group.chatting.application;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class UserThree extends JFrame implements ActionListener,Runnable{
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea a1;
    
    BufferedWriter writer;
    BufferedReader reader;
    
    UserThree(){
        p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7, 94,84));
        p1.setBounds(0, 0, 450, 70);
        add(p1);
        getContentPane().setBackground(Color.WHITE);
        
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/3.png"));
        Image i2=i1.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i3=new  ImageIcon(i2);
        JLabel l1=new JLabel(i3);
        l1.setBounds(5,15,30,30);
        l1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent ae){
                System.exit(0);
            }
        });
        p1.add(l1);
        
        
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/1.png"));
        Image i5=i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon i6=new  ImageIcon(i5);
        JLabel l2=new JLabel(i6);
        l2.setBounds(40,5,60,60);
        p1.add(l2);
        
        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/video.png"));
        Image i8=i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9=new  ImageIcon(i8);
        JLabel l5=new JLabel(i9);
        l5.setBounds(290,20,30,30);
        p1.add(l5);
        
        ImageIcon i10=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/phone.png"));
        Image i11=i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12=new  ImageIcon(i11);
        JLabel l6=new JLabel(i12);
        l6.setBounds(350,20,35,30);
        p1.add(l6);
        
        ImageIcon i13=new ImageIcon(ClassLoader.getSystemResource("group/chatting/application/icons/3icon.png"));
        Image i14=i13.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon i15=new  ImageIcon(i14);
        JLabel l7=new JLabel(i15);
        l7.setBounds(410,20,13,25);
        p1.add(l7);
        
        JLabel l3=new JLabel("Mirzapur");
        l3.setFont(new Font("SAN_SERIF",Font.BOLD,20));
        l3.setForeground(Color.WHITE);
        l3.setBounds(110,15,100,20);
        p1.add(l3);
        
        JLabel l4=new JLabel(" Bablu, Kaleen, Guddu, Shukla");
        l4.setFont(new Font("SAN_SERIF",Font.PLAIN,14));
        l4.setForeground(Color.WHITE);
        l4.setBounds(110,35,160,20);
        p1.add(l4);
        
        a1=new JTextArea();
        a1.setBounds(5,75,440,570);
        a1.setEditable(false);
        //a1.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        a1.setLineWrap(true);
        a1.setWrapStyleWord(true);
        a1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        add(a1);
        
        t1=new JTextField();
        t1.setBounds(10,655,310,40);
        t1.setFont(new Font("SAN_SERIF",Font.PLAIN,16));
        add(t1);
        
        b1=new JButton("Send");
        b1.setBounds(330,657,110,35);
        b1.setBackground(new Color(7, 94,84));
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("SAN_SERIF",Font.BOLD,15));
        b1.addActionListener(this);
        add(b1);
        
        setLayout(null);
        setSize(450,700);
        setLocation(950,200);
        setUndecorated(true);
        setVisible(true);
        
        try{
            Socket socketClient=new Socket("localhost",2003);
            writer=new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
            reader=new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        }catch(Exception e){}
        
    }
    

    public void actionPerformed(ActionEvent ae){
        String str="Bablu Bhaiya \n" + t1.getText();
        t1.setText("");
        try{
            writer.write(str);
            writer.write("\r\n");
            writer.flush();
        }catch(Exception e){}
    }
    
    public void run(){
        try{
            String msg="";
            while((msg=reader.readLine())!=null)
                a1.append(msg +"\n");
        }catch(Exception e){}
    }
    
    public static void main(String []args){
        UserThree one=new UserThree();
        Thread t1=new Thread(one);
        t1.start();
        
    }
}
