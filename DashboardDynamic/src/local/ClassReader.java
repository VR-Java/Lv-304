package local;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.softserve.edu.dashboard.dto.ItemDTO;
import com.softserve.edu.dashboard.services.ItemServise;

public class ClassReader {
	

	public static void main(String[] args) {
		List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
		classLoadersList.add(ClasspathHelper.contextClassLoader());
		classLoadersList.add(ClasspathHelper.staticClassLoader());

		Reflections reflections = new Reflections(new ConfigurationBuilder()
		    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
		    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
		    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix("com.softserve.edu.dashboard"))));
		

		 Set<Class<? extends Object>> allClasses = 
		     reflections.getSubTypesOf(Object.class);
		 
		 List<Item> list = new ArrayList<>();
		 
		 for (Class<? extends Object> class1 : allClasses) {
			 
			 String fullname = class1.toString();
			 System.out.println(fullname);
			 String type = fullname.split(" ")[0];
			String title = type + " " + class1.getSimpleName();
			String description = fullname.split(" ")[1];
			String status = "_";
			list.add(new Item(title, description, status));
			
			ItemDTO itemDTO = new ItemDTO(-1, title, description, 36, status);
			
			ItemServise is = new ItemServise();
			is.setItemDTO(itemDTO);
		}
		 
		 list.forEach(System.out::println);
	}
	

}
