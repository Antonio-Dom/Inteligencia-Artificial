package LogicaDifusa;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

public class Horario {

	private Random r;
	private Graphics g;
	private int materiasCargadas;
	private int horasLibres;
	private int horaDeEntrada;
	private int[] horario;
	private int calificacion;
	
	public Horario(Graphics g) {
		this.g = g;
		this.r = new Random();
		this.materiasCargadas = generaMateriasCargadas();
		this.horasLibres = generaHorasLibres();
		this.horaDeEntrada = generaHoraDeEntrada();
		this.horario = generaHorarioRandom();
	}
	
	private int generaMateriasCargadas() {
		
		int materiasCargadas = 0;
		double nAleatorio = r.nextDouble();
		
		if(nAleatorio < 0.1) {
			if(nAleatorio < 0.05) materiasCargadas = 3;
			else materiasCargadas = 4;
			
		} else if(nAleatorio < 0.3) {
			if(nAleatorio < 0.2) materiasCargadas = 5;
			else materiasCargadas = 8;
			
		} else {
			if(nAleatorio < 0.65) materiasCargadas = 6;
			else materiasCargadas = 7;
		}
		return materiasCargadas;
	}
	
	private int generaHorasLibres() {

		double nAleatorio = r.nextDouble();
		int horasLibres = 0;
		
		if(nAleatorio < 0.1) {
			if (nAleatorio < 0.33) horasLibres = 3;
			else if (nAleatorio < 0.66) horasLibres = 4;
			else horasLibres = 5;
			
		} else if(nAleatorio < 0.2) {
			horasLibres = 2;
			
		} else {
			if (nAleatorio < 0.6) horasLibres = 1;
			else horasLibres = 0;
		}
		return horasLibres;
	}
	
	private int generaHoraDeEntrada() {

		int horaEntrada = 0;
		double nAleatorio;
		do {
			nAleatorio = r.nextDouble();
			
			if(nAleatorio < 0.3) {
				
				if(nAleatorio < 0.15) horaEntrada = 7;
				else horaEntrada = 8;
				
			} else if(nAleatorio < 0.5) {
	
				if(nAleatorio < 0.35) horaEntrada = 9;
				if(nAleatorio < 0.4) horaEntrada = 10;
				if(nAleatorio < 0.45) horaEntrada = 11;
				else horaEntrada = 12;
				
			} else if(nAleatorio < 0.8) {
				
				if(nAleatorio < 0.65) horaEntrada = 13;
				else horaEntrada = 14;
				
			} else {
				
				if(nAleatorio < 0.866) horaEntrada = 15;
				if(nAleatorio < 0.933) horaEntrada = 16;
				else horaEntrada = 17;
			}
		}while((materiasCargadas+horasLibres+horaEntrada)>=21);
		return horaEntrada;
	}
	
	private int[] generaHorarioRandom() {
		
		horario = new int[13];
		int asignar = materiasCargadas+horasLibres;
		int materiasPorBorrar = horasLibres;
		int posicion = horaDeEntrada-6;
		
		for(int i =0; i<horario.length; i++) {
			horario[i] = -1;
			if(asignar > 0 && i >=  horaDeEntrada-7) {
				horario[i] = generaExigenciaProfesor();
				asignar--;
			}
		}
		
		while(materiasPorBorrar > 0) {

			if(posicion == horaDeEntrada+materiasCargadas+horasLibres-8) {
				posicion = horaDeEntrada-6;
			}
			if (r.nextInt(5) == 0 && horario[posicion] != -1) {
				horario[posicion] = -1;
				materiasPorBorrar--;
			}
			posicion++;
		}
		
		return horario;
	}

	
	private int generaExigenciaProfesor() {
		double nAleatorio = r.nextDouble();
		int exigencia = 0;
		
		if(nAleatorio < 0.8) {
			exigencia = r.nextInt(40)+31;
			
		} else if(nAleatorio < 0.95) {
			if(nAleatorio < 0.875) exigencia = r.nextInt(20)+11;
			else exigencia = r.nextInt(20)+71;
			
		} else {
			if(nAleatorio < 0.975) exigencia = r.nextInt(11);
			else exigencia = r.nextInt(10)+91;
		}
		return exigencia;
	}
	
