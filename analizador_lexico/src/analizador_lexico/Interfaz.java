package analizador_lexico;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.ScrollPane;
import java.awt.TextArea;

public class Interfaz extends JFrame {

	ArrayList<Token> lista_token = new ArrayList();
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz frame = new Interfaz();
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
	public Interfaz() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txt_editor = new JTextArea();
		txt_editor.setBounds(85, 11, 257, 128);
		contentPane.add(txt_editor);
		
		TextArea txt_consola = new TextArea();
		txt_consola.setBounds(30, 199, 380, 122);
		contentPane.add(txt_consola);
		
		JButton btn_analizar = new JButton("Analizar");
		btn_analizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Le pasa la lista de lo escrito en el txt_editor
				new Analizador(lista_token).analizar(txt_editor.getText());
				
				//Se imprime la lista en el txt_consola
				for(int i=0; i<lista_token.size(); i++) {
					txt_consola.setText(txt_consola.getText() + "\n" + lista_token.get(i).toString());
				}
			}
		});
		btn_analizar.setBounds(104, 158, 89, 23);
		contentPane.add(btn_analizar);
		
		JButton btn_limpiar = new JButton("Limpiar");
		btn_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txt_editor.setText("");
				txt_consola.setText("");
			}
		});
		btn_limpiar.setBounds(238, 158, 89, 23);
		contentPane.add(btn_limpiar);
	}
}
