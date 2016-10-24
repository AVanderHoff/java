package petshop;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component("petsDao")
public class PetsDAO {

	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public boolean exists(String animal) {

		String sql = "select count(*) from pets where animal = :animal";
		MapSqlParameterSource params = new MapSqlParameterSource("animal", animal);

		try {
			int rowCount = jdbc.queryForObject(sql, params, Integer.class);

			if (rowCount == 0) {
				return false;
			} else {
				return true;
			}
		} catch (CannotGetJdbcConnectionException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
			return false;
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
			return false;
		}

	}

	public Pet getPet(String animal) {

		MapSqlParameterSource params = new MapSqlParameterSource("animal", animal);
		final Pet pet = new Pet();

		try {
			return jdbc.queryForObject("select * from pets where animal = :animal", params, new RowMapper<Pet>() {

				public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {

					pet.setId(rs.getInt("id"));
					pet.setAnimal(rs.getString("animal"));
					pet.setPrice(rs.getInt("price"));
					pet.setQty(rs.getInt("qty"));

					return pet;
				}

			});
		} catch (CannotGetJdbcConnectionException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
			return pet;
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
			return pet;
		}

	}

	public boolean updateQty(String animal) {

		MapSqlParameterSource params = new MapSqlParameterSource("animal", animal);
		try {
			return jdbc.update("update pets set qty = qty - 1  where animal = :animal and qty > 0", params) == 1;
		} catch (CannotGetJdbcConnectionException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
			return false;
		} catch (DataAccessException ex) {
			System.out.println(ex.getMessage());
			System.out.println(ex.getClass());
			return false;
		}
	}

}
