package com.app.huffmancoding;

public class Node implements Comparable<Node> {
	 private char character;           
     private int frequency;       
     private Node left;
     private Node right;
	
    public boolean isLeafNode(){
    	return this.left ==null && this.right == null;
    }

	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	public Node(char character, int frequency, Node left, Node right) {
		super();
		this.character = character;
		this.frequency = frequency;
		this.left = left;
		this.right = right;
	}
	
	public Node(char character, int frequency) {
		super();
		this.character = character;
		this.frequency = frequency;
	}

	public char getCharacter() {
		return character;
	}

	public void setCharacter(char character) {
		this.character = character;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	@Override
	public int compareTo(Node arg0) {
		return this.frequency - arg0.getFrequency();
	}
    
    
}
