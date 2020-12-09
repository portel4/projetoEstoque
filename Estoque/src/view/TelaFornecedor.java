package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.SystemColor;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.Fornecedor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaFornecedor extends JFrame {

	private JPanel contentPane;
	private JTextField tfCodigo;
	private JTextField tfCNPJ;
	private JTextField tfTelefone;
	private JTextField tfNome;
	private JTable tabela;
	private JButton btListar;

	/**
	 * Create the frame.
	 */
	public TelaFornecedor() {
		initComponents();
		setTitle("Sistema de Controle de Estoque");
		setLocationRelativeTo(null);  // centralizado
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel pnTitulo = new JPanel();
		pnTitulo.setBackground(Color.LIGHT_GRAY);
		pnTitulo.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		contentPane.add(pnTitulo, BorderLayout.NORTH);
		
		JLabel lbTitulo = new JLabel("Cadastro de Fornecedores");
		lbTitulo.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pnTitulo.add(lbTitulo);
		
		JTabbedPane tpFornecedor = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tpFornecedor, BorderLayout.CENTER);
		
		JPanel pnCadastro = new JPanel();
		pnCadastro.setBackground(SystemColor.menu);
		tpFornecedor.addTab("Cadastro", null, pnCadastro, null);
		SpringLayout sl_pnCadastro = new SpringLayout();
		pnCadastro.setLayout(sl_pnCadastro);
		
		JLabel lbCdigo = new JLabel("C\u00F3digo:");
		sl_pnCadastro.putConstraint(SpringLayout.NORTH, lbCdigo, 45, SpringLayout.NORTH, pnCadastro);
		sl_pnCadastro.putConstraint(SpringLayout.WEST, lbCdigo, 57, SpringLayout.WEST, pnCadastro);
		pnCadastro.add(lbCdigo);
		
		tfCodigo = new JTextField();
		sl_pnCadastro.putConstraint(SpringLayout.NORTH, tfCodigo, -3, SpringLayout.NORTH, lbCdigo);
		pnCadastro.add(tfCodigo);
		tfCodigo.setColumns(10);
		
		JLabel lbNome = new JLabel("Nome");
		sl_pnCadastro.putConstraint(SpringLayout.NORTH, lbNome, 25, SpringLayout.SOUTH, lbCdigo);
		sl_pnCadastro.putConstraint(SpringLayout.EAST, lbNome, 0, SpringLayout.EAST, lbCdigo);
		pnCadastro.add(lbNome);
		
		JLabel lbCnpj = new JLabel("CNPJ");
		sl_pnCadastro.putConstraint(SpringLayout.NORTH, lbCnpj, 27, SpringLayout.SOUTH, lbNome);
		sl_pnCadastro.putConstraint(SpringLayout.EAST, lbCnpj, 0, SpringLayout.EAST, lbCdigo);
		pnCadastro.add(lbCnpj);
		
		tfCNPJ = new JTextField();
		sl_pnCadastro.putConstraint(SpringLayout.EAST, tfCodigo, 66, SpringLayout.WEST, tfCNPJ);
		sl_pnCadastro.putConstraint(SpringLayout.EAST, tfCNPJ, -277, SpringLayout.EAST, pnCadastro);
		sl_pnCadastro.putConstraint(SpringLayout.WEST, tfCodigo, 0, SpringLayout.WEST, tfCNPJ);
		sl_pnCadastro.putConstraint(SpringLayout.NORTH, tfCNPJ, -3, SpringLayout.NORTH, lbCnpj);
		sl_pnCadastro.putConstraint(SpringLayout.WEST, tfCNPJ, 20, SpringLayout.EAST, lbCnpj);
		pnCadastro.add(tfCNPJ);
		tfCNPJ.setColumns(10);
		
		JLabel lbTelefone = new JLabel("Telefone");
		sl_pnCadastro.putConstraint(SpringLayout.NORTH, lbTelefone, 32, SpringLayout.SOUTH, lbCnpj);
		sl_pnCadastro.putConstraint(SpringLayout.EAST, lbTelefone, 0, SpringLayout.EAST, lbCdigo);
		pnCadastro.add(lbTelefone);
		
		tfTelefone = new JTextField();
		sl_pnCadastro.putConstraint(SpringLayout.WEST, tfTelefone, 0, SpringLayout.WEST, tfCNPJ);
		sl_pnCadastro.putConstraint(SpringLayout.EAST, tfTelefone, -277, SpringLayout.EAST, pnCadastro);
		sl_pnCadastro.putConstraint(SpringLayout.NORTH, tfTelefone, -3, SpringLayout.NORTH, lbTelefone);
		pnCadastro.add(tfTelefone);
		tfTelefone.setColumns(10);
		
		tfNome = new JTextField();
		sl_pnCadastro.putConstraint(SpringLayout.NORTH, tfNome, -3, SpringLayout.NORTH, lbNome);
		sl_pnCadastro.putConstraint(SpringLayout.WEST, tfNome, 0, SpringLayout.WEST, tfCNPJ);
		sl_pnCadastro.putConstraint(SpringLayout.EAST, tfNome, -111, SpringLayout.EAST, pnCadastro);
		pnCadastro.add(tfNome);
		tfNome.setColumns(10);
		
		JScrollPane pnTabela = new JScrollPane();
		tpFornecedor.addTab("Tabela", null, pnTabela, null);
		
		tabela = new JTable();
		tabela.setModel(Fornecedor.getTableModel());
		pnTabela.setViewportView(tabela);
		
		JPanel pnRodape = new JPanel();
		pnRodape.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnRodape, BorderLayout.SOUTH);
		pnRodape.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		btListar = new JButton("Listar");
		btListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listaFornecedores();
			}
		});
		pnRodape.add(btListar);
		
		JButton btLimpar = new JButton("Limpar");
		btLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpaTela();
			}
		});
		pnRodape.add(btLimpar);
		
		JButton btGravar = new JButton("Gravar");
		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gravaFornecedor();
			}
		});
		pnRodape.add(btGravar);
	}
	
	private void listaFornecedores() {
		Fornecedor.getLista().forEach(e -> System.out.println(e));
	}
	
	private void limpaTela() {
		tfCodigo.setText("");
		tfNome.setText("");
		tfCNPJ.setText("");
		tfTelefone.setText("");
		tfCodigo.requestFocus();
	}
	
	private void gravaFornecedor() {
		int codigo = Integer.parseInt(tfCodigo.getText());
		String nome = tfNome.getText();
		String cnpj = tfCNPJ.getText();
		String telefone = tfTelefone.getText();
		new Fornecedor(codigo,nome,cnpj,telefone);
		limpaTela();
	}
	
}