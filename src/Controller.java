import java.sql.SQLException;
import java.util.List;
import javax.swing.JFrame;

public class Controller {
    private MyDAO dao;
    private View view;

    public Controller() throws SQLException {
        dao = new MyDAO();
        view = new View(this);
    }

    public List<String> getDistinctGeoValues() throws SQLException {
        return dao.getDistinctGeoValues();
    }

    public List<String> getDistinctRefDateValues() throws SQLException {
        return dao.getDistinctRefDateValues();
    }

    public List<RowData> getDataByGeo(String geo, boolean monthly) throws SQLException {
        return dao.getDataByGeo(geo, monthly);
    }

    public List<RowData> getCustomDataByGeo(String geo, String start, String end, boolean monthly) throws SQLException {
        return dao.getCustomDataByGeo(geo, start, end, monthly);
    }

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