package contactlist;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class App {

	public static void main(String[] args){
	
	ApplicationContext context = new ClassPathXmlApplicationContext("contactlist/beans/beans.xml");
	
	ContactsDAO contactsDao = (ContactsDAO)context.getBean("contactsDao");
	
	List <Contact> contacts = contactsDao.getContacts();
	
	for(Contact contact:contacts){
		System.out.println(contact);
	}
	
	((ClassPathXmlApplicationContext)context).close();

	}

}
