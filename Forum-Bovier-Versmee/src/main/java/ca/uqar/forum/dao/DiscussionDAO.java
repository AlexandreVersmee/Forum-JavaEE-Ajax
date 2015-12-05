package ca.uqar.forum.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.uqar.forum.entities.FilDiscussion;

public interface DiscussionDAO extends JpaRepository<FilDiscussion, Long>
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
	public FilDiscussion findById(Long idDiscussion);
}
