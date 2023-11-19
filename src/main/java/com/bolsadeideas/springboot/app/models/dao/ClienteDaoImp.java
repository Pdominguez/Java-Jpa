package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.entity.Cliente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class ClienteDaoImp implements IClienteDao {

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}

	
	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cliente").getResultList();
	}

	@Override
	public void save(Cliente cliente) {
		// TODO Auto-generated method stub
		if(cliente.getId() != null && cliente.getId()>0) {
			em.merge(cliente);
		}else {
			
			em.persist(cliente);
		}
		
	}

	
	@Override
	public void delete(Long id) {
		
		Cliente cliente = findOne(id);
		em.remove(cliente);
		
	}
	

}
