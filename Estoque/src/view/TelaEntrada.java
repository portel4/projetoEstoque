package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.Entrada;
import model.Fornecedor;
import model.Produto;
import util.Conversao;

public class TelaEntrada extends JFrame {

	private JPanel contentPane;
	private JTextField tfDoc;
	private JTextField tfQtde;
	private JTextField tfValor;
	private JTextField tfData;
	private JComboBox cbProduto;
	private JComboBox cbFornecedor;
	private List<Produto> produtos = new ArrayList<>();
	private List<Fornecedor> fornecedores = new ArrayList<>();

	/**
	 * Create the frame.
	 */
	public TelaEntrada() {
		initComponents();
		setTitle("Sistema de Controle de Estoque");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		configuraTela();
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 435);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnTitulo = new JPanel();
		pnTitulo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnTitulo.setBackground(Color.LIGHT_GRAY);
		contentPane.add(pnTitulo, BorderLayout.NORTH);
		
		JLabel lbCadastroDeEntradas = new JLabel("Cadastro de Entradas");
		lbCadastroDeEntradas.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pnTitulo.add(lbCadastroDeEntradas);
		
		JPanel pnRodape = new JPanel();
		pnRodape.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnRodape, BorderLayout.SOUTH);
		pnRodape.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btListar = new JButton("Listar");
		pnRodape.add(btListar);
		
		JButton btLimpar = new JButton("Limpar");
		pnRodape.add(btLimpar);
		
		JButton btGravar = new JButton("Gravar");
		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaEntrada();
			}
		});
		pnRodape.add(btGravar);
		
		JPanel pnCentral = new JPanel();
		contentPane.add(pnCentral, BorderLayout.CENTER);
		SpringLayout sl_pnCentral = new SpringLayout();
		pnCentral.setLayout(sl_pnCentral);
		
		JLabel lbProduto = new JLabel("Produto:");
		sl_pnCentral.putConstraint(SpringLayout.EAST, lbProduto, -446, SpringLayout.EAST, pnCentral);
		pnCentral.add(lbProduto);
		
		cbProduto = new JComboBox();
		sl_pnCentral.putConstraint(SpringLayout.WEST, cbProduto, 129, SpringLayout.WEST, pnCentral);
		sl_pnCentral.putConstraint(SpringLayout.EAST, cbProduto, -149, SpringLayout.EAST, pnCentral);
		sl_pnCentral.putConstraint(SpringLayout.NORTH, lbProduto, 3, SpringLayout.NORTH, cbProduto);
		sl_pnCentral.putConstraint(SpringLayout.NORTH, cbProduto, 48, SpringLayout.NORTH, pnCentral);
		cbProduto.setModel(new DefaultComboBoxModel(new String[] {"Produto"}));
		pnCentral.add(cbProduto);
		
		JLabel lbFornecedor = new JLabel("Fornecedor:");
		sl_pnCentral.putConstraint(SpringLayout.NORTH, lbFornecedor, 19, SpringLayout.SOUTH, lbProduto);
		sl_pnCentral.putConstraint(SpringLayout.EAST, lbFornecedor, -446, SpringLayout.EAST, pnCentral);
		pnCentral.add(lbFornecedor);
		
		cbFornecedor = new JComboBox();
		sl_pnCentral.putConstraint(SpringLayout.NORTH, cbFornecedor, 13, SpringLayout.SOUTH, cbProduto);
		sl_pnCentral.putConstraint(SpringLayout.WEST, cbFornecedor, 129, SpringLayout.WEST, pnCentral);
		sl_pnCentral.putConstraint(SpringLayout.EAST, cbFornecedor, 0, SpringLayout.EAST, cbProduto);
		cbFornecedor.setModel(new DefaultComboBoxModel(new String[] {"Fornecedor"}));
		pnCentral.add(cbFornecedor);
		
		JLabel lbData = new JLabel("Data:");
		sl_pnCentral.putConstraint(SpringLayout.NORTH, lbData, 23, SpringLayout.SOUTH, lbFornecedor);
		sl_pnCentral.putConstraint(SpringLayout.EAST, lbData, 0, SpringLayout.EAST, lbProduto);
		pnCentral.add(lbData);
		
		JLabel lbDocto = new JLabel("Docto:");
		sl_pnCentral.putConstraint(SpringLayout.EAST, lbDocto, 0, SpringLayout.EAST, lbProduto);
		pnCentral.add(lbDocto);
		
		tfDoc = new JTextField();
		sl_pnCentral.putConstraint(SpringLayout.NORTH, tfDoc, 63, SpringLayout.SOUTH, cbFornecedor);
		sl_pnCentral.putConstraint(SpringLayout.NORTH, lbDocto, 0, SpringLayout.NORTH, tfDoc);
		tfDoc.setText("Doc");
		sl_pnCentral.putConstraint(SpringLayout.WEST, tfDoc, 0, SpringLayout.WEST, cbProduto);
		pnCentral.add(tfDoc);
		tfDoc.setColumns(10);
		
		JLabel lbQtde = new JLabel("Qtde:");
		sl_pnCentral.putConstraint(SpringLayout.NORTH, lbQtde, 30, SpringLayout.SOUTH, lbDocto);
		sl_pnCentral.putConstraint(SpringLayout.WEST, lbQtde, 81, SpringLayout.WEST, pnCentral);
		pnCentral.add(lbQtde);
		
		JLabel lbValor = new JLabel("Valor:");
		sl_pnCentral.putConstraint(SpringLayout.NORTH, lbValor, 25, SpringLayout.SOUTH, lbQtde);
		sl_pnCentral.putConstraint(SpringLayout.WEST, lbValor, 81, SpringLayout.WEST, pnCentral);
		pnCentral.add(lbValor);
		
		tfQtde = new JTextField();
		tfQtde.setText("Qtde");
		sl_pnCentral.putConstraint(SpringLayout.NORTH, tfQtde, -3, SpringLayout.NORTH, lbQtde);
		sl_pnCentral.putConstraint(SpringLayout.WEST, tfQtde, 0, SpringLayout.WEST, cbProduto);
		pnCentral.add(tfQtde);
		tfQtde.setColumns(10);
		
		tfValor = new JTextField();
		tfValor.setText("Valor");
		sl_pnCentral.putConstraint(SpringLayout.NORTH, tfValor, 0, SpringLayout.NORTH, lbValor);
		sl_pnCentral.putConstraint(SpringLayout.WEST, tfValor, 0, SpringLayout.WEST, cbProduto);
		pnCentral.add(tfValor);
		tfValor.setColumns(10);
		
		tfData = new JTextField();
		tfData.setText("Data");
		sl_pnCentral.putConstraint(SpringLayout.NORTH, tfData, 20, SpringLayout.SOUTH, cbFornecedor);
		sl_pnCentral.putConstraint(SpringLayout.WEST, tfData, 20, SpringLayout.EAST, lbData);
		sl_pnCentral.putConstraint(SpringLayout.EAST, tfData, 135, SpringLayout.EAST, lbData);
		pnCentral.add(tfData);
		tfData.setColumns(10);
	}
	
	private void gravaEntrada() {
		Produto p = null;
		Fornecedor f = null;
		int id = cbProduto.getSelectedIndex();
		if (id >= 0) p = produtos.get(id);
		id = cbFornecedor.getSelectedIndex();
		if (id >= 0) f = fornecedores.get(id);
		Date data = Conversao.str2dmy(tfData.getText());
		String doc = tfDoc.getText();
		int qtde = Conversao.str2int(tfQtde.getText());
		double valor = Conversao.str2double(tfValor.getText());
		new Entrada(p,f,data,doc,qtde,valor).gravar();
		limpaTela();
	}
	
	private void configuraTela() {
		carregaProdutos();
		carregaFornecedores();
		limpaTela();
	}
	
	private void carregaProdutos() {
		produtos = Produto.getLista();
		cbProduto.removeAllItems();
		for (Produto p: produtos) {
			cbProduto.addItem(p.getNome());
		}
	}
	
	private void carregaFornecedores() {
		fornecedores = Fornecedor.getLista();
		cbFornecedor.removeAllItems();
		fornecedores.forEach(e -> cbFornecedor.addItem(e.getNome()));
	}
	
	private void limpaTela() {
		tfData.setText("");
		tfDoc.setText("");
		tfQtde.setText("");
		tfValor.setText("");
		cbProduto.requestFocus();
	}
	
	
}