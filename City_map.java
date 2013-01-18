import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author hamzah
 */
 class City_map extends javax.swing.JFrame implements MouseListener,Runnable {
	private int x, y, flag = 0, len = 0;
	private int x1, y1, x2, y2;
	private int[][] adj = new int[1000][1000];
	public int[] v1 = new int[1000];
	public int[] v2 = new int[1000];
	/**
	 * Creates new form City_map
	 */
	private javax.swing.JPanel jPaneL2;

	public City_map() throws IOException{
		initComponents();
		start();
	}

	public void actionPerformed(ActionEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public void mouseReleased(MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// throw new UnsupportedOperationException("Not supported yet.");
	}

	public class imagepanel extends JPanel {
		public imagepanel() {
			setBackground(Color.white);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			// draw circle outline
			try {
				FileReader fr1 = new FileReader("cityname.txt");
				BufferedReader br1 = new BufferedReader(fr1);
				FileReader fr2 = new FileReader("road.txt");
				BufferedReader br2 = new BufferedReader(fr2);
				String s = new String();
				len = 0;
				while ((s = br1.readLine()) != null) {
					String[] words = s.split(" ");
					g.setColor(Color.black);
					g.drawRect(Integer.parseInt(words[0]),Integer.parseInt(words[1]),12,9);  

					// set color to RED
					// So after this, if you draw anything, all of it's result
					// will be RED
					g.setColor(Color.GREEN);  
			           
			           //fill circle with RED  
			           g.fillRect(Integer.parseInt(words[0]),Integer.parseInt(words[1]),12,9);
			           g.setColor(Color.black);
						g.drawString(words[2], Integer.parseInt(words[0]), Integer.parseInt(words[1]));
						len++;
			    		}

				br1.close();
				fr1.close();

				for (int i = 0; i < len; i++) {
					for (int j = 0; j < len; j++)
						adj[i][j] = 0;
				}
				while ((s = br2.readLine()) != null) {
					String[] words = s.split(" ");
					int c = -1, d = -1;
					g.setColor(Color.blue);
		            g.drawLine(Integer.parseInt(words[0]),Integer.parseInt(words[1]),Integer.parseInt(words[2]),Integer.parseInt(words[3]));  
		           
					x1 = Integer.parseInt(words[0]);
					y1 = Integer.parseInt(words[1]);
					x2 = Integer.parseInt(words[2]);
					y2 = Integer.parseInt(words[3]);
					// System.out.println(x1+" "+y1+" "+x2+" "+y2);
					int a, b, len1 = 0;
					String s1 = new String();
					fr1 = new FileReader("cityname.txt");
					br1 = new BufferedReader(fr1);
					while ((s1 = br1.readLine()) != null) {
						String[] words1 = s1.split(" ");
						a = Integer.parseInt(words1[0]);
						b = Integer.parseInt(words1[1]);
						if (x1 < a + 20 && x1 > a - 20 && y1 < b + 20
								&& y1 > b - 20)
							c = len1;
						if (x2 < a + 20 && x2 > a - 20 && y2 < b + 20
								&& y2 > b - 20)
							d = len1;
						len1++;
						
					}
					br1.close();
					fr1.close();
					adj[c][d] = 1;
					adj[d][c] = 1;
					// set color to RED
					// So after this, if you draw anything, all of it's result
					// will be RED

				}
				for (int i = 0; i < len; i++) {
					for (int j = 0; j < len; j++)
						System.out.print(adj[i][j] + " ");
					System.out.println();
				}
				br1.close();
				fr1.close();
				br2.close();
				fr2.close();
			} catch (FileNotFoundException ex) {
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				System.out.println("success1");
				Connection con = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/cab_service", "root",
						"hamzah");
				if (con != null) {
					System.out
							.println("A database connection has been established!");
				}
				System.out.println("success2");
				Statement st = con.createStatement();
				String s1 = "select * from taxi";
				ResultSet rs = st.executeQuery(s1);
				int i = 0;
				while (rs.next()) {
					
					g.setColor(Color.black);
					g.drawRect(v1[i], v2[i], 10, 10);
					if(rs.getString("Availability").compareTo("available")==0)
					{
						g.setColor(Color.blue);
					}
					else{
					g.setColor(Color.RED);
					}
					
					
					g.fillRect(v1[i], v2[i], 10, 10);
				//	g.setColor(Color.black);
					g.drawString(rs.getString("regno"), v1[i], v2[i]);
					i++;
				}
				rs.close();
				st.close();
				con.close();
			} catch (Exception e) {
				e.getMessage();
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		this.setTitle("CITY MAP");
		System.out.println("success5");
		
		jLabel1 = new javax.swing.JLabel();
		jPanel2 = new imagepanel();
		jButton1 = new javax.swing.JButton();
		jRadioButton1 = new javax.swing.JRadioButton();
		jTextField1 = new javax.swing.JTextField();
		jRadioButton2 = new javax.swing.JRadioButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setFont(new java.awt.Font("Monotype Corsiva", 0, 24)); // NOI18N
		jLabel1.setText("");

		jPanel2.setBackground(new java.awt.Color(255, 255, 204));
		jPanel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(	0, 0, 0), 1, true));

		org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 555,
				Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(0, 389,
				Short.MAX_VALUE));
		System.out.println("success6");
		jButton1.setBackground(new java.awt.Color(90, 100, 4));
		jButton1.setText("BACK");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				City_map.this.dispose();
				try {

					new Admin().setVisible(true);
				} catch (Exception ex) {
					Logger.getLogger(City_map.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}
		});
		jRadioButton1.setText("Add Location");
		jRadioButton1.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				// jRadioButton1MouseClicked(evt);
			}
		});
		jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jRadioButton1ActionPerformed(evt);
			}
		});

		jTextField1.setText("");
		jTextField1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jRadioButton2.setText("Add Road");
		jRadioButton2.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				jRadioButton2MouseClicked(evt);
			}
		});

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(layout
						.createSequentialGroup()
						.add(layout
								.createParallelGroup(
										org.jdesktop.layout.GroupLayout.LEADING)
								.add(layout
										.createSequentialGroup()
										.add(241, 241, 241)
										.add(jButton1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												112,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(layout
										.createSequentialGroup()
										.add(39, 39, 39)
										.add(layout
												.createParallelGroup(
														org.jdesktop.layout.GroupLayout.LEADING)
												.add(layout
														.createSequentialGroup()
														.add(jRadioButton1)
														.add(18, 18, 18)
														.add(jTextField1,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																152,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(122, 122, 122)
														.add(jRadioButton2))
												.add(jPanel2,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
						.addContainerGap(63, Short.MAX_VALUE))
				.add(org.jdesktop.layout.GroupLayout.TRAILING,
						layout.createSequentialGroup()
								.add(0, 0, Short.MAX_VALUE)
								.add(jLabel1,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										156,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.add(234, 234, 234)));
		layout.setVerticalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(layout
						.createSequentialGroup()
						.add(jLabel1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								25,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED, 8,
								Short.MAX_VALUE)
						.add(layout
								.createParallelGroup(
										org.jdesktop.layout.GroupLayout.BASELINE)
								.add(jRadioButton1)
								.add(jTextField1,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										29, Short.MAX_VALUE).add(jRadioButton2))
						.add(15, 15, 15)
						.add(jPanel2,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED)
						.add(jButton1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								38,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		pack();
		System.out.println("painting");
		jPanel2.repaint();
		System.out.println("paintingout");
		jPanel2.addMouseListener(this);
	}// </editor-fold>

	private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here

	}

	private void jRadioButton2MouseClicked(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		jPanel2.addMouseListener(this);
	}

	private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		System.out.println("You clicked the button");
		jPanel2.addMouseListener(this);

	}

	public void mouseClicked(MouseEvent e) {

		if (jRadioButton2.isSelected() == true) {
			Point p = e.getPoint();
			if (p.x != x1 & p.y != y1)
				flag++;
			System.out.println(e.getPoint());
			System.out.println("value of flag:" + flag);
			if (flag % 2 != 0) {
				x1 = p.x;
				y1 = p.y;
			}
			if (flag % 2 == 0) {
				jRadioButton2.setSelected(false);
				flag = 0;
				x2 = p.x;
				y2 = p.y;
				
				FileWriter fw;
				try {
					FileReader fr1 = new FileReader("cityname.txt");
					BufferedReader br1 = new BufferedReader(fr1);
					fw = new FileWriter("road.txt", true);
					fw.write(x1 + " " + y1 + " " + x2 + " " + y2 + "\n");
					fw.close();
					br1.close();
					fr1.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				jPanel2.repaint();
			}
		}
		if (jRadioButton1.isSelected() == true) {
			Point p = e.getPoint();
			x = p.x;
			y = p.y;
			try {
				FileWriter fw = new FileWriter("cityname.txt", true);
				fw.write((x-10) + " " + (y-10) + " " + jTextField1.getText() + "\n");
				fw.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			jPanel2.repaint();
			System.out.println(e.getPoint());
			String s1 = jTextField1.getText();

			jRadioButton1.setSelected(false);
			jTextField1.setText("");
		}
	}

	public void mousePressed(MouseEvent e) {

		// throw new UnsupportedOperationException("Not supported yet.");

	}

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JRadioButton jRadioButton1;
	private javax.swing.JRadioButton jRadioButton2;
	private javax.swing.JTextField jTextField1;
	// End of variables declaration

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(City_map.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(City_map.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(City_map.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(City_map.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>
		System.out.println("success3");
		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				setVisible(true);
			}
		});
		Connection con;
		try {
			con = DriverManager.getConnection(
			        "jdbc:mysql://localhost:3306/cab_service", "root",
			        "hamzah");
		
        if (con != null) {
            System.out
                    .println("A database connection has been established!");
        }
        System.out.println("success2");
        Statement st = con.createStatement();
        String s1 = "select * from taxi";
        ResultSet rs = st.executeQuery(s1);
        int i = 0;
        while (rs.next()) {
            FileReader fr1 = new FileReader("cityname.txt");
            BufferedReader br1 = new BufferedReader(fr1);
            String s=new String();
            while ((s = br1.readLine()) != null) {
                    String[] words = s.split(" ");
                    if(rs.getString("Startplace").compareTo(words[2])==0){        
                    v1[i] =Integer.parseInt(words[0])+10;
                    v2[i] =Integer.parseInt(words[1])+10;
                    }
            }
            System.out.println("v1 for "+i+v1[i]+"v2 for "+i+v2[i]);
            br1.close();
            fr1.close();
            i++;
        }
        repaint();
        rs.close();
        st.close();
        con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	public void start(){
		Thread thread=new Thread(this);
		thread.start();
	}
}

