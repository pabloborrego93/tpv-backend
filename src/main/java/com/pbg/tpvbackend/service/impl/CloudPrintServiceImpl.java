package com.pbg.tpvbackend.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.UrlEncodedContent;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.auth.oauth2.GoogleCredentials;
import com.pbg.tpvbackend.exception.gcp.AccessTokenException;
import com.pbg.tpvbackend.exception.gcp.SubmitJobException;
import com.pbg.tpvbackend.service.CloudPrintService;
import com.pbg.tpvbackend.utils.GoogleCloudUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CloudPrintServiceImpl implements CloudPrintService {

	private static final String URL_SUBMIT = "https://www.google.com/cloudprint/submit";
	private static final String URL_PROCESS_INVITE = "https://www.google.com/cloudprint/processinvite";
	private static final String URL_SCOPE_GOOGLE_CLOUD_PRINT = "https://www.googleapis.com/auth/cloudprint";

	public Boolean submitJob(String printerid, String title, InputStream content, Integer retries)
			throws IOException, AccessTokenException, GeneralSecurityException, SubmitJobException {

		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

		Map<String, Object> params = new LinkedHashMap<>();
		params.put("printerid", printerid);
		params.put("title", title);
		params.put("ticket", "{\"version\":\"1.0\", \"print\":{}}");
	    params.put("contentTransferEncoding", "base64");
	    params.put("contentType", "application/pdf");
	    byte[] byteArrayContent = GoogleCloudUtils.toByteArray(content);
	    String base64Content = Base64.getEncoder().encodeToString(byteArrayContent);
		params.put("content", base64Content);

		HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
		HttpRequest httpRequest = requestFactory.buildPostRequest(
			new GenericUrl(URL_SUBMIT),
			new UrlEncodedContent(params));

		httpRequest.getHeaders().set("Authorization", Arrays.asList(("Bearer " + this.getAccessToken()).split(",")));
		
		httpRequest.setParser(new JsonObjectParser(new JacksonFactory()));
		GenericJson response = httpRequest.execute().parseAs(GenericJson.class);

		Boolean success = Boolean.parseBoolean(response.get("success").toString());

		if (success) {
			return success;
		} else if (response.get("errorCode").toString().equals("8") && retries > 0) {
			--retries;
			this.processInvite(printerid);
			success = this.submitJob(printerid, title, content, retries);
		}

		return success;
	}

	@Override
	public Boolean processInvite(String printerid) throws GeneralSecurityException, IOException, AccessTokenException {
		HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();

		Map<String, String> params = new LinkedHashMap<>();
		params.put("printerid", printerid);
		params.put("accept", "true");

		HttpRequestFactory requestFactory = httpTransport.createRequestFactory();
		HttpRequest httpRequest = requestFactory.buildPostRequest(new GenericUrl(URL_PROCESS_INVITE),
				new UrlEncodedContent(params));

		httpRequest.getHeaders().set("Authorization", Arrays.asList(("Bearer " + this.getAccessToken()).split(",")));

		httpRequest.setParser(new JsonObjectParser(new JacksonFactory()));
		GenericJson response = httpRequest.execute().parseAs(GenericJson.class);
		return Boolean.parseBoolean(response.get("success").toString());
	}

	@Override
	public String getAccessToken() throws AccessTokenException {
		try {
			GoogleCredentials googleCredentials = GoogleCredentials.getApplicationDefault();
			googleCredentials = googleCredentials.createScoped(Collections.singletonList(URL_SCOPE_GOOGLE_CLOUD_PRINT));
			if (googleCredentials.getAccessToken() == null) {
				googleCredentials.refresh();
			}
			googleCredentials.refreshIfExpired();
			return googleCredentials.getAccessToken().getTokenValue();
		} catch (IOException e) {
			throw new AccessTokenException();
		}
	}

}
