package com.example.jed.s11;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.jed.s05.CoderPlain;

@WebServlet("/s11/coder/get")
public class CoderGet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CoderGet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.trace("enter");

		String param = request.getParameter("id");
		long id = Long.parseLong(param);

//		new CoderDao().read(id).ifPresentOrElse(coder -> {
//			log.debug("Found coder " + id);
//			request.setAttribute("coder", coder);
//		}, () -> log.info(String.format("Coder %d not found", id)));

		CoderDao dao = new CoderDao();
		CoderPlain coder = dao.readRaw(id);
		if (coder == null) {
			log.info(String.format("Coder %d not found", id));
		} else {
			log.debug("Found coder " + id);
			request.setAttribute("coder", coder);
		}

		request.getRequestDispatcher("/coder.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
