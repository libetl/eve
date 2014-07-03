/**
 * UportalService.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.unice.ent.eve.controle.ws.client.uportalservice;

public interface UportalService extends java.rmi.Remote {
  java.lang.Object[] getGroupById (java.lang.String in0)
      throws java.rmi.RemoteException;

  java.lang.Object[] getGroupByName (java.lang.String in0)
      throws java.rmi.RemoteException;

  java.lang.Object[] getGroupHierarchy () throws java.rmi.RemoteException;

  java.lang.Object[] getGroupHierarchyById (java.lang.String in0)
      throws java.rmi.RemoteException;

  java.lang.Object[] getGroupHierarchyByName (java.lang.String in0)
      throws java.rmi.RemoteException;

  java.lang.Object[] getRootGroup () throws java.rmi.RemoteException;

  java.lang.Object[] getSubGroupsByName (java.lang.String in0)
      throws java.rmi.RemoteException;

  java.lang.Object[] getUser (java.lang.String in0)
      throws java.rmi.RemoteException;

  java.lang.Object[] getUserGroups (java.lang.String in0)
      throws java.rmi.RemoteException;

  boolean isUserMemberOfGroup (java.lang.String in0, java.lang.String in1)
      throws java.rmi.RemoteException;

  java.lang.Object[] searchGroupsByName (java.lang.String in0)
      throws java.rmi.RemoteException;

  java.lang.Object[] searchUsers (java.lang.String in0)
      throws java.rmi.RemoteException;
}
