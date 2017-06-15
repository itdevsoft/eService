package com.eservice.utils;
import java.util.HashMap;
import java.util.Map;

import com.paypal.sdk.core.nvp.NVPDecoder;
import com.paypal.sdk.core.nvp.NVPEncoder;
import com.paypal.sdk.exceptions.PayPalException;
import com.paypal.sdk.profiles.APIProfile;
import com.paypal.sdk.profiles.ProfileFactory;
import com.paypal.sdk.services.NVPCallerServices;
public class PayPalPayment {

	private static final String API_USERNAME = "eshop_1332911551_biz_api1.yahoo.com";
	private static final String API_PASSWORD = "1332911572";
	private static final String API_SIGNATURE = "AQuC3M5jX2di13bDxAQ7EhtdzbjAAim2BvoQitBESHjKLcoJXFAlBLYL";
	private static final String API_ENVIRONMENT = "sandbox";
	private static final String API_VERSION = "51.0";
	private static final String API_METHOD = "SetExpressCheckout";

	
	public static Map<String,String> ECSetExpressCheckoutCodeMap(String returnURL, String cancelURL,String amount, String paymentType, String currencyCode) {

		NVPEncoder encoder = new NVPEncoder();
		NVPDecoder decoder = new NVPDecoder();
		Map<String,String> codeMap = new HashMap<String, String>();

		try {
			NVPCallerServices caller = new NVPCallerServices();
			APIProfile profile = initializeProfile();
			
			encoder.add("VERSION", API_VERSION);
			encoder.add("METHOD", API_METHOD);
			caller.setAPIProfile(profile);
			// Add request-specific fields to the request string.
			encoder.add("RETURNURL", returnURL);
			encoder.add("CANCELURL", cancelURL);
			encoder.add("AMT", amount);
			encoder.add("PAYMENTACTION", paymentType);
			encoder.add("CURRENCYCODE", currencyCode);

			// Execute the API operation and obtain the response.
			String NVPRequest = encoder.encode();
			String NVPResponse = caller.call(NVPRequest);
			decoder.decode(NVPResponse);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		codeMap.put("ACK", decoder.get("ACK"));
		codeMap.put("TOKEN", decoder.get("TOKEN"));
		return codeMap;
	}


	private static APIProfile initializeProfile() throws PayPalException {
		APIProfile profile = ProfileFactory.createSignatureAPIProfile();
		/*
		 * WARNING: Do not embed plaintext credentials in your application
		 * code. Doing so is insecure and against best practices. Your API
		 * credentials must be handled securely. Please consider encrypting
		 * them for use in any production environment, and ensure that only
		 * authorized individuals may view or modify them.
		 */

		// Set up your API credentials, PayPal end point, API operation and
		// version.
		profile.setAPIUsername(API_USERNAME);
		profile.setAPIPassword(API_PASSWORD);
		profile.setSignature(API_SIGNATURE);
		profile.setEnvironment(API_ENVIRONMENT);
		profile.setSubject("");
		
		return profile;
		
	}

}
