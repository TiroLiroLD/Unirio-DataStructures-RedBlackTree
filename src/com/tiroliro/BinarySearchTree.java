package com.tiroliro;

class BinarySearchTree<Key extends Comparable<Key>,Value> implements OrderedSymbolTable<Key,Value> {
	private class Node {
		public Key key;
		public Value val;
		public int size; // tamanho da sub�rvore enraizada em Node
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

	// ############# TRABALHO 1 - MATHEUS LUCENA #############

	/* Metodo privado searchMin, que eh chamado do metodo publico min 
	 * e utiliza o parametro node para procurar o no minimo da arvore recursivamente */
	private Node searchMin(Node node) {
		if (node.leftSubtree == null) { // se nao ha no menor (a esquerda de node), entao retorna o proprio
			return node;
		} else {
			return searchMin(node.leftSubtree); // senao, percorre a arvore ate o ultimo no a esquerda recursivamente
		}
	}

	/* Metodo auxiliar, usado para recuperar a menor chave que existir na arvore
	 * utilizado posteriormente para achar o nodeCeil no metodo searchDelete */
	public Key min() {
		if (this.root == null) { // se a raiz eh nula, a arvore esta vazia, portanto retorna null, ja que nao ha no
			return null;
		} else {
			Node nodeMin = searchMin(this.root); // realiza a chamada do metodo privado a partir do Node raiz
			return nodeMin.key; // retorna a chave do no em questao (chave do menor no da arvore)
		}
	}

	// Metodo privado searchMax, que eh chamado do metodo publico max e atua recursivamente
	private Node searchMax(Node node) {
		if (node.rightSubtree == null) { // Verifica se a referencia para o Node a direita eh igual a null
			return node; // Se sim, retorna o Node, (ja que nao tera Nodes mais a direita/maiores)
		} else { // Se nao = existe Node a direita, logo h� Nodes maiores
			return searchMax(node.rightSubtree); // Executa a chamada recursiva tomando como parametro o Node a direita
		}
	}

	// Metodo implementado da interface, usado para recuperar a maior chave que existir na arvore
	public Key max() {
		if (this.root == null) { // Verifica se a arvore esta vazia, ou seja, raiz da arvore = null
			return null; // Se sim, retorna chave null
		} else { // Se nao = Arvore tem raiz
			Node nodeMax = searchMax(this.root); // Realiza a chamada do metodo privado a partir do Node raiz
			// E guarda o resultado na variavel nodeMax do tipo Node
			return nodeMax.key; // Entao retorna-se a chave associada a este Node achado
		}
	}

	// Metodo privado searchFloor, que eh chamado do metodo publico floor e atua recursivamente
	private Node searchFloor(Node node, Key key) {
		if (node.key == key) { // Checa se a chave associada ao Node eh igual a chave fornecida como parametro
			return node; // Se sim, achou o Node!
		} else if (node.key.compareTo(key) < 0) { // Checa se a chave associada ao Node eh menor que a chave fornecida como parametro
			if (node.rightSubtree == null) { // Se sim e a referencia ao Node direito nao existir
				return node; // Retorna o Node (ja que eh maior e nao tera mais onde buscar o Node com a chave igual a fornecida)
			} else { // Se nao = a referencia ao Node direito existir
				Node aux = searchFloor(node.rightSubtree, key);  // Executa a chamada recursiva tomando como parametro o Node a direita
				if (aux == null) { // Checa se o resultado aplicado na variavel auxiliar eh nulo
					return node; // Se sim, retorna o node pai do auxiliar/do da direita
				} else { // Se nao = a variavel auxiliar nao eh nula
					return aux; // Entao retorna o Node aux como floor
				}
			}
		} else if (node.key.compareTo(key) > 0) { // Checa se a chave associada ao Node eh maior que a chave fornecida como parametro
			if (node.leftSubtree == null) { // Se sim e a referencia ao Node esquerdo nao existir
				return null; // Retorna nulo (ja que nao tera mais onde buscar a chave fornecida)
			} else { // Se nao = a referencia ao Node esquerdo existir 
				return searchFloor(node.leftSubtree, key); // Executa a chamada recursiva tomando como parametro o Node a esquerda
			}
		}
		return node; // retorna o objeto Node achado
	}

