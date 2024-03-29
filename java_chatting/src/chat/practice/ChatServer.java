package chat.practice;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChatServer {

    private JFrame frame;
    private JTextField textContent;
    private JTextArea textArea;

    
    ServerSocket server = null;
    Socket socket = null;
    BufferedReader in = null;
    BufferedWriter out = null;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) { 
        
        ChatServer cs = new ChatServer();
        cs.frame.setVisible(true);
        cs.setServerSocket();
    }

    /**
     * Create the application.
     */
    public ChatServer() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        
        frame.setTitle("서버");
        
        frame.setBounds(100, 100, 450, 468);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 10, 410, 346);
        frame.getContentPane().add(scrollPane);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        
        JPanel panel = new JPanel();
        panel.setBounds(12, 376, 434, 43);
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        textContent = new JTextField();
        textContent.setBounds(0, 1, 325, 32);
        panel.add(textContent);
        textContent.setColumns(10);
        
        JButton btnSend = new JButton("전송");
        btnSend.setBounds(335, 0, 75, 32);
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        panel.add(btnSend);
    }

    private void sendMessage() {
        
        try {
            String outMessage = textContent.getText();
            out.write(outMessage + "\n");
            out.flush();
            
            textArea.append("[서버] : " + outMessage + "\n");
            textContent.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    private void setServerSocket() {
                
        try {
            server = new ServerSocket(1002);
            textArea.append("연결 대기중...\n");
            
            socket = server.accept();
            textArea.append("연결 되었습니다.\n");
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            while(true) {
                String inMessage = in.readLine();
                
                if (inMessage.equalsIgnoreCase("bye")) {
                    System.out.println("클라이언트가 나갔습니다.");
                    break;
                }
                
                textArea.append("[클라이언트] : " + inMessage + "\n");
            }
                        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    
    
    
}
