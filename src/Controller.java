import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;




/**
 * The Controller class represents the controller in the MVC pattern.
 * It acts as an intermediary between the view and the model, handling user input from the view and
 * updating the model accordingly. It also handles retrieving data from the model and passing it
 * to the view for display.
*/
public class Controller {
    private MyDAO dao;
    private View view;


    /**
     * Constructs a new Controller object and initializes its DAO and View objects.
     * Passes an instance of itself as a parameter to view
     * 
     * @throws SQLException if an SQL exception occurs while initializing the DAO object
    */
    public Controller() throws SQLException {
        dao = new MyDAO();
        view = new View(this);
    }

    /**
     * Returns a list of distinct geo values from the database.
     * 
     * @return a list of distinct geo values from the database
     * @throws SQLException if an SQL exception occurs while retrieving the data from the database
    */
    public List<String> getDistinctGeoValues() throws SQLException {
        return dao.getDistinctGeoValues();
    }


    /**
     *Returns a list of distinct ref date values from the database.
     *
     *@return a list of distinct ref date values from the database
     *@throws SQLException if an SQL exception occurs while retrieving the data from the database
    */
    public List<String> getDistinctRefDateValues() throws SQLException {
        return dao.getDistinctRefDateValues();
    }


    /**
     * Returns a list of RowData objects containing data for the specified geographical 
     * location and the time granularity.
     * 
     * @param geo the geographical location for which to retrieve data
     * @param monthly a boolean indicating whether to retrieve monthly or yearly data
     * @return a list of RowData objects containing data for the specified geographical location 
     * and time granularity
     * @throws SQLException if an SQL exception occurs while retrieving the data from the database
    */
    public List<RowData> getDataByGeo(String geo, boolean monthly) throws SQLException {
        return dao.getDataByGeo(geo, monthly);
    }


    /**
     * Returns a list of RowData objects containing custom data for the specified geographical location, 
     * start date, end date,and time granularity.
     * 
     * @param geo the geographical location for which to retrieve data
     * @param start the start date for which to retrieve data
     * @param end the end date for which to retrieve data
     * @param monthly a boolean indicating whether to retrieve monthly or yearly data
     * @return a list of RowData objects containing custom data for the specified geographical location, 
     * start date, end date and time granularity
     * @throws SQLException if an SQL exception occurs while retrieving the data from the database
    */
    public List<RowData> getCustomDataByGeo(String geo, String start, String end, boolean monthly) throws SQLException {
        return dao.getCustomDataByGeo(geo, start, end, monthly);
    }



    /**
     * Displays the specified list of RowData objects in the view.
     * 
     * @param data the list of RowData objects to display in the view
    */
    public void displayTable(List<RowData> data) {
        view.displayTable(data);
    }

    public static void main(String[] args) throws SQLException {
        Controller controller = new Controller();
        JFrame frame = new JFrame("My App");
        frame.add(controller.view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}