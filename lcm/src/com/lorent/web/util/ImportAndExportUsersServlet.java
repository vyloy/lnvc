package com.lorent.web.util;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lorent.exception.CustomSqlException;
import com.lorent.service.UserService;
import com.lorent.service.UserService.ImportResult;

/**
 * Servlet implementation class ImportAndExportUsersServlet
 */
public class ImportAndExportUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(ImportAndExportUsersServlet.class);
	private static final int FILE_SIZE=2*1024*1024;
	private static final int MAX_FILE_SIZE=2*FILE_SIZE;
	private static final ConcurrentHashMap<Integer,Workbook> results=new ConcurrentHashMap<Integer,Workbook>();
	private static final AtomicInteger seed=new AtomicInteger();
	private static final ScheduledExecutorService cleanWorker=Executors.newScheduledThreadPool(1, new ThreadFactory() {
		
		@Override
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			t.setDaemon(true);
			t.setName("ImportAndExportUsersServlet.cleanWorker");
			return t;
		}
	});
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ServletOutputStream out = response.getOutputStream();
		Workbook wb=null;
		if(id!=null){
			wb = results.get(Integer.parseInt(id));
		}else{
			UserService service = WebApplicationContextUtils.getWebApplicationContext(this
					.getServletContext()).getBean("userService", UserService.class);
			try {
				wb = service.exportToXLS();
			} catch (CustomSqlException e) {
				log.error(e.getMessage(),e);
				e.printStackTrace();
			}
		}
		if(wb==null)
			return;
		try {
			response.addHeader("Content-Disposition", "attachment; filename=\"users.xls\"");
			response.setContentType("application/vnd.ms-excel");
			wb.write(out);
		}finally{
			out.flush();
			out.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int contentLength = request.getContentLength();
		if(contentLength>FILE_SIZE){
			showMessage("导入文件太大了", request, response);
			return;
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		DiskFileItemFactory factory = new DiskFileItemFactory(MAX_FILE_SIZE, new File(System.getProperty("java.io.tmpdir")));
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> result = upload.parseRequest(request);
			FileItem item = result.get(0);
			String name = item.getName();
			if(name==null||name.isEmpty()){
				showMessage("文件名为空",request,response);
				return;
			}
			if(!name.endsWith(".xls")){
				showMessage(name+"不是Excel文件。目前只能导入Excel文件",request,response);
				return;
			}
			ImportResult importResult = process(item);
			final int id = seed.getAndIncrement();
			results.put(id, importResult.book);
			request.setAttribute("id", id);
			request.setAttribute("count", importResult.count);
			request.setAttribute("success", importResult.success);
			cleanWorker.schedule(new Runnable() {
				
				@Override
				public void run() {
					results.remove(id);
				}
			}, 10, TimeUnit.MINUTES);
		} catch (Exception e) {
			log.error(e.getMessage(),e);
			showMessage(e.getMessage(),request,response);
			return;
		}
		request.getRequestDispatcher("app/userAction_importSuccess_import_success.action").forward(request, response);
	}
	
	private ImportResult process(FileItem item) throws Exception {
		UserService service = WebApplicationContextUtils.getWebApplicationContext(this
				.getServletContext()).getBean("userService", UserService.class);
		return service.importFromXLS(item.getInputStream());
	}

	private void showMessage(String message,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.sendRedirect("app/userAction_toImportUser_importuser.action?message="+URLEncoder.encode(message,"utf-8"));
	}

}
