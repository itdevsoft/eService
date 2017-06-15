package com.eservice.test;

import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.eservice.core.beans.NestedCategory;
import com.eservice.hibernate.utils.HibernateUtil;
import com.eservice.utils.PayPalPayment;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Map<String,String> codeMap = PayPalPayment.ECSetExpressCheckoutCodeMap("http://localhost:8080/eShop/home", "http://localhost:8080/eShop/home", "10.50", "Sale", "AUD");
		//System.out.printf("%s = %s \n","ACK",codeMap.get("ACK"));
		//System.out.printf("%s = %s \n","TOKEN",codeMap.get("TOKEN"));
		getCategories();
	}
	
	private static void getCategories(){
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			List<NestedCategory> categories = session.createQuery("from NestedCategory").list();
			for(NestedCategory category:categories){
				System.out.println(category.getId()+"="+category.getCategory().getName());
			}
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

}
