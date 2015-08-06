/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.access.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author joao.oliveira
 */
public class DAOSegmento {
           
       public List getSegmentos() throws SQLException, ClassNotFoundException {

        DAOUtil Util = new DAOUtil();
        Conexao Conexao = new Conexao();
        ResultSet var = Conexao.stm.executeQuery("SELECT SEGMENTO AS SEGMENTO FROM SEGMENTOS");
        List producao = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return producao;

    }
    
}
