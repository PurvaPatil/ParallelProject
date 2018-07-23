package com.capgemini.paytm.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Transaction;
import com.capgemini.paytm.beans.Wallet;
import com.capgemini.paytm.exception.InsufficientBalanceException;
import com.capgemini.paytm.exception.InvalidInputException;
import com.capgemini.paytm.repo.WalletRepo;
import com.capgemini.paytm.repo.WalletRepo2;

@Component(value="walletService")
public class WalletServiceImpl implements WalletService {

	@Autowired
	WalletRepo repo;
	
	@Autowired
	WalletRepo2 repo2;
	
	public Customer createAccount(Customer customer) {
				
		return repo.save(customer);
		}

	public Customer showBalance(String mobileNo) {
		
		Customer customer=repo.findOne(mobileNo);		
		if(customer!=null)
			return customer;
		else
			throw new InvalidInputException("Invalid mobile no ");
	}

	public Customer fundTransfer(String sourceMobileNo, String targetMobileNo, BigDecimal amount) {	
		

		if(sourceMobileNo.equals(targetMobileNo))
		{
			throw new InvalidInputException("Source and target mobile numbers cannot be same");
		}		
		Customer scust=repo.findOne(sourceMobileNo);
		Customer tcust=repo.findOne(targetMobileNo);	
		Wallet sw=scust.getWallet();
		Wallet tw=tcust.getWallet();
		
		Transaction strans=new Transaction();
		Transaction ttrans=new Transaction();		
	
		strans.setMobileNo(sourceMobileNo);
		ttrans.setMobileNo(targetMobileNo);
		strans.setTransaction_amount(amount.floatValue());
		ttrans.setTransaction_amount(amount.floatValue());
		strans.setTransactionDate(new Date().toString());
		ttrans.setTransactionDate(new Date().toString());
		strans.setTransaction_type("Fund Transfer");
		ttrans.setTransaction_type("Fund Transfer");
		
		
		if(scust!=null && tcust!=null )
		{	
			if(scust.getWallet().getBalance().compareTo(amount)==1)
			{
			BigDecimal amtSub=scust.getWallet().getBalance();
			BigDecimal diff=amtSub.subtract(amount);
			sw.setBalance(diff);
			scust.setWallet(sw);
			
			BigDecimal amtAdd=tcust.getWallet().getBalance();
			BigDecimal sum=amtAdd.add(amount);			
			tw.setBalance(sum);
			tcust.setWallet(tw);
			
		
			strans=new Transaction(sourceMobileNo, new Date().toString(), "Fundtransfer", amount.floatValue(), "Success");
			ttrans=new Transaction(targetMobileNo, new Date().toString(), "Fundtransfer", amount.floatValue(), "Success");
			
			tcust.setMobileNo(targetMobileNo);
			repo.save( tcust);
			scust.setMobileNo(sourceMobileNo);
			repo.save( scust);
			
			}
			else
				{
					strans=new Transaction(sourceMobileNo, new Date().toString(), "Fundtransfer", amount.floatValue(), "Failure");
					ttrans=new Transaction(targetMobileNo, new Date().toString(), "Fundtransfer", amount.floatValue(), "Failure");
					throw new InsufficientBalanceException("Amount is more than available balance");				
				}
		}
		else
		{
			throw new InvalidInputException("Account does not exists");
		}		
		repo2.save(strans);		
		repo2.save(ttrans);		
		return tcust;
	}

	public Customer depositAmount(String mobileNo, BigDecimal amount) {
		
		Customer cust=new Customer();
		cust=repo.findOne(mobileNo);
		Wallet wallet=cust.getWallet();
		Transaction strans;		
		
		if(cust!=null)
		{	
			BigDecimal amtAdd=cust.getWallet().getBalance().add(amount);
			wallet.setBalance(amtAdd);
			cust.setWallet(wallet);
			strans=new Transaction(mobileNo, new Date().toString(), "Deposit", amount.floatValue(), "Success");			
			
			repo2.save(strans);
			cust.setMobileNo(mobileNo);
			repo.save(cust);			
		}		
		return cust;
		
	}

	public Customer withdrawAmount(String mobileNo, BigDecimal amount) {		
		
		Customer cust=new Customer();
		cust=repo.findOne(mobileNo);
		Wallet wallet=cust.getWallet();
		
		Transaction strans=null;		
		if(cust!=null)
		{
			if(cust.getWallet().getBalance().compareTo(amount)==1)
			{
			BigDecimal amtSub=cust.getWallet().getBalance().subtract(amount);
			wallet.setBalance(amtSub);
			cust.setWallet(wallet);
			cust.setMobileNo(mobileNo);
			repo.save( cust);
			strans=new Transaction(mobileNo, new Date().toString(), "Withdraw", amount.floatValue(), "Success");
			repo2.save(strans);
			}
			else
			{
				strans=new Transaction(mobileNo, new Date().toString(), "Withdraw", amount.floatValue(), "Failure");
				repo2.save(strans);
				throw new InsufficientBalanceException("Sorry cannot withdraw,amount to be withdrawn is more than available balance");
			}
		}		
		return cust;
	}
	
	
	public List<Transaction> printTransaction(String mobileNo)
	{
		return repo2.findByMobileNo(mobileNo);
	}
	
}
