import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) class for fetching data from a MySQL database.
 */
public class MyDAO {

	public static void main(String[] args) {
		MyDAO daoy1 = new MyDAO();
		try {
//			System.out.println(daoy1.getDataByGeo("", false).size());
			System.out.println(daoy1.getCustomDataByGeo("New Brunswick", "", "", false).size());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// JDBC connection parameters
	private final String url = "jdbc:mysql://localhost:8080/proj3311";
	private final String username = "root";
	private final String password = "my-secret-pw";

	// SQL statements
	private static final String GET_DISTINCT_GEO = "SELECT DISTINCT GEO FROM range18100205";
	private static final String GET_DISTINCT_REF_DATE = "SELECT DISTINCT REF_DATE FROM range18100205";
	private static final String GET_DATA_BY_PARAMETERS_MO = "SELECT REF_DATE, AVG(VALUE)\r\n" + "FROM range18100205\r\n"
			+ "WHERE GEO ? AND VALUE <> '' ?\r\n" + "GROUP BY REF_DATE;";

	private static final String GET_DATA_BY_PARAMETERS_YR = "SELECT LEFT(REF_DATE, 4), AVG(VALUE)\r\n"
			+ "FROM range18100205\r\n" + "WHERE GEO ? AND VALUE <> '' ?\r\n" + "GROUP BY LEFT(REF_DATE, 4);";

	/**
	 * Returns a list of distinct GEO values from the database.
	 */
	public List<String> getDistinctGeoValues() throws SQLException {
		List<String> geoValues = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(GET_DISTINCT_GEO);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				geoValues.add(rs.getString("GEO"));
			}
		}
		return geoValues;
	}

	/**
	 * Returns a list of distinct REF_DATE values from the database.
	 */
	public List<String> getDistinctRefDateValues() throws SQLException {
		List<String> refDateValues = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(GET_DISTINCT_REF_DATE);
				ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				refDateValues.add(rs.getString("REF_DATE"));
			}
		}
		return refDateValues;
	}

	/**
	 * Returns data from the database for the given GEO and REF_DATE parameters.
	 */
	public List<RowData> getDataByGeo(String geo, boolean monthly) throws SQLException {
		List<RowData> data = new ArrayList<>();
		String[] splitStmt = monthly ? GET_DATA_BY_PARAMETERS_MO.split("\\r\\n")
				: GET_DATA_BY_PARAMETERS_YR.split("\\r\\n");

		if (geo.isBlank()) {
			splitStmt[2] = "WHERE GEO <> '' AND VALUE <> ''";
		} else {
			splitStmt[2] = "WHERE GEO = ? AND VALUE <> ''";
		}
		System.out.println(String.join(" ", splitStmt));
		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(String.join(" ", splitStmt))) {

			if (!geo.isBlank()) {
				stmt.setString(1, geo);
			}

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					RowData d = new RowData();
					d.setRefDate(rs.getString(monthly ? "REF_DATE" : "LEFT(REF_DATE, 4)"));
					d.setValue(rs.getDouble("AVG(VALUE)"));
					data.add(d);
				}
			}
		}
		return data;
	}

	public List<RowData> getCustomDataByGeo(String geo, String start, String end, boolean monthly) throws SQLException {
		List<RowData> data = new ArrayList<>();

		String dateForm = !monthly ? "LEFT(REF_DATE, 4)" : "REF_DATE";

		String[] splitStmt = monthly ? GET_DATA_BY_PARAMETERS_MO.split("\\r\\n")
				: GET_DATA_BY_PARAMETERS_YR.split("\\r\\n");

		if (geo.isBlank()) {
			splitStmt[2] = "WHERE GEO <> '' AND VALUE <> '' ";
		} else {
			splitStmt[2] = "WHERE GEO = ? AND VALUE <> '' ";
		}

		// Checks which parameters are provided.
		if (!start.isBlank() && !end.isBlank()) {
			splitStmt[2] += String.format("And %s >= ? And %s <= ?", dateForm, dateForm);
		} else if (!start.isBlank()) {
			splitStmt[2] += String.format("And %s >= ?", dateForm);
		} else if (!end.isBlank()) {
			splitStmt[2] += String.format("And %s <= ?", dateForm);
		} else {
			splitStmt[2] += "";
		}

		try (Connection conn = DriverManager.getConnection(url, username, password);
				PreparedStatement stmt = conn.prepareStatement(String.join(" ", splitStmt))) {
			if (!geo.isBlank()) {
				stmt.setString(1, geo);
			}
			if (!start.isBlank() && !end.isBlank()) {
				stmt.setString(2, start);
				stmt.setString(3, end);
			} else if (!start.isBlank()) {
				stmt.setString(2, start);
			} else if (!end.isBlank()) {
				stmt.setString(2, end);
			}
			System.out.println(String.join(" ", splitStmt));
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					RowData d = new RowData();
					d.setRefDate(rs.getString(monthly ? "REF_DATE" : "LEFT(REF_DATE, 4)"));
					d.setValue(rs.getDouble("AVG(VALUE)"));
					data.add(d);
				}
			}
		}
		return data;
	}
}
