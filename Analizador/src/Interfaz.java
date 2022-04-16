import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.ScrollPane;

public class Interfaz extends JFrame {

	ArrayList<Token> lista_token = new ArrayList<Token>();
	
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
		setBounds(100, 100, 709, 483);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txt_editor = new JTextArea();
		txt_editor.setBounds(29, 69, 257, 164);
		contentPane.add(txt_editor);
		
		JLabel lblNewLabel = new JLabel("Analizador Léxico");
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblNewLabel.setBounds(274, 0, 129, 14);
		contentPane.add(lblNewLabel);
		
		JTextArea txt_sintactico = new JTextArea();
		txt_sintactico.setEditable(false);
		txt_sintactico.setBounds(145, 325, 371, 85);
		contentPane.add(txt_sintactico);
		
		JLabel lblAnalizadorSintctico = new JLabel("Analizador Sintáctico");
		lblAnalizadorSintctico.setFont(new Font("Verdana", Font.PLAIN, 14));
		lblAnalizadorSintctico.setBounds(274, 254, 151, 14);
		contentPane.add(lblAnalizadorSintctico);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(324, 69, 327, 164);
		contentPane.add(scrollPane);
		
		JTable table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Token[][] {
			},
			new String[] {
				"Lexema", "Token", "#"
			}
		));
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 15, 662, 14);
		contentPane.add(separator_1);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(10, 15, 10, 230);
		contentPane.add(separator_4);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBounds(10, 244, 662, 14);
		contentPane.add(separator_1_1);
		
		JSeparator separator_4_1 = new JSeparator();
		separator_4_1.setOrientation(SwingConstants.VERTICAL);
		separator_4_1.setBounds(673, 15, 10, 230);
		contentPane.add(separator_4_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBounds(111, 269, 441, 14);
		contentPane.add(separator_1_1_1);
		
		JSeparator separator_4_2 = new JSeparator();
		separator_4_2.setOrientation(SwingConstants.VERTICAL);
		separator_4_2.setBounds(111, 269, 10, 150);
		contentPane.add(separator_4_2);
		
		JSeparator separator_1_1_1_1 = new JSeparator();
		separator_1_1_1_1.setBounds(111, 419, 441, 14);
		contentPane.add(separator_1_1_1_1);
		
		JSeparator separator_4_2_1 = new JSeparator();
		separator_4_2_1.setOrientation(SwingConstants.VERTICAL);
		separator_4_2_1.setBounds(551, 269, 10, 150);
		contentPane.add(separator_4_2_1);
		
		JButton btn_analizar = new JButton("Analizar");
		btn_analizar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btn_analizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Le pasa la lista de lo escrito en el txt_editor
				new Analizador(lista_token).analizar(txt_editor.getText());
				
				//Se imprimen los datos en la matriz
				String matriz[][] = new String[lista_token.size()][3];
				for(int i=0; i<lista_token.size(); i++) {
					matriz[i][0] = lista_token.get(i).getLexema();
					matriz[i][1] = lista_token.get(i).getTipo();
					matriz[i][2] = Integer.toString(lista_token.get(i).getNumero_token());
				}
				
				table.setModel(new DefaultTableModel(
						matriz,
						new String[] {
							"Lexema", "Token", "#"
						}
					));
			}
		});
		btn_analizar.setBounds(314, 40, 89, 23);
		contentPane.add(btn_analizar);
		
		JButton btn_limpiar = new JButton("Limpiar");
		btn_limpiar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btn_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista_token.clear();
				txt_editor.setText("");
				txt_sintactico.setText("");
				table.setModel(new DefaultTableModel(
						new Token[][] {
						},
						new String[] {
							"Lexema", "Token", "#"
						}
					));
			}
		});
		btn_limpiar.setBounds(29, 40, 89, 24);
		contentPane.add(btn_limpiar);
		
		JButton btn_analizar2 = new JButton("Analizar");
		btn_analizar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//new Analizador(lista_token).analizar(txt_editor.getText());
				boolean validar;
				validar = new Sintactico(lista_token).LR_1_2();
				
				if(validar) {
					txt_sintactico.setText("La expresion esta escrita correctamente.");
				} else {
					txt_sintactico.setText("Error: La expresion no esta escrita correctamente.");
				}
			}
		});
		btn_analizar2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btn_analizar2.setBounds(145, 291, 89, 23);
		contentPane.add(btn_analizar2);
	}
}
