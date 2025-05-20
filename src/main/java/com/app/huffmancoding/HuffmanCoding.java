package com.app.huffmancoding;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;


public class HuffmanCoding {
	/**
	 * este metodo codifica un string
	 * @param input recibe el string a codificar como un array de caracteres
	 * @return objeto Result con el bitString, el nodo raiz del arbol y la tabla de Huffman
	 */
	public Result encode(char[] input) {
		Map<Character, Integer> map = new HashMap<Character, Integer>(); // se utiliza para almacenar los caracteres y frecuencias
		Map<Character, String> table = new HashMap<Character, String>(); // tabla de huffman
		for (int i = 0; i < input.length; i++) // se recorre el array y se insertan los caracteres con sus frecuencias
			if (map.containsKey(input[i]))
				map.put(input[i], map.get(input[i]) + 1);
			else
				map.put(input[i], 1);
		Node tree = buildTree(map); // se construye el arbol
		buildTable(table, tree, ""); // se construye la tabla
		return new Result(tree, buildBitString(table, input), table);
	}

	/**
	 * Este metodo decofica un bitString utilizando la tabla de huffman
	 * @param bitString
	 * @param table tabla de huffman con cada caracter y su codigo
	 * @return mensaje decodificado
	 */
	public String decode(String bitString, Map<String, Character> table) {
		StringBuilder decodedText = new StringBuilder();
		StringBuilder prefix = new StringBuilder();
		for (int i = 0; i < bitString.length(); i++) {
			prefix.append(bitString.charAt(i)); // agregamos el bit actual al prefijo
			if (table.containsKey(prefix.toString())) {
				decodedText.append(table.get(prefix.toString())); // decodificamos el carácter
				prefix.setLength(0); // reiniciamos el prefijo
			}
		}
		return decodedText.toString();
	}

	public String decode(String bitString,Node root) {
		StringBuilder decodedMessage = new StringBuilder();
		Node current = root;
		for (int i = 0; i < bitString.length(); i++) {
			char c = bitString.charAt(i);
			if (c == '0') {
				current = current.getLeft();
			} else if (c == '1') {
				current = current.getRight();
			}
			if (current.getLeft() == null && current.getRight() == null) {
				decodedMessage.append(current.getCharacter());
				current = root;
			}
		}
		return decodedMessage.toString();
	}

	/**
	 * este metodo construye el bitString
	 * @param table la tabla de huffman generada
	 * @param input la cadena a codificar en forma de array de caracteres
	 * @return el bitString resultado de la compresion
	 */
	private String buildBitString(Map<Character, String> table, char[] input) {
		StringBuilder bitString = new StringBuilder();
		for (int i = 0; i < input.length; i++) {
			String value = table.get(input[i]);
			for (int j = 0; j < value.length(); j++)
				if (value.charAt(j) == '0')
					bitString.append(0);
				else
					bitString.append(1);
		}
		return bitString.toString();
	}

	/**
	 * este metodo construye el arbol de Huffman
	 * @param map con los caracteres y frecuencias
	 * @return nodo raiz del arbol
	 */
	private Node buildTree(Map<Character, Integer> map) {
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Iterator<Character> it = map.keySet().iterator();
		Node node = null;
		while (it.hasNext()) {
			char key = (char) it.next();
			int occurrences = (int) map.get(key);
			node = new Node(key, occurrences);
			queue.offer(node);
		}
		Node root = queue.peek();
		while (queue.size() > 1) {
			Node subTree1 = queue.poll();
			Node subTree2 = queue.poll();
			root = new Node('\0', subTree1.getFrequency() + subTree2.getFrequency(), subTree1, subTree2);
			queue.offer(root);
		}
		return root;
	}

	/**
	 * este metodo construye la tabla de Huffman, a traves de llamadas recursivas va construyendo el codigo
	 * para cada caracter
	 * @param table map vacio
	 * @param tree nodo raiz del arbol
	 * @param code codigo del caracter que se co
	 */
	private void buildTable(Map<Character, String> table, Node tree, String code) {
		if (!tree.isLeafNode()) {
			buildTable(table, tree.getLeft(), code + "0");
			buildTable(table, tree.getRight(), code + "1");
		} else if (code.isEmpty())
			table.put(tree.getCharacter(), "1");
		else
			table.put(tree.getCharacter(), code);
	}
}
