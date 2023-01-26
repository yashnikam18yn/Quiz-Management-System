import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

class Welcome_page1 extends JFrame{
    Welcome_page1()
    {
        setTitle("Quiz Management System");
        setLocation(380,120);
        setLayout(null);
        setResizable(false);

        Font fo = new Font("Times New Roman", Font.BOLD, 40);
        Font fo1 = new Font("Times New Roman", Font.BOLD, 30);
        Font fo2 = new Font("Times New Roman", Font.PLAIN, 18);


        ImageIcon i1 = new ImageIcon("Images/logo.png");
        setIconImage(i1.getImage());

        JLabel stu = new JLabel("*Welcome*");
        stu.setForeground(Color.white);
        stu.setBounds(310, 1, 200, 100);
        stu.setFont(fo);
        add(stu);

        //Background images add

        JLabel j1 = new JLabel("-Quiz Management System-");
        j1.setForeground(Color.white);
        j1.setFont(fo1);
        j1.setBounds(350, 90, 400, 100);
        add(j1);
/*
        JLabel quiz_img = new JLabel();
        quiz_img.setIcon(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\Quiz.jpg"));
        quiz_img.setBounds(200, 180, 360, 180);
        add(quiz_img);

*/
        JButton b1 = new JButton("Student");
        b1.setFont(fo2);
        b1.setBounds(350, 380, 100, 40);
        add(b1);

        JButton b2 = new JButton("Teacher");
        b2.setFont(fo2);
        b2.setBounds(480, 380, 100, 40);
        add(b2);

        JButton b3 = new JButton("Admin");
        b3.setFont(fo2);
        b3.setBounds(610, 380, 100, 40);
        add(b3);

        JLabel welcome_bg=new JLabel(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\welcome_bg.jpg"));
        add(welcome_bg);
        welcome_bg.setBounds(0,0,800,600);

        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Student_login1 s1 = new Student_login1();
                s1.setVisible(true);
                s1.setSize(800,600);
            }
        });

        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Teacher_login1 t1=new Teacher_login1();
                t1.setVisible(true);
                t1.setSize(800,600);
            }
        });

        b3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin_login1 a1=new Admin_login1();
                a1.setVisible(true);
                a1.setSize(800,600);
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
public class Welcome_Page {
    public static void main(String[] args) {
        Welcome_page1 w1 = new Welcome_page1();
        w1.setSize(800, 600);
    }
}