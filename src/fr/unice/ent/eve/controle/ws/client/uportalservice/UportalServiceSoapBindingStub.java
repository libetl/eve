/**
 * UportalServiceSoapBindingStub.java
 * 
 * This file was auto-generated from WSDL by the Apache Axis 1.4 Apr 22, 2006
 * (06:55:48 PDT) WSDL2Java emitter.
 */

package fr.unice.ent.eve.controle.ws.client.uportalservice;

import javax.xml.rpc.ServiceException;

@SuppressWarnings ({ "unused", "nls", "unchecked", "rawtypes" })
public class UportalServiceSoapBindingStub extends org.apache.axis.client.Stub
    implements
    fr.unice.ent.eve.controle.ws.client.uportalservice.UportalService {
  private static java.lang.String                            endpoint;
  private static org.apache.axis.description.OperationDesc[] operations;

  static {
    UportalServiceSoapBindingStub.operations = new org.apache.axis.description.OperationDesc[12];
    UportalServiceSoapBindingStub.initOperationDesc1 ();
    UportalServiceSoapBindingStub.initOperationDesc2 ();
  }

  private static void initOperationDesc1 () {
    org.apache.axis.description.OperationDesc oper;
    org.apache.axis.description.ParameterDesc param;
    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getRootGroup");
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "getRootGroupReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    UportalServiceSoapBindingStub.operations[0] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getUser");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("", "getUserReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceUserNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[1] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("searchUsers");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("", "searchUsersReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[2] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getGroupById");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "getGroupByIdReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceGroupNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[3] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getGroupByName");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "getGroupByNameReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceGroupNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[4] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("searchGroupsByName");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "searchGroupsByNameReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[5] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getSubGroupsByName");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "getSubGroupsByNameReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceGroupNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[6] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getGroupHierarchy");
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "getGroupHierarchyReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceGroupNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[7] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getGroupHierarchyById");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "getGroupHierarchyByIdReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceGroupNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[8] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getGroupHierarchyByName");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "getGroupHierarchyByNameReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceGroupNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[9] = oper;

  }

  private static void initOperationDesc2 () {
    org.apache.axis.description.OperationDesc oper;
    org.apache.axis.description.ParameterDesc param;
    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("getUserGroups");
    param = new org.apache.axis.description.ParameterDesc (
        new javax.xml.namespace.QName ("", "in0"),
        org.apache.axis.description.ParameterDesc.IN,
        new javax.xml.namespace.QName (
            "http://schemas.xmlsoap.org/soap/encoding/", "string"),
        java.lang.String.class, false, false);
    oper.addParameter (param);
    oper.setReturnClass (java.lang.Object[].class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "getUserGroupsReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceUserNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[10] = oper;

    oper = new org.apache.axis.description.OperationDesc ();
    oper.setName ("isUserMemberOfGroup");
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
    oper.setReturnType (new javax.xml.namespace.QName (
        "http://www.w3.org/2001/XMLSchema", "boolean"));
    oper.setReturnClass (boolean.class);
    oper.setReturnQName (new javax.xml.namespace.QName ("",
        "isUserMemberOfGroupReturn"));
    oper.setStyle (org.apache.axis.constants.Style.RPC);
    oper.setUse (org.apache.axis.constants.Use.ENCODED);
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceErrorException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceUserNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    oper.addFault (new org.apache.axis.description.FaultDesc (
        new javax.xml.namespace.QName (UportalServiceSoapBindingStub.endpoint,
            "fault"),
        "fr.unice.ent.uPortal.services.UportalService.UportalServiceGroupNotFoundException",
        new javax.xml.namespace.QName ("http://www.w3.org/2001/XMLSchema",
            "anyType"), false));
    UportalServiceSoapBindingStub.operations[11] = oper;

  }

  private final java.util.Vector                            cachedDeserFactories = new java.util.Vector ();

  private final java.util.Vector<Class>                     cachedSerClasses     = new java.util.Vector<Class> ();

  private final java.util.Vector                            cachedSerFactories   = new java.util.Vector ();

  private final java.util.Vector<javax.xml.namespace.QName> cachedSerQNames      = new java.util.Vector<javax.xml.namespace.QName> ();

  public UportalServiceSoapBindingStub () {
    this (null);
  }

  public UportalServiceSoapBindingStub (final java.net.URL endpointURL,
      final javax.xml.rpc.Service s) {
    this (s);
    super.cachedEndpoint = endpointURL;
    UportalServiceSoapBindingStub.endpoint = endpointURL.toString ();
    for (int i = 0 ; i < 11 ; i++) {
      UportalServiceSoapBindingStub.operations[i]
          .setReturnType (new javax.xml.namespace.QName (super.cachedEndpoint
              .toString (), "ArrayOf_xsd_anyType"));
    }
  }

  public UportalServiceSoapBindingStub (final javax.xml.rpc.Service s) {
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
        "http://localhost/uPortal/services/UportalService",
        "ArrayOf_xsd_anyType");
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
  public java.lang.Object[] getGroupById (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[3]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getGroupById"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] getGroupByName (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[4]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getGroupByName"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] getGroupHierarchy ()
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[7]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getGroupHierarchy"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] {});

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] getGroupHierarchyById (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[8]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getGroupHierarchyById"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] getGroupHierarchyByName (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[9]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getGroupHierarchyByName"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] getRootGroup () throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[0]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getRootGroup"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] {});

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

  @Override
  public java.lang.Object[] getSubGroupsByName (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[6]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getSubGroupsByName"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] getUser (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[1]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getUser"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceUserNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceUserNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] getUserGroups (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[10]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "getUserGroups"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceUserNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceUserNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public boolean isUserMemberOfGroup (final java.lang.String in0,
      final java.lang.String in1) throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[11]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "isUserMemberOfGroup"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call.invoke (new java.lang.Object[] {
          in0, in1, });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return ((java.lang.Boolean) resp).booleanValue ();
      } catch (final java.lang.Exception exception) {
        return ((java.lang.Boolean) org.apache.axis.utils.JavaUtils.convert (
            resp, boolean.class)).booleanValue ();
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceUserNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceUserNotFoundException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceGroupNotFoundException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] searchGroupsByName (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[5]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "searchGroupsByName"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

  @Override
  public java.lang.Object[] searchUsers (final java.lang.String in0)
      throws java.rmi.RemoteException {
    if (super.cachedEndpoint == null) { throw new org.apache.axis.NoEndPointException (); }
    this._call = this.createCall ();
    this._call.setOperation (UportalServiceSoapBindingStub.operations[2]);
    this._call.setUseSOAPAction (true);
    this._call.setSOAPActionURI ("");
    this._call
        .setSOAPVersion (org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
    this._call.setOperationName (new javax.xml.namespace.QName (
        "http://server.ws.portal.esupportail.org", "searchUsers"));

    this.setRequestHeaders (this._call);
    this.setAttachments (this._call);
    try {
      final java.lang.Object resp = this._call
          .invoke (new java.lang.Object[] { in0 });

      if (resp instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) resp; }
      this.extractAttachments (this._call);
      try {
        return (java.lang.Object[]) resp;
      } catch (final java.lang.Exception exception) {
        return (java.lang.Object[]) org.apache.axis.utils.JavaUtils.convert (
            resp, java.lang.Object[].class);
      }
    } catch (final org.apache.axis.AxisFault axisFaultException) {
      if (axisFaultException.detail != null) {
        if (axisFaultException.detail instanceof java.rmi.RemoteException) { throw (java.rmi.RemoteException) axisFaultException.detail; }
        if (axisFaultException.detail instanceof fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) { throw (fr.unice.ent.eve.controle.ws.client.uportalservice.UportalServiceErrorException) axisFaultException.detail; }
      }
      throw axisFaultException;
    }
  }

}
