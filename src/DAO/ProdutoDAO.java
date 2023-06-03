package DAO;

import DTO.NotaFiscalDTO;
import DTO.ProdutoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoDAO extends NotaFiscalDTO{
    
    Connection conexao;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<ProdutoDTO> lista = new ArrayList<>();

    @Override
    public int getQuant_prod() {
        return super.getQuant_prod(); 
    }
    
        
    public void cadastrarProduto(ProdutoDTO objProdutoDTO) throws SQLException {
        String sql = "insert into produto (modelo, quantidade, valor_unitario) values (?,?,?)";
        
        conexao = new ConexaoBD().conectaBD();
        
        try {
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objProdutoDTO.getNome_produto());
            pstm.setInt(2, objProdutoDTO.getQuantidade_produto());
            pstm.setString(3, objProdutoDTO.getValor_produto());
            
            pstm.execute();
            pstm.close();
            
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO" + erro);
        }
    }
    
   public ArrayList<ProdutoDTO> pesquisarProduto() throws SQLException{
        String sql = "select * from produto";
        
        conexao = new ConexaoBD().conectaBD();
        
        try {
            pstm = conexao.prepareStatement(sql);
            rs = pstm.executeQuery();
            
            while (rs.next()){
                ProdutoDTO objProdutoDTO = new ProdutoDTO();
                objProdutoDTO.setCod_produto(rs.getInt("cod_produto"));
                objProdutoDTO.setNome_produto(rs.getString("modelo"));
                objProdutoDTO.setQuantidade_produto(rs.getInt("quantidade"));
                objProdutoDTO.setValor_produto(rs.getString("valor_unitario"));
                
                lista.add(objProdutoDTO);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "ProdutoDAO Pesquisar: " + erro);
        }
        return lista;    
   }
   
   public void alterarProduto(ProdutoDTO objProdutoDTO) throws SQLException{
       String sql = "update produto set modelo = ?, quantidade = ?, valor_unitario = ? where cod_produto = ?";
       
       conexao = new ConexaoBD().conectaBD();
       
       try {
            pstm = conexao.prepareStatement(sql);
            pstm.setString(1, objProdutoDTO.getNome_produto());
            pstm.setInt(2, objProdutoDTO.getQuantidade_produto());
            pstm.setString(3, objProdutoDTO.getValor_produto());
            pstm.setInt(4, objProdutoDTO.getCod_produto());
            
            pstm.execute();
            pstm.close();
               
       } catch (SQLException erro) {
           JOptionPane.showMessageDialog(null, "ProdutoDAO Alterar: " + erro);
       }
   }
   
    // Aperfeiçoar o código posteriormente
    public void atualizarQuantia(ProdutoDTO objProdutoDTO) throws  SQLException {
        
        conexao = new ConexaoBD().conectaBD();
                
        try {
            String sql = "UPDATE PRODUTO SET QUANTIDADE = quantidade - (?) WHERE COD_PRODUTO = ?";
            pstm = conexao.prepareStatement(sql);
            pstm.setInt(1, getQuant_prod());
            pstm.setInt(2, objProdutoDTO.getCod_produto());
            
            pstm.execute();
            pstm.close();
            
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, "ProdutoDAO Atualizar: " + e);
       }            
    }
     
}

/*String sql = "select quantidade from produto where cod_produto = ?";
            pstm = conexao.prepareStatement(sql);
            if (rs != null && rs.next()) {
                qtdOriginal = rs.getInt("quantidade");
            }
            pstm.setInt(1, objProdutoDTO.getCod_produto());
            
            String sql1 = "select quant_prod from notafiscal where cod_produto = ?";
            pstm = conexao.prepareStatement(sql1);
            if (rs != null && rs.next()) {
                qtdSubtrair = rs.getInt("quant_prod");
            }        
            pstm.setInt(1, objProdutoDTO.getCod_produto());
*/