package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.Fornecedor;
import model.Produto;
import util.Conversao;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class TelaProduto extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfNome;
	private JTextField tfQtde;
	private JTextField tfValor;
	private JTable tabela;

	/**
	 * Create the frame.
	 */
	public TelaProduto() {
		
		setTitle("Sistema de Controle de Estoque");
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 611, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnTitulo = new JPanel();
		pnTitulo.setBackground(Color.LIGHT_GRAY);
		pnTitulo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(pnTitulo, BorderLayout.NORTH);
		
		JLabel lbTitulo = new JLabel("Cadastro de Produtos");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pnTitulo.add(lbTitulo);
		
		JTabbedPane tpProduto = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tpProduto, BorderLayout.CENTER);
		
		JPanel pnCadastro = new JPanel();
		pnCadastro.setLayout(null);
		pnCadastro.setBackground(SystemColor.menu);
		tpProduto.addTab("Cadastro", null, pnCadastro, null);
		
		JLabel lbCodigo = new JLabel("C\u00F3digo:");
		lbCodigo.setBounds(66, 30, 46, 14);
		pnCadastro.add(lbCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setEditable(false);
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(122, 27, 57, 20);
		pnCadastro.add(tfCodigo);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(66, 74, 38, 14);
		pnCadastro.add(lbNome);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(122, 71, 392, 20);
		pnCadastro.add(tfNome);
		
		JLabel lbQtde = new JLabel("Quantidade:");
		lbQtde.setBounds(46, 113, 78, 14);
		pnCadastro.add(lbQtde);
		
		tfQtde = new JTextField();
		tfQtde.setColumns(10);
		tfQtde.setBounds(123, 110, 86, 20);
		pnCadastro.add(tfQtde);
		
		JLabel lblNewLabel = new JLabel("Valor:");
		lblNewLabel.setBounds(66, 147, 38, 14);
		pnCadastro.add(lblNewLabel);
		
		tfValor = new JTextField();
		tfValor.setColumns(10);
		tfValor.setBounds(122, 144, 124, 20);
		pnCadastro.add(tfValor);
		
		JPanel pnTabela = new JPanel();
		tpProduto.addTab("Tabela", null, pnTabela, null);
		pnTabela.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnTabela.add(scrollPane, BorderLayout.CENTER);
		
		tabela = new JTable();
		atualizaTabela();
		tabela.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(tabela);
		
		JPanel pnRodape = new JPanel();
		contentPane.add(pnRodape, BorderLayout.SOUTH);
		pnRodape.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		pnRodape.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btLimpar = new JButton("Limpar");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaTela();
			}
		});
		
		JButton btListar = new JButton("Listar");
		btListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaProdutos();
			}
		});
		pnRodape.add(btListar);
		pnRodape.add(btLimpar);
		
		JButton btGravar = new JButton("Gravar");
		
		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaProduto();
			}
		});
		
		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				excluiProduto();
			}
		});
		pnRodape.add(btnExcluir);
		
		pnRodape.add(btGravar);
	}
	
	private void gravaProduto() {
		int codigo = Conversao.str2int(tfCodigo.getText());
		String nome = tfNome.getText();
		int qtde = Integer.parseInt(tfQtde.getText());
		double valor = Double.parseDouble(tfValor.getText());
		new Produto(codigo,nome,qtde,valor).gravar();
		limpaTela();
		atualizaTabela();
	}
	
	private void limpaTela() {
		tfCodigo.setText("");
		tfNome.setText("");
		tfQtde.setText("");
		tfValor.setText("");
		tfNome.requestFocus();
	}
	
	private void listaProdutos() {
		Produto.getLista().forEach(e -> System.out.println(e));		
	}
	
	private void excluiProduto() {
		int linha = tabela.getSelectedRow();
		String codigo = tabela.getModel().getValueAt(linha,0).toString();
		String nome = tabela.getModel().getValueAt(linha,1).toString();
		int id = Integer.parseInt(codigo);
		boolean ok = Produto.excluir(id);
		atualizaTabela();
		if (ok) {
			JOptionPane.showMessageDialog(null, 
					"Produto [" + nome + "] excluído com sucesso!");
		}
	}

	private void atualizaTabela() {
		tabela.setModel(Produto.getTableModel());		
	}
	
}