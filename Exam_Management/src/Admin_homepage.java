import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


class Amin_homepage1 extends JFrame{
    JLabel id,no,name,Db_id,Db_no,Db_name,stu,teach;
    JTextField Db_stu,Db_teach;
    JPanel p1,p2,p3,p4;
    JButton b1,b2,b3,b4;
    JTabbedPane tp;
    String stu_id,stu_roll,stu_name,stu_username,stu_password,stu_marks;
    String teach_id,teach_no,teach_name,teach_username,teach_password;
    Connection con=null;
    Amin_homepage1(int adm_id, String adm_name, int adm_no)
    {
        con=(Connection) Db.dbconnect();
        setTitle("Admin Page");
        setLocation(380,120);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.lightGray);
        Font fo = new Font("Times New Roman", Font.ITALIC, 40);
        Font fo1 = new Font("Times New Roman", Font.PLAIN, 30);
        Font fo2 = new Font("Times New Roman", Font.PLAIN, 18);

        ImageIcon i1 = new ImageIcon("Images/logo.png");
        setIconImage(i1.getImage());

        JPanel panel1 = new JPanel();
        panel1.setBounds(0,0,250,560);
        panel1.setLayout(null);
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setBackground(Color.cyan);
        add(panel1);


        JLabel adm_img = new JLabel();
        adm_img.setIcon(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\Admin.jpg"));
        adm_img.setBounds(20, 15, 200, 100);
        panel1.add(adm_img);

        id=new JLabel("Admin Id:-");
        id.setFont(fo2);
        id.setBounds(5, 120, 110, 30);
        panel1.add(id);

        Db_id=new JLabel(String.valueOf(adm_id));
        Db_id.setFont(fo2);
        Db_id.setBounds(115, 120, 100, 30);
        panel1.add(Db_id);

        name=new JLabel("Admin Name:-");
        name.setFont(fo2);
        name.setBounds(5, 150, 160, 30);
        panel1.add(name);

        Db_name=new JLabel(String.valueOf(adm_name));
        Db_name.setFont(fo2);
        Db_name.setBounds(5,180,160,30);
        panel1.add(Db_name);

        no=new JLabel("Teach_no:-");
        no.setFont(fo2);
        no.setBounds(5,210,100,30);
        panel1.add(no);

        Db_no=new JLabel(String.valueOf(adm_no));
        Db_no.setFont(fo2);
        Db_no.setBounds(110,210,100,30);
        panel1.add(Db_no);

        p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(255,153,204));

        p2=new JPanel();
        p2.setLayout(null);
        p2.setBackground(new Color(204,0,102));

        p3=new JPanel();
        p3.setLayout(null);
        p3.setBackground(new Color(204,153,255));

        p4=new JPanel();
        p4.setLayout(null);
        p4.setBackground(new Color(102,0,204));

        tp=new JTabbedPane();
        tp.setBounds(251,0,530,560);
        tp.add("Show Student Info",p1);
        tp.add("Delete Student Info",p2);
        tp.add("Show Teacher Info",p3);
        tp.add("Delete Teacher Info",p4);
        add(tp);

        b1=new JButton("Show Student Information");
        b1.setFont(fo2);
        b1.setBounds(120,30,240,40);
        p1.add(b1);

        stu=new JLabel("Enter Student Id:-");
        stu.setFont(fo2);
        stu.setForeground(Color.green);
        stu.setBounds(60,30,200,40);
        p2.add(stu);

        Db_stu=new JTextField();
        Db_stu.setBounds(240,30,100,40);
        p2.add(Db_stu);

        teach=new JLabel("Enter Teacher Id:");
        teach.setFont(fo2);
        teach.setForeground(Color.green);
        teach.setBounds(60,30,200,40);
        p4.add(teach);

        Db_teach=new JTextField();
        Db_teach.setBounds(240,30,100,40);
        p4.add(Db_teach);


        b2=new JButton("Delete Student Information");
        b2.setFont(fo2);
        b2.setBounds(120,100,240,40);
        p2.add(b2);

