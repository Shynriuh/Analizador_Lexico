import java.awt.Color;
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
import java.util.Stack;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Component;

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
					frame.setTitle("Analizador Léxico, Sintáctico y Semántico");
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
		setBounds(140, 20, 1132, 691);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//CUADRO DE CODIGO
		JTextArea txt_editor = new JTextArea();
		txt_editor.setFont(new Font("Monospaced", Font.PLAIN, 15));
		JScrollPane scroll_d = new JScrollPane(txt_editor);
		scroll_d.setBounds(29, 69, 346, 230);
		contentPane.add(scroll_d);
		
		JLabel lblNewLabel = new JLabel("Analizador Léxico");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		lblNewLabel.setBounds(10, 0, 796, 20);
		contentPane.add(lblNewLabel);
		
		//LEXICO
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(422, 69, 371, 230);
		contentPane.add(scrollPane);
		
		JTable table = new JTable();
		table.setEnabled(false);
		table.setFont(new Font("Cambria", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Token[][] {
			},
			new String[] {
				"Lexema", "Token", "#"
			}
		));
		
		//SINTACTICO
		JLabel lblAnalizadorSintctico = new JLabel("Analizador Sintáctico");
		lblAnalizadorSintctico.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnalizadorSintctico.setFont(new Font("Verdana", Font.BOLD, 15));
		lblAnalizadorSintctico.setBounds(29, 335, 737, 26);
		contentPane.add(lblAnalizadorSintctico);
		
		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(49, 418, 698, 185);
		contentPane.add(scrollPane2);
		
		JTable table_sintactico = new JTable();
		table_sintactico.setEnabled(false);
		table_sintactico.setFont(new Font("Cambria", Font.PLAIN, 15));
		scrollPane2.setViewportView(table_sintactico);
		table_sintactico.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Pila", "Salida", "Entrada"
			}
		));
		table_sintactico.getColumnModel().getColumn(0).setPreferredWidth(400);
		
		//ARBOL
		JLabel lbl_arbol = new JLabel("Árbol Sintáctico");
		lbl_arbol.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_arbol.setFont(new Font("Verdana", Font.BOLD, 15));
		lbl_arbol.setBounds(836, 374, 257, 20);
		contentPane.add(lbl_arbol);
		
		JTextArea txt_arbol = new JTextArea();
		txt_arbol.setEditable(false);
		txt_arbol.setFont(new Font("Cambria", Font.PLAIN, 15));
		JScrollPane scroll_a = new JScrollPane(txt_arbol);
		scroll_a.setBounds(856, 418, 210, 185);
		contentPane.add(scroll_a);
		
		JButton btn_txt = new JButton("");
		btn_txt.setFont(new Font("Tahoma", Font.BOLD, 12));
		btn_txt.setEnabled(false);
		btn_txt.setBackground(Color.LIGHT_GRAY);
		btn_txt.setBounds(435, 387, 312, 31);
		contentPane.add(btn_txt);
		
		//SEMANTICO
		JTextArea txt_sem = new JTextArea();
		txt_sem.setFont(new Font("Cambria", Font.PLAIN, 15));
		JScrollPane scroll_sem = new JScrollPane(txt_sem);
		scroll_sem.setBounds(856, 57, 210, 242);
		contentPane.add(scroll_sem);
		
		JLabel lbl_sem = new JLabel("Errores Semánticos");
		lbl_sem.setHorizontalAlignment(SwingConstants.CENTER);
		lbl_sem.setFont(new Font("Verdana", Font.BOLD, 15));
		lbl_sem.setBounds(836, 17, 247, 20);
		contentPane.add(lbl_sem);
		
		
		//BOTON ANALIZAR SINTACTICO
		JButton btn_analizar2 = new JButton("Analizar");
		btn_analizar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sintactico sint = new Sintactico(lista_token);
				boolean validar;
				validar = sint.LR_1_2();
				
				if(validar) {
					btn_txt.setText("La cadena es correcta");
					btn_txt.setBackground(Color.CYAN);
				} else {
					btn_txt.setText("Error: La cadena es incorrecta");
					btn_txt.setBackground(Color.PINK);
				}
				
				//Se actualiza la tabla
				table_sintactico.setModel(new DefaultTableModel(
					sint.matriz_sintactica(),
					new String[] {
						"Pila", "Salida", "Entrada"
					}
				));
				table_sintactico.getColumnModel().getColumn(0).setPreferredWidth(400);
				
				//Se actualiza el arbol
				Stack<String> arbol = sint.getArbol();
				for(String p : arbol)
					txt_arbol.setText(txt_arbol.getText() + p +"\n");
				
				lista_token.clear();
				btn_analizar2.setEnabled(false);
			}
		});
		btn_analizar2.setFont(new Font("Verdana", Font.PLAIN, 12));
		btn_analizar2.setBounds(49, 374, 89, 31);
		contentPane.add(btn_analizar2);
		btn_analizar2.setEnabled(false);
		
		//BOTON ANALIZAR LEXICO
		JButton btn_analizar = new JButton("Analizar");
		btn_analizar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btn_analizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Le pasa la lista de lo escrito en el txt_editor
				new Lexico(lista_token).analizar(txt_editor.getText()+" $");
				
				if(lista_token.isEmpty())
					return;
				
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
				btn_analizar2.setEnabled(true);
				txt_arbol.setText("");
			}
		});
		btn_analizar.setBounds(420, 31, 89, 31);
		contentPane.add(btn_analizar);
		
		//BOTON LIMPIAR
		JButton btn_limpiar = new JButton("Limpiar");
		btn_limpiar.setFont(new Font("Verdana", Font.PLAIN, 12));
		btn_limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lista_token.clear();
				txt_editor.setText("");
				txt_arbol.setText("");
				btn_analizar2.setEnabled(false);
				btn_txt.setText("");
				btn_txt.setBackground(Color.LIGHT_GRAY);
				
				table.setModel(new DefaultTableModel(
						new Token[][] {
						},
						new String[] {
							"Lexema", "Token", "#"
						}
					));
				table_sintactico.setModel(new DefaultTableModel(
						new Token[][] {
						},
						new String[] {
							"Pila", "Salida", "Entrada"
						}
					));
				table_sintactico.getColumnModel().getColumn(0).setPreferredWidth(400);
			}
		});
		btn_limpiar.setBounds(29, 31, 89, 31);
		contentPane.add(btn_limpiar);
		
		
		//SEPARADORES
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 23, 796, 14);
		contentPane.add(separator_1);
		
		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setBounds(10, 22, 10, 302);
		contentPane.add(separator_4);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(10, 322, 796, 14);
		contentPane.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(803, 23, 10, 301);
		contentPane.add(separator_3);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(29, 362, 737, 14);
		contentPane.add(separator_5);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setOrientation(SwingConstants.VERTICAL);
		separator_6.setBounds(29, 362, 10, 265);
		contentPane.add(separator_6);
		
		JSeparator separator_8 = new JSeparator();
		separator_8.setBounds(29, 627, 737, 14);
		contentPane.add(separator_8);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setOrientation(SwingConstants.VERTICAL);
		separator_7.setBounds(767, 362, 10, 265);
		contentPane.add(separator_7);
		
		JSeparator separator_9 = new JSeparator();
		separator_9.setBounds(836, 395, 247, 14);
		contentPane.add(separator_9);
		
		JSeparator separator_12 = new JSeparator();
		separator_12.setBounds(834, 627, 247, 14);
		contentPane.add(separator_12);
		
		JSeparator separator_10 = new JSeparator();
		separator_10.setOrientation(SwingConstants.VERTICAL);
		separator_10.setBounds(836, 397, 10, 230);
		contentPane.add(separator_10);
		
		JSeparator separator_11 = new JSeparator();
		separator_11.setOrientation(SwingConstants.VERTICAL);
		separator_11.setBounds(1083, 397, 10, 230);
		contentPane.add(separator_11);
		
		JSeparator separator_13 = new JSeparator();
		separator_13.setBounds(836, 38, 247, 14);
		contentPane.add(separator_13);
		
		JSeparator separator_14 = new JSeparator();
		separator_14.setBounds(836, 322, 247, 14);
		contentPane.add(separator_14);
		
		JSeparator separator_15 = new JSeparator();
		separator_15.setOrientation(SwingConstants.VERTICAL);
		separator_15.setBounds(836, 38, 10, 283);
		contentPane.add(separator_15);
		
		JSeparator separator_16 = new JSeparator();
		separator_16.setOrientation(SwingConstants.VERTICAL);
		separator_16.setBounds(1083, 38, 10, 283);
		contentPane.add(separator_16);
	}
}
