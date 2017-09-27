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
import modelo.Local;
import modelo.Modalidade;
import dao.DAOFactory;
import dao.EventoDAO;
import dao.LocalDAO;
import dao.ModalidadeDAO;

public class EventoControle {

	EventoDAO eventoDao = DAOFactory.getEventoDao();
	
	public void salvar(Evento evento) throws Exception {
		if (evento.getNome() == null || evento.getNome().trim().isEmpty()) {
			throw new Exception("O nome é obrigatório");
		}
		if (evento.getLocal().equals(null)) {
			throw new Exception ("Você deve adicionar um local primeiro");
		}
		if (evento.getModalidade().equals(null)) {
			throw new Exception("Você deve adicionar uma modalidade primeiro");
		}
		if (evento.getDataInicio() == null){
			throw new Exception("A data de inicio é obrigatória");
		}
		if (evento.getDataTermino() == null) {
			throw new Exception("A data de termino é obrigatória");
		}
		if (evento.getEmpresaResponsavel() == null || evento.getEmpresaResponsavel().trim().isEmpty()) {
			throw new Exception("A empresa responsável é obrigatória");
		}
		if (evento.getValorIngressoEvento() < 0) {
			throw new Exception("O valor do ingresso deve ser 0 ou maior que 0");
		}
	
		if (evento.getIdEvento() == 0) {
			eventoDao.salvar(evento);
		}else{
			eventoDao.update();
		}
	}
	
	public List<Evento> buscarTodos() {
		return eventoDao.buscarTodos();
	}
	
	public void excluir(Evento evento) throws Exception {
		if (evento == null) {
			throw new Exception("Deve-se selecionar um evento para excluir");
		}
		eventoDao.excluir(evento);
	}
	
	public List<Evento> buscarPorNome(String nome) throws Exception {
		if (nome == null || nome.trim().isEmpty()) {
			throw new Exception("O nome do evento é necessário para a consulta");
		}
		return eventoDao.buscarPorNome(nome);
	}
	
	public Local buscarLocalPorId(long id) {
		LocalDAO localDao = DAOFactory.getLocalDao();
		return localDao.buscarPorId(id);
	}
	
	public List<Local> buscarTodosLocais() {
		LocalDAO localDao = DAOFactory.getLocalDao();
		return localDao.buscarTodos();
	}
	
	public List<Modalidade> buscarTodasModalidades() {
		ModalidadeDAO modalidadeDao = DAOFactory.getModalidadeDao();
		return modalidadeDao.buscarTodos();
	}
	
	public Modalidade buscarModalidadePorId(long id) {
		ModalidadeDAO modalidadeDao = DAOFactory.getModalidadeDao();
		return modalidadeDao.buscarPorId(id);
	}
	
	public Evento buscarPorId(long idEvento) throws Exception{
		if (idEvento <= 0) {
			throw new Exception("O id deve ser maior que 0");
		}
		return eventoDao.buscarPorId(idEvento);
	}
	
	public String gerarRelatorio() throws DocumentException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Evento> listEvento= eventoDao.buscarTodos();

		Document document = new Document();
		File file = new File("H:\\relatorioEvento.pdf");
		FileOutputStream fos = new FileOutputStream(file);
		PdfWriter.getInstance(document, fos);
		document.open();
		
		Paragraph pTitulo = new Paragraph("Relatório de Eventos", new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
		pTitulo.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(pTitulo, 1);
		document.add(pTitulo);
		
		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100);
		table.addCell("ID");
		table.addCell("Nome");
		table.addCell("Local");
		table.addCell("Modalidade");
		table.addCell("Empresa responsavel");
		table.addCell("Data inicio");
		table.addCell("Data termino");
		for (Evento e : listEvento) {
			table.addCell(e.getIdEventoPdf());
			table.addCell(e.getNome());
			table.addCell(e.getLocal().getNome());
			table.addCell(e.getModalidade().getTipo());
			table.addCell(e.getEmpresaResponsavel());
			table.addCell(sdf.format(e.getDataInicio()));
			table.addCell(sdf.format(e.getDataTermino()));
		}
		document.add(table);
		
		document.close();
		
		return "H:\\relatorioEvento.pdf";
	}
	
	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}
	
}
