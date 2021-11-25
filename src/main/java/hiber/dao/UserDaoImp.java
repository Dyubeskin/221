package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import javassist.bytecode.stackmap.BasicBlock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
   private Transaction transaction;

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user, Car car) {
//      sessionFactory.getCurrentSession().save(user);
      try (Session session = sessionFactory.openSession()){
         Transaction transaction = session.beginTransaction();
         session.save(user);
         session.save(car);
         transaction.commit();
      } catch (Exception e){
         if (transaction !=null){
            transaction.rollback();
         }
      }

   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }
   @Override
   public User getUser(String model, int series) {
      Session session = sessionFactory.openSession();
      String HQL = "FROM User WHERE car.model=:model AND car.series=:series";
      Query query = session.createQuery(HQL);
      query.setParameter("model",model);
      query.setParameter("series", series);
      User user = (User) query.getSingleResult();
      return user;
   }


}
