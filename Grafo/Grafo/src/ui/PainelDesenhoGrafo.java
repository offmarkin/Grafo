package ui;

import modelo.GrafoEstrutura;
import java.awt.*;
import javax.swing.*;

public class PainelDesenhoGrafo extends JPanel {

    private GrafoEstrutura grafo;
    private boolean[] visitados;

    //posições x/y dos vertices no painel
    private int[] posX = {300, 120, 480, 80, 200, 400};
    private int[] posY = {80, 200, 200, 350, 350, 350};

    public PainelDesenhoGrafo(GrafoEstrutura grafo, boolean[] visitados) {
        this.grafo = grafo;
        this.visitados = visitados;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int[][] matriz = grafo.getMatriz();
        String[] vertices = grafo.getVertices();

        // Desenhar arestas
        for (int i = 0; i < vertices.length; i++) {
            for (int j = 0; j < vertices.length; j++) {
                if (matriz[i][j] == 1) {
                	
                	//desenha linha do vertice i ao j
                    g.drawLine(posX[i], posY[i], posX[j], posY[j]);
                }
            }
        }

        // Desenhar vértices
        for (int i = 0; i < vertices.length; i++) {
        	
        	//se visitado, pinta verde; caso contrário, cinza claro
            if (visitados[i])
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.LIGHT_GRAY);
            
            //circulo preenchido do vertice
            g.fillOval(posX[i] - 20, posY[i] - 20, 40, 40);
            
            //contorno e etiqueta do vertice
            g.setColor(Color.BLACK);
            g.drawOval(posX[i] - 20, posY[i] - 20, 40, 40);
            g.drawString(vertices[i], posX[i] - 5, posY[i] + 5);
        }
    }

    public void atualizarVisitados(boolean[] visitados) {
        this.visitados = visitados;
        repaint(); //solicita redraw imediato
    }
}