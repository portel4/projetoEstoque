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
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import model.Produto;
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
	private JTable table;

	/**
	 * Create the frame.
	 */
	public TelaProduto() {
		
		setTitle("Sistema de Controle de Estoque");
		setLocationRelativeTo(null);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 370);
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
		
		JPanel pnRodape = new JPanel();
		pnRodape.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		contentPane.add(pnRodape, BorderLayout.SOUTH);
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
		
		pnRodape.add(btGravar);
		
		JTabbedPane tpProduto = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tpProduto, BorderLayout.CENTER);
		
		JPanel pnCentral = new JPanel();
		pnCentral.setLayout(null);
		pnCentral.setBackground(SystemColor.menu);
		tpProduto.addTab("Cadastro", null, pnCentral, null);
		
		JLabel lbCodigo = new JLabel("C\u00F3digo:");
		lbCodigo.setBounds(70, 17, 46, 14);
		pnCentral.add(lbCodigo);
		
		tfCodigo = new JTextField();
		tfCodigo.setColumns(10);
		tfCodigo.setBounds(126, 11, 57, 20);
		pnCentral.add(tfCodigo);
		
		JLabel lbNome = new JLabel("Nome:");
		lbNome.setBounds(70, 47, 38, 14);
		pnCentral.add(lbNome);
		
		tfNome = new JTextField();
		tfNome.setColumns(10);
		tfNome.setBounds(126, 44, 392, 20);
		pnCentral.add(tfNome);
		
		JLabel lbQtde = new JLabel("Quantidade:");
		lbQtde.setBounds(46, 78, 70, 14);
		pnCentral.add(lbQtde);
		
		tfQtde = new JTextField();
		tfQtde.setColumns(10);
		tfQtde.setBounds(125, 72, 86, 20);
		pnCentral.add(tfQtde);
		
		JLabel lblNewLabel = new JLabel("Valor:");
		lblNewLabel.setBounds(70, 109, 38, 14);
		pnCentral.add(lblNewLabel);
		
		tfValor = new JTextField();
		tfValor.setColumns(10);
		tfValor.setBounds(126, 103, 124, 20);
		pnCentral.add(tfValor);
		
		JPanel pnTabela = new JPanel();
		tpProduto.addTab("Tebela", null, pnTabela, null);
		pnTabela.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pnTabela.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"C\u00F3digo", "Nome", "Quantidade", "Valor"
			}
		));
		scrollPane.setViewportView(table);
	}
	
	private void gravaProduto() {
		int codigo = Integer.parseInt(tfCodigo.getText());
		String nome = tfNome.getText();
		int qtde = Integer.parseInt(tfQtde.getText());
		double valor = Double.parseDouble(tfValor.getText());
		new Produto(codigo,nome,qtde,valor);
		Produto.gravar();
		limpaTela();
	}
	
	private void limpaTela() {
		tfCodigo.setText("");
		tfNome.setText("");
		tfQtde.setText("");
		tfValor.setText("");
		tfCodigo.requestFocus();
	}
	
	private void listaProdutos() {
		Produto.getLista().forEach(e -> System.out.println(e));		
	}
}