	public void graficar() {
		
		Font font = new Font("Calibri", Font.PLAIN, 20);
	    g.setFont(font);

		g.setColor(new Color(188,188,188));
		g.drawRect(30, 447, 370, 75);
		
		g.setColor(new Color(248,248,248));
		g.fillRect(30, 85, 370, 350);
		
		g.setColor(new Color(34,42,53));
		g.fillRect(30, 85, 370, 25);

		g.setColor(new Color(238,238,238));
		g.drawRect(30, 85, 70, 350);
		for(int i=0; i<5; i++) {
			g.drawRect(100, 85, 60+(i*60), 350);
		}
		
		for(int i=0; i<horario.length; i++) {
			g.setColor(new Color(34,42,53));
			if (horario[i] != -1) {
				pintarMateria(i);
			}
		}
		
		pintarFondoHora();
		for(int i=0; i<horario.length; i++) {
			g.drawString((i+7)+":00", 43, 130+(i*25));
		}
		
		for(int i=0; i<14; i++) {
			g.setColor(new Color(238,238,238));
			g.drawRect(30, (85+(i*25)), 370, 25);
		}

		g.drawLine(100, 85, 100, 435);
		
		
	}
	
	private void pintarMateria(int i) {
		if (horario[i] < 16 || (horario[i]>82 && horario[i] <= 100)) {
			g.setColor(new Color(196, 89, 17));
			g.fillRect(30, (110+(i*25)), 370, 25);
			g.setColor(Color.WHITE);
		    g.drawString("Exigencia del profesor "+horario[i]+"%", 125, 130+(i*25));
		}
		else if (horario[i]>34 && horario[i] < 67) {
			g.setColor(new Color(56, 86, 36));
			g.fillRect(30, (110+(i*25)), 370, 25);
			g.setColor(Color.WHITE);
		    g.drawString("Exigencia del profesor "+horario[i]+"%", 125, 130+(i*25));
		}
		else {
			g.setColor(new Color(255, 192, 0));
			g.fillRect(30, (110+(i*25)), 370, 25);
			g.setColor(Color.BLACK);
		    g.drawString("Exigencia del profesor "+horario[i]+"%", 125, 130+(i*25));
		}
	}
	
	private void pintarFondoHora() {
		
		
		if ( calificacion < 65 ) {
			g.setColor(new Color(196, 89, 17));
			g.fillRect(30, 85, 70, 350);
			g.setColor(Color.WHITE);
			
		} else if ( calificacion < 85 ) {
			g.setColor(new Color(255, 192, 0));
			g.fillRect(30, 85, 70, 350);
			g.setColor(Color.BLACK);
			
		} else {
			g.setColor(new Color(56, 86, 36));
			g.fillRect(30, 85, 70, 350);
			g.setColor(Color.WHITE);
		}
	}

	public int getMateriasCargadas() {
		return materiasCargadas;
	}


	public int getHorasLibres() {
		return horasLibres;
	}
	

	public String getMateriasCargadasYHorasLibres() {
		return "Materias cargadas: "+materiasCargadas
				+"      \tHoras libres: "+horasLibres;
	}


	public int getHoraDeEntrada() {
		return horaDeEntrada;
	}

	public int getPromedioDeExigenciaProfesores() {
		
		int promedio = 0;
		for(int i =0; i<horario.length; i++) {
			if(horario[i] != -1) {
				promedio += horario[i];
			}
		}
		return promedio / materiasCargadas;
		
	}
	
	public String getHoraDeEntradaYPromedio() {
		return "Hora de entrada: "+horaDeEntrada
				+"      \tPromedio de exigencia: "
				+getPromedioDeExigenciaProfesores()+"%";
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}
	
	
}