	// Metodo implementado da interface, usado para recuperar a maior chave que seja menor ou igual a chave fornecida
	public Key floor(Key key) {
		if (this.root == null) { // Verifica se a arvore esta vazia, ou seja, raiz da arvore = null
			return null; // Se sim, retorna chave null
		} else { // Se nao = Arvore tem raiz
			Node floorNode = searchFloor(this.root, key); // Realiza a chamada do metodo privado a partir do Node raiz e sua chave associada
			// E guarda o resultado na variavel floorNode do tipo Node
			if (floorNode == null) { // Verifica se floorNode veio com valor null
				return null; // Se sim, retorna null
			} else { // Se nao = floorNode achou o Node na �rvore por meio da key fornecida
				return floorNode.key; // Entao retorna-se o valor deste Node achado
			}
		}
	}

	// Metodo privado searchCeil, que eh chamado do metodo publico ceil e atua recursivamente
	private Node searchCeil(Node node, Key key) {
		if (node.key == key) { // Checa se a chave associada ao Node eh igual a chave fornecida como parametro
			return node; // Se sim, achou o Node!
		} else if (node.key.compareTo(key) < 0) { // Checa se a chave associada ao Node eh menor que a chave fornecida como parametro
			if (node.rightSubtree == null) { // Se sim e a referencia ao Node direito nao existir
				return null; // Retorna nulo (ja que nao tera mais onde buscar a chave fornecida)
			} else { // Se nao = a referencia ao Node direito existir
				return searchCeil(node.rightSubtree, key); // Executa a chamada recursiva tomando como parametro o Node a direita
			}
		} else if (node.key.compareTo(key) > 0) { // Checa se a chave associada ao Node eh maior que a chave fornecida como parametro
			if (node.leftSubtree == null) { // Se sim e a referencia ao Node esquerdo nao existir
				return node; // Retorna o Node (ja que eh menor e nao tera mais onde buscar o Node com a chave igual a fornecida)
			} else { // Se nao = a referencia ao Node esquerdo existir 
				Node aux = searchCeil(node.leftSubtree, key); // Executa a chamada recursiva tomando como parametro o Node a esquerda
				if (aux != null) { // Checa se o resultado aplicado na variavel auxiliar eh diferente de nulo
					return aux; // Se sim, retorna o Node auxiliar como ceil
				} else { // Se nao = a variavel auxiliar eh nula
					return node; // Entao retorna o Node pai do auxiliar/do da esquerda
				}
			}
		}
		return node; // retorna o objeto Node achado
	}

	// Metodo implementado da interface, usado para recuperar a menor chave que seja maior ou igual a chave fornecida
	public Key ceil(Key key) {
		if (this.root == null) { // Verifica se a arvore esta vazia, ou seja, raiz da arvore = null
			return null; // Se sim, retorna chave null
		} else { // Se nao = Arvore tem raiz
			Node ceilNode = searchCeil(this.root, key);	// Realiza a chamada do metodo privado a partir do Node raiz e sua chave associada
			// E guarda o resultado na variavel ceilNode do tipo Node
			if (ceilNode == null) { // Verifica se ceilNode veio com valor null
				return null; // Se sim, retorna null
			} else { // Se nao = ceilNode achou o Node na �rvore por meio da key fornecida
				return ceilNode.key; // Entao retorna-se o valor deste Node achado
			}
		}
	}

	// Metodo privado searchDeleteMin, que eh chamado do metodo publico deleteMin e atua recursivamente
	public Node searchDeleteMin(Node node) {
		if (node == null) { // se o no node nao existir na arvore, a arvore esta vazia ou , logo retorna null
			return null;
		} else { // senao, o no node existe na arvore e segue buscando seus filhos a esquerda com recursao ate o menor no folha

			if (node.leftSubtree == null) { // se nao ha filho a esquerda, entao prossegue para direita
				return node.rightSubtree;

			} else { // se ha filho a esquerda, continua a recursao,
				// e atualiza a referencia da subarvore a esquerda ao encontrar o no minimo
				node.leftSubtree = searchDeleteMin(node.leftSubtree);
			}
		}
		return node; // retorna node, o no minimo a ser deletado
	}

	// Metodo implementado da interface, usado para deletar a menor chave que existir na arvore
	public void deleteMin() {
		if (this.root == null) { // se a raiz eh nula, a arvore esta vazia, portanto retorna '', ja que nao ha no
			return;
		} else {
			this.root = searchDeleteMin(this.root); // atualiza a nova raiz da arvore, com base na chamada do metodo recursivo
		}
	}

