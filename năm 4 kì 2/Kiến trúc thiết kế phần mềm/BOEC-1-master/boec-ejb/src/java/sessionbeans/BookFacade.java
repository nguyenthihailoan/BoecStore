/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionbeans;

import entities.items.Book;
import entities.items.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author DoHue
 */
@Stateless
public class BookFacade extends AbstractFacade<Book> implements BookFacadeLocal {

    @PersistenceContext(unitName = "boec-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookFacade() {
        super(Book.class);
    }

    @Override
    public List<Book> findByName(String name) {
        return getEntityManager().createQuery("Book.findByName").setParameter("name", name).getResultList();
    }

}
