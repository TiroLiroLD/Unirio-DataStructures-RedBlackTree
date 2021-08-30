package com.tiroliro;

class Main {
	public static void main(String[] args) {
		/*BinarySearchTree<Character,Integer> tree = new BinarySearchTree<>();

		String name = "ESTRUTURAS DE DADOS II";
		for( char c : name.toCharArray() ) {
			Integer count = tree.get(c);
			if( count == null ) {
				count = 0;
			}
			count++;
			tree.put( c, count );
		}
		String test = "ADEIORSTU ";
		for( char c : test.toCharArray() ) {
			System.out.println( c + " " + tree.get(c) );
		}*/

		RedBlackTree<Integer,Integer> treeTeste = new RedBlackTree<>();
		System.out.println("Insercao de nos:");
		treeTeste.put(15, 15);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(7, 7);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(27, 27);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(18, 18);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(25, 25);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(2, 2);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(5, 5);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(13, 13);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(11, 11);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(9, 9);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(8, 8);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		System.out.println();
		System.out.println("min = " + treeTeste.min());

		/*

		RedBlackTree<Integer,Integer> treeTeste = new RedBlackTree<>();
		System.out.println("Insercao de nos:");
		treeTeste.put(10, 10);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(7, 7);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(5, 5);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(15, 15);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(12, 12);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(16, 16);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		treeTeste.put(25, 25);
		System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());
		System.out.println();
		System.out.println("min = " + treeTeste.min());
		//treeTeste.deleteMin();
		//System.out.println(treeTeste.repr());
		treeTeste.delete(5);
		//System.out.println(treeTeste.repr());
		System.out.println(treeTeste.isBST());

		*/



		/**JUNK

		System.out.println("Select de nos:");
		//System.out.println(treeTeste.select(-1));
		System.out.println(treeTeste.select(0));
		System.out.println(treeTeste.select(1));
		System.out.println(treeTeste.select(2));
		System.out.println(treeTeste.select(3));
		System.out.println(treeTeste.select(4));
		System.out.println(treeTeste.select(5));
		System.out.println(treeTeste.select(6));

		System.out.println("Rank de nos:");
		System.out.println(treeTeste.rank(10));
		System.out.println(treeTeste.rank(7));
		System.out.println(treeTeste.rank(5));
		System.out.println(treeTeste.rank(15));
		System.out.println(treeTeste.rank(12));
		System.out.println(treeTeste.rank(16));
		System.out.println(treeTeste.rank(25));
		System.out.println();*/
	}
}