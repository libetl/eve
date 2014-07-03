/**
 * UportalServiceServiceLocator.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.unice.ent.eve.controle.ws.client.uportalservice;

import java.net.MalformedURLException;

@SuppressWarnings ({ "nls" })
public class UportalServiceServiceLocator extends
    org.apache.axis.client.Service implements
    fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceService {

  /**
   * 
   */
  private static final long                            serialVersionUID              = 1L;

  private java.util.HashSet<javax.xml.namespace.QName> ports;

  // Use to get a proxy class for UportalService
  private java.lang.String                             uPortalServiceAddress         = "http://localhost/uPortal/services/UportalService";

  // The WSDD service name defaults to the port name.
  private java.lang.String                             uPortalServiceWSDDServiceName = "UportalService";

  public UportalServiceServiceLocator () {
  }

  public UportalServiceServiceLocator (final java.lang.String wsdlLoc,
      final javax.xml.namespace.QName sName)
      throws javax.xml.rpc.ServiceException {
    super (wsdlLoc, sName);
  }

  public UportalServiceServiceLocator (
      final org.apache.axis.EngineConfiguration config) {
    super (config);
  }

  public UportalServiceServiceLocator (final String endpoint) {
    this.uPortalServiceAddress = endpoint;
  }

  /**
   * For the given interface, get the stub implementation. If this service has
   * no port for the given interface, then ServiceException is thrown.
   */
  @Override
  public java.rmi.Remote getPort (
      @SuppressWarnings ("rawtypes") final Class serviceEndpointInterface)
      throws javax.xml.rpc.ServiceException {
    try {
      if (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalService.class
          .isAssignableFrom (serviceEndpointInterface)) {
        fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceSoapBindingStub stub;

        stub = new fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceSoapBindingStub (
            new java.net.URL (this.uPortalServiceAddress), this);

        stub.setPortName (this.getUportalServiceWSDDServiceName ());
        return stub;
      }
      return null;
    } catch (final MalformedURLException e) {
      if (serviceEndpointInterface == null) { throw new javax.xml.rpc.ServiceException (
          "There is no stub implementation for the interface:  " + "null"); }
      throw new javax.xml.rpc.ServiceException (
          "There is no stub implementation for the interface:  "
              + serviceEndpointInterface.getName ());
    }
  }

  /**
   * For the given interface, get the stub implementation. If this service has
   * no port for the given interface, then ServiceException is thrown.
   */
  @Override
  public java.rmi.Remote getPort (final javax.xml.namespace.QName portName,
      @SuppressWarnings ("rawtypes") final Class serviceEndpointInterface)
      throws javax.xml.rpc.ServiceException {
    if (portName == null) { return this.getPort (serviceEndpointInterface); }
    final java.lang.String inputPortName = portName.getLocalPart ();
    if ("UportalService".equals (inputPortName)) { return this
        .getUportalService (); }
    final java.rmi.Remote stub = this.getPort (serviceEndpointInterface);
    ((org.apache.axis.client.Stub) stub).setPortName (portName);
    return stub;

  }

  @SuppressWarnings ("rawtypes")
  @Override
  public java.util.Iterator getPorts () {
    if (this.ports == null) {
      this.ports = new java.util.HashSet<javax.xml.namespace.QName> ();
      this.ports.add (new javax.xml.namespace.QName (
          this.uPortalServiceAddress, "UportalService"));
    }
    return this.ports.iterator ();
  }

  @Override
  public javax.xml.namespace.QName getServiceName () {
    return new javax.xml.namespace.QName (this.uPortalServiceAddress,
        "UportalServiceService");
  }

  @Override
  public fr.unice.ent.eve.controle.ws.client.uportalservice.UportalService getUportalService ()
      throws javax.xml.rpc.ServiceException {
    java.net.URL endpoint;
    try {
      endpoint = new java.net.URL (this.uPortalServiceAddress);
    } catch (final java.net.MalformedURLException e) {
      throw new javax.xml.rpc.ServiceException (e);
    }
    return this.getUportalService (endpoint);
  }

  @Override
  public fr.unice.ent.eve.controle.ws.client.uportalservice.UportalService getUportalService (
      final java.net.URL portAddress) {
    final fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceSoapBindingStub stub = new fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceSoapBindingStub (
        portAddress, this);
    stub.setPortName (this.getUportalServiceWSDDServiceName ());
    return stub;
  }

  @Override
  public java.lang.String getUportalServiceAddress () {
    return this.uPortalServiceAddress;
  }

  public java.lang.String getUportalServiceWSDDServiceName () {
    return this.uPortalServiceWSDDServiceName;
  }

  /**
   * Set the endpoint address for the specified port name.
   */
  public void setEndpointAddress (final java.lang.String portName,
      final java.lang.String address) throws javax.xml.rpc.ServiceException {

    if ("UportalService".equals (portName)) {
      this.setUportalServiceEndpointAddress (address);
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

  public void setUportalServiceEndpointAddress (final java.lang.String address) {
    this.uPortalServiceAddress = address;
  }

  public void setUportalServiceWSDDServiceName (final java.lang.String name) {
    this.uPortalServiceWSDDServiceName = name;
  }

}
