package com.jf.haozi.appauthorize.common.control;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.jf.haozi.appauthorize.R;

/**
 * 类名：MProgressDialog
 * @author yinhao
 * @功能 公用的进度等待页面
 * @创建日期 2015年10月8日 上午9:55:43
 * @备注 [修改者，修改日期，修改内容]
 */
public class MProgressDialog extends ProgressDialog {

	private TextView tvwMsg;
	private String msg;
	
	public MProgressDialog(Context context) {
		super(context, R.style.appauthorize_dialog);
	}
	
	public MProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public MProgressDialog(Context context, String msg) {
		this(context);
		this.msg = msg;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.appauthorize_progressdialog);
		setCanceledOnTouchOutside(false);
		setCancelable(true);
		tvwMsg = (TextView) findViewById(R.id.tvw_pbar);
		if(TextUtils.isEmpty(msg)==false){
			setMessage(msg);
		}
	}
	
	/**
	 * 设置显示内容
	 * @param msg
	 * */
	public void setMessage(String msg){
		this.msg = msg;
		if(tvwMsg != null) {
			tvwMsg.setText(msg);
		}
	}

	/**
	 * 设置显示内容
	 * @param msg
	 * */
	public void setMessage(CharSequence msg){
		this.msg = msg.toString();
		if(tvwMsg != null) {
			tvwMsg.setText(msg);
		}
		super.setMessage(msg);
	}
	
	/**
	 * 显示
	 * */
	public static MProgressDialog show(Context context, String msg) {
		MProgressDialog progress = new MProgressDialog(context,msg);
		progress.show();
		return progress;
	}
	
	/**
	 * 显示
	 * */
	@Override
	public void show() {
		super.show();
	}
	
	/**
	 * 隐藏
	 * */
	@Override
	public void dismiss() {
		super.dismiss();
	}

}
