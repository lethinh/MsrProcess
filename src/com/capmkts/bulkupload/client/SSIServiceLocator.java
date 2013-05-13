/**
 * SSIServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.capmkts.bulkupload.client;

public class SSIServiceLocator extends org.apache.axis.client.Service implements com.capmkts.bulkupload.client.SSIService {

    public SSIServiceLocator() {
    }


    public SSIServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public SSIServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SSIServiceSoap
    private java.lang.String SSIServiceSoap_address = "https://www.cmcfunding.com/BulkImport/Services/SSIService.asmx";

    public java.lang.String getSSIServiceSoapAddress() {
        return SSIServiceSoap_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SSIServiceSoapWSDDServiceName = "SSIServiceSoap";

    public java.lang.String getSSIServiceSoapWSDDServiceName() {
        return SSIServiceSoapWSDDServiceName;
    }

    public void setSSIServiceSoapWSDDServiceName(java.lang.String name) {
        SSIServiceSoapWSDDServiceName = name;
    }

    public com.capmkts.bulkupload.client.SSIServiceSoap getSSIServiceSoap() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SSIServiceSoap_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSSIServiceSoap(endpoint);
    }

    public com.capmkts.bulkupload.client.SSIServiceSoap getSSIServiceSoap(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.capmkts.bulkupload.client.SSIServiceSoapStub _stub = new com.capmkts.bulkupload.client.SSIServiceSoapStub(portAddress, this);
            _stub.setPortName(getSSIServiceSoapWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSSIServiceSoapEndpointAddress(java.lang.String address) {
        SSIServiceSoap_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.capmkts.bulkupload.client.SSIServiceSoap.class.isAssignableFrom(serviceEndpointInterface)) {
                com.capmkts.bulkupload.client.SSIServiceSoapStub _stub = new com.capmkts.bulkupload.client.SSIServiceSoapStub(new java.net.URL(SSIServiceSoap_address), this);
                _stub.setPortName(getSSIServiceSoapWSDDServiceName());
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
        if ("SSIServiceSoap".equals(inputPortName)) {
            return getSSIServiceSoap();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "SSIService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "SSIServiceSoap"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SSIServiceSoap".equals(portName)) {
            setSSIServiceSoapEndpointAddress(address);
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
