package com.sda.auction.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "bid")
@Data
@EqualsAndHashCode(exclude = {"user", "item"})
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bid_id")
	private int id;

	@Column
	private int value;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "item_id")
	private Item item;
}
