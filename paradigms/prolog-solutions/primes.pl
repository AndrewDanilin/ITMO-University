init(MAX_N) :- pre_counting(2, MAX_N).

while_construction(P, K, MAX_N) :-
			P < MAX_N,
			assert(composite(P)),
			P1 is P + K,
			while_construction(P1, K, MAX_N).

if_construction(N, MAX_N) :-
			not(composite(N)),
			assert(prime(N)),
			P is N * 2,
			while_construction(P, N, MAX_N).
		
pre_counting(N, MAX_N) :-
			N < MAX_N,
			(if_construction(N, MAX_N); true),
			N1 is N + 1,
			pre_counting(N1, MAX_N).

for_construction_divisors(1, _, []) :- !.

for_construction_divisors(N, CUR_DIV, [H | T]) :-
		not(0 is mod(N, CUR_DIV)),
		NEXT_DIV is CUR_DIV + 1,
		for_construction_divisors(N, NEXT_DIV, [H | T]).

for_construction_divisors(N, CUR_DIV, [H | T]) :-
		0 is mod(N, CUR_DIV),
		H = CUR_DIV,
		N1 is div(N, CUR_DIV),
		for_construction_divisors(N1, CUR_DIV, T).		
		
prime_divisors(N, Divisors) :-
		for_construction_divisors(N, 2, Divisors).

square_divisors(N, D) :-
		N1 is N * N,
		prime_divisors(N1, D).		


			
			