package com.sda.auction.model;

import java.sql.Blob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "item")
@Data
@EqualsAndHashCode(exclude = "user")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "item_id")
	private int id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private int startingPrice;
	@Column
	private Date startDate;
	@Column
	private Date endDate;

	@Column(name = "image")
	private Blob image;

	@ToString.Exclude
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "user_id")
	private User user;

	@Column
	private String category;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
	private Set<Bid> bids = new HashSet<>();

	public int currentPrice() {
		if (bids.isEmpty()) {
			return startingPrice;
		}
		int maxBid = startingPrice;
		for (Bid bid : bids) {
			if (bid.getValue() > maxBid) {
				maxBid = bid.getValue();
			}
		}
		return maxBid;
	}

	public int getHighestBidValueFor(String userEmail) {
		int result = 0;
		for (Bid bid : bids) {
			User user = bid.getUser();
			if (user.getEmail().compareTo(userEmail) == 0) {
				//bidul e al userului dat ca parametru
				result = Math.max(result, bid.getValue());
			}
		}
		return result;
	}

	public String getUserName() {
		return user.getFirstName() + " " + user.getLastName();
	}
}
