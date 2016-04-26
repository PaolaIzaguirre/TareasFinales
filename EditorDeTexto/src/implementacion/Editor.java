package implementacion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JScrollPane;

import java.awt.Toolkit;

@SuppressWarnings("serial")
public class Editor extends JFrame {

	
	private JPanel contentPane;
	private JMenuBar mnuOpciones;
	private JMenuItem mntmNuevo;
	private JMenu mnOpciones;
	private JMenuItem mntmGuardar;
	private JMenuItem mntmAbrir;
	private JMenuItem mntmSalir;
	private JTextArea textArea;
	private JScrollPane srcVertical;

	


	public Editor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Editor.class.getResource("/recursos/libro.gif")));
		setResizable(false);
		setTitle("Editor");
		inicializarFormulario();
		acciones();
	}
	public void inicializarFormulario(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 629, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		mnuOpciones = new JMenuBar();
		mnuOpciones.setBounds(0, 0, 623, 26);
		contentPane.add(mnuOpciones);
		
		mnOpciones = new JMenu("Opciones");
		mnuOpciones.add(mnOpciones);
		
		mntmNuevo = new JMenuItem("Nuevo");
		
		mnOpciones.add(mntmNuevo);
		
		mntmGuardar = new JMenuItem("Guardar");
		mnOpciones.add(mntmGuardar);
		
		mntmAbrir = new JMenuItem("Abrir");
		mnOpciones.add(mntmAbrir);
		
		mntmSalir = new JMenuItem("Salir");
		mnOpciones.add(mntmSalir);
		
		textArea = new JTextArea();
		textArea.setBounds(1, 1, 588, 284);
		
		contentPane.add(textArea);
		
		srcVertical = new JScrollPane(textArea);
		srcVertical.setBounds(15, 40, 590, 286);
		getContentPane().add(srcVertical);
		setVisible(true);
	}
	public void abrir(){
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int resultado= fileChooser.showOpenDialog(this);
		if (resultado== JFileChooser.CANCEL_OPTION) return;
		File nombre	= fileChooser.getSelectedFile();
		if(nombre.isFile()){
			try{
				BufferedReader ingresar=new BufferedReader(new FileReader(nombre));
				StringBuffer bf=new StringBuffer();
				String texto;
				textArea.setText("");
				while((texto=ingresar.readLine())!=null){
					bf.append(texto+"\n");
					textArea.append(bf.toString());
				}
			ingresar.close();
			} catch(IOException ioException){
				JOptionPane.showMessageDialog(null,"Error en el archivo");
			}
			
		}
			
		}
	
		

	public void acciones(){
		mntmSalir.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
						
					}
				});
		mntmAbrir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				abrir();
				
			}
		});
		mntmNuevo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(textArea!=null){
					int respuesta=JOptionPane.showConfirmDialog(null, "Desea Guardar El Documento Actual");
					if (respuesta==0){
						guardar();
						textArea.setText(null);
					}
					else if (respuesta==1){
						textArea.setText(null);
					}
					else{
						return;
					}
				}
				
				
			}
		});
		mntmGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				guardar();
				
			}
		});
	}
	public void guardar(){
		JFileChooser fileChooser=new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int resultado=fileChooser.showSaveDialog(this);
		if (resultado==JFileChooser.CANCEL_OPTION)return;
		File nombre=fileChooser.getSelectedFile();
		try{
			PrintWriter Salida =new PrintWriter( new FileWriter(nombre));
			Salida.write(textArea.getText());
			Salida.close();
		}catch(IOException ioException){
			JOptionPane.showMessageDialog(null, "Error ");
		}
	}
	public static void main(String[] args) {
		new Editor();	
	}
}
