package ie.kevinmay.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import ie.kevinmay.model.Ticket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * An implementation of the ContactDAO interface.
 */
public class TicketDAOImpl implements TicketDAO {

	private JdbcTemplate jdbcTemplate;// create jdbcTemplate bean

	public TicketDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	// Return a list of all tickets
	public List<Ticket> list() {
		String sql = "SELECT * FROM ticket"; // SQL for selecting all tickets

		// assign list to query results
		List<Ticket> listTickets = jdbcTemplate.query(sql, new RowMapper<Ticket>() {

			@Override // implementing RowMapper which maps rows to List object
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket ticket = new Ticket();

				ticket.setId(rs.getInt("ticket_id"));
				ticket.setTitle(rs.getString("title"));
				ticket.setCustomer(rs.getString("customer"));

				return ticket;
			}// end of map row

		});// end of assign list to query results

		return listTickets;
	}// end of list()

	@Override
	public List<Ticket> list(String username) {
		String sql = "SELECT * FROM ticket WHERE customer='" + username + "'"; 																	
																		
		// assign list to query results
		List<Ticket> listTickets = jdbcTemplate.query(sql, new RowMapper<Ticket>() {

			@Override // implementing RowMapper which maps rows to List object
			public Ticket mapRow(ResultSet rs, int rowNum) throws SQLException {
				Ticket ticket = new Ticket();

				ticket.setId(rs.getInt("ticket_id"));
				ticket.setTitle(rs.getString("title"));
				ticket.setCustomer(rs.getString("customer"));

				return ticket;
			}// end of map row

		});// end of assign list to query results

		return listTickets;
	}// end of list()

	public void saveTicket(Ticket ticket) {
		String sql = "INSERT INTO ticket (title, customer)" + " VALUES (?, ?)";
		jdbcTemplate.update(sql, ticket.getTitle(), ticket.getCustomer());
	}

}
