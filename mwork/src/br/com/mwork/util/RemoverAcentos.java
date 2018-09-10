package br.com.mwork.util;

import java.text.Normalizer;

public class RemoverAcentos {

	public static String value = "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞßàáâãäåæçèéêëìíîïðñòóôõöøùúûüýþÿ";
	
	/**
	 * 
	 */
	public RemoverAcentos() {
		System.err.println(value);
		System.err.println(remover(value));
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String remover(String str) {
		str = Normalizer.normalize(str, Normalizer.Form.NFD);
		str = str.replaceAll("[^\\p{ASCII}]", "");
		return str;
	}

	public static void main(String[] args) {
		//new RemoverAcentos();
	}
}
