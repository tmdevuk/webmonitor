package uk.gov.ho.api.webmonitor.dao;

import uk.gov.ho.api.webmonitor.core.UrlResponse;
import uk.gov.ho.api.webmonitor.util.HibernateUtil;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;

import java.util.List;


/** data handler for UrlResponse. */
public class UrlResponseDao {

  Session session = HibernateUtil.getSessionFactory().getCurrentSession();

  public UrlResponseDao() {

  }
  /** return all rows. */
  public List<UrlResponse> getAllUrlResponses() {

    DateTime date = new DateTime().minusHours(1);
    session.beginTransaction();
    List<UrlResponse> myUrlResponses = session.createCriteria(UrlResponse.class)
    //.add(Restrictions.ge("responseDate", date);
        .addOrder(Order.desc("responseDate"))
        .setMaxResults(10)
        .list();
    session.getTransaction().commit();

    return myUrlResponses;

  }



}
