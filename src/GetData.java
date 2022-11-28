import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.io.*;

public class GetData {

	 private JFrame f;
	 private JPanel panel;
	 private JLabel rolll;
	 private JLabel rolnum;
	 private JLabel name;
	 private JLabel seatnum;
	 private JLabel pnr;
	 private JLabel gender;
	 private JTextField userRollnum;
	 private JButton button;

	 public GetData(){
			panel = new JPanel();
			f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setTitle("Get Your 1st Sem Exam Hall Ticket");
			f.add(panel);
			f.setSize(420,380);
			f.setLocationRelativeTo(null);
			f.getContentPane().add(panel);
			f.setVisible(true);
			panel.setLayout(null);
			panel.setBorder(BorderFactory.createLineBorder(Color.black));

			rolll = new JLabel("Enter Roll Number : ");
			rolll.setBounds(20, 20, 150, 30);
			rolll.setFont(rolll.getFont().deriveFont((float) 15.2));			
			panel.add(rolll);

			userRollnum = new JTextField();
			userRollnum.setBounds(175, 20, 205, 30);
			userRollnum.setFont(userRollnum.getFont().deriveFont((float) 15.2));
			panel.add(userRollnum);

			button = new JButton("Get Hall Ticket");
			button.setBounds(20, 70, 360, 33);
			button.setFont(button.getFont().deriveFont((float) 15.2));
			
			rolnum = new JLabel();
			rolnum.setBounds(20, 120, 1120, 25);
			rolnum.setFont(rolnum.getFont().deriveFont((float) 18.1));
			panel.add(rolnum);
			
			name = new JLabel();
			name.setBounds(20, 150, 1120, 25);
			name.setFont(name.getFont().deriveFont((float) 18.1));
			panel.add(name);
			
			seatnum = new JLabel();
			seatnum.setBounds(20, 180, 1120, 25);
			seatnum.setFont(seatnum.getFont().deriveFont((float) 18.1));
			panel.add(seatnum);
			
			pnr = new JLabel();
			pnr.setBounds(20, 210, 1120, 25);
			pnr.setFont(pnr.getFont().deriveFont((float) 18.1));
			panel.add(pnr);
			
			gender = new JLabel();
			gender.setBounds(20, 240, 1120, 25);
			gender.setFont(gender.getFont().deriveFont((float) 18.1));
			panel.add(gender);

			 button.addActionListener(new ActionListener() {
				 public void actionPerformed(ActionEvent ae){
					String rollnum = userRollnum.getText();

					try {
						Integer.parseInt(rollnum);

					} catch (NumberFormatException aae) {
//						System.out.println("Roll number is not proper..");
						JOptionPane.showMessageDialog(null, "Roll number is not proper..");
					}
					
										
					try {
			            Class.forName("com.mysql.jdbc.Driver");
			            Connection conn = null;
			            conn = DriverManager.getConnection("jdbc:mysql://localhost/mca_dbms","root", "");
			            //System.out.println("Database is connected !");
			            Statement stmt=conn.createStatement();
			            ResultSet rs=stmt.executeQuery("select * from mca_student where id="+rollnum);
			            
			            if(rs.next() == false) {
//			            	result.setText("Student/Roll_number Does Not EXIST!!!");
			            	JOptionPane.showMessageDialog(null, "Student/Roll_number Does Not EXIST!!!");
			            } else {
			            	do {
			            		rolnum.setText("Roll Number: "+rs.getInt(1));
			            		name.setText("Name: "+rs.getString(2)+" "+rs.getString(3));
			            		seatnum.setText("Seat Number: "+rs.getInt(4));
			            		pnr.setText("PNR Number: "+rs.getString(5));
			            		gender.setText("Gender: "+rs.getString(6));
			            		String path= rs.getString(7);
			            		File pdfFile = new File(path);
			            		if (pdfFile.exists())
			            			if (Desktop.isDesktopSupported()) {
			            				Desktop.getDesktop().open(pdfFile);
			            			}
			            			else {
			            				JOptionPane.showMessageDialog(null, "Desktop is not supportetd");
			            			}
			            		else {
			            			JOptionPane.showMessageDialog(null, "File not Exists");
			            		}
			            	
			            	}while(rs.next());
				           }
			            	
			            conn.close();
			        }
			        catch(Exception ae1) {
			            System.out.print("Do not connect to DB - Error:"+ae1);
//			            JOptionPane.showMessageDialog(null, "Do not connect to DB - Error:"+ae1);
			        }
				 }

			 });

			 panel.add(button);
	 }

	 public static void main(String[] args){
		 new GetData();
	 }
 }