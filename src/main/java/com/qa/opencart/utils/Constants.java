package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String ACC_PAGE_TITLE = "My Account";
	public static final int ACC_PAGE_SECTIONS_COUNT = 5;
	public static final int PRODUCT_IMAGES_COUNT_MACBOOK = 4;
	public static final String ACCOUNT_CREATION_SUCCESS_MESSAGE = "Your Account Has Been Created!";

//	**************/Sheet names/*********************

	public static final String REGISTER_SHEET_NAME = "register";

	public static List<String> expectedAccSecList() {
		List<String> expEcList = new ArrayList<String>();
		expEcList.add("My Account");
		expEcList.add("My Orders");
		expEcList.add("My Affiliate Account");
		expEcList.add("Newsletter");

		return expEcList;

	}
}
