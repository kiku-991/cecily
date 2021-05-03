package com.shoping.kiku.entity;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.shoping.kiku.until.Favorite;

import lombok.Data;

@Data
@Entity
@Table(name = "favorite")
@IdClass(value = Favorite.class)
public class FavoriteEntity {
	
	@Id
	public int userId;
	@Id
	public int productId;
	public Timestamp createdate;

}
