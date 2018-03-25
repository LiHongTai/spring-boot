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
@Table(name = "cust_detail_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustDetailInfo {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name = "cust_no")
	private String custNo;
	
	@Column(name = "cust_name")
	private String custName;
	
	@Column(name = "cust_age")
	private String custAge;
	
	@Column(name = "cust_sex")
	private String custSex;
}
