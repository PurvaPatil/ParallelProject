package com.capgemini.paytm.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.paytm.beans.Customer;
import com.capgemini.paytm.beans.Transaction;
import com.capgemini.paytm.service.WalletService;

@Controller
public class CustomerActionController {
	@Autowired
	WalletService walletService;

	@RequestMapping(value = "/loginSave")
	public ModelAndView getLogin(@RequestParam("mobno") String mobileno) {
		Customer customer = null;
		String error="Mobile no doesn't exists";
		customer = walletService.showBalance(mobileno);
		if (customer == null)
			return new ModelAndView("login","error",error);
		else
			return new ModelAndView("menu", "mobno", mobileno);
	}

	@RequestMapping(value = "/registerCustomer")
	public ModelAndView registerCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {

		try {
			if (result.hasErrors())
				return new ModelAndView("registration");
			customer = walletService.createAccount(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("errors");
		}
		return new ModelAndView("registrationSuccess", "customer", customer);
	}

	@RequestMapping(value = "/show")
	public ModelAndView showBalance(@RequestParam("mobileNo") String mobileno) {
		Customer customer = null;

		try {
			customer = walletService.showBalance(mobileno);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ModelAndView("viewBalance", "customer", customer);
	}

	@RequestMapping(value = "/depos")
	public ModelAndView deposit(@RequestParam("mobileNo") String mobileno,
			@RequestParam("wallet.balance") BigDecimal amount) {
		Customer customer = null;

		try {

			customer = walletService.depositAmount(mobileno, amount);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ModelAndView("viewBalance", "customer", customer);
	}

	@RequestMapping(value = "/with")
	public ModelAndView withdraw(@RequestParam("mobileNo") String mobileno,
			@RequestParam("wallet.balance") BigDecimal amount) {
		Customer customer = null;

		try {

			customer = walletService.withdrawAmount(mobileno, amount);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ModelAndView("viewBalance", "customer", customer);
	}

	@RequestMapping(value = "/ft")
	public ModelAndView fundTransfer(@RequestParam("mobileNo1") String mobileno1,
			@RequestParam("mobileNo2") String mobileno2, @RequestParam("wallet.balance") BigDecimal amount) {
		Customer customer = null;

		try {

			customer = walletService.fundTransfer(mobileno1, mobileno2, amount);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return new ModelAndView("viewBalance", "customer", customer);
	}

	@RequestMapping(value = "/print")
	public ModelAndView printTransaction(@RequestParam("mobileNo") String mobileno) {
		List<Transaction> transaction = null;

		transaction = walletService.printTransaction(mobileno);

		ModelAndView modelAndView = new ModelAndView("viewtransaction");
		modelAndView.addObject("transaction", transaction);
		System.out.println(transaction);

		return modelAndView;
		// return new ModelAndView("viewtransaction","transaction",transaction);
	}

}
