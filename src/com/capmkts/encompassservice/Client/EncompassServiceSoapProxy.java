package com.capmkts.encompassservice.Client;

public class EncompassServiceSoapProxy implements com.capmkts.encompassservice.Client.EncompassServiceSoap {
  private String _endpoint = null;
  private com.capmkts.encompassservice.Client.EncompassServiceSoap encompassServiceSoap = null;
  
  public EncompassServiceSoapProxy() {
    _initEncompassServiceSoapProxy();
  }
  
  public EncompassServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initEncompassServiceSoapProxy();
  }
  
  private void _initEncompassServiceSoapProxy() {
    try {
      encompassServiceSoap = (new com.capmkts.encompassservice.Client.EncompassServiceLocator()).getEncompassServiceSoap();
      if (encompassServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)encompassServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)encompassServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (encompassServiceSoap != null)
      ((javax.xml.rpc.Stub)encompassServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.capmkts.encompassservice.Client.EncompassServiceSoap getEncompassServiceSoap() {
    if (encompassServiceSoap == null)
      _initEncompassServiceSoapProxy();
    return encompassServiceSoap;
  }
  
  public java.lang.String helloWorld() throws java.rmi.RemoteException{
    if (encompassServiceSoap == null)
      _initEncompassServiceSoapProxy();
    return encompassServiceSoap.helloWorld();
  }
  
  public boolean exportDocumentsToEncompass(java.lang.String loanNumber, byte[] fileByteArray) throws java.rmi.RemoteException{
    if (encompassServiceSoap == null)
      _initEncompassServiceSoapProxy();
    return encompassServiceSoap.exportDocumentsToEncompass(loanNumber, fileByteArray);
  }
  
  
}