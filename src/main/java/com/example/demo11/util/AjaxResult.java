package com.example.demo11.util;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;

/**
 * 用户请求后数据返回对象
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AjaxResult implements Serializable {

	private static final long serialVersionUID = 314617866190266404L;
	/** 操作结果是否成功. false:失败;true:成功; */
	private boolean state = true;
	/** 返回结果对应的编码 */
	private String code;
	/** 操作结果 */
	private String msg;
	/** 消息产生时间 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	private Date time = Calendar.getInstance().getTime();
	/** 其他参数 */
	private Map<String, Object> parms;
	/** 请求需要返回的数据,可以为空 */
	private Object data;
	/** 装载扩展表数据 */
	private Object extend;

	/** 默认成功 */
	public AjaxResult() {
	}

	/** 操作成功 */
	public AjaxResult(String msg) {
		this.msg = msg;
	}

	/** 操作结题是否成功. 0:失败;1:成功;其它数据由相应操作自己定义 */
	public AjaxResult(boolean state, String msg) {
		this.state = state;
		this.msg = msg;
	}

	/** 操作结题是否成功. 0:失败;1:成功;其它数据由相应操作自己定义 */
	public boolean getState() {
		return state;
	}

	/** 操作结题是否成功. 0:失败;1:成功;其它数据由相应操作自己定义 */
	public void setState(boolean state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Map<String, Object> getParms() {
		return parms;
	}

	public void setParms(Map<String, Object> parms) {
		if (this.parms == null) {
			this.parms = parms;
		} else {
			this.parms.putAll(parms);
		}
	}

	public void setParms(String key, Object value) {
		if (this.parms == null) {
			this.parms = Maps.newHashMap();
		}
		this.parms.put(key, value);
	}

	public <T> void setPageParms(List<T> list) {
		if (list instanceof Page) {
			PageInfo<T> pageInfo = new PageInfo<T>(list);
			if (this.parms == null) {
				this.parms = Maps.newHashMap();
			}
			this.parms.put("pageIndex", pageInfo.getPageNum());
			this.parms.put("pageCount", pageInfo.getPages());
			this.parms.put("pageSize", pageInfo.getPageSize());
			this.parms.put("totalCount", pageInfo.getTotal());
		}
		this.data = list;
	}

//	public void setPageParms(PageVo pageVo) {
////		if (pageVo != null) {
////			if (this.parms == null) {
////				this.parms = Maps.newHashMap();
////			}
////			this.parms.put("pageIndex", pageVo.getPageIndex());
////			this.parms.put("pageCount", pageVo.getPageCount());
////			this.parms.put("pageSize", pageVo.getPageSize());
////			this.parms.put("totalCount", pageVo.getTotal());
////		}
//
//	}

	/** 装载扩展表数据 */
	public Object getExtend() {
		return extend;
	}

	/** 装载扩展表数据 */
	public void setExtend(Object extend) {
		this.extend = extend;
	}

}
