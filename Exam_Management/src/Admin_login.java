import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

class Admin_login1 extends JFrame{
    JTextField user, password;
    public int adm_id,adm_no;
    public String adm_name="";
    JLabel adm;
    Connection con=null;
    Admin_login1()
    {
        con=(Connection) Db.dbconnect();
        setTitle("Admin Login");
        setLocation(380,120);
        setLayout(null);
        setResizable(false);

        Font fo = new Font("Times New Roman", Font.BOLD, 30);
        Font fo1 = new Font("Times New Roman", Font.BOLD, 18);


        ImageIcon i1 = new ImageIcon("Images/logo.png");
        setIconImage(i1.getImage());


        adm = new JLabel("Admin Login");
        adm.setBounds(300, 1, 200, 100);
        //adm.setForeground(Color.GREEN);
        adm.setForeground(Color.green);
        adm.setFont(fo);
        add(adm);

        JLabel Adm_img = new JLabel();
        Adm_img.setIcon(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\Admin.jpg"));
        Adm_img.setBounds(355, 90, 200, 100);
        add(Adm_img);


        JLabel j1 = new JLabel("User Name :");
        j1.setBounds(300, 200, 100, 40);
        j1.setForeground(Color.black);
        j1.setFont(fo1);
        add(j1);

        JLabel j2 = new JLabel("Password :");
        j2.setBounds(300, 300, 100, 40);
        j2.setForeground(Color.black);
        j2.setFont(fo1);
        add(j2);

        user = new JTextField();
        user.setBounds(400, 200, 120, 40);
        add(user);

        password = new JPasswordField();
        password.setBounds(400, 300, 120, 40);
        add(password);


        JButton b1 = new JButton("Login");
        b1.setFont(fo1);
        b1.setBounds(300, 380, 100, 40);
        add(b1);

        JButton b2 = new JButton("Back");
        b2.setFont(fo1);
        b2.setBounds(420, 380, 100, 40);
        add(b2);

        JLabel Adm_bg=new JLabel(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\Admin_bg.jpg"));
        add(Adm_bg);
        Adm_bg.setBounds(0,0,800,600);

        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String usr= user.getText();
                    String pwd=String.valueOf(password.getText());
                    PreparedStatement pst=(PreparedStatement) con.prepareStatement("select * from admin_login where adm_username =? and adm_password=?");
                    pst.setString(1,usr);
                    pst.setString(2,pwd);
                    ResultSet r = pst.executeQuery();
                    if (r.next())
                    {
                        adm_id=r.getInt("ad_id");
                        adm_no=r.getInt("adm_no");
                        adm_name=r.getString("adm_name");
                        JOptionPane.showMessageDialog(null,"Login Successfully"+adm_name+"");
                        dispose();
                        Amin_homepage1 h1=new Amin_homepage1(adm_id,adm_name,adm_no);
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
               Welcome_page1 w1 =new Welcome_page1();
               w1.setSize(800, 600);
           }
       });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
public class Admin_login {
    public static void main(String[] args) {

        Admin_login1 a=new Admin_login1();
        a.setSize(800, 600);
    }
}
