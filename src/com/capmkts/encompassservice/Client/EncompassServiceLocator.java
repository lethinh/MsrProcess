/**
 * EncompassServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.capmkts.encompassservice.Client;

public class EncompassServiceLocator extends org.apache.axis.client.Service implements com.capmkts.encompassservice.Client.EncompassService {

    public EncompassServiceLocator() {
    }


    public EncompassServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public EncompassServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for EncompassServiceSoap
//    private java.lang.String EncompassServiceSoap_address = "http://localhost:49436/EncompassService.asmx";
    private java.lang.String EncompassServiceSoap_address = "http://localhost:80/EncompassService.asmx";

    public java.lang.String getEncompassServiceSoapAddress() {
        return EncompassServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String EncompassServiceSoapWSDDServiceName = "EncompassServiceSoap";

    public java.lang.String getEncompassServiceSoapWSDDServiceName() {
        return EncompassServiceSoapWSDDServiceName;
    }

    public void setEncompassServiceSoapWSDDServiceName(java.lang.String name) {
        EncompassServiceSoapWSDDServiceName = name;
    }

    public com.capmkts.encompassservice.Client.EncompassServiceSoap getEncompassServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(EncompassServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getEncompassServiceSoap(endpoint);
    }

    public com.capmkts.encompassservice.Client.EncompassServiceSoap getEncompassServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.capmkts.encompassservice.Client.EncompassServiceSoapStub _stub = new com.capmkts.encompassservice.Client.EncompassServiceSoapStub(portAddress, this);
            _stub.setPortName(getEncompassServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setEncompassServiceSoapEndpointAddress(java.lang.String address) {
        EncompassServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.capmkts.encompassservice.Client.EncompassServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.capmkts.encompassservice.Client.EncompassServiceSoapStub _stub = new com.capmkts.encompassservice.Client.EncompassServiceSoapStub(new java.net.URL(EncompassServiceSoap_address), this);
                _stub.setPortName(getEncompassServiceSoapWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("EncompassServiceSoap".equals(inputPortName)) {
            return getEncompassServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "EncompassService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "EncompassServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("EncompassServiceSoap".equals(portName)) {
            setEncompassServiceSoapEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
