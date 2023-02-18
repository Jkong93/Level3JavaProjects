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

public interface RecipientsDao {
    List<RecipientsDTO> getAllRecipients();
	
	RecipientsDTO getRecipientByAwardId(Integer awardID);
	void addRecipient(RecipientsDTO recipient);
	void deleteRecipient(RecipientsDTO recipient);
}
