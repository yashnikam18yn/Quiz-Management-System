import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Teacher_login1 extends JFrame{
    JTextField user,password;
    public int teach_id,teach_no;
    String teach_name;
    Connection con=null;
    Teacher_login1()
    {
        con=(Connection) Db.dbconnect();
        setTitle("Teacher Login");
        setLocation(380,120);
        setLayout(null);
        setResizable(false);

        Font fo = new Font("Times New Roman", Font.BOLD, 30);
        Font fo1 = new Font("Times New Roman", Font.BOLD, 16);


        ImageIcon i1 = new ImageIcon("Images/logo.png");
        setIconImage(i1.getImage());

        JLabel tea = new JLabel("Teacher Login ");
        tea.setBounds(430, 1, 200, 100);
        tea.setForeground(Color.white);
        tea.setFont(fo);
        add(tea);

        JLabel Tea_img = new JLabel();
        Tea_img.setIcon(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\Teacher.png"));
        Tea_img.setBounds(480, 90, 200, 100);
        add(Tea_img);


        JLabel j1 = new JLabel("User Name :");
        j1.setBounds(430, 200, 100, 40);
        j1.setForeground(Color.white);
        j1.setFont(fo1);
        add(j1);

        JLabel j2 = new JLabel("Password :");
        j2.setBounds(430, 300, 100, 40);
        j2.setForeground(Color.white);
        j2.setFont(fo1);
        add(j2);

        user = new JTextField();
        user.setBounds(540, 200, 120, 40);
        add(user);

        password = new JPasswordField();
        password.setBounds(540, 300, 120, 40);
        add(password);


        JButton b1 = new JButton("Login");
        b1.setFont(fo1);
        b1.setBounds(430, 380, 100, 40);
        add(b1);

        JButton b2 = new JButton("Back");
        b2.setFont(fo1);
        b2.setBounds(550, 380, 100, 40);
        add(b2);

        JLabel tec_bg=new JLabel(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\teacher_login.jpg"));
        add(tec_bg);
        tec_bg.setBounds(0,0,800,600);

        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String usr= user.getText();
                    String pwd=String.valueOf(password.getText());
                    PreparedStatement pst=(PreparedStatement) con.prepareStatement("select * from Teacher_login where teach_username =? and teach_password=?");
                    pst.setString(1,usr);
                    pst.setString(2,pwd);
                    ResultSet r = pst.executeQuery();
                    if (r.next())
                    {
                        teach_id=r.getInt("teach_id");
                        teach_no=r.getInt("tech_no");
                        teach_name=r.getString("teach_name");
                        JOptionPane.showMessageDialog(null,"Login Successfully"+teach_name+"");
                        dispose();
                        Teacher_homepage1 h1=new Teacher_homepage1(teach_id,teach_no,teach_name);
                        h1.setSize(800,600);
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

        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Welcome_page1 w2 =new Welcome_page1();
                w2.setSize(800, 600);
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}

public class Teacher_login {
    public static void main(String[] args) {
        Teacher_login1 t=new Teacher_login1();
        t.setSize(800, 600);
    }
}
