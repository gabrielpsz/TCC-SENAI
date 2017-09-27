package controle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import dao.DAOFactory;
import dao.UsuarioDAO;
import modelo.Modalidade;
import modelo.Usuario;

public class UsuarioControle {
	
	UsuarioDAO usuarioDao = DAOFactory.getUsuarioDao();
	
	public void salvar(Usuario usuario) throws Exception {
		if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
			throw new Exception("O nome é obrigatório!");
		}
		if (usuario.getCpf() == null || usuario.getCpf().trim().isEmpty()) {
			throw new Exception("O CPF é obrigatório!");
		}
		if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
			throw new Exception("O E-Mail é obrigatório!");
		}
		if (usuario.getDataNascimento() == null) {
			throw new Exception("A data de nascimento é obrigatória!");
		}
		if (usuario.getCelular() == null || usuario.getCelular().trim().isEmpty()) {
			throw new Exception("Você deve adicionar um número de celular");
		}
		if (usuario.getSexo() == null || usuario.getSexo().trim().isEmpty()) {
			throw new Exception("Você deve selecionar um sexo!");			
		}
		if (usuario.getSenha() == null || usuario.getSenha().trim().isEmpty()) {
			throw new Exception("A senha é obrigatória!");
		}
		if (usuario.getEndereco().getBairro() == null || usuario.getEndereco().getBairro().trim().isEmpty()) {
			throw new Exception("O bairro é obrigatório");
		}
		if (usuario.getEndereco().getCep() == null || usuario.getEndereco().getCep().trim().isEmpty()) {
			throw new Exception("O CEP é obrigatório");
		}
		if (usuario.getEndereco().getCidade() == null || usuario.getEndereco().getCidade().trim().isEmpty()) {
			throw new Exception("A cidade é obrigatória");
		}
		if (usuario.getEndereco().getComplemento() == null || usuario.getEndereco().getComplemento().trim().isEmpty()) {
			throw new Exception("O complemento é obrigatório");
		}
		if (usuario.getEndereco().getNumero() == null || usuario.getEndereco().getNumero().trim().isEmpty()) {
			throw new Exception("O número é obrigatório");
		}
		if (usuario.getEndereco().getRua() == null || usuario.getEndereco().getRua().trim().isEmpty()) {
			throw new Exception("A rua é obrigatória");
		}
		if (usuario.getIdUsuario() == 0) {
			usuarioDao.salvar(usuario);
		}else {
			usuarioDao.update();
		}
	}
	
	public Usuario buscarPorId(long idUsuario) throws Exception{
		if (idUsuario <= 0) {
			throw new Exception("O id deve ser maior que 0");
		}
		return usuarioDao.buscarPorId(idUsuario);
	}
	
	public List<Usuario> buscarTodos(){
		return usuarioDao.buscarTodos();
	}
	
	public List<Usuario> buscarPorCPF(String cpf) throws Exception {
		if (cpf == null || cpf.trim().isEmpty()) {
			throw new Exception("O cpf é obrigatório para a consulta!");
		}
		return usuarioDao.buscarPorCPF(cpf);
	}

	public void excluir(Usuario usuario) throws Exception {
		if (usuario == null) {
			throw new Exception("É obrigatório selecionar um usuario para excluir");
		}
		usuarioDao.excluir(usuario);
	}
	
	public Usuario autenticarUsuario(String login, String senha) {
		UsuarioDAO usuarioDao = DAOFactory.getUsuarioDao();
		return usuarioDao.autenticarUsuario(login, senha);
	}
	
	public String gerarRelatorio() throws DocumentException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Usuario> listUsuario = usuarioDao.buscarTodos();

		Document document = new Document();
		File file = new File("C:\\Users\\Gabriel\\Documents\\relatorioUsuario.pdf");
		FileOutputStream fos = new FileOutputStream(file);
		PdfWriter.getInstance(document, fos);
		document.open();
		
		Paragraph pTitulo = new Paragraph("Relatório de Usuarios", new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
		pTitulo.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(pTitulo, 1);
		document.add(pTitulo);
		
		PdfPTable table = new PdfPTable(8);
		table.setWidthPercentage(100);
		table.addCell("ID");
		table.addCell("Nome");
		table.addCell("Sexo");
		table.addCell("CPF");
		table.addCell("Celular");
		table.addCell("Data de Nascimento");
		table.addCell("Email");
		table.addCell("Senha");
		for (Usuario u : listUsuario) {
			table.addCell(u.getIdUsuarioPdf());
			table.addCell(u.getNome());
			table.addCell(u.getSexo());
			table.addCell(u.getCpf());
			table.addCell(u.getCelular());
			table.addCell(sdf.format(u.getDataNascimento()));
			table.addCell(u.getEmail());
			table.addCell(u.getSenha());
			
		}
		document.add(table);
		
		document.close();
		
		return "C:\\Users\\Gabriel\\Documents\\relatorioUsuario.pdf";
	}
	
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
