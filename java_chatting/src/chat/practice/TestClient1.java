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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TestClient1 extends JFrame {

    private JTextField textContent;
    private JTextArea textArea;
    private JPanel contentPane;
    
    Socket socket = null;
    BufferedReader in = null;
    BufferedWriter out = null;
    
    /**
     * Launch the application.
     */
    public static void main(String args[]) {
        
        TestClient1 frame = new TestClient1();
        frame.setVisible(true);
        frame.setSocketClient();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                frame.setSocketClient();
//            }
//        });
        
    }

    /**
     * Create the frame.
     */
    public TestClient1() {
        initialize();
    }

    public void initialize() {
        setTitle("채팅");
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 10, 410, 392);
        contentPane.add(scrollPane);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        
        JPanel panel = new JPanel();
        panel.setBounds(12, 407, 410, 44);
        contentPane.add(panel);
        panel.setLayout(null);
        
        textContent = new JTextField();
        textContent.setBounds(0, 1, 325, 32);
        panel.add(textContent);
        textContent.setColumns(10);
        
        JButton btnSend = new JButton("전송");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
        btnSend.setBounds(335, 0, 75, 32);
        panel.add(btnSend);
    }

    private void sendMessage() { // 전송
        try {
            String outMessage = textContent.getText();
            out.write(outMessage + "\n");
            out.flush();
            
            textArea.append("[클라이언트] : " + outMessage + "\n");
            textContent.setText("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void setSocketClient() {
        try {
            socket = new Socket("localhost", 9999);
            textArea.append("연결 완료! \n");
            
            
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            while(true) {
                String inMsg = in.readLine();
                textArea.append("[서버] : " + inMsg + "\n");
            }
                        
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                out.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
}
