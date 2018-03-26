

import java.util.List;

import com.softserve.edu.dashboard.dao.ItemDAO;
import com.softserve.edu.dashboard.dao.UserDAO;
import com.softserve.edu.dashboard.dto.ItemDTO;
import com.softserve.edu.dashboard.dto.LoginDTO;
import com.softserve.edu.dashboard.dto.UserDTO;
import com.softserve.edu.dashboard.dto.UserItemsDTO;
import com.softserve.edu.dashboard.entity.UserEntity;
import com.softserve.edu.dashboard.services.ItemServise;
import com.softserve.edu.dashboard.services.LoginService;
import com.softserve.edu.dashboard.services.SQLProperty;
import com.softserve.edu.dashboard.services.UserItemsServise;
import com.softserve.edu.dashboard.services.UserProfileService;

public class ApplServices {

	public static void main(String[] args) {
	
		
		
		UserDAO userDAO = new UserDAO();
		ItemDAO itemDAO = new ItemDAO();
		//
		LoginService loginService = new LoginService(userDAO);
		UserProfileService userProfileService = new UserProfileService(userDAO);
		ItemServise itemServise = new  ItemServise(itemDAO);
		UserItemsServise userItemsServise = new UserItemsServise(userDAO, itemDAO);

		
		// *** loginService
		
		// login invalid user - OK
//		LoginDTO loginDTO = new LoginDTO("Ivan", "qwertyqwerty"); // - FALSE, wrong password
//		boolean result = loginService.isLogged(loginDTO);
//		System.out.println("result = " + result);
		
		// login valid user - OK
//		LoginDTO loginDTO = new LoginDTO("Ivan", "qwerty"); // - TRUE
//		boolean result = loginService.isLogged(loginDTO);
//		System.out.println("result = " + result);
		
//		LoginDTO loginDTO = new LoginDTO("igor", "igor");
//		loginService.isLogged(loginDTO);
		
		
		
		// *** userProfileService
		
		// Create new user - OK
//		UserDTO userDTO = new UserDTO(-1, "user1", "1", "1", "user1@gmail.com");
//		userProfileService.setUserDTO(userDTO);
		
		
//		// Delete user - OK
//		UserDTO userDTO = new UserDTO(2, "IvanName", "Ivan0", "qwerty", "a@gmail.com");
//		userProfileService.deleteUserDTO(userDTO);
		
//		userProfileService.deleteUserDTOById(9L); // OK

		
		// Update existing user - OK
//		UserDTO userDTO = new UserDTO(1, "Ivan&Petro", "Ivan2", "qwerty", "a@gmail.com");
//		userProfileService.setUserDTO(userDTO);


		// Check existing login - OK
//		userProfileService.isExistLogin("Ivan2");
		

		// Get existing user - OK
//		UserDTO userDTO = userProfileService.getUserDTO("Ivan3");
//		System.out.println(userDTO.getId());
		
		// Get non existing user // NullPointerException expected - OK
//		UserDTO userDTO = userProfileService.getUserDTO("Ivan");
//		System.out.println(userDTO.getId());
		

		// *** itemServise
		// create item
		// Correct data - OK
//		ItemDTO itemDTO = new ItemDTO(-1, "Title1", "Description1", 1, "OK");
//		itemServise.setItemDTO(itemDTO);
		
		// Invalid userId // SQLException found - OK
//		ItemDTO itemDTO = new ItemDTO(-1, "Title2", "Description2", 2, "OK");
//		itemServise.setItemDTO(itemDTO);
		
		// update item - OK
		// TODO check
//		ItemDTO itemDTO = new ItemDTO(3, "Title2", "Description2", 3, "OK");
//		itemServise.setItemDTO(itemDTO);
		
		// is exists item
		//boolean result = itemServise.isExistItem(2);
		//System.out.println("isExistItem, result = " + result);
		//
		
		
		// *** userItemsServise - OK
//		LoginDTO loginDTO = new LoginDTO("1", "1");
////		LoginDTO loginDTO = new LoginDTO("Stepan", "qwerty");
//		UserItemsDTO userItemsDTO = userItemsServise.getUserItems(loginDTO);
//		System.out.println("userLogin= " + userItemsDTO.getUserLogin());
//		
//		for (ItemDTO itemDTO : userItemsDTO.getItems()) {
//			System.out.println("idItem= " + itemDTO.getIdItem() 
//					+ " title= " + itemDTO.getTitle() 
//					+ " descriptions= " + itemDTO.getDescription()
//					+ " idUser= " + itemDTO.getIdUser());
//		} 
	}
	
}
