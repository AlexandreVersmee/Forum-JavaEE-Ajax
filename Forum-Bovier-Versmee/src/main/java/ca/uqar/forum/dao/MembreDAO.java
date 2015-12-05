package ca.uqar.forum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.uqar.forum.entities.Membre;

public interface MembreDAO extends JpaRepository<Membre, Long>{
	   /*
    ###############################
    #                             #
    #       Default method        #
    #    (delete, save, FindAll,  #
    #      getOne, flush, ...)    #
    #                             #
    ###############################
    */
	public Membre findByPseudo(String membrePseudo);

	public List<Membre> findByValideIsNull();
	
	public Membre findById(long parseLong);
	
	public List<Membre> findByValideIsNotNullAndDeleted(Object object);
}
