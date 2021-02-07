package com.github.camque.mutants.dto;

import java.io.Serializable;

public class Node implements Serializable {

	private static final long serialVersionUID = 6688504144134066256L;

	private int i;
	private int j;

	public Node(int i, int j) {
		super();
		this.i = i;
		this.j = j;
	}

	public int getI() {
		return this.i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return this.j;
	}

	public void setJ(int j) {
		this.j = j;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Node other = (Node) obj;
		if (this.i != other.i) {
			return false;
		}
		if (this.j != other.j) {
			return false;
		}
		return true;
	}

}
