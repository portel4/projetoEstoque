package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.Entrada;
import model.Kardex;
import model.Produto;
import util.Conversao;

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
		setTitle("Movimenta��o de Estoque");
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
		
		JButton btSaida = new JButton("Saida");
		btSaida.setIconTextGap(8);
		btSaida.setIcon(new ImageIcon(TelaKardex.class.getResource("/images/btAdiciona.png")));
		pnRodape.add(btSaida);
		
		JButton btAlterar = new JButton("Alterar");
		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				alteraKardex();
			}
		});
		btAlterar.setIconTextGap(8);
		btAlterar.setIcon(new ImageIcon(TelaKardex.class.getResource("/images/btAltera.png")));
		pnRodape.add(btAlterar);
		
		JButton btExcluir = new JButton("Excluir");
		btExcluir.setIconTextGap(8);
		btExcluir.setIcon(new ImageIcon(TelaKardex.class.getResource("/images/btExclui.png")));
		pnRodape.add(btExcluir);
	}
	
	private void alteraKardex() {
		int linha = tabela.getSelectedRow();
		String tipo = tabela.getModel().getValueAt(linha,0).toString();		
		int codigo = Conversao.str2int(
					tabela.getModel().getValueAt(linha,1).toString());
		if (tipo.equals("E")) {  // � uma entrada
			Entrada e = Entrada.pesquisar(codigo);
			if (e == null) {
				JOptionPane.showMessageDialog(null, 
						"Registro de Entrada n�o localizado!");
			} else {
				new TelaEntrada(e).setVisible(true);
			}
		}
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
		tabela.setModel(Kardex.getTableModel(produto));
	}

}