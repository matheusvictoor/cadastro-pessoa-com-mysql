package teste;

import java.sql.Connection;
import java.sql.SQLException;

import util.ConnectionFactory;

public class ConnectionFactoryTest {
    
    public static void main(String[] args) {
        
        try {
            Connection con = ConnectionFactory.getConnection();
            System.out.println("Conexão estabelecida com sucesso!");
            con.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Erro na conexão: driver não encontrado!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Erro na conexão: não foi possível conectar com o banco de dados!");
            e.printStackTrace();
        }      
    }   
}
