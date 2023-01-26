import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

class Student_exam_page1 extends JFrame {
    JPanel p1;
    public int que_id = 1;
    JLabel j1, j2, name, Db_name, Roll_no, DbRoll_no, date_lbl, date_lbl1, time_taken, min_lbl, sec_lbl, colon_lbl,st_id,stu_id;
    JRadioButton b1, b2, b3, b4;
    ButtonGroup g1;
    JButton save_next, clear, submit;
   // public int marks = 0;
    public int marks;
    public String answer;
    String stu_ans = "";
    public int min = 0;
    public int sec = 0;
    Timer time;
    Connection con = null;

    Student_exam_page1(final int student_id, int roll_no, String stu_name) {
        con = (Connection) Db.dbconnect();
        setTitle("Student Homepage");
        setLocation(280, 120);
        setLayout(null);
        setResizable(false);

        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();

        Font fo1 = new Font("Times New Roman", Font.BOLD, 18);
        Font fo2 = new Font("Times New Roman", Font.PLAIN, 18);
        Font fo3 = new Font("Times New Roman", Font.BOLD, 15);

        ImageIcon i1 = new ImageIcon("Images/logo.png");
        setIconImage(i1.getImage());

        p1 = new JPanel();
        p1.setBounds(0, 0, 300, 564);
        p1.setLayout(null);
        p1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        p1.setBackground(Color.green);
        add(p1);

        JLabel stu_img = new JLabel();
        stu_img.setIcon(new ImageIcon("C:\\Users\\yash\\Desktop\\Exam_Management\\Images\\Student.png"));
        stu_img.setBounds(20, 15, 200, 100);
        p1.add(stu_img);

        name = new JLabel("Student Name:-");
        name.setFont(fo3);
        name.setBounds(5, 120, 110, 30);
        p1.add(name);

        Db_name = new JLabel(stu_name);
        Db_name.setFont(fo3);
        Db_name.setBounds(115, 120, 100, 30);
        p1.add(Db_name);

        Roll_no = new JLabel("RollNo:-");
        Roll_no.setFont(fo3);
        Roll_no.setBounds(5, 140, 110, 30);
        p1.add(Roll_no);

        DbRoll_no = new JLabel(String.valueOf(roll_no));
        DbRoll_no.setFont(fo3);
        DbRoll_no.setBounds(115, 140, 100, 30);
        p1.add(DbRoll_no);

        stu_id=new JLabel("Student_Id:-");
        stu_id.setFont(fo3);
        stu_id.setBounds(5,160,110,30);
        p1.add(stu_id);

        st_id=new JLabel(String.valueOf(student_id));
        st_id.setFont(fo3);
        st_id.setBounds(115,160,100,30);
        p1.add(st_id);

        date_lbl = new JLabel("Date:-");
        date_lbl.setFont(fo2);
        date_lbl.setBounds(340, 10, 50, 60);
        add(date_lbl);

        date_lbl1 = new JLabel();
        date_lbl1.setText(d.format(date));
        date_lbl1.setBounds(400, 25, 100, 30);
        date_lbl1.setFont(fo2);
        add(date_lbl1);

        time_taken = new JLabel("Time:");
        time_taken.setFont(fo1);
        time_taken.setBounds(600, 25, 50, 30);
        add(time_taken);

        min_lbl = new JLabel(" ");
        min_lbl.setFont(fo1);
        min_lbl.setForeground(Color.RED);
        min_lbl.setBounds(660, 25, 30, 30);
        add(min_lbl);

        colon_lbl = new JLabel(":");
        colon_lbl.setFont(fo1);
        colon_lbl.setForeground(Color.RED);
        colon_lbl.setBounds(700, 25, 10, 30);
        add(colon_lbl);

        sec_lbl = new JLabel(" ");
        sec_lbl.setFont(fo1);
        sec_lbl.setForeground(Color.RED);
        sec_lbl.setBounds(720, 25, 50, 30);
        add(sec_lbl);


        time = new Timer(1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                min_lbl.setText(String.valueOf(min));
                sec_lbl.setText(String.valueOf(sec));
                if (sec == 60) {
                    sec = 0;
                    min++;
                    if (min == 10) {
                        time.stop();
                        try {
                            answer_check(que_id, answer);
                            Statement st= con.createStatement();
                            JOptionPane.showMessageDialog(null,"Times up !!!!!marks in exam:"+marks+"out of 6");
                            st.executeUpdate("update student_login set marks="+marks+"where stu_id="+student_id+"");
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }


                    }
                }
                sec++;
            }
        });


        j1 = new JLabel("Question ?");
        j1.setFont(fo1);
        j1.setBounds(400, 100, 400, 60);
        add(j1);

        j2 = new JLabel("Oue_no. ?");
        j2.setBounds(340, 120, 50, 20);
        j2.setFont(fo1);
        j2.setBackground(Color.blue);
        add(j2);

        g1 = new ButtonGroup();


        b1 = new JRadioButton("Option1");
        b1.setFont(fo2);
        b1.setBounds(400, 170, 150, 20);
        add(b1);

        b2 = new JRadioButton("Option2");
        b2.setFont(fo2);
        b2.setBounds(400, 240, 150, 20);
        add(b2);

        b3 = new JRadioButton("Option3");
        b3.setFont(fo2);
        b3.setBounds(400, 310, 150, 20);
        add(b3);

        b4 = new JRadioButton("Option4");
        b4.setFont(fo2);
        b4.setBounds(400, 380, 150, 20);
        add(b4);


        clear = new JButton("Clear selection");
        clear.setBounds(340, 500, 120, 30);
        add(clear);

        save_next = new JButton("Save & next");
        save_next.setBounds(500, 500, 120, 30);
        add(save_next);


        submit = new JButton("Submit");
        submit.setBounds(660, 500, 100, 30);
        add(submit);

        JButton back=new JButton("Back");
        back.setBounds(5,510,100,30);
        p1.add(back);


        g1.add(b1);
        g1.add(b2);
        g1.add(b3);
        g1.add(b4);
        time.start();
        try {
            PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from questions where que_no=" + que_id + "");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                answer_check(que_id,answer);
                j2.setText("Q." + r.getString(1) + ")");
                j1.setText(r.getString(2));
                b1.setText(r.getString(3));
                b2.setText(r.getString(4));
                b3.setText(r.getString(5));
                b4.setText(r.getString(6));
                answer=r.getString(7);

            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        save_next.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PreparedStatement pst = (PreparedStatement) con.prepareStatement("select * from questions where que_no=" + (que_id + 1) + "");
                    ResultSet r = pst.executeQuery();
                    while (r.next()) {

                        answer_check(que_id,answer);
                        g1.clearSelection();
                        j2.setText("Q." + r.getString(1) + ")");
                        j1.setText(r.getString(2));
                        b1.setText(r.getString(3));
                        b2.setText(r.getString(4));
                        b3.setText(r.getString(5));
                        b4.setText(r.getString(6));
                        answer=r.getString(7);
                        que_id += 1;
                    }

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        submit_ans(student_id);

        clear.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g1.clearSelection();
            }
        });


        back.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Student_login1 s1 = new Student_login1();
                s1.setSize(800,600);
            }
        });

        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void submit_ans(final int student_id)
    {
        submit.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // int p2=JOptionPane.showConfirmDialog(null,"Do You Want To Submit the Exam ?"+answer+" "+marks+"confirm");
                try{
                    answer_check(que_id,answer);
                   Statement st= con.createStatement();
                    JOptionPane.showMessageDialog(null,"marks in exam:"+marks+"out of 6");
                    st.executeUpdate("update student_login set marks="+marks+"where stu_id="+student_id+"");

                   System.exit(0);
                   setVisible(false);
                }catch (Exception e1)
                {
                    e1.printStackTrace();
                }
            }
        });
    }

    public void answer_check(int q_num, String ans) {
        if (b1.isSelected()) {
            stu_ans = b1.getText();
        } else if (b2.isSelected()) {
            stu_ans = b2.getText();
        } else if (b3.isSelected()) {
            stu_ans = b3.getText();
        } else if (b4.isSelected()) {
            stu_ans = b4.getText();
        }
        if (stu_ans.equals(ans)) {
            marks++;
        }
        if (q_num==7) {
            save_next.setVisible(false);
        }
    }

}

     class Student_exam_page {
        public static void main(String[] args) {
            int student_id = 0, roll_no = 10;
            String stu_name = "";
            Student_exam_page1 h1 = new Student_exam_page1(student_id, roll_no, stu_name);
            h1.setSize(900, 600);
        }
    }
