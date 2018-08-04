package com.pbg.tpvbackend.architecture.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@Configuration
@NoArgsConstructor
@ConfigurationProperties(prefix = "application")
public class AppProperties {

	public Pagination pagination = new Pagination();
	public Jwt jwt = new Jwt();
	public String signUpUrl;

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	public Jwt getJwt() {
		return jwt;
	}

	public void setJwt(Jwt jwt) {
		this.jwt = jwt;
	}

	public String getSignUpUrl() {
		return signUpUrl;
	}

	public void setSignUpUrl(String signUpUrl) {
		this.signUpUrl = signUpUrl;
	}

	/*
	 * Pagination Config
	 */
	public static class Pagination {
		private Integer maxElementsPerPage;
		private Integer minElementsPerPage;
		public Integer getMaxElementsPerPage() {
			return maxElementsPerPage;
		}
		public void setMaxElementsPerPage(Integer maxElementsPerPage) {
			this.maxElementsPerPage = maxElementsPerPage;
		}
		public Integer getMinElementsPerPage() {
			return minElementsPerPage;
		}
		public void setMinElementsPerPage(Integer minElementsPerPage) {
			this.minElementsPerPage = minElementsPerPage;
		}
	}
	
	/*
	 * Jwt Config
	 */
	@NoArgsConstructor
	public static class Jwt {
		private String secret;
		private Integer expirationTime;
		public String getSecret() {
			return secret;
		}
		public void setSecret(String secret) {
			this.secret = secret;
		}
		public Integer getExpirationTime() {
			return expirationTime;
		}
		public void setExpirationTime(Integer expirationTime) {
			this.expirationTime = expirationTime;
		}
	}
	
}
