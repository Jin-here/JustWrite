import com.vgaw.hibernate.pojo.User;
import com.vgaw.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TestClient {

	public static void main(String[] args) throws Exception {
		HibernateUtil util = HibernateUtil.getInstance();

		Session session = null;
        Transaction tx = null;

        try {
            session = util.getSession();
            tx = session.beginTransaction();

            User user1 = new User();
			user1.setNickName("caojin");
			user1.setPassword("caojin");
			session.persist(user1);

            User user2 = new User();
            user2.setNickName("caojin");
            user2.setPassword("caojin");
            session.persist(user2);

            /*Group group = new Group();
            group.setName("Kingdom");
            session.persist(group);*/

            tx.commit();

		} catch (Exception e) {
			if (tx != null){
				tx.rollback();
			}
			throw e;
		} finally {
			util.close();
		}
	}
}
