package com.reffians.c2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.EnumUtils;

import lombok.Getter;

@Entity
@Table(name="users") //double check what adam named the table for real 
public class User {
	@Id //denotes primary key
	@JsonProperty("username")
	private String username;

	@Getter //do we need this? unsure about what @Getter does
	@JsonProperty("password")
	private String password;
}
