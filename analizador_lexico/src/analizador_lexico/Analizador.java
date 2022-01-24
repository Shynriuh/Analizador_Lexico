package analizador_lexico;

import java.util.ArrayList;

public class Analizador {
	ArrayList<Token> lista_token = new ArrayList();
	
	//Constructor parametrizado
	public Analizador(ArrayList<Token> lista_token) {
		this.lista_token = lista_token;
	}
	
	public void analizar(String cadena) {
		int estado = -1;
		int numero_token = -1;
		String lexema = "";
		String tipo = "";
		//Separa cada que encuentre un espacio
		String [] lineas = separador(cadena, ' ');
		
		//Analiza cada caracter
		for(int i=0; i<lineas.length; i++) {			//Num de filas
			for(int j=0; j<lineas[i].length(); j++) {	//Num de columas de la fila
				
				int n_actual, n_siguiente = -1;
				
				//Palabras reservadas
				if((lineas[i].equals("int"))
						|| (lineas[i].equals("float"))
						|| (lineas[i].equals("void"))) {
					estado = 4;
					j=lineas[i].length()-1;
				} else if(lineas[i].equals("if")) {
					estado = 19;
					j=lineas[i].length()-1;
				} else if(lineas[i].equals("while")) {
					estado = 20;
					j=lineas[i].length()-1;
				} else if(lineas[i].equals("return")) {
					estado = 21;
					j=lineas[i].length()-1;
				} else if(lineas[i].equals("else")) {
					estado = 22;
					j=lineas[i].length()-1;
				}
				
				//Se obtiene el valor en ASCII del caracter
				n_actual = lineas[i].codePointAt(j);
				
				if(estado == -1) {
					estado = estado_transicion(n_actual);
				}
				
				//Avanza al siguiente caracter
				try {
					n_siguiente = lineas[i].codePointAt(j+1);
				}catch(Exception e) {
					
				}
				
				//Estado de aceptacion
				switch(estado) {
				
					case 0: //ID
						//Va almacenando los caracteres
						lexema = lexema + lineas[i].charAt(j);
						//Si el siguiente es un caracter o numero
						if((n_siguiente>96 && n_siguiente<123)
								|| (n_siguiente>64 && n_siguiente<91)
								|| (n_siguiente>47 && n_siguiente<58)) {
							estado = 0;
						} else {
							numero_token = 0;
							tipo = "Identificador";
							estado = -1;
						}
						break;
						
					case 1: //Entero
						lexema = lexema + lineas[i].charAt(j);
						if(n_siguiente>47 && n_siguiente<58) {
							estado = 1;
						} else if(n_siguiente == 46) { //Si es un punto
							estado = 2;
						} else {
							numero_token = 1;
							tipo = "Entero";
							estado = -1;
						}
						break;
						
					case 2: //Real
						lexema = lexema + lineas[i].charAt(j);
						if(n_siguiente>47 && n_siguiente<58) {
							estado = 2;
						} else {
							numero_token = 2;
							tipo = "Real";
							estado = -1;
						}
						break;
						
					case 4: //Tipo
						lexema = lineas[i];
						numero_token = 4;
						tipo = "Tipo";
						estado = -1;
						break;
					
					case 5: //Operador de adicion
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 5;
						tipo = "OpAdicion";
						estado = -1;
						break;
						
					case 6: //Operador de multiplicacion
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 6;
						tipo = "OpMul";
						estado = -1;
						break;
						
					case 7: //Operador relacional
						lexema = lexema + lineas[i].charAt(j);
						if(n_siguiente == 61) {
							estado = 7;
						} else {
							numero_token = 7;
							tipo = "OpRelac";
							estado = -1;
						}
						break;
					
					case 8: //Operador OR
						lexema = lexema + lineas[i].charAt(j);
						if(n_siguiente == 124) {
							estado = 8;
						} else {
							numero_token = 8;
							tipo = "OpOR";
							estado = -1;
						}
						break;
						
					case 9: //Operador AND
						lexema = lexema + lineas[i].charAt(j);
						if(n_siguiente == 38) {
							estado = 9;
						} else {
							numero_token = 9;
							tipo = "OpAND";
							estado = -1;
						}
						break;
						
					case 10: //Operador NOT
						lexema = lexema + lineas[i].charAt(j);
						if(n_siguiente == 61) {
							estado = 11;
						} else {
							numero_token = 10;
							tipo = "OpNOT";
							estado = -1;
						}
						break;
						
					case 11: //Operador de igualdad
						lexema = lexema + lineas[i].charAt(j);
						if(n_siguiente == 61) {
							estado = 11;
						} else {
							numero_token = 11;
							tipo = "OpIgualdad";
							estado = -1;
						}
						break;
						
					case 12: //Punto y coma
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 12;
						tipo = "PuntoComa";
						estado = -1;
						break;
					
					case 13: //Coma
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 13;
						tipo = "Coma";
						estado = -1;
						break;
						
					case 14: //Parentesis
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 14;
						tipo = "Parentesis";
						estado = -1;
						break;
						
					case 15: //Parentesis
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 15;
						tipo = "Parentesis";
						estado = -1;
						break;
						
					case 16: //Llave
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 16;
						tipo = "Llave";
						estado = -1;
						break;
						
					case 17: //Llave
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 17;
						tipo = "Llave";
						estado = -1;
						break;
					
					case 18: //Asignacion
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 18;
						tipo = "Asignacion";
						estado = -1;
						break;
						
					case 19: //if
						lexema = lineas[i];
						numero_token = 19;
						tipo = "Reservado";
						estado = -1;
						break;
						
					case 20: //while
						lexema = lineas[i];
						numero_token = 20;
						tipo = "Reservado";
						estado = -1;
						break;
						
					case 21: //return
						lexema = lineas[i];
						numero_token = 21;
						tipo = "Reservado";
						estado = -1;
						break;
						
					case 22: //else
						lexema = lineas[i];
						numero_token = 22;
						tipo = "Reservado";
						estado = -1;
						break;
						
					case 23: //$
						lexema = lexema + lineas[i].charAt(j);
						numero_token = 23;
						tipo = "Pesos";
						estado = -1;
						break;
						
					case 100: //Ignora los espacios
						estado = -2;
						break;
						
					case 999:
						lexema = String.valueOf(lineas[i].charAt(j));
						numero_token = 666;
						tipo = "Error";
						estado = -1;
						break;
				}
				//Se agrega nuevo Token y le pasa las variables
				if(estado == -1) {
					lista_token.add(new Token(lexema, numero_token, tipo));
					lexema = "";
					tipo = "";
				}
				//Cuando hay un espacio o enter
				if(estado == -2) {
					estado = -1;
				}
			}
		}
		
	}
	
