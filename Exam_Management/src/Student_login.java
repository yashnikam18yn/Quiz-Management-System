import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Student_login1 extends JFrame{

    JTextField user;
    JTextField password;
    Connection con=null;
    public  int roll_no,stu_id;
    public String name;
    Student_login1()
    {
        con=(Connection) Db.dbconnect();
        setTitle("Student Login");
        setLocation(380,120);
        setLayout(null);
        setResizable(false);

        Font fo = new Font("Times New Roman",Font.BOLD,30);
        Font fo1 = new Font("Times New Roman",Font.BOLD,16);
        Font fo2=new Font("Times New Roman",Font.BOLD,17);


        ImageIcon i1 = new ImageIcon("Images/logo.png");
        setIconImage(i1.getImage());

        JLabel stu = new JLabel("Student Login ");
        stu.setBounds(300, 1, 200, 100);
        stu.setForeground(Color.white);
        stu.setFont(fo);
        add(stu);

        JLabel stu_img = new JLabel();
        stu_img.setIcon(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\Student.png"));
        stu_img.setBounds(355, 90, 200, 100);
        add(stu_img);

        JLabel j1 = new JLabel("User Name :");
        j1.setBounds(300, 200, 100, 40);
        j1.setForeground(Color.white);
        j1.setFont(fo1);
        add(j1);

        JLabel j2 = new JLabel("Password :");
        j2.setBounds(300, 300, 100, 40);
        j2.setForeground(Color.white);
        j2.setFont(fo1);
        add(j2);

        user = new JTextField();
        user.setBounds(400,200,120,40);
        add(user);

        password = new JPasswordField();
        password.setBounds(400,300,120,40);
        add(password);


        JButton b1 = new JButton("Login");
        b1.setFont(fo1);
        b1.setBounds(300,380,100,40);
        b1.setBorderPainted(true);

        add(b1);


        JButton b2 = new JButton("Back");
        b2.setFont(fo1);
        b2.setBounds(420,380,100,40);
        add(b2);

        JButton b3=new JButton("Register");
        b3.setFont(fo2);
        b3.setBounds(360,440,100,40);
        add(b3);

        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String usr= user.getText();
                    String pwd=String.valueOf(password.getText());
                    PreparedStatement pst=(PreparedStatement) con.prepareStatement("select * from student_login where stu_username =? and stu_password=?");
                    pst.setString(1,usr);
                    pst.setString(2,pwd);
                    ResultSet r = pst.executeQuery();

                    if (r.next())
                    {
                        stu_id = r.getInt("stu_id");
                        roll_no = r.getInt("stu_rollno");
                        String name=r.getString("stu_name");
                        JOptionPane.showMessageDialog(null,"Login Successfully"+name);
                        dispose();
                        Student_exam_page1 p1=new Student_exam_page1(stu_id,roll_no,name);
                        p1.setSize(900,600);
                    }else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid User name and passWord");
                    }
                }catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
        JLabel stu_bg=new JLabel(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\student_login.jpg"));
        add(stu_bg);
        stu_bg.setBounds(0,0,800,600);

        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Welcome_page1 w2 =new Welcome_page1();
                w2.setSize(800, 600);
            }
        });

        b3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                registration_form1 f1=new registration_form1();
                f1.setSize(800,600);
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}

public class Student_login {
    public static void main(String[] args) {

        Student_login1 s = new Student_login1();
        s.setSize(800,600);
    }
}
