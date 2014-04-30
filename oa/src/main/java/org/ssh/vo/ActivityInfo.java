package org.ssh.vo;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.history.HistoricTaskInstance;

public class ActivityInfo {
	/**
	 * 任务信息
	 */
	private HistoricTaskInstance task;
	/**
	 * 坐标信息
	 */
	private double x;
	private double y;
	private double width;
	private double height;
	/**
	 * 回退的任务信息
	 */
	private List<HistoricTaskInstance> withdrawTasks;
	/**
	 * 状态 current/complete/withdraw
	 */
	private String state;

	public HistoricTaskInstance getTask() {
		return task;
	}

	public void setTask(HistoricTaskInstance task) {
		this.task = task;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public List<HistoricTaskInstance> getWithdrawTasks() {
		if (this.withdrawTasks == null) {
			return new ArrayList<HistoricTaskInstance>();
		}
		return withdrawTasks;
	}

	public void setWithdrawTasks(List<HistoricTaskInstance> withdrawTasks) {
		this.withdrawTasks = withdrawTasks;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
