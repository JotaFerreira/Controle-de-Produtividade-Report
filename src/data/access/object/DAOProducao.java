package data.access.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author joao.oliveira
 */
public class DAOProducao {
    
       public List getProducaoPorSegmento(String segmento,String dataInicio, String dataFim) throws SQLException, ClassNotFoundException {

        String querySegmento = segmento.equals("") ? "" : " AND A.SEGMENTO ='" + segmento + "'";
        String queryData = " AND DATE(A.DATA_ENTRADA) BETWEEN '" + dataInicio + "' AND '" + dataFim + "'";
        
        data.access.object.DAOUtil Util = new data.access.object.DAOUtil();
        data.access.object.Conexao Conexao = new data.access.object.Conexao();

        ResultSet var = Conexao.stm.executeQuery("SELECT A.CONTROLADOR, A.DATA_ENTRADA, A.UF, A.GRA, A.SEGMENTO, "
                + "A.TRATAMENTO, A.ATIVIDADE, B.NOME AS TECNICO, A.MATERIAL_APLICADO, A.LOC_CIR_TER, A.USERNAME "
                + "FROM DADOSCONTROLE AS A, TECNICOS AS B WHERE A.TECNICO = B.MAT " + querySegmento + queryData 
                + " ORDER BY 2 ASC");
        List ocorrencias = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return ocorrencias;

    }
       
      public List getProducaoControlador(String segmento,String dataInicio, String dataFim) throws SQLException, ClassNotFoundException {

        String queryData = " AND DATE(DATA_ENTRADA) BETWEEN '" + dataInicio + "' AND '" + dataFim + "'";
        
        data.access.object.DAOUtil Util = new data.access.object.DAOUtil();
        data.access.object.Conexao Conexao = new data.access.object.Conexao();
                
        ResultSet var = Conexao.stm.executeQuery("SELECT CONTROLADOR AS CONTROLADOR,COUNT(TRATAMENTO) AS QUANTIDADE"
                + " FROM DADOSCONTROLE WHERE SEGMENTO='" + segmento + "'" + queryData 
                + " GROUP BY 1 ORDER BY 2 DESC");
//          System.out.println("SELECT CONTROLADOR AS CONTROLADOR,COUNT(TRATAMENTO) AS QUANTIDADE"
//                + " FROM DADOSCONTROLE WHERE SEGMENTO='" + segmento + "'" + queryData 
//                + " GROUP BY 1 ORDER BY 2 DESC");
        List ocorrencias = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return ocorrencias;

    }
      
      public List getMediaProducaoControlador(String segmento,String mes, String ano) throws SQLException, ClassNotFoundException{
          
        data.access.object.DAOUtil Util = new data.access.object.DAOUtil();
        data.access.object.Conexao Conexao = new data.access.object.Conexao();
          
        ResultSet var = Conexao.stm.executeQuery("SELECT DATE(Data_entrada) AS Dia,COUNT(tratamento) as Qtd_Tratamentos,"
                + "COUNT(DISTINCT controlador) as Qtd_Controladores,CAST(COUNT(tratamento) / COUNT(DISTINCT controlador) "
                + "AS UNSIGNED) as Media FROM dadoscontrole WHERE segmento = '" + segmento + "' AND CAST(strftime('%m', data_entrada) "
                + "AS INTEGER) = " + mes + " AND CAST(strftime('%Y', data_entrada) AS INTEGER) = " + ano + " GROUP BY DIA ORDER BY DIA");
        List ocorrencias = Util.resultSetToArrayList(var);
        Conexao.stm.close();
        Conexao.conn.close();
        return ocorrencias; 
          
      }
    
}
