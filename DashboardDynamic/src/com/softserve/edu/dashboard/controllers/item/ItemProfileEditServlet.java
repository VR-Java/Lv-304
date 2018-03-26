package com.softserve.edu.dashboard.controllers.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.dashboard.constants.Attributes;
import com.softserve.edu.dashboard.constants.WebPaths;
import com.softserve.edu.dashboard.dto.ItemDTO;
import com.softserve.edu.dashboard.tools.Context;

@WebServlet(WebPaths.ITEM_EDIT_SERVLET)
public class ItemProfileEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ItemProfileEditServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet from ItemProfileEditServlet");
		Long idItem;
		if (request.getParameter(Attributes.ID_ITEM) != null && !request.getParameter(Attributes.ID_ITEM).isEmpty()) {
			idItem = Long.parseLong(request.getParameter(Attributes.ID_ITEM));
			ItemDTO itemDTO = Context.getInstance().getItemServise().getItemDTO(idItem);
			request.setAttribute(Attributes.ITEM_DTO, itemDTO);
			request.getSession().setAttribute(Attributes.ID_ITEM, idItem);
		} else {
			idItem = null;
		}
		request.getRequestDispatcher(WebPaths.ITEM_PROFILE_JSP).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost from ItemProfileEditServlet");
		doGet(request, response);
	}

}
