package com.softserve.edu.dashboard.tools;

import javax.servlet.http.HttpServletRequest;

import com.softserve.edu.dashboard.constants.Attributes;
import com.softserve.edu.dashboard.constants.Messages;
import com.softserve.edu.dashboard.dto.ItemDTO;
import com.softserve.edu.dashboard.dto.UserDTO;

public class ItemUtils {
	
	public static ItemDTO initItemDTO(HttpServletRequest request) {
		
		Long userId = ((UserDTO) request.getSession().getAttribute(Attributes.USER_DTO)).getId();
		ItemDTO itemDTO = null;
		Long idItem;	
		if(request.getSession().getAttribute(Attributes.ID_ITEM) != null
				&& !request.getSession().getAttribute(Attributes.ID_ITEM).toString().isEmpty()) {
			idItem = Long.parseLong(request.getSession().getAttribute(Attributes.ID_ITEM).toString());
			itemDTO = Context.getInstance().getItemServise().getItemDTO(idItem);
		} else {
			idItem = -1L;
		}
		
		String title = checkTitle(request, itemDTO);
		String description = checkDescription(request, itemDTO);
		String status = checkStatus(request, itemDTO);
		
		if (title != null && description != null && status != null) {
			return new ItemDTO(idItem, title, description, userId, status);			
		} else {
			request.setAttribute(Attributes.MESSAGE, Messages.NOT_FILLED);
			return null;
		}
	}
	
	private static String checkTitle(HttpServletRequest request, ItemDTO itemDTO) {
		if (request.getParameter(Attributes.TITLE) != null && !request.getParameter(Attributes.TITLE).isEmpty()) {
			return request.getParameter(Attributes.TITLE);
		} else if ((String) request.getAttribute(Attributes.TITLE) != null
				&& !((String) request.getAttribute(Attributes.TITLE)).isEmpty()) {
			return (String) request.getAttribute(Attributes.TITLE);
		} else if (itemDTO != null) {
			return itemDTO.getTitle();
		} else {
			return null;
		}
	}

	private static String checkDescription(HttpServletRequest request, ItemDTO itemDTO) {
		if (request.getParameter(Attributes.DESCRIPTION) != null
				&& !request.getParameter(Attributes.DESCRIPTION).isEmpty()) {
			return request.getParameter(Attributes.DESCRIPTION);
		} else if ((String) request.getAttribute(Attributes.DESCRIPTION) != null
				&& !((String) request.getAttribute(Attributes.DESCRIPTION)).isEmpty()) {
			return (String) request.getAttribute(Attributes.DESCRIPTION);
		} else if (itemDTO != null) {
			return itemDTO.getDescription();
		} else {
			return null;
		}
	}

	private static String checkStatus(HttpServletRequest request, ItemDTO itemDTO) {
		if (request.getParameter(Attributes.STATUS) != null && !request.getParameter(Attributes.STATUS).isEmpty()) {
			return request.getParameter(Attributes.STATUS);
		} else if ((String) request.getAttribute(Attributes.STATUS) != null
				&& !((String) request.getAttribute(Attributes.STATUS)).isEmpty()) {
			return (String) request.getAttribute(Attributes.STATUS);
		} else if (itemDTO != null) {
			return itemDTO.getStatus();
		} else {
			return null;
		}
	}
	
}
