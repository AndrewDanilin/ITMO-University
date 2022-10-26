map_build(ListMap, TreeMap) :-
	build_tree(ListMap, TreeMap).

build_tree([], null).
build_tree(List, node(K, V, Left, Right)) :-
	length(List, N),
	Half is div(N, 2),
	length(LeftHalf, Half),
	append(LeftHalf, [(K, V)| RightHalf], List),
	build_tree(LeftHalf, Left),
	build_tree(RightHalf, Right).	

map_get(TreeMap, Key, Value) :- tree_recursion(TreeMap, Key, Value).

tree_recursion(node(Key, Val, _, _), Key, Val) :-!.

tree_recursion(node(K, V, L, R), Key, Value) :-
	K < Key,
	tree_recursion(R, Key, Value).
	
tree_recursion(node(K, V, L, R), Key, Value) :-
	K > Key,
	tree_recursion(L, Key, Value).

map_containsKey(Map, Key) :- map_get(Map, Key, _).

map_containsValue(node(_, Val, _, _), Val) :- !.
map_containsValue(node(_, V, L, R), Value) :- 
	map_containsValue(L, Value);
	map_containsValue(R, Value).

	