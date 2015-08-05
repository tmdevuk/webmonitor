package uk.gov.ho.api.webmonitor.resources;

import uk.gov.ho.api.webmonitor.core.RequestLogItem;

import uk.gov.ho.api.webmonitor.util.HibernateUtil;

import org.hibernate.Session;

import javax.ws.rs.GET;

import javax.ws.rs.Path;

import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;

/**
 * Created by mooret on 04/08/15.
 */

@Path("/response")
@Produces(MediaType.TEXT_PLAIN)


public class RequestLogItemResource {


  Session session = HibernateUtil.getSessionFactory().getCurrentSession();

  /** sample doc. */
  public RequestLogItemResource() {

  }

  /** get requests. */
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  //public String addUser(@FormParam("firstname") String fname,
  //@FormParam("lastname") String lname) {
  public String addRequestLogItem() {

    session.beginTransaction();
    RequestLogItem myRequestLogItem = new RequestLogItem();
    session.save(myRequestLogItem);
    session.getTransaction().commit();
    //HibernateUtil.getSessionFactory().close();



    //UserDao dao = new UserDao();
    //dao.addUser("Peter", "Jones");
    return "record added";
  }

  //@GET
  //@Produces(MediaType.TEXT_PLAIN)
  //public String getNamedGreeting() {
  //  return "Hello";
  //}
}


