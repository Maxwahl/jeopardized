package at.htl.jeopardized.category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
public class CategoryDao {

    @PersistenceContext
    EntityManager em;

    @Transactional
    public Category persist(Category entity){
        em.persist(entity);
        return entity;
    }
    public List<Category> getAll() {
        TypedQuery<Category> query = em.createNamedQuery("Category.findAll",Category.class);
        return query.getResultList();
    }

    public Category getById(long id) {
        TypedQuery<Category> query = em.createNamedQuery("Category.findById",Category.class);
        query.setParameter("Id",id);
        return query.getSingleResult();
    }

    public Double getAverageValue(long category) {
        TypedQuery<Double> query = em.createNamedQuery("Category.averageValue",Double.class);
        query.setParameter("category",category);
        return query.getSingleResult();
    }
}
