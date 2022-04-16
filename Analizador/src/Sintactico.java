import java.util.Stack;
import java.util.ArrayList;

public class Sintactico {
	ArrayList<Token> lista_token = new ArrayList<Token>();
	
	//Constructor parametrizado
	public Sintactico(ArrayList<Token> lista_token) {
		this.lista_token = lista_token;
	}
	
	public boolean LR_1_2() {
		int[][] tablaLR = new int[5][4];
		tablaLR[0][0] = 2; tablaLR[0][1] = 0; tablaLR[0][2] = 0; tablaLR[0][3] = 1;
		tablaLR[1][0] = 0; tablaLR[1][1] = 0; tablaLR[1][2] = -1; tablaLR[1][3] = 0;
		tablaLR[2][0] = 0; tablaLR[2][1] = 3; tablaLR[2][2] = -2; tablaLR[2][3] = 0;
		tablaLR[3][0] = 2; tablaLR[3][1] = 0; tablaLR[3][2] = 0; tablaLR[3][3] = 4;
		tablaLR[4][0] = 0; tablaLR[4][1] = 0; tablaLR[4][2] = -3; tablaLR[4][3] = 0;
		Stack<String> pila = new Stack<String>();
		int fila, columna, accion, E=0, i=0;
		
		//Inicio
		lista_token.add(new Token("$", 2, "pesos"));
		pila.push("$");
		pila.push("0");
		
		fila = Integer.parseInt(pila.peek()); //Se obtiene el valor de la pila
		columna = lista_token.get(0).getNumero_token();	//Se obtiene el valor del token
		accion =  tablaLR[fila][columna];	//Se determina el valor de la accion
		
		for(String p : pila) //Se imprime la pila
			System.out.print(p);
		System.out.println("\nfila: " + fila + " | columna: " + columna + " | accion: " + accion);
		
			
		while(accion != 0) {
				
			if(accion > 0) {
					
				if(accion != 4) {	//Se realiza un desplazamiento
					pila.push(lista_token.get(i).getLexema());
					pila.push(Integer.toString(accion));
				}
				//Se obtiene el valor de la pila y se determina la accion
				
				fila = Integer.parseInt(pila.peek()); //Se obtiene el valor de la pila
				columna = lista_token.get(i+1).getNumero_token();	//Se obtiene el valor del token
				accion =  tablaLR[fila][columna];	//Se determina el valor de la accion
				
				for(String p : pila) //Se imprime la pila
					System.out.print(p);
				System.out.println("\nfila: " + fila + " | columna: " + columna + " | accion: " + accion);
			}
			else if(accion < 0) {	//Se realiza una reduccion
					
				if(accion == -1)	//Si es r0
					return true;
				else if(accion == -3)//Si es r1
					E = 6;
				else if(accion == -2)//Si es r2
					E = 2;
					
				//Se reduce la pila E veces
				for(int j=0; j<E; j++) {
					pila.pop();
				}
					
				//Se ingresa E y su accion a la pila
				fila = Integer.parseInt(pila.peek()); //Se obtiene el valor de la pila
				columna = 3; //3 es el valor de E
				accion =  tablaLR[fila][columna];	//Se determina el valor de la accion
				pila.push("E");
				pila.push(Integer.toString(accion));

				fila = Integer.parseInt(pila.peek()); //Se obtiene el valor de la pila
				columna = lista_token.get(i).getNumero_token();	//Se obtiene el valor del token
				accion =  tablaLR[fila][columna];	//Se determina el valor de la accion
					
				for(String p : pila) //Se imprime la pila
					System.out.print(p);
				System.out.println("\nfila: " + fila + " | columna: " + columna + " | accion: " + accion);
			}
			else if(accion == 0)	//Se invalida la cadena
				return false;
			
			if(i<lista_token.size()-1)
				i++;
		}
		return false;
	}

}
