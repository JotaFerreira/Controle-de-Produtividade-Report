package data.access.object;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author joao.oliveira
 */
public class DAOUtil {

    public List resultSetToArrayList(ResultSet rs) throws SQLException { // converte um resultset em list para ser manipulado em todo o sistema
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();

        ArrayList list = new ArrayList();
        while (rs.next()) {
            HashMap row = new HashMap(columns);
            for (int i = 1; i <= columns; ++i) {
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            list.add(row);
        }

        return list;
    }

}
