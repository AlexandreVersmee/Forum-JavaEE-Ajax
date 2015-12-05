package ca.uqar.forum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.uqar.forum.entities.Message;

public interface MessageDAO extends JpaRepository<Message, Long>
{
	 /*
	 ###############################
	 #                             #
	 #       Default method        #
	 #    (delete, save, FindAll,  #
	 #      getOne, flush, ...)    #
	 #                             #
	 ###############################
	 */
	
	public Message findById(Long idSubject);
}
