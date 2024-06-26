package com.mt.quarkus.model;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "campaigns")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Campaign{

	@Id
	private BigInteger id;
	
	@Column(name = "user_id")
	private BigInteger userId;
	
	private String title;
	
	@Column(name = "api_key")
	private String apiKey;
	
}
