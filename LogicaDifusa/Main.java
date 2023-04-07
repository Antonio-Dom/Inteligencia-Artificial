package LogicaDifusa;

import javax.swing.JButton;
//import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Main extends JFrame implements ActionListener  {

	private static final long serialVersionUID = 1L;
	private JButton boton1;
//	private JButton boton2;
//	private JDialog ventana2;
	private Horario horario;
	private JLabel etiqResultados1;
	private JLabel etiqResultados2;
	private JLabel etiqPromedio;
	private String rutaArchivoFCL;
	
    public static void main(String[] args) {
    	
    	String rutaArchivo = "C:\\Users\\PC\\eclipse-workspace\\InteligenciaAr\\src\\LogicaDifusa\\archivo.fcl";
    	
    	new Main(rutaArchivo);
    }
    
    public Main(String rutaArchivo) {
    	super("Evaluar horarios de estudiantes");
        this.rutaArchivoFCL = rutaArchivo;
    	creaVentanaPrincipal();
//      creaVentana2();
    }
    
    public void creaVentanaPrincipal() {
    	boton1 = new JButton("Generar otro horario");
//    	boton2 = new JButton("Mostrar gráficas de entrada");
    	etiqResultados1 = new JLabel();
    	etiqResultados2 = new JLabel();
    	etiqPromedio = new JLabel();
    	
        add(boton1);
 //     add(boton2);
        add(etiqResultados1);
        add(etiqResultados2);
        add(etiqPromedio);
        
        setLayout(null);
        setSize(430, 560);
//      setSize(430, 700);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        boton1.setBounds(135, 15, 150, 25);
//      boton1.setBounds(25, 15, 150, 25);
//      boton2.setBounds(195, 15, 195, 25);
        etiqResultados1.setBounds(98, 427, 250, 15);
        etiqResultados2.setBounds(65, 445, 300, 15);
        etiqPromedio.setBounds(150, 462, 150, 25);
        
        boton1.addActionListener(this);
//      boton2.addActionListener(this);
    }
/*
    public void creaVentana2() {
    //	JLabel etiqueta = new JLabel("Texto");
    	ventana2 = new JDialog(this, "Gráficas de entrada");
    //	ventana2.add(etiqueta);
    	ventana2.setSize(800, 500);
    	ventana2.setResizable(false);
    }
*/    
    public void aplicaLogicaDifusa(String rutaArchivo) {
        
    	FIS fis = FIS.load(rutaArchivo, true);
    	FunctionBlock fb = new FunctionBlock(fis);
    	
    	try {
    	fb = fis.getFunctionBlock(null);
    	}catch(Exception e) {
    		System.out.println("Escriba correctamente la ruta del archivo");
    	}

        fb.setVariable("materiasCargadas", horario.getMateriasCargadas());
        fb.setVariable("horasLibres", horario.getHorasLibres());
        fb.setVariable("horaDeEntrada", horario.getHoraDeEntrada());
        fb.setVariable("promedioDeExigenciaProfesores", horario.getPromedioDeExigenciaProfesores());

        fb.evaluate();

        // Ajusta al rango 0 - 100
        Variable calificacionHorario = fb.getVariable("calificacionHorario");
        horario.setCalificacion((int)(calificacionHorario.getValue() - 33.3) * 3);
        
    }

    public void actionPerformed(ActionEvent e) {
    	if (e.getSource() == boton1) {
    		repaint();
    		etiqResultados1.setText(horario.getMateriasCargadasYHorasLibres());
    		etiqResultados2.setText(horario.getHoraDeEntradaYPromedio());
    		etiqPromedio.setText("Calificación: "+horario.getCalificacion()+"/100");
        }/*
    	else if (e.getSource() == boton2) {
        	ventana2.setVisible(true);
        }*/
    }
    
    public void paint(Graphics g) {
		super.paint(g);
		horario = new Horario(g);
		aplicaLogicaDifusa(rutaArchivoFCL);
		etiqResultados1.setText(horario.getMateriasCargadasYHorasLibres());
		etiqResultados2.setText(horario.getHoraDeEntradaYPromedio());
		etiqPromedio.setText("Calificación: "+horario.getCalificacion()+"/100");
		horario.graficar();
		etiqResultados1.repaint(getBounds());
    }
    
}



