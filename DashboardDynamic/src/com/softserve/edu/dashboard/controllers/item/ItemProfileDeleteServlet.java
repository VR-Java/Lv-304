package com.softserve.edu.dashboard.controllers.item;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softserve.edu.dashboard.constants.Attributes;
import com.softserve.edu.dashboard.constants.WebPaths;
import com.softserve.edu.dashboard.dto.UserDTO;
import com.softserve.edu.dashboard.tools.Context;

@WebServlet(WebPaths.ITEM_DELETE_SERVLET)
public class ItemProfileDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ItemProfileDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet from ItemProfileDeleteServlet");
		UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Attributes.USER_DTO);
		if (userDTO != null) {
			Long idItem = Long.parseLong(request.getParameter(Attributes.ID_ITEM));
			Context.getInstance().getItemServise().deleteItemDTOById(idItem);
			request.getRequestDispatcher(WebPaths.USER_ITEMS_SERVLET).forward(request, response);
		} else {
			request.getRequestDispatcher(WebPaths.LOGIN_SERVLET).forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost from ItemProfileDeleteServlet");
		doGet(request, response);
	}

}
