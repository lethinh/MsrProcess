/**
 * EncompassServiceSoap.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.capmkts.encompassservice.Client;

public interface EncompassServiceSoap extends java.rmi.Remote {
    public java.lang.String helloWorld() throws java.rmi.RemoteException;
    public boolean exportDocumentsToEncompass(java.lang.String loanNumber, byte[] fileByteArray) throws java.rmi.RemoteException;
}
