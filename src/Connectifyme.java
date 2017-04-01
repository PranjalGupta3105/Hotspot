import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class Connectifyme extends JFrame {

	private JPanel contentPane;
	private JTextField t1;
	private JTextField t2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connectifyme frame = new Connectifyme();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Connectifyme() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterYourUsername = new JLabel("Enter Your Username:-");
		lblEnterYourUsername.setBounds(5, 5, 170, 20);
		lblEnterYourUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(lblEnterYourUsername);
		
		JLabel lblEnterYourPassword = new JLabel("Enter Your Password:-");
		lblEnterYourPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEnterYourPassword.setBounds(5, 36, 170, 14);
		contentPane.add(lblEnterYourPassword);
		
		t1 = new JTextField();
		t1.setBounds(185, 7, 155, 20);
		contentPane.add(t1);
		t1.setColumns(10);
		
		t2 = new JTextField();
		t2.setBounds(185, 35, 155, 20);
		contentPane.add(t2);
		t2.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
			//System.out.println("Hello");
			
			t1.setVisible(true);
			t2.setVisible(true);
			String uname=t1.getText();
			String passw=t2.getText();
			String arg1="netsh wlan show hostednetwork";
			String arg2="pause";
			String arg3="netsh wlan set hostednetwork mode=allow ssid="+uname+" key="+passw;
			String arg4="pause";
			String arg5="netsh wlan show hostednetwork";
			String arg6="pause";
			String arg7="netsh wlan start hostednetwork";
			
			String stop_connection="netsh wlan stop hostednetwork";
//-->Makes a New Directory in within the C Drive of the Machine that has files to start and stop the connection.......
			new File("C:\\Hotspot").mkdir();
			
			if(passw.length()<8)
			{
				System.out.println("The length of your your password must be greater then 8 please re-type your password again");
			}
			else{
			String cmd=arg1+"\n"+arg2+"\n"+arg3+"\n"+arg4+"\n"+arg5+"\n"+arg6+"\n"+arg7;
			System.out.println(cmd);
			File file=new File("C:\\Hotspot\\hotspotcode.bat");
			if(file.exists())
			{
				System.out.println("File already Exists rewritting into this file now....");
				file.delete();
				JOptionPane.showMessageDialog(null,"please re-run the program again to start the hotspot");
			}
			else
			{
				
			try {
				FileOutputStream fos=new FileOutputStream("C:\\Hotspot\\hotspotcode.bat");
				fos.write(cmd.getBytes());	
				fos.close();
				
				// Creating the Stop the network file
				File stop_file=new File("C:\\Hotspot\\stophotspotcode.bat");
				FileOutputStream filetostop=new FileOutputStream("C:\\Hotspot\\stophotspotcode.bat");
				filetostop.write(stop_connection.getBytes());
				filetostop.close();
				System.out.println("yes yes yes!!");
				} catch (FileNotFoundException e1) {
					
					e1.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			    }
			}
			}
		});
		btnConnect.setBounds(165, 89, 89, 23);
		contentPane.add(btnConnect);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		try {
		FileInputStream getfileinfo=new FileInputStream("C:\\Hotspot\\stophotspotcode.bat");
		int commandinfile=0;
		char command;
		String finalCommand = null;
		while((commandinfile=getfileinfo.read())!=-1)
		{
		command=(char)commandinfile;
		finalCommand=finalCommand.concat((Character.toString(command)));
		}
		if(finalCommand=="netsh wlan stop hostednetwork")
		{
			getfileinfo.close();
			System.exit(0);
		
		}
		getfileinfo.close();
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		btnStop.setBounds(264, 89, 89, 23);
		contentPane.add(btnStop);
	}
}
