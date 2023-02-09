package simplechatting.client;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ChattingClient extends JFrame {

	private Socket socket;
	private String username;

	private JPanel contentPane;
	private JTextField ipInput;
	private JTextField portInput;
	private JTextArea contentView;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChattingClient frame = new ChattingClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChattingClient() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		ipInput = new JTextField();
		ipInput.setBounds(353, 10, 170, 32);
		contentPane.add(ipInput);
		ipInput.setColumns(10);

		JButton connectButton = new JButton("연결");
		connectButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String ip = null;
				int port = 0;

				ip = ipInput.getText();
				port = Integer.parseInt(portInput.getText());

				try {
					socket = new Socket(ip, port);

					JOptionPane.showMessageDialog(null, socket.getInetAddress() + "서버 접속", "접속 성공",
							JOptionPane.INFORMATION_MESSAGE);

					InputStream inputStream = socket.getInputStream();
					BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));

					if (in.readLine().equals("join")) { 
						username = JOptionPane.showInputDialog(null, "사용자이름을 입력하세요.", JOptionPane.INFORMATION_MESSAGE);

						OutputStream outputStream = socket.getOutputStream();
						PrintWriter out = new PrintWriter(outputStream, true);

						out.println(username + "님이 접속하였습니다.");

						String welcomeMessage = in.readLine();
						contentView.append(welcomeMessage);
					}
				} catch (ConnectException e1) {
					JOptionPane.showMessageDialog(null, "서버 접속 실패", "접속 실패", JOptionPane.ERROR_MESSAGE);

				} catch (UnknownHostException e1) {
					e1.printStackTrace();

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		connectButton.setBounds(606, 9, 80, 33);
		contentPane.add(connectButton);

		portInput = new JTextField();
		portInput.setBounds(535, 10, 59, 32);
		contentPane.add(portInput);
		portInput.setColumns(10);

		JScrollPane contentsScroll = new JScrollPane();
		contentsScroll.setBounds(12, 10, 329, 406);
		contentPane.add(contentsScroll);

		contentView = new JTextArea();
		contentsScroll.setViewportView(contentView);

		JScrollPane userListScroll = new JScrollPane();
		userListScroll.setBounds(353, 52, 339, 364);
		contentPane.add(userListScroll);

		JList userList = new JList();
		userListScroll.setViewportView(userList);

		JScrollPane messageScroll = new JScrollPane();
		messageScroll.setBounds(12, 455, 582, 46);
		contentPane.add(messageScroll);

		JTextArea messageInput = new JTextArea();
		messageScroll.setViewportView(messageInput);

		JButton sendButton = new JButton("전송");
		sendButton.setBounds(600, 455, 86, 46);
		contentPane.add(sendButton);
	}
}
