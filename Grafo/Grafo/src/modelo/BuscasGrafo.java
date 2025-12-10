package modelo;

import java.util.*;

public class BuscasGrafo {
	
	//referencia para a estrutura do grafo
    private GrafoEstrutura grafo;
    
    //construtor recebe o grafo que sera usado nas buscas
    public BuscasGrafo(GrafoEstrutura grafo) {
        this.grafo = grafo;
    }

    // DFS recursiva
    public void dfs(int atual, boolean[] visitados, java.util.List<Integer> ordem) {
        visitados[atual] = true; 	//marca o vertice atual como visitado
        ordem.add(atual);			//registra o vértice na ordem de visita

        //percorre todos os possiveis vizinhos do vertice atual
        for (int i = 0; i < grafo.getVertices().length; i++) {
        	
        	 //se existe uma aresta entre 'atual' e 'i' e ainda não visitou 'i'
            if (grafo.getMatriz()[atual][i] == 1 && !visitados[i]) {
            	
            	// Chamada recursiva: "mergulha" no próximo vizinho
                dfs(i, visitados, ordem);
            }
        }
    }

    // BFS iterativa
    public java.util.List<Integer> bfs(int inicio) {
        boolean[] visitados = new boolean[grafo.getVertices().length];
        Queue<Integer> fila = new LinkedList<>(); // Estrutura fundamental da BFS
        java.util.List<Integer> ordem = new ArrayList<>();

        visitados[inicio] = true;	//marca o primeiro vertice
        fila.add(inicio);			//coloca o inicial na fila
        
        //enquanto houver vertices na fila
        while (!fila.isEmpty()) {
        	
            int atual = fila.poll();	//remove da fila
            ordem.add(atual);			// Registra a visita
            
            //verifica todos os vizinhos do vertice atual
            for (int i = 0; i < grafo.getVertices().length; i++) {
                if (grafo.getMatriz()[atual][i] == 1 && !visitados[i]) {
                    visitados[i] = true;	//marca como visitado
                    fila.add(i);			//insere o vizinho na fila
                }
            }
        }
        return ordem;	//retorna a sequência completa da BFS
    }
}