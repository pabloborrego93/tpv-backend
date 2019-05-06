package com.pbg.tpvbackend.service;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

import com.pbg.tpvbackend.exception.gcp.AccessTokenException;
import com.pbg.tpvbackend.exception.gcp.SubmitJobException;

public interface CloudPrintService {

	public Boolean submitJob(String printerid, String title, InputStream content, Integer retries) throws IOException, AccessTokenException, GeneralSecurityException, SubmitJobException;
	public Boolean processInvite(String printerid) throws GeneralSecurityException, IOException, AccessTokenException;
	public String getAccessToken() throws AccessTokenException;
	
}
