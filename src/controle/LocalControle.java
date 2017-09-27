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
import dao.LocalDAO;
import modelo.Evento;
import modelo.Local;

public class LocalControle {

	LocalDAO localDao = DAOFactory.getLocalDao();

	public void salvar(Local local) throws Exception {
		if (local.getBairro().trim().isEmpty()) {
			throw new Exception("O bairro é obrigatório");
		}
		if (local.getCep().trim().isEmpty()) {
			throw new Exception("O CEP é obrigatório");
		}
		if (local.getCidade().trim().isEmpty()) {
			throw new Exception("A cidade é obrigatória");
		}
		if (local.getNome().trim().isEmpty()) {
			throw new Exception("O nome é obrigatório");
		}
		if (local.getIdLocal() == 0) {
			localDao.salvar(local);
		} else {
			localDao.update();
		}
	}

	public List<Local> buscarTodos() {
		return localDao.buscarTodos();
	}

	public void excluir(Local local) throws Exception {
		if (local == null) {
			throw new Exception(
					"É obrigatório selecionar um local para excluir");
		}
		localDao.excluir(local);
	}

	public List<Local> buscarPorCidade(String cidade) throws Exception {
		if (cidade == null || cidade.trim().isEmpty()) {
			throw new Exception("A cidade é necessário para a consulta");
		}
		return localDao.buscarPorCidade(cidade);
	}

	public List<Local> buscarPorCep(String cep) throws Exception {
		if (cep == null || cep.trim().isEmpty()) {
			throw new Exception("O cep é necessário para a consulta");
		}
		return localDao.buscarPorCep(cep);
	}

	public Local buscarPorId(long idLocal) throws Exception {
		if (idLocal <= 0) {
			throw new Exception("O id deve ser maior que 0");
		}
		return localDao.buscarPorId(idLocal);
	}

	public String gerarRelatorio() throws DocumentException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		List<Local> listLocal = localDao.buscarTodos();

		Document document = new Document();
		File file = new File("H:\\relatorioLocal.pdf");
		FileOutputStream fos = new FileOutputStream(file);
		PdfWriter.getInstance(document, fos);
		document.open();

		Paragraph pTitulo = new Paragraph("Relatório de locais", new Font(
				Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD));
		pTitulo.setAlignment(Element.ALIGN_CENTER);
		addEmptyLine(pTitulo, 1);
		document.add(pTitulo);

		PdfPTable table = new PdfPTable(4);
		table.setWidthPercentage(100);
		table.addCell("ID");
		table.addCell("Nome");
		table.addCell("Cidade");
		table.addCell("Bairro");
		for (Local l : listLocal) {
			table.addCell(l.getIdLocalPdf());
			table.addCell(l.getNome());
			table.addCell(l.getCidade());
			table.addCell(l.getBairro());
		}
		document.add(table);

		document.close();

		return "H:\\relatorioLocal.pdf";
	}

	private void addEmptyLine(Paragraph paragraph, int number) {
		for (int i = 0; i < number; i++) {
			paragraph.add(new Paragraph(" "));
		}
	}

}
