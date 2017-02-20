package com.ruiqin.materialdesign.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast 吐司
 */
public class ToastUtils {

	private static Toast toast;

	/**
	 * String类型的
	 * @param mContext
	 * @param message
     */
	public static void show(Context mContext, String message) {

		if ( null == toast) {
			toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(message);
		}
		toast.show();
	}

	/**
	 * boolean类型
	 * @param mContext
	 * @param message
     */
	public static void show(Context mContext, boolean message) {

		if ( null == toast) {
			toast = Toast.makeText(mContext, message+"", Toast.LENGTH_SHORT);
		} else {
			toast.setText(message +"");
		}
		toast.show();
	}


}
