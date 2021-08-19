class RedBlackTree<Key extends Comparable<Key>,Value> implements OrderedSymbolTable<Key,Value> {
  private class Node {
    public Key key;
    public Value val;
    public boolean color; // cor do enlace vindo do pai
    public Node leftSubtree;
    public Node rightSubtree;
    // TODO: TRABALHO 2
    public int height;
    public int balance;

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public Node( Key key, Value val, boolean color ) {
      this.key = key;
      this.val = val;
      this.color = color;
    }
  }
  private Node root;

  private Node rotateLeft( Node node ) {
    Node newRoot = node.rightSubtree;
    // REARRANJO DE REFERÊNCIAS
    node.rightSubtree = newRoot.leftSubtree;
    newRoot.leftSubtree = node;

    // TROCA (SWAP) DE CORES
    boolean newRootColor = node.color;
    node.color = newRoot.color;
    newRoot.color = newRootColor;

    return newRoot;
  }

  private Node rotateRight( Node node ) {
    Node newRoot = node.leftSubtree;
    // REARRANJO DE REFERÊNCIAS
    node.leftSubtree = newRoot.rightSubtree;
    newRoot.rightSubtree = node;

    // TROCA (SWAP) DE CORES
    boolean newRootColor = node.color;
    node.color = newRoot.color;
    newRoot.color = newRootColor;

    return newRoot;
  }

  private void flipColors( Node node ) {
    node.color = !node.color;
    if( node.leftSubtree != null)
      node.leftSubtree.color = !node.leftSubtree.color;
    if( node.rightSubtree != null )
      node.rightSubtree.color = !node.rightSubtree.color;
  }

  private Node put( Key key, Value val, Node node ) {
    // NA DESCIDA (antes da chamada recursiva): procura o lcoal adequado e insere um novo nó
    if( node == null ) // CASO BASE da recursão
      return new Node( key, val, Node.RED );
    if( key.compareTo(node.key) < 0 )
      node.leftSubtree = put( key, val, node.leftSubtree );
    else if( key.compareTo(node.key) > 0 )
      node.rightSubtree = put( key, val, node.rightSubtree );
    else // chave já existente
      node.val = val;

    // NA SUBIDA (depois da chamada recursiva): rebalanceamento
    // Convenção: todo enlace nulo é automaticamente negro

    // CASO I: operação única
    // CASO II.between: 1ª operação (no nível inferior da árvore)
    if( ( node.leftSubtree == null || node.leftSubtree.color == Node.BLACK ) &&
          node.rightSubtree != null && node.rightSubtree.color == Node.RED )
      node = rotateLeft( node );

    // CASO II.smaller: 1ª operação
    // CASO II.between: 2ª operação
    if( node.leftSubtree != null &&
        node.leftSubtree.color == Node.RED &&
        node.leftSubtree.leftSubtree != null &&
        node.leftSubtree.leftSubtree.color == Node.RED )
      node = rotateRight( node );

    // CASO II.larger: operação única
    // CASO II.smaller: 2ª operação
    // CASO II.between: 3ª operação
    if( node.leftSubtree != null && node.leftSubtree.color == Node.RED &&
        node.rightSubtree != null && node.rightSubtree.color == Node.RED )
      flipColors( node );

    return node;
  }

  public void put( Key key, Value val ) {
    root = put( key, val, root );
    root.color = Node.BLACK;
  }

  public boolean isRedBlackTree() {
    // TODO: TRABALHO 2
  }

  public boolean isAVL() {
    // TODO: TRABALHO 2
  }
}