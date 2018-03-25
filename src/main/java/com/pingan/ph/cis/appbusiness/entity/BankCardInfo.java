package com.pingan.ph.cis.appbusiness.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bank_card_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankCardInfo {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name = "bank_card_no")
	private String bankCardNo;
	
	@NotNull
	@Column(name = "bank_id")
	private String bankCardName;
	
	@NotNull
	@Column(name = "account_no")
	private String accountNo;
}
