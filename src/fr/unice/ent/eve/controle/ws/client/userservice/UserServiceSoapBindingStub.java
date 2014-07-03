/**
 * UserServiceSoapBindingStub.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.unice.ent.eve.controle.ws.client.userservice;

import javax.xml.rpc.ServiceException;

@SuppressWarnings ({ "unused", "nls", "unchecked", "rawtypes" })
public class UserServiceSoapBindingStub extends org.apache.axis.client.Stub
    implements fr.unice.ent.eve.controle.ws.client.userservice.UserService {
  private static org.apache.axis.description.OperationDesc[] operations;
  static {
    UserServiceSoapBindingStub.operations = new org.apache.axis.description.OperationDesc[1];
    UserServiceSoapBindingStub.initOperationDesc1 ();
  }

  private static void initOperationDesc1 () {
    org.apache.axis.description.OperationDesc oper;
    org.apache.axis.description.ParameterDesc param;
    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("query");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in1"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("", "queryReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    UserServiceSoapBindingStub.operations[0] = oper;

  }

  private final java.util.Vector                            cachedDeserFactories = new java.util.Vector ();

  private final java.util.Vector<Class>                     cachedSerClasses     = new java.util.Vector<Class> ();

  private final java.util.Vector                            cachedSerFactories   = new java.util.Vector ();

  private final java.util.Vector<javax.xml.namespace.QName> cachedSerQNames      = new java.util.Vector<javax.xml.namespace.QName> ();

  public UserServiceSoapBindingStub () {
    this (null);
  }

  public UserServiceSoapBindingStub (final java.net.URL endpointURL,
      final javax.xml.rpc.Service s) {
    this (s);
    super.cachedEndpoint = endpointURL;
    UserServiceSoapBindingStub.operations[0]
        .setReturnType (new javax.xml.namespace.QName (super.cachedEndpoint
            .toString (), "ArrayOf_xsd_anyType"));
  }

  public UserServiceSoapBindingStub (final javax.xml.rpc.Service s) {
    if (s == null) {
      super.service = new org.apache.axis.client.Service ();
    } else {
      super.service = s;
    }
    ((org.apache.axis.client.Service) super.service)
        .setTypeMappingVersion ("1.2");
    java.lang.Class cls;
    javax.xml.namespace.QName qName;
    javax.xml.namespace.QName qName2;
    final java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
    final java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
    final java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
    final java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
    final java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
    final java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
    final java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
    final java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
    final java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
    final java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
    qName = new javax.xml.namespace.QName (
        "http://127.0.0.1/eve-dev/services/UserService", "ArrayOf_xsd_anyType");
    this.cachedSerQNames.add (qName);
    cls = java.lang.Object[].class;
    this.cachedSerClasses.add (cls);
    qName = new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
        "anyType");
    qName2 = null;
    this.cachedSerFactories
        .add (new org.apache.axis.encoding.ser.ArraySerializerFactory (qName,
            qName2));
    this.cachedDeserFactories
        .add (new org.apache.axis.encoding.ser.ArrayDeserializerFactory ());

  }

  protected org.apache.axis.client.Call createCall ()
      throws java.rmi.RemoteException {
    try {
      this._call = super._createCall ();
      if (super.maintainSessionSet) {
        this._call.setMaintainSession (super.maintainSession);
      }
      if (super.cachedUsername != null) {
        this._call.setUsername (super.cachedUsername);
      }
      if (super.cachedPassword != null) {
        this._call.setPassword (super.cachedPassword);
      }
      if (super.cachedEndpoint != null) {
        this._call.setTargetEndpointAddress (super.cachedEndpoint);
      }
      if (super.cachedTimeout != null) {
        this._call.setTimeout (super.cachedTimeout);
      }
      if (super.cachedPortName != null) {
        this._call.setPortName (super.cachedPortName);
      }
      final java.util.Enumeration keys = super.cachedProperties.keys ();
      while (keys.hasMoreElements ()) {
        final java.lang.String key = (java.lang.String) keys.nextElement ();
        this._call.setProperty (key, super.cachedProperties.get (key));
      }
      // All the type mapping information is registered
      // when the first call is made.
      // The type mapping information is actually registered in
      // the TypeMappingRegistry of the service, which
      // is the reason why registration is only needed for the first call.
      synchronized (this) {
        if (this.firstCall ()) {
          // must set encoding style before registering serializers
          this._call
              .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
          this._call
              .setEncodingStyle (org.apache.axis.Constants.URI_SOAP11_ENC);
          for (int i = 0 ; i < this.cachedSerFactories.size () ; ++i) {
            final java.lang.Class cls = this.cachedSerClasses.get (i);
            final javax.xml.namespace.QName qName = this.cachedSerQNames
                .get (i);
            final java.lang.Object x = this.cachedSerFactories.get (i);
            if (x instanceof Class) {
              final java.lang.Class sf = (java.lang.Class) this.cachedSerFactories
                  .get (i);
              final java.lang.Class df = (java.lang.Class) this.cachedDeserFactories
                  .get (i);
              this._call.registerTypeMapping (cls, qName, sf, df, false);
            } else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
              final org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory) this.cachedSerFactories
                  .get (i);
              final org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory) this.cachedDeserFactories
                  .get (i);
              this._call.registerTypeMapping (cls, qName, sf, df, false);
            }
          }
        }
      }
      return this._call;
    } catch (final ServiceException e) {
      throw new org.apache.axis.AxisFault (
          "Failure trying to get the Call object", e);
    }
  }

  @Override
  public java.lang.Object[] query (final java.lang.String in0,
      final java.lang.String in1) throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UserServiceSoapBindingStub.operations[0]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://127.0.0.1/eve-dev/services/UserService", "query"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call.invoke (new java.lang.Object[] {
          in0, in1, });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      throw axisFaultException;
    }
  }

}
