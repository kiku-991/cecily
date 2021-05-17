package com.shoping.kiku.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class PwdReSetEntity {
	
	@Id
	private String userMail;
	private String name;
	

}
