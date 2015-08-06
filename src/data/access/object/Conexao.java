package data.access.object;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author joao.oliveira
 */
public class Conexao {

    public Connection conn;
    public Statement stm;

    public Conexao() throws SQLException, ClassNotFoundException {

        FileSystemView fs = FileSystemView.getFileSystemView();
        File[] roots = File.listRoots();
        String arquivo = "";
        // String caminho = "c:\\controleprodutividadeLOCAL.db";

        for (File file : roots) {

            Path path = Paths.get(file + "\\CONTROLE LOCAL DIR_CE\\APPS\\App_Data\\Data\\controleprodutividade.db");
            //      Path path = Paths.get(caminho);

            if (Files.exists(path)) {

                arquivo = file + "\\CONTROLE LOCAL DIR_CE\\APPS\\App_Data\\Data\\controleprodutividade.db";
                //   arquivo = caminho;

            }

        }

        // System.out.println(file + "  " + fs.getSystemTypeDescription(file));
        Class.forName("org.sqlite.JDBC");
        this.conn = DriverManager.getConnection("jdbc:sqlite:" + arquivo);
        this.stm = this.conn.createStatement();

    }

    public static void copiarBanco() {

        File[] roots = File.listRoots();
        Path source = null;

        for (File file : roots) {

            Path path = Paths.get(file + "\\CONTROLE LOCAL DIR_CE\\APPS\\App_Data\\Data\\diariodebordo.db");

            if (Files.exists(path)) {

                source = path;

            }

        }

        if (source == null) {

            JOptionPane.showMessageDialog(null, "Erro: Banco de dados não encontrado! Certifique se o computador está com rede e tente novamente.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;

        }

        Path target = Paths.get(System.getProperty("user.dir") + "\\" + "diariodebordo.db");
        try {
            Files.copy(source, target, REPLACE_EXISTING);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

}
