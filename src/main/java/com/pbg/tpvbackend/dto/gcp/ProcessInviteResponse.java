package com.pbg.tpvbackend.dto.gcp;

import java.io.Serializable;

import com.google.api.client.util.Key;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class ProcessInviteResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5094978636931282025L;
	
	@Key
	@Getter @Setter private Boolean success;
	
}
