package at.htl.jeopardized.clue;


import at.htl.jeopardized.category.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class ClueDao {

    @PersistenceContext
    EntityManager em;


    @Transactional
    public Clue persist(Clue entity){
        em.persist(entity);
        return entity;
    }

    public List<Clue> getAll(int offset,int limit) {
        TypedQuery<Clue> query = em.createNamedQuery("Clue.findAll",Clue.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return query.getResultList();
    }

    public Clue getById(long id) {
        TypedQuery<Clue> query = em.createNamedQuery("Clue.findById",Clue.class);
        query.setParameter("Id",id);
        return query.getSingleResult();
    }
}
