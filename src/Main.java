class Main {
  public static void main(String[] args) {
    BinarySearchTree<Character,Integer> tree
        = new BinarySearchTree<>();
    
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
    }
  }
}