package contactlist;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import javax.swing.tree.TreePath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;


@Component("contactsDao")
public class ContactsDAO {

	private JdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc = new JdbcTemplate(jdbc);
	}
	
	public List<Contact> getContacts(){
		
		return jdbc.query("select * from contacttable",new RowMapper<Contact>(){

			public Contact mapRow(ResultSet rs , int rowNum) throws SQLException {
				
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setPhone(rs.getString("phone"));
				return contact;
				
			}
			
			
			
			
			
		});
		
	}
	
	
}
