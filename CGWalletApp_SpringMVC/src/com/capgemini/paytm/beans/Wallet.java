package com.capgemini.paytm.beans;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

@Entity
@Table(name="walletspring")
public class Wallet {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int walletid;
	
	//@Check(constraints = "available_count > 0")
	private BigDecimal balance;
	

	public int getWalletid() {
		return walletid;
	}

	public void setWalletid(int walletid) {
		this.walletid = walletid;
	}

	
	
	public Wallet(BigDecimal balance) {
		super();
		this.balance = balance;
	}

	public Wallet() {
		super();
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	


	@Override
	public String toString() {
		return  balance + "\n";
	}
	
}
