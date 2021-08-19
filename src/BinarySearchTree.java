class BinarySearchTree<Key extends Comparable<Key>,Value> implements OrderedSymbolTable<Key,Value> {
  private class Node {
    public Key key;
    public Value val;
    public int size; // tamanho da subárvore enraizada em Node
    public Node leftSubtree;
    public Node rightSubtree;
    Node( Key key, Value val ) {
      this.key = key;
      this.val = val;
      this.size = 1;
    }
  }
  private Node root;

  BinarySearchTree() {
    root = null;
  }

  public void put( Key key, Value val ) {
    if( val == null )
      delete( key );
    else {
      root = put( key, val, root );
      updateSize( key, root );
    }
  }

  public Node put( Key key, Value val, Node node ) {
    if( node == null ) {
      return new Node( key, val );
    }
    if( key.compareTo( node.key ) == 0 )
      node.val = val;
    else if( key.compareTo( node.key ) < 0 )
      node.leftSubtree = put( key, val, node.leftSubtree );
    else
      node.rightSubtree = put( key, val, node.rightSubtree );
    return node;
  }

  public void updateSize( Key key, Node node ) {
    if( key.compareTo( node.key ) < 0 ) {
      updateSize( key, node.leftSubtree );
    }
    else if( key.compareTo( node.key ) > 0 ) {
      updateSize( key, node.rightSubtree );
    }
    node.size = (node.leftSubtree == null ? 0 : node.leftSubtree.size)
              + (node.rightSubtree == null ? 0 : node.rightSubtree.size)
              + 1;
  }

  public int size() {
    if( root == null ) return 0;
                  else return root.size;
  }

  public Value get( Key key ) {
    return get( key, root );
  }

  public Value get( Key key, Node node ) {
    if( node == null )
      return null;

    if( key.compareTo( node.key ) == 0 )
      return node.val;
    else if( key.compareTo( node.key ) < 0 )
      return get( key, node.leftSubtree );
    else
      return get( key, node.rightSubtree );
  }
}