package uk.gov.ho.api.webmonitor.dao;

import uk.gov.ho.api.webmonitor.core.UrlResponse;
import uk.gov.ho.api.webmonitor.util.HibernateUtil;



import org.hibernate.Session;

import java.util.List;


/**
 * Created by mooret on 05/08/15.
 */

/** data handler for UrlResponse. */
public class UrlResponseDao {

  Session session = HibernateUtil.getSessionFactory().getCurrentSession();

  public UrlResponseDao() {

  }
  /** return all rows. */
  public List<UrlResponse> getAllUrlResponses() {

    session.beginTransaction();
    List<UrlResponse> myUrlResponses = session.createCriteria(UrlResponse.class).list();
    session.getTransaction().commit();

    return myUrlResponses;

  }

}
