import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

class registration_form1 extends JFrame{
    JTextField id,roll_no,name,username,password;
    Connection con=null;
    registration_form1()
    {
        con=(Connection) Db.dbconnect();
        setTitle("Registration Page");
        setLocation(380,120);
        setLayout(null);
        setResizable(false);
        ImageIcon i1 = new ImageIcon("Images/logo.png");
        setIconImage(i1.getImage());

        //getContentPane().setBackground(Color.PINK);
        Font fo = new Font("Times New Roman", Font.ITALIC, 40);
        Font fo1 = new Font("Times New Roman", Font.PLAIN, 30);
        Font fo2 = new Font("Times New Roman", Font.PLAIN, 18);

        JLabel stu_nm = new JLabel("Register ");
        stu_nm.setBounds(320, 1, 200, 50);
        stu_nm.setFont(fo1);
        add(stu_nm);

        /*
        JLabel j1=new JLabel("User_id:");
        j1.setBounds(260,70,150,40);
        j1.setFont(fo2);
        add(j1);

        id=new JTextField();
        id.setBounds(350,70,140,40);
        add(id);
          */
        JLabel j2=new JLabel("Roll-No:");
        j2.setBounds(260,140,150,40);
        j2.setFont(fo2);
        add(j2);

        roll_no=new JTextField();
        roll_no.setBounds(350,140,140,40);
        add(roll_no);

        JLabel j3=new JLabel("Name:");
        j3.setBounds(260,210,150,40);
        j3.setFont(fo2);
        add(j3);

        name=new JTextField();
        name.setBounds(350,210,140,40);
        add(name);

        JLabel j4=new JLabel("UserName:");
        j4.setBounds(260,280,150,40);
        j4.setFont(fo2);
        add(j4);

        username=new JTextField();
        username.setBounds(350,280,140,40);
        add(username);

        JLabel j5=new JLabel("Password:");
        j5.setBounds(260,350,150,40);
        j5.setFont(fo2);
        add(j5);

        password=new JTextField();
        password.setBounds(350,350,140,40);
        add(password);

        JButton b1=new JButton("SingUp");
        b1.setFont(fo1);
        b1.setBounds(260,440,140,40);
        add(b1);

        JButton b2 = new JButton("Back");
        b2.setFont(fo1);
        b2.setBounds(430,440,100,40);
        add(b2);

        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String sql = "insert into Student_login values(?,?,?,?,?)";
                    PreparedStatement pst=((PreparedStatement) con.prepareStatement(sql));
                    pst.setString(1,null);
                   // pst.setInt(1,Integer.parseInt(id.getText()));
                    pst.setString(2,roll_no.getText());
                    pst.setString(3,name.getText());
                    pst.setString(4,username.getText());
                    pst.setString(5,password.getText());
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Register Successfully");
                    clearField();

                }catch (Exception e3)
                {
                    e3.printStackTrace();
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

    private void clearField()
    {
       // id.setText(null);
        roll_no.setText(null);
        name.setText(null);
        username.setText(null);
        password.setText(null);

    }
}
public class registration_form {
    public static void main(String[] args) {
    registration_form1 f1=new registration_form1();
    f1.setSize(800,600);
    }
}