        b3=new JButton("Show Teacher Information");
        b3.setFont(fo2);
        b3.setBounds(120,30,240,40);
        p3.add(b3);

        b4=new JButton("Delete Teacher Information");
        b4.setFont(fo2);
        b4.setBounds(120,100,240,40);
        p4.add(b4);

        JButton back=new JButton("Back");
        back.setBounds(5,510,100,30);
        panel1.add(back);

        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{

                    String[][] row={{stu_id,stu_roll,stu_name,stu_username,stu_password,stu_marks}};
                    String[] col={stu_id,stu_roll,stu_name,stu_username,stu_password,stu_marks};

                    DefaultTableModel model=new DefaultTableModel(row,col);
                    JTable table1=new JTable(model);
                    table1.setBounds(30,100,400,100);
                    table1.setBorder(BorderFactory.createLineBorder(Color.black));
                    ScrollPane sp=new ScrollPane();
                    sp.setBounds(30,100,400,100);
                    sp.add(table1);
                    p1.add(sp);

                    PreparedStatement pst= con.prepareStatement("select * from student_login");
                    ResultSet r=pst.executeQuery();

                    while (r.next())
                    {
                        stu_id=r.getString("stu_id");
                        stu_roll=r.getString("stu_rollno");
                        stu_name=r.getString("stu_name");
                        stu_username=r.getString("stu_username");
                        stu_password=r.getString("stu_password");
                        stu_marks=r.getString("marks");

                        String[] tbData={stu_id,stu_roll,stu_name,stu_username,stu_password,stu_marks};
                        model.addRow(tbData);
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
                try{
                    int student_id= Integer.parseInt(Db_stu.getText());
                   PreparedStatement pst= con.prepareStatement("delete from student_login where stu_id="+student_id+"");
                   pst.executeUpdate();
                   JOptionPane.showMessageDialog(null,"Data of student having id:"+student_id+"deleted successfully");

                }catch (Exception e2)
                {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Check Id You Entered!!");
                }
            }
        });

        b3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e3) {
                try {
                    String[][] row = {{teach_id, teach_no, teach_name, teach_username, teach_password}};
                    String[] col = {teach_id, teach_no, teach_name, teach_username, teach_password};

                    DefaultTableModel model2 = new DefaultTableModel(row, col);
                    JTable table2 = new JTable(model2);
                    table2.setBounds(30, 100, 400, 100);
                    table2.setBorder(BorderFactory.createLineBorder(Color.black));
                    ScrollPane sp2 = new ScrollPane();
                    sp2.setBounds(30, 100, 400, 100);
                    sp2.add(table2);
                    p3.add(sp2);

                    PreparedStatement pst = con.prepareStatement("select * from teacher_login");
                    ResultSet r= pst.executeQuery();

                    while (r.next()) {
                        teach_id= r.getString("teach_id");
                        teach_no = r.getString("tech_no");
                        teach_name = r.getString("teach_name");
                        teach_username = r.getString("teach_username");
                        teach_password = r.getString("teach_password");


                        String[] tbData = {teach_id, teach_no, teach_name, teach_username, teach_password};
                        model2.addRow(tbData);
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
        });

        b4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int teacher_id= Integer.parseInt(Db_teach.getText());
                    PreparedStatement pst= con.prepareStatement("delete from teacher_login where teach_id="+teacher_id+"");
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null,"Data of Teacher having id:"+teacher_id+"deleted successfully");

                }catch (Exception e2)
                {
                    e2.printStackTrace();
                    JOptionPane.showMessageDialog(null,"Check Id You Entered!!");
                }
            }
        });

        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Admin_login1 a1=new Admin_login1();
                a1.setSize(800,600);
            }
        });

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

public class Admin_homepage {
    public static void main(String[] args) {
         int adm_id=0,adm_no=0;
        String adm_name="";
     Amin_homepage1 a1=new Amin_homepage1(adm_id,adm_name,adm_no);
     a1.setSize(800,600);
    }
}
