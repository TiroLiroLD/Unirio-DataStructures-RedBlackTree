public interface OrderedSymbolTable<Key extends Comparable<Key>,Value> {
  // put: adiciona o par (key, value) à tabela
  //    - se key já estiver na tabela, substitui o value associado
  //    - se value = null e key estiver na tabela, remove key
  public void put( Key key, Value val );

  // delete: remove a chave key da tabela, caso ela esteja presente
  default void delete( Key key ) {
    put( key, null );
  }

  // delete: remove a menor chave da tabela, caso exista alguma
  public void deleteMin();

  // TODOS os métodos abaixos devem ser implementados sem causar modificações
  // na estrutura interna da tabela de símbolos

  // get: recupera o valor associado à chave key na tabela
  //    - se a chave não estiver na tabela, retorna null
  public Value get( Key key ); // recupera o valor associado

  // contains: retorna true se key está presente na tabela, e false c.c.
  default boolean contains( Key key ) {
    return (get(key) != null);
  }

  // size: retorna a quantidade de chaves presentes na tabela
  public int size();

  // // min: retorna a menor chave existente na tabela
  // //    - caso a tabela esteja vazia, retorna null
  // public Key min();

  // // max: retorna a maior chave existente na tabela
  // //    - caso a tabela esteja vazia, retorna null
  // public Key max();

  // // floor (piso): retorna a maior chave que seja menor ou igual à chave ref
  // //             - caso a tabela esteja vazia, retorna null
  // public Key floor( Key ref );

  // // ceil (ceiling/teto): retorna a menor chave que seja maior ou igual à chave ref
  // //                    - caso a tabela esteja vazia, retorna null
  // public Key ceil( Key ref );

  // select: retorna a n-ésima menor chave presente na árvore (contando a partir de 0, isto é: a menor chave possui índice 0, a segunda menor, índice 1, etc.)
  //         - caso não haja chave com índice n, retorna null
  public Key select( int n );
  
  // rank: retorna o índice da chave key ou, caso esta chave não esteja presente na árvore, o índice que esta chave possuiria se fosse inserida na árvore naquele momento
  public int rank( Key key );

  // isEmpty: retorna true se a tabela está vazia, e false c.c.
  default boolean isEmpty() {
    return ( size() == 0 );
  }
}