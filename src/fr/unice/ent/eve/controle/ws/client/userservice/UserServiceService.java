/**
 * UserServiceService.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.unice.ent.eve.controle.ws.client.userservice;

public interface UserServiceService extends javax.xml.rpc.Service {
  fr.unice.ent.eve.controle.ws.client.userservice.UserService getUserService ()
      throws javax.xml.rpc.ServiceException;

  fr.unice.ent.eve.controle.ws.client.userservice.UserService getUserService (
      java.net.URL portAddress) throws javax.xml.rpc.ServiceException;

  java.lang.String getUserServiceAddress ();
}
