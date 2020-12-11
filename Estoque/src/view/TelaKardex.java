package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import model.Entrada;
import model.Produto;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class TelaKardex extends JFrame {

	private JPanel contentPane;
	private JTable tabela;
	private JComboBox cbProduto;
	private Produto produto;
	private List<Produto> produtos = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public TelaKardex() {
		initComponents();
		setTitle("Movimentação de Estoque");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		produtos = Produto.getLista();
		mostraProdutos();
	}
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbProduto = new JLabel("Produto:");
		panel.add(lbProduto);
		
		cbProduto = new JComboBox();
		cbProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionaProduto();
			}
		});
		cbProduto.setPreferredSize(new Dimension(250, 22));
		cbProduto.setModel(new DefaultComboBoxModel(new String[] {"Selecione o Produto..."}));
		panel.add(cbProduto);
		
		JScrollPane pnTabela = new JScrollPane();
		contentPane.add(pnTabela, BorderLayout.CENTER);
		
		tabela = new JTable();
		atualizaTabela(0);
		pnTabela.setViewportView(tabela);
		
		JPanel pnRodape = new JPanel();
		pnRodape.setBackground(Color.LIGHT_GRAY);
		pnRodape.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnRodape, BorderLayout.SOUTH);
		
		JButton btEntrada = new JButton("Entrada");
		btEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaEntrada();
			}
		});
		btEntrada.setIconTextGap(8);
		btEntrada.setIcon(new ImageIcon(TelaKardex.class.getResource("/images/btAdiciona.png")));
		pnRodape.add(btEntrada);
		
		JButton btnSaida = new JButton("Saida");
		btnSaida.setIconTextGap(8);
		btnSaida.setIcon(new ImageIcon(TelaKardex.class.getResource("/images/btAdiciona.png")));
		pnRodape.add(btnSaida);
	}
	
	
	// chama a tela de Entrada
	private void telaEntrada() {
		new TelaEntrada().setVisible(true);
	}
	
	// Preencher o Combobox de Produtos
	private void mostraProdutos() {
		cbProduto.removeAllItems();
		for (Produto p: produtos) {
			cbProduto.addItem(p.getNome());
		}
	}
	
	// Seleciona o Produto determinado pelo Combobox
	private void selecionaProduto() {
		int item = cbProduto.getSelectedIndex();
		if (item >= 0) {
			produto = produtos.get(item);
			atualizaTabela(produto.getCodigo());
			//System.out.println("Item Selecionado: " + item +
			//				   "\tProduto: " + produto.getNome());
		} else {
			produto = null;
			atualizaTabela(0);
		}
	}
	
	private void atualizaTabela(int produto) {
		tabela.setModel(Entrada.getTableModel(produto));
	}

}