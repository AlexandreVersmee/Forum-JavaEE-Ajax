package ca.uqar.forum.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ca.uqar.forum.entities.Sujet;


public interface SujetDAO extends JpaRepository<Sujet, Long>
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
	public List<Sujet> findAll();
	
	public Sujet findById(Long idSubject);
}
