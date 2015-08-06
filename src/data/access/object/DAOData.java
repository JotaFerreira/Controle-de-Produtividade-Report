package data.access.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author joao.oliveira
 */
public class DAOData {

    public List getDias() throws SQLException, ClassNotFoundException {

        DAOUtil Util = new DAOUtil();
        Conexao Conexao = new Conexao();
        ResultSet var = Conexao.stm.executeQuery("SELECT DISTINCT(cast (strftime('%d', DATA_ENTRADA) as integer)) AS DIA FROM DADOSCONTROLE ORDER BY 1 ASC");
        List dias = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return dias;

    }

    public List getDiasPorMesAno(String mes, String ano) throws SQLException, ClassNotFoundException {

        DAOUtil Util = new DAOUtil();
        Conexao Conexao = new Conexao();
        ResultSet var = Conexao.stm.executeQuery("SELECT DISTINCT(cast (strftime('%d', DATA_ENTRADA) as integer)) AS DIA "
                + "FROM DADOSCONTROLE WHERE CAST (strftime('%m', DATA_ENTRADA) as integer)=" + mes
                + " AND CAST (strftime('%Y', DATA_ENTRADA) as integer)= " + ano + " ORDER BY 1 ASC");
        List dias = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return dias;

    }

    public List getMeses() throws SQLException, ClassNotFoundException {

        DAOUtil Util = new DAOUtil();
        Conexao Conexao = new Conexao();
        ResultSet var = Conexao.stm.executeQuery("SELECT DISTINCT(CAST (strftime('%m', DATA_ENTRADA) as integer)) AS MES "
                + "FROM DADOSCONTROLE ORDER BY 1 ASC");
        List meses = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return meses;

    }

    public List getMesesPorAno(String ano) throws SQLException, ClassNotFoundException {

        DAOUtil Util = new DAOUtil();
        Conexao Conexao = new Conexao();
        ResultSet var = Conexao.stm.executeQuery("SELECT DISTINCT(CAST (strftime('%m', DATA_ENTRADA) as integer)) AS MES "
                + "FROM DADOSCONTROLE WHERE CAST (strftime('%Y', DATA_CONTROLE) as integer)= " + ano + " ORDER BY 1 ASC");
        List meses = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return meses;

    }

    public List getAnos() throws SQLException, ClassNotFoundException {

        DAOUtil Util = new DAOUtil();
        Conexao Conexao = new Conexao();
        ResultSet var = Conexao.stm.executeQuery("SELECT DISTINCT(CAST (strftime('%Y', DATA_ENTRADA) as integer)) AS ANO"
                + " FROM DADOSCONTROLE ORDER BY 1 ASC");
        List meses = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return meses;

    }

}