	public int estado_transicion(int n) {
		//Si pertenece a-z o A-Z
		if((n>96 && n<123) || (n>64 && n<91)) {
			return 0;
		}
		//Si es un entero
		else if(n>47 && n<58) {
			return 1;
		}
		//Operador de adicion
		else if(n==43 || n==45) {
			return 5;
		}
		//Operador de multiplicacion
		else if(n==42 || n==47) {
			return 6;
		}
		//Operador relacional
		else if(n==60 || n==62) {
			return 7;
		}
		//Operador OR
		else if(n==124) {
			return 8;
		}
		//Operador AND
		else if(n==38) {
			return 9;
		}
		//Operador NOT
		else if(n==33) {
			return 10;
		}
		//Operador de igualdad
		else if(n==61 || n==33) {
			return 11;
		}
		//Punto y coma
		else if(n==59) {
			return 12;
		} //Coma
		else if(n==44) {
			return 13;
		} //Parentesis abierto
		else if(n==40) {
			return 14;
		} //Parentesis cerrado
		else if(n==41) {
			return 15;
		} //Llave abierta
		else if(n==123) {
			return 16;
		} //Lave cerrada
		else if(n==125) {
			return 17;
		} //Operador de asignacion
		else if(n==61) {
			return 18;
		} //$
		else if(n==36) {
			return 23;
		}
		//estado de aceptacion de espacios o enter
		else if(n<=32) {
			return 100; 
		}
		else {
			return 999;
		}
	}
	
	public String[] separador(String texto, char separar) {
		String linea = "";
		int contador = 0;
		
		//Cuenta la cantidad de lineas de la cadena
		for(int i=0; i<texto.length(); i++) {
			if(texto.charAt(i) == separar) {
				contador++;
			}
		}
		
		//Se crea un nuevo arreglo del tamaño del contador
		String[] cadenas = new String[contador];
		contador = 0;
		
		for(int i=0; i<texto.length(); i++) {
			if(texto.charAt(i) != separar) { //Si no es un espacio
				linea = linea + String.valueOf(texto.charAt(i)); //Concatena los caracteres
			}
			else { //Si es un espacio
				cadenas[contador] = linea; //Guarda el lexema
				contador++; //Cambia a la siguiente linea
				linea = ""; //Se limpia para la siguiente linea
			}
		}
		
		return cadenas; //Devuelve los lexemas encontrados
	}
}
