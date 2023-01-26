import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

class Teacher_homepage1 extends JFrame{

    JButton b1,b2,b3;
    JLabel id,no,name,Db_id,Db_no,Db_name;
    JTextField Que_no,Question,opt1,opt2,opt3,opt4,ans;
    Connection con=null;
    Teacher_homepage1(int teach_id,int teach_no,String teach_name)
    {
        con=(Connection) Db.dbconnect();
        setTitle("Instruction Page");
        setLocation(380,120);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.orange);

        ImageIcon i1 = new ImageIcon("Images/logo.png");
        setIconImage(i1.getImage());

        Font fo = new Font("Times New Roman", Font.ITALIC, 40);
        Font fo1 = new Font("Times New Roman", Font.PLAIN, 30);
        Font fo2 = new Font("Times New Roman", Font.PLAIN, 18);

        JPanel panel1 = new JPanel();
        panel1.setBounds(0,0,250,560);
        panel1.setLayout(null);
        panel1.setBorder(BorderFactory.createLineBorder(Color.blue));
        panel1.setBackground(Color.cyan);
        add(panel1);

        JLabel stu_img = new JLabel();
        stu_img.setIcon(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\Teacher.png"));
        stu_img.setBounds(20, 15, 200, 100);
        panel1.add(stu_img);

        //
        id=new JLabel("Teacher Id:-");
        id.setFont(fo2);
        id.setBounds(5, 120, 110, 30);
        panel1.add(id);

        Db_id=new JLabel(String.valueOf(teach_id));
        Db_id.setFont(fo2);
        Db_id.setBounds(115, 120, 100, 30);
        panel1.add(Db_id);

        name=new JLabel("Teacher Name:-");
        name.setFont(fo2);
        name.setBounds(5, 150, 160, 30);
        panel1.add(name);

        Db_name=new JLabel(String.valueOf(teach_name));
        Db_name.setFont(fo2);
        Db_name.setBounds(5,180,160,30);
        panel1.add(Db_name);

        no=new JLabel("Teach_no:-");
        no.setFont(fo2);
        no.setBounds(5,210,100,30);
        panel1.add(no);

        Db_no=new JLabel(String.valueOf(teach_no));
        Db_no.setFont(fo2);
        Db_no.setBounds(110,210,100,30);
        panel1.add(Db_no);
        //

        b1=new JButton("Create Quiz");
        b1.setBounds(300,60,120,50);
        add(b1);

        b2=new JButton("Delete Question");
        b2.setBounds(300,120,120,50);
        add(b2);

        b3=new JButton("Delete Quiz");
        b3.setBounds(300,180,120,50);
        add(b3);

        JButton b4=new JButton("Back");
        b4.setBounds(5,510,100,30);
        panel1.add(b4);

        JLabel l1=new JLabel("Que_No:");
        l1.setBounds(470,60,50,50);
        add(l1);

        Que_no=new JTextField();
        Que_no.setBounds(530,60,180,40);
        add(Que_no);

        JLabel l2=new JLabel("Question:");
        l2.setBounds(470,130,60,50);
        add(l2);

        Question=new JTextField();
        Question.setBounds(530,130,180,40);
        add(Question);

        JLabel o1=new JLabel("Option 1 :");
        o1.setBounds(470,200,120,50);
        add(o1);

        opt1=new JTextField();
        opt1.setBounds(530,200,180,40);
        add(opt1);

        JLabel o2=new JLabel("Option 2 :");
        o2.setBounds(470,270,120,50);
        add(o2);

        opt2=new JTextField();
        opt2.setBounds(530,270,180,40);
        add(opt2);

        JLabel o3=new JLabel("Option 3 :");
        o3.setBounds(470,330,120,50);
        add(o3);

        opt3=new JTextField();
        opt3.setBounds(530,330,180,40);
        add(opt3);

        JLabel o4=new JLabel("Option 4 :");
        o4.setBounds(470,400,120,50);
        add(o4);

        opt4=new JTextField();
        opt4.setBounds(530,400,180,40);
        add(opt4);

        JLabel Answer=new JLabel("Answer :");
        Answer.setBounds(470,470,120,50);
        add(Answer);

        ans=new JTextField();
        ans.setBounds(530,470,180,40);
        add(ans);

        b1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int p1 = JOptionPane.showConfirmDialog(null,"Are you confirm to add Question in Quiz?","Confirm",JOptionPane.YES_NO_OPTION);
                if(p1==0)
                {
                    try {
                        String sql="insert into questions values (?,?,?,?,?,?,?)";
                        PreparedStatement pst= (PreparedStatement)con.prepareStatement(sql);
                        pst.setInt(1,Integer.parseInt(Que_no.getText()));
                        pst.setString(2,Question.getText());
                        pst.setString(3,opt1.getText());
                        pst.setString(4,opt2.getText());
                        pst.setString(5,opt3.getText());
                        pst.setString(6,opt4.getText());
                        pst.setString(7,ans.getText());

                        pst.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Question Inserted Successfully");
                        clearField();

                    }catch (Exception e1)
                    {
                       e1.printStackTrace();
                    }
                }
            }
        });

        b2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int p2 = JOptionPane.showConfirmDialog(null,"Do you really want to delete Question No :"+Integer.parseInt(Que_no.getText())+"","Delete",JOptionPane.YES_NO_OPTION);
                if(p2==0)
                {
                    try{
                        String sql="delete from questions where que_no=?";
                        PreparedStatement pst1= (PreparedStatement)con.prepareStatement(sql);
                        pst1.setInt(1,Integer.parseInt(Que_no.getText()));
                        pst1.executeUpdate();
                        JOptionPane.showMessageDialog(null,"Question no:"+Integer.parseInt(Que_no.getText())+" is Deleted Successfully");
                        clearField();

                    }catch (Exception e2)
                    {
                       e2.printStackTrace();
                    }
                }
                clearField();
            }
        });

        b3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int p3 = JOptionPane.showConfirmDialog(null,"Do you really want to delete all Data","Delete",JOptionPane.YES_NO_OPTION);
                if(p3==0)
                {
                    try{
                        String sql="truncate table questions";
                        PreparedStatement pst2= (PreparedStatement)con.prepareStatement(sql);
                        pst2.executeUpdate();

                        JOptionPane.showMessageDialog(null,"All Questions is Deleted Successfully.");
                        clearField();

                    }catch (Exception e3)
                    {
                        e3.printStackTrace();
                    }
                }
            }
        });

        b4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Teacher_login1 t1=new Teacher_login1();
                t1.setSize(800,600);
            }
        });
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    //Tp Empty the Textfield After Submit

    private void clearField()
    {
        Que_no.setText(null);
        Question.setText(null);
        opt1.setText(null);
        opt2.setText(null);
        opt3.setText(null);
        opt4.setText(null);
        ans.setText(null);
    }
}
public class Teacher_homepage {
    public static void main(String[] args) {
        int teach_id=0,teach_no=0;
        String teach_name="";
        Teacher_homepage1 h1 = new Teacher_homepage1(teach_id,teach_no,teach_name);
        h1.setSize(800, 600);
    }
}
