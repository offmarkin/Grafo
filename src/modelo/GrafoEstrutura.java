package modelo;

public class GrafoEstrutura {
	
	//vetor contendo os nomes dos vertices (A, B, C, D...)
    private String[] vertices;
    
    //matriz de adjacencia: matriz[i][j]
    private int[][] matrizAdj;
    
    
    //construtor que recebe o conjunto de vertices e sua matriz de adjacencia.
    public GrafoEstrutura(String[] vertices, int[][] matrizAdj) {
        this.vertices = vertices;
        this.matrizAdj = matrizAdj;
    }
    
    public String[] getVertices() { 
        return vertices; 
    }

    public int[][] getMatriz() { 
        return matrizAdj; 
    }
    
    
    //nome = letra digitada pelo usuario
    public int buscarIndice(String nome) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equalsIgnoreCase(nome)) //comparaçao sem diferenciar maiusculas
                return i;
        }
        return -1; //nao encontrou o vertice
    }
}