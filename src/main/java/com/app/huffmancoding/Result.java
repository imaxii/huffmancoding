
package com.app.huffmancoding;

import java.util.Map;

public class Result {
	private Node tree;
	private String bitString;
	private Map<Character, String> table;

	public Result(Node tree, String bitString, Map<Character, String> table) {
		super();
		this.tree = tree;
		this.bitString = bitString;
		this.table = table;
	}
	public Node getTree() {
		return tree;
	}
	public void setTree(Node tree) {
		this.tree = tree;
	}
	public String getBitString() {
		return bitString;
	}
	public void setBitString(String bitString) {
		this.bitString = bitString;
	}
	public Map<Character, String> getTable() {
		return table;
	}
	public void setTable(Map<Character, String> table) {
		this.table = table;
	}
}
