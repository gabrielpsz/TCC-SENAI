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

import modelo.Evento;
import modelo.Modalidade;
import dao.DAOFactory;
import dao.ModalidadeDAO;

public class ModalidadeControle {
	
	ModalidadeDAO modalidadeDao = DAOFactory.getModalidadeDao();
	
	public void salvar(Modalidade modalidade) throws Exception {
		if (modalidade.getTipo() == null || modalidade.getTipo().trim().isEmpty()) {
			throw new Exception("O tipo da modalidade é obrigatório");
		}
		if (modalidade.getIdModalidade() == 0) {
			modalidadeDao.salvar(modalidade);
		}else{
			modalidadeDao.update();
		}
	}

	public List<Modalidade> buscarTodos() {
		return modalidadeDao.buscarTodos();
	}
	
	public List<Modalidade> buscarPorNome(String nome) throws Exception {
		if (nome == null || nome.trim().isEmpty()) {
			throw new Exception("O nome da modalidade é necessário para a consulta");
		}
		return modalidadeDao.buscarPorNome(nome);
	}
	
	public List<Modalidade> buscarPorTipo(String tipo) throws Exception {
		if(tipo == null || tipo.trim().isEmpty()) {
			throw new Exception("O tipo modalidade é necessário para a consulta");
		}
		return modalidadeDao.buscarPorTipo(tipo);
	}
	
	public Modalidade buscarPorId(long idModalidade) throws Exception{
		if (idModalidade <= 0) {
			throw new Exception("O id deve ser maior que 0");
		}
		return modalidadeDao.buscarPorId(idModalidade);
	}
	
	public void excluir(Modalidade modalidade) throws Exception {
		if (modalidade == null) {
			throw new Exception("É obrigatório selecionar uma modalidade para excluir");
		}
		modalidadeDao.excluir(modalidade);
	}
	public String gerarRelatorio() throws DocumentException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Modalidade> listModalidade= modalidadeDao.buscarTodos();

		Document document = new Document();
		File file = new File("H:\\relatorioModalidade.pdf");
		FileOutputStream fos = new FileOutputStream(file);
		PdfWriter.getInstance(document, fos);
		document.open();
		
		Paragraph pTitulo = new Paragraph("Relatório de Modalidades", new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
		pTitulo.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(pTitulo, 1);
		document.add(pTitulo);
		
		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.addCell("ID");
		table.addCell("Tipo");
		for (Modalidade m : listModalidade) {
			table.addCell(m.getIdModalidadePdf());
			table.addCell(m.getTipo());
			
		}
		document.add(table);
		
		document.close();
		
		return "H:\\relatorioModalidade.pdf";
	}
	
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
}