	/* Metodo privado searchDelete, que eh chamado do metodo publico delete
	 * e utiliza o parametro node para procurar o no a ser deletado da arvore recursivamente */
	private Node searchDelete(Key key, Node node) {
		/* inicializa o no auxiliar nodeCeil como o no parametrizado node, 
		 * para futuras manipulacoes de posicao apos delecao, se node for achado na arvore */
		Node nodeCeil = node; 

		if (node == null) { // se o no procurado node nao existir, entao nao esta contido na arvore, portanto retorna null
			return null;
		} else { // senao, compara a chave parametrizada key com a chave do no node (node.key) e segue percorrendo as subarvores de node
			if (key.compareTo(node.key) < 0) { // se a chave key for menor que a chave do no node,
				// entao executa a chamada recursiva para searchDelete na subarvore esquerda
				node.leftSubtree = searchDelete(key, node.leftSubtree);

			} else if (key.compareTo(node.key) > 0) { // se a chave key for maior que a chave do no node,
				// entao executa a chamada recursiva para searchDelete na subarvore direita
				node.rightSubtree = searchDelete(key, node.rightSubtree);

			} else { 
				/* encontrou a chave key buscada na arvore (key == node.key), 
				 * entao agora eh necessario saber se este no node possui filhos */

				if (node.rightSubtree == null) { // se nao ha filho a direita,
					// retorna o filho a esquerda de node, que sera utilizado como a nova raiz da subarvore (antes enraizada por node)
					return node.leftSubtree;

				} else if (node.leftSubtree == null) { // se nao ha filho a esquerda,
					// retorna o filho a direita de node, que sera utilizado como a nova raiz da subarvore (antes enraizada por node)
					return node.rightSubtree;

				} else { // senao, a chave procurada esta em um no com 2 filhos
					/* no auxiliar nodeCeil aponta para o menor no na subarvore direita de node 
					 * (que eh recuperado por meio da chamada do metodo searchMin) */
					nodeCeil = searchMin(node.rightSubtree);

					/* A referencia para o menor filho direito do pai de nodeCeil eh deletada 
					 * e em seguida o mesmo atualizado como filho a direita de nodeCeil*/
					nodeCeil.rightSubtree = searchDeleteMin(node.rightSubtree); // 

					/* "Reencaixa" a subarvore a esquerda do no node
					 *  ao no nodeCeil*/
					nodeCeil.leftSubtree = node.leftSubtree;
				}
			}
		}
		return nodeCeil; // retorna nodeCeil, o no encontrado a ser deletado
	}

	// Metodo implementado da interface, usado para deletar a chave key, fornecida como parametro, na arvore
	public void delete(Key key) {
		if (this.root == null) { // se a raiz eh nula, a arvore esta vazia, portanto retorna '', ja que nao ha no
			return;
		} else {
			this.root = searchDelete(key, this.root); // atualiza a nova raiz da arvore, com base na chamada do metodo recursivo
		}
	}

	/* Metodo auxiliar, usado para recuperar o tamanho da arvore ate um no node
	 * utilizado posteriormente para designar tamanhos parciais e auxiliar no calculo de metodos rank e select*/
	private int sizeNode(Node node) {
		if (node == null) { // se o no node parametrizado nao existir, entao o tamanho referente aquele no eh 0
			return 0;
		} else { // senao retorna o atributo size (tamanho parcial) referente ao node parametrizado
			return node.size;
		}
	}

	/* Metodo privado searchSelect, que eh chamado do metodo publico select
	 * e utiliza o parametro node para procurar a menor n-esima chave da arvore recursivamente */
	private Key searchSelect(int n, Node node) {
		if (node == null) { // se o no node parametrizado nao existir, entao retorna null
			return null;
		} else { // senao, entao existe e agora eh preciso comparar o tamanho parcial do filho a esquerda de node ao valor parametrizado n
			int t = sizeNode(node.leftSubtree);
			if (t > n) { 
				/* se o tamanho parcial for maior que n, 
				 * entao retorna a chamada recursiva do proprio metodo com o filho a esquerda */
				return searchSelect(n, node.leftSubtree);

			} else if (t < n) { 
				/* senao, se o tamanho parcial for menor que n, 
				 * entao retorna a chamada recursiva do proprio metodo com o filho a direita e atualizando o valor n para (n-t-1) */
				return searchSelect((n-t-1), node.rightSubtree);

			} else {
				/* senao, t = n : achou o no referente a n-esima menor chave
				 * entao retorna a chave do talno */
				return node.key;
			}
		}
	}

