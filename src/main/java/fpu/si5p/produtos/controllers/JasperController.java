package fpu.si5p.produtos.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

@Controller
public class JasperController {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private ApplicationContext appContext;

	@RequestMapping("/relatorio")
	public ModelAndView generatePdfReport() {
		final JasperReportsPdfView view = new JasperReportsPdfView();
		view.setUrl("classpath:report.jrxml");
		view.setJdbcDataSource(dataSource);
		view.setApplicationContext(appContext);
		Map<String, Object> params = new HashMap<>();
		return new ModelAndView(view, params);
	}

}
