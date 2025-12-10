package ui;

import modelo.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;

public class JanelaGrafo extends JFrame {

    private GrafoEstrutura grafo;
    private BuscasGrafo buscas;

    private JTextField campoVertice;
    private JTextArea areaResultado;
    private JRadioButton radioDFS, radioBFS;
    
    //aqui marca quais os vertices visitados
    private boolean[] visitados;
    private PainelDesenhoGrafo painel;

    public JanelaGrafo(GrafoEstrutura grafo) {
        this.grafo = grafo;
        this.buscas = new BuscasGrafo(grafo);
        this.visitados = new boolean[grafo.getVertices().length];

        configurarJanela();
    }

    private void configurarJanela() {
        setTitle("Busca em Grafo");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // parte de cima
        JPanel topo = new JPanel();
        topo.add(new JLabel("Vértice inicial:"));

        campoVertice = new JTextField(5);
        topo.add(campoVertice);

        radioDFS = new JRadioButton("Profundidade", true);
        radioBFS = new JRadioButton("Largura");

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(radioDFS);
        grupo.add(radioBFS);

        topo.add(radioDFS);
        topo.add(radioBFS);

        JButton botao = new JButton("Executar");
        botao.addActionListener(e -> executarBusca());
        topo.add(botao);

        add(topo, BorderLayout.NORTH);

        // rodape
        areaResultado = new JTextArea(4, 30);
        areaResultado.setEditable(false);
        add(new JScrollPane(areaResultado), BorderLayout.SOUTH);

        // e o centro
        painel = new PainelDesenhoGrafo(grafo, visitados);
        add(painel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void executarBusca() {
        String nome = campoVertice.getText().toUpperCase();
        int inicio = grafo.buscarIndice(nome);

        if (inicio == -1) {// vertice invalido
            JOptionPane.showMessageDialog(this, "Vértice inválido!");
            return;
        }
        
        //reseta estado
        Arrays.fill(visitados, false);
        areaResultado.setText("");
        painel.repaint();
        
        new Thread(() -> animarBusca(inicio)).start();
    }

    private void animarBusca(int inicio) {
        try {
            java.util.List<Integer> ordem;
            
            //obtem a ordem sem animar
            if (radioDFS.isSelected()) {
            	//para o DFS recursiva precisamos passar um array de visitados local
                ordem = new ArrayList<>();
                boolean[] usados = new boolean[grafo.getVertices().length];
                buscas.dfs(inicio, usados, ordem);
            } else {
                ordem = buscas.bfs(inicio);
            }
            
            //agora faz a animacao passo a passo
            for (int v : ordem) {
                visitados[v] = true;//marca para colorir no painel
                
                //atualiza área de texto com o nome do vertice
                areaResultado.append(grafo.getVertices()[v] + " -> ");
                
                //pede para o painel redesenhar com o vertice marcado
                painel.repaint();
                
                //pausa para que usuario veja a etapa
                Thread.sleep(800);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}