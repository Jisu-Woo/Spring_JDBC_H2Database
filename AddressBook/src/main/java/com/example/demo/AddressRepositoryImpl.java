package com.example.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AddressRepositoryImpl implements IAddressRepository {
	
	@Autowired
	private JdbcOperations jdbc; // also JdbcTemplate 
	
	// SQL statements for the database operations
	private static final String SQL_INSERT = "insert into address(firstname, lastname, address, email) values(?, ?, ?, ?)";
	
	//private static final String SQL_UPDATE = "update customer set firstname=?, lastname=?, address=?, email=? where id=?";
	
	private static final String SQL_DELETE_ONE = "delete from address where email=?"; //specific id 
	
	private static final String SQL_DELETE_ALL = "delete from address"; //specific id 

	
	private static final String SQL_FIND_ONE = "select * from address where email=?";
	
	private static final String SQL_FIND_ALL = "select * from address order by lastname";
	
	//? mark can't be together with order by...
	private static final String SQL_FIND_LASTNAME = "select * from address where lastname=?";

	
	
	@Override
	public Address add(Address address) {
		// will hold the generated "id" of the inserted customer.
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		
		int rows = jdbc.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
				
				PreparedStatement ps = con.prepareStatement(SQL_INSERT, new String[] {"id"});
				ps.setString(1, address.getFirstName());
				ps.setString(2, address.getLastName());
				ps.setString(3, address.getAddress());
				ps.setString(4, address.getEmail());
				
				
				return ps;
			}
		}, keyHolder);
		
		if(rows == 1) {// success
			System.out.println("Inserting address succeeded.");
			address.setId(keyHolder.getKey().longValue());
			return address;

		} else { // insert failed
			System.out.println("Inserting address failed.");
			return null;
		}

	}

	/*@Override
	public int update(Address address) { //search
		
		return jdbc.update(SQL_UPDATE, address.getFirstName(), address.getLastName(), address.getAddress(), address.getEmail(), address.getId());
		
	}*/

	@Override
	public int delete(String email) {
		
		return jdbc.update(SQL_DELETE_ONE, email);
	}

	@Override
	public int deleteAll() {
		return jdbc.update(SQL_DELETE_ALL);

	}
	@Override
	public Address findOne(String email) {
		return jdbc.queryForObject(SQL_FIND_ONE, new AddressRowMapper(), email);
		
	}

	@Override
	public List<Address> findAll() {

		return jdbc.query(SQL_FIND_ALL, new AddressRowMapper());  //here's no question mark

	}
	
	
	// work done for each iteration to handle the query results
	private static class AddressRowMapper implements RowMapper<Address>{

		@Override
		public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
			System.out.println("Mapping row: "+rowNum);
			
			//map a row of the results to a Customer object
			
			return new Address(
					rs.getLong("id"),
					rs.getString("firstname"),
					rs.getString("lastname"),
					rs.getString(4),  //it can use index
					rs.getString("email"));
			
		}
		
	}


	@Override
	public List<Address> search(String lastname) {
		return jdbc.query(SQL_FIND_LASTNAME, new AddressRowMapper(), lastname);
	}
}