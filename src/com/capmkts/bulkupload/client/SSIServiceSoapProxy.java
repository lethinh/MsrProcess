package com.capmkts.bulkupload.client;

public class SSIServiceSoapProxy implements com.capmkts.bulkupload.client.SSIServiceSoap {
  private String _endpoint = null;
  private com.capmkts.bulkupload.client.SSIServiceSoap sSIServiceSoap = null;
  
  public SSIServiceSoapProxy() {
    _initSSIServiceSoapProxy();
  }
  
  public SSIServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initSSIServiceSoapProxy();
  }
  
  private void _initSSIServiceSoapProxy() {
    try {
      sSIServiceSoap = (new com.capmkts.bulkupload.client.SSIServiceLocator()).getSSIServiceSoap();
      if (sSIServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sSIServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sSIServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sSIServiceSoap != null)
      ((javax.xml.rpc.Stub)sSIServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.capmkts.bulkupload.client.SSIServiceSoap getSSIServiceSoap() {
    if (sSIServiceSoap == null)
      _initSSIServiceSoapProxy();
    return sSIServiceSoap;
  }
  
  public java.lang.String[] processImport(java.lang.String importData, com.capmkts.bulkupload.client.Channel channel, java.lang.String productType) throws java.rmi.RemoteException{
    if (sSIServiceSoap == null)
      _initSSIServiceSoapProxy();
    return sSIServiceSoap.processImport(importData, channel, productType);
  }
  
  
}