	// Metodo implementado da interface, usado para recuperar a n-esima menor chave da arvore, com base no parametro n
	public Key select( int n ) {
		if (this.root == null || n > size() || n < 0) {
			/* conforme as regras estabelecidas na descricao do trabalho, 
			 * ao receber valor n menor que 0
			 * ou se esse valor n for maior que o tamanho da arvore,
			 * ou se a raiz for nula, a arvore esta vazia,
			 * logo, retorna null*/
			return null;
		} else {
			return searchSelect(n, this.root); // executa a chamada do metodo privado a partir da raiz, 
			// buscando o no com o parametro n e retorna a chave do mesmo
		}
	}

	/* Metodo privado searchSelect, que eh chamado do metodo publico select
	 * e utiliza o parametro node para procurar o rank de um no da arvore, por meio do parametro key, recursivamente */
	private int searchRank(Key key, Node node) {
		if (node == null) { // se o no node parametrizado nao existir na arvore, entao retorna rank = 0
			return 0;
		} else { // senao, compara a chave parametrizada key com a chave referente ao no node
			int t = sizeNode(node.leftSubtree);
			if (key.compareTo(node.key) < 0) {
				/* se a chave buscada for menor que a do no node,
				 * entao retorna chamada recursiva do proprio metodo com o filho a esquerda (percorrendo a arvore pela esquerda) */
				return searchRank(key, node.leftSubtree);

			} else if (key.compareTo(node.key) > 0) {
				/* se a chave buscada for maior que a do no node,
				 * entao retorna chamada recursiva do proprio metodo com o filho a direita (percorrendo a arvore pela direita) 
				 * e depois retorna o rank total como o resultado da recursao+t+1 */
				return (searchRank(key, node.rightSubtree)+t+1);

			} else {
				/* senao, entao a chave buscada e a chave do node sao iguais,
				 * logo encontrou o no 
				 * e retorna a variavel que armazena o rank do no atual como o tamanho parcial do filho a esquerda */
				return t;					
			}
		}
	}

	// Metodo implementado da interface, usado para recuperar o rank de um no com chave key, fornecida como parametro, na arvore
	public int rank(Key key) {
		if (this.root == null) { // se a raiz eh nula, a arvore esta vazia, portanto retorna rank 0, ja que nao ha no
			return 0;
		} else {
			return searchRank(key, this.root); // executa a chamada do metodo privado a partir da raiz, 
			// buscando o no com a chave key e retorna o rank desse no
		}
	}

	// #### METODOS PARA TESTAR/VISUALIZAR ARVORE ####

	// Metodo privado escreverEstrutura, que eh chamado do metodo publico repr
	private String printStructure(Node node) {
		if (this.root == null) { // Verifica se a arvore esta vazia, ou seja, raiz da arvore = null
			return "Arvore Vazia"; // Se sim, retorna a string "arvore Vazia"
		}

		String structure = node.val + " "; // Criacao da variavel local structure, que recebe o valor do Node e sua cor numa cadeia de string
		if (node.leftSubtree == null) { // Verifica se a referencia para o Node a esquerda for igual a nulo
			structure += " esq-"; // Se sim, concatena a string " -esq" para a variavel structure, ja que nao ha Node a esquerda
		} else { // Se nao = Existe Node a esquerda
			structure += " esq[";
			structure += printStructure(node.leftSubtree); // Realiza a chamada recursiva fornecendo como parametro o Node a esquerda que foi achado
			structure += "]";
			// Entao concatena a string structure de acordo com o Node filho a esquerda
		}

		if (node.rightSubtree == null) { // Verifica se a referencia para o Node a direita for igual a nulo
			structure += " dir-"; // Se sim, concatena a string " dir-" para a variavel structure, ja que nao ha Node a direita
		} else { // Se nao = Existe Node a direita
			structure += " dir[";
			structure += printStructure(node.rightSubtree); // Realiza a chamada recursiva fornecendo como parametro o Node a direita que foi achado
			structure += "]";
			// Entao concatena a string structure de acordo com o Node filho a direita
		}

		return structure; // No final, retorna a variavel structure, preenchida depois de todas rodadas recursivas
	}

	// Metodo implementado da interface, usado para retornar uma representa�ao da structure da arvore na forma de uma string
	public String repr() {
		return printStructure(this.root); // Realiza a chamada do metodo privado a partir do Node raiz
	}

}