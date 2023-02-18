/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

/**
 *
 * @author jessekong
 */

import java.util.List;
import transferobjects.RecipientsDTO;

import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RecipientsDaoImp implements RecipientsDao {
@Override
	public List<RecipientsDTO> getAllRecipients() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<RecipientsDTO> recipients = null;
                
                try{
			DataSource ds = new DataSource();
			con = ds.createConnection();
			pstmt = con.prepareStatement("SELECT AwardID, Name, Year, City, Category FROM recipients ORDER BY AwardID");
                        rs = pstmt.executeQuery();
			recipients = new ArrayList<RecipientsDTO>();
			while(rs.next()){
				RecipientsDTO recipient = new RecipientsDTO();
				recipient.setAwardID(rs.getInt("AwardID"));
				recipient.setName(rs.getString("Name"));
				recipient.setYear(rs.getInt("Year"));
                                recipient.setCity(rs.getString("City"));
				recipient.setCategory(rs.getString("Category"));
				recipients.add(recipient);
			}
                        		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{ if(rs != null){ rs.close(); } }
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
		return recipients;
}

    @Override
    public RecipientsDTO getRecipientByAwardId(Integer awardID) {
        Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	RecipientsDTO recipient = null;
        
        try{
			DataSource ds = new DataSource();
			con = ds.createConnection();
			pstmt = con.prepareStatement("SELECT AwardID, Name, Year, City, Category FROM recipients WHERE AwardID = ?");
                        pstmt.setInt(1, awardID.intValue());
                        rs = pstmt.executeQuery();
                        while(rs.next()){
				recipient = new RecipientsDTO();
				recipient.setAwardID(rs.getInt("AwardID"));
				recipient.setName(rs.getString("Name"));
				recipient.setYear(rs.getInt("Year"));
                                recipient.setCity(rs.getString("City"));
				recipient.setCategory(rs.getString("Category"));
                                			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{ if(rs != null){ rs.close(); } }
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
		return recipient;
    }

    @Override
    public void addRecipient(RecipientsDTO recipient) {
        Connection con = null;
	PreparedStatement pstmt = null;
        
        try{
			DataSource ds = new DataSource();
			con = ds.createConnection();
                        pstmt = con.prepareStatement("INSERT INTO recipients (Name, Year, City, Category)" + "VALUES(?, ?, ?, ?)");
                        pstmt.setString(1, recipient.getName());
			pstmt.setInt(2, recipient.getYear());
                        pstmt.setString(3, recipient.getCity());
                        pstmt.setString(4, recipient.getCategory());
			pstmt.executeUpdate();
                        		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
    }

    @Override
    public void deleteRecipient(RecipientsDTO recipient) {
        Connection con = null;
	PreparedStatement pstmt = null;
        
        try{
			DataSource ds = new DataSource();
			con = ds.createConnection();
			pstmt = con.prepareStatement("DELETE FROM recipients WHERE AwardID = ?");
                        pstmt.setInt(1, recipient.getAwardID().intValue());
			pstmt.executeUpdate();
    }
        		catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			try{ if(pstmt != null){ pstmt.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
			try{ if(con != null){ con.close(); }}
			catch(SQLException ex){System.out.println(ex.getMessage());}
		}
	}
}


