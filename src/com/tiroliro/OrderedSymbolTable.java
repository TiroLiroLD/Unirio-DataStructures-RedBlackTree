package com.tiroliro;

public interface OrderedSymbolTable<Key extends Comparable<Key>,Value> {
	// put: adiciona o par (key, value) � tabela
	//    - se key j� estiver na tabela, substitui o value associado
	//    - se value = null e key estiver na tabela, remove key
	public void put( Key key, Value val );

	// delete: remove a chave key da tabela, caso ela esteja presente
	default void delete( Key key ) {
		put( key, null );
	}

	// delete: remove a menor chave da tabela, caso exista alguma
	public void deleteMin();

	// TODOS os m�todos abaixos devem ser implementados sem causar modifica��es
	// na estrutura interna da tabela de s�mbolos

	// get: recupera o valor associado � chave key na tabela
	//    - se a chave n�o estiver na tabela, retorna null
	public Value get( Key key ); // recupera o valor associado

	// contains: retorna true se key est� presente na tabela, e false c.c.
	default boolean contains( Key key ) {
		return (get(key) != null);
	}

	// size: retorna a quantidade de chaves presentes na tabela
	public int size();

	// // min: retorna a menor chave existente na tabela
	// //    - caso a tabela esteja vazia, retorna null
	public Key min();

	// // max: retorna a maior chave existente na tabela
	// //    - caso a tabela esteja vazia, retorna null
	public Key max();

	// // floor (piso): retorna a maior chave que seja menor ou igual � chave ref
	// //             - caso a tabela esteja vazia, retorna null
	public Key floor( Key ref );

	// // ceil (ceiling/teto): retorna a menor chave que seja maior ou igual � chave ref
	// //                    - caso a tabela esteja vazia, retorna null
	public Key ceil( Key ref );

	// select: retorna a n-�sima menor chave presente na �rvore (contando a partir de 0, isto �: a menor chave possui �ndice 0, a segunda menor, �ndice 1, etc.)
	//         - caso n�o haja chave com �ndice n, retorna null
	public Key select( int n );

	// rank: retorna o �ndice da chave key ou, caso esta chave n�o esteja presente na �rvore, o �ndice que esta chave possuiria se fosse inserida na �rvore naquele momento
	public int rank( Key key );

	// isEmpty: retorna true se a tabela est� vazia, e false c.c.
	default boolean isEmpty() {
		return ( size() == 0 );
	}
}