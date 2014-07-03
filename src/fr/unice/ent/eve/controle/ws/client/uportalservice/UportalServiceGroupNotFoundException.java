/**
 * UportalServiceGroupNotFoundException.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.unice.ent.eve.controle.ws.client.uportalservice;

public class UportalServiceGroupNotFoundException extends
    org.apache.axis.AxisFault {
  /**
   * 
   */
  private static final long      serialVersionUID = 1L;
  private final java.lang.Object fault;

  public UportalServiceGroupNotFoundException () {
    this.fault = new Object ();
  }

  public UportalServiceGroupNotFoundException (final java.lang.Exception target) {
    super (target);
    this.fault = new Object ();
  }

  public UportalServiceGroupNotFoundException (final java.lang.Object f) {
    this.fault = f;
  }

  public UportalServiceGroupNotFoundException (final java.lang.String message,
      final java.lang.Throwable t) {
    super (message, t);
    this.fault = new Object ();
  }

  public java.lang.Object getFault () {
    return this.fault;
  }

  /**
   * Writes the exception data to the faultDetails
   */
  @Override
  public void writeDetails (final javax.xml.namespace.QName qname,
      final org.apache.axis.encoding.SerializationContext context)
      throws java.io.IOException {
    context.serialize (qname, null, this.fault);
  }
}
