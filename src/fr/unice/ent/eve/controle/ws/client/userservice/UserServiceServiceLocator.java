/**
 * UserServiceServiceLocator.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.unice.ent.eve.controle.ws.client.userservice;

@SuppressWarnings ({ "nls", "rawtypes" })
public class UserServiceServiceLocator extends org.apache.axis.client.Service
    implements
    fr.unice.ent.eve.controle.ws.client.userservice.UserServiceService {

  /**
   * 
   */
  private static final long                            serialVersionUID           = 1L;

  private java.util.HashSet<javax.xml.namespace.QName> ports;

  // Use to get a proxy class for UserService
  private java.lang.String                             userServiceAddress         = "http://localhost/eve/services/UserService";

  // The WSDD service name defaults to the port name.
  private java.lang.String                             userServiceWSDDServiceName = "UserService";

  public UserServiceServiceLocator () {
  }

  public UserServiceServiceLocator (final java.lang.String wsdlLoc,
      final javax.xml.namespace.QName sName)
      throws javax.xml.rpc.ServiceException {
    super (wsdlLoc, sName);
  }

  public UserServiceServiceLocator (
      final org.apache.axis.EngineConfiguration config) {
    super (config);
  }

  public UserServiceServiceLocator (final String endPoint) {
    this.userServiceAddress = endPoint;
  }

  /**
   * For the given interface, get the stub implementation. If this service has
   * no port for the given interface, then ServiceException is thrown.
   */
  @Override
  public java.rmi.Remote getPort (final Class serviceEndpointInterface)
      throws javax.xml.rpc.ServiceException {
    try {
      if (fr.unice.ent.eve.controle.ws.client.userservice.UserService.class
          .isAssignableFrom (serviceEndpointInterface)) {
        final fr.unice.ent.eve.controle.ws.client.userservice.UserServiceSoapBindingStub stub = new fr.unice.ent.eve.controle.ws.client.userservice.UserServiceSoapBindingStub (
            new java.net.URL (this.userServiceAddress), this);
        stub.setPortName (this.getUserServiceWSDDServiceName ());
        return stub;
      }

    } catch (final java.net.MalformedURLException t) {
      throw new javax.xml.rpc.ServiceException (t);
    }
    if (serviceEndpointInterface == null) { throw new javax.xml.rpc.ServiceException (
        "There is no stub implementation for the interface:  " + "null"); }
    throw new javax.xml.rpc.ServiceException (
        "There is no stub implementation for the interface:  "
            + serviceEndpointInterface.getName ());
  }

  /**
   * For the given interface, get the stub implementation. If this service has
   * no port for the given interface, then ServiceException is thrown.
   */
  @Override
  public java.rmi.Remote getPort (final javax.xml.namespace.QName portName,
      final Class serviceEndpointInterface)
      throws javax.xml.rpc.ServiceException {
    if (portName == null) { return this.getPort (serviceEndpointInterface); }
    final java.lang.String inputPortName = portName.getLocalPart ();
    if ("UserService".equals (inputPortName)) { return this.getUserService (); }
    final java.rmi.Remote stub = this.getPort (serviceEndpointInterface);
    ((org.apache.axis.client.Stub) stub).setPortName (portName);
    return stub;
  }

  @Override
  public java.util.Iterator getPorts () {
    if (this.ports == null) {
      this.ports = new java.util.HashSet<javax.xml.namespace.QName> ();
      this.ports.add (new javax.xml.namespace.QName (this.userServiceAddress,
          "UserService"));
    }
    return this.ports.iterator ();
  }

  @Override
  public javax.xml.namespace.QName getServiceName () {
    return new javax.xml.namespace.QName (this.userServiceAddress,
        "UserServiceService");
  }

  @Override
  public fr.unice.ent.eve.controle.ws.client.userservice.UserService getUserService ()
      throws javax.xml.rpc.ServiceException {
    java.net.URL endpoint;
    try {
      endpoint = new java.net.URL (this.userServiceAddress);
    } catch (final java.net.MalformedURLException e) {
      throw new javax.xml.rpc.ServiceException (e);
    }
    return this.getUserService (endpoint);
  }

  @Override
  public fr.unice.ent.eve.controle.ws.client.userservice.UserService getUserService (
      final java.net.URL portAddress) {
    final fr.unice.ent.eve.controle.ws.client.userservice.UserServiceSoapBindingStub stub = new fr.unice.ent.eve.controle.ws.client.userservice.UserServiceSoapBindingStub (
        portAddress, this);
    stub.setPortName (this.getUserServiceWSDDServiceName ());
    return stub;
  }

  @Override
  public java.lang.String getUserServiceAddress () {
    return this.userServiceAddress;
  }

  public java.lang.String getUserServiceWSDDServiceName () {
    return this.userServiceWSDDServiceName;
  }

  /**
   * Set the endpoint address for the specified port name.
   */
  public void setEndpointAddress (final java.lang.String portName,
      final java.lang.String address) throws javax.xml.rpc.ServiceException {

    if ("UserService".equals (portName)) {
      this.setUserServiceEndpointAddress (address);
    } else {
      throw new javax.xml.rpc.ServiceException (
          " Cannot set Endpoint Address for Unknown Port" + portName);
    }
  }

  /**
   * Set the endpoint address for the specified port name.
   */
  public void setEndpointAddress (final javax.xml.namespace.QName portName,
      final java.lang.String address) throws javax.xml.rpc.ServiceException {
    this.setEndpointAddress (portName.getLocalPart (), address);
  }

  public void setUserServiceEndpointAddress (final java.lang.String address) {
    this.userServiceAddress = address;
  }

  public void setUserServiceWSDDServiceName (final java.lang.String name) {
    this.userServiceWSDDServiceName = name;
  }

}
