package app;

import modelo.GrafoEstrutura;
import ui.JanelaGrafo;

public class Main {
    public static void main(String[] args) {
    	//definir nomes dos vertices (aqui a ordem importa por causa dos indices)
        String[] vertices = {"A", "B", "C", "D", "E", "F"};
        
        //matriz[i][j] == 1 indica aresta entre i e j
        int[][] matriz = {
        	   //A, B, C, D, E, F
                {0, 1, 1, 0, 0, 0},//A
                {1, 0, 0, 1, 1, 0},//B
                {1, 0, 0, 0, 0, 0},//C
                {0, 1, 0, 0, 0, 0},//D
                {0, 1, 0, 0, 0, 1},//E
                {0, 0, 0, 0, 1, 0} //F
        };
        
        //cria o objeto que representa a estrutura do grafo
        GrafoEstrutura grafo = new GrafoEstrutura(vertices, matriz);
        
        //abre a janelinha
        new JanelaGrafo(grafo);
    }
}