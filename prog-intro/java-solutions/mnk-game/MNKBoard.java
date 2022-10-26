import java.util.Arrays;
import java.util.Map;
public class MNKBoard implements Board, Position {
    private static final Map<Cell, Character> SYMBOLS = Map.of(
            Cell.X, 'X',
            Cell.O, 'O',
            Cell.E, '.'
    );
    private final Cell[][] cells;
    Cell turn;
    private final int m;
    private final int n;
    private final int k;

    public MNKBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public Result makeMove(final Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }

        cells[move.getRow()][move.getColumn()] = move.getValue();
        System.out.println(getPosition());

        int inDiag1 = 0;
        int inDiag2 = 0;
        int empty = 0;
        int inRow = 0;
        int inCol = 0;
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                for (int j = move.getColumn() - k + 1; j < move.getColumn() + k; j++) {
                    if (j >= 0 && j < m) {
                        if (cells[move.getRow()][j] == turn) {
                            inRow++;
                        } else if (inRow >= k) {
                            return Result.WIN;
                        } else {
                            inRow = 0;
                        }
                    }
                    if (inRow >= k) {
                        return Result.WIN;
                    }
                }
            }
            if (i == 1) {
                for (int j = move.getRow() - k + 1; j < move.getRow() + k; j++) {
                    if (j >= 0 && j < n) {
                        if (cells[j][move.getColumn()] == turn) {
                            inCol++;
                        } else if (inCol >= k) {
                            return Result.WIN;
                        } else {
                            inCol = 0;
                        }
                    }
                    if (inCol >= k) {
                        return Result.WIN;
                    }
                }
            } else if (i == 2) {
                int minRow = move.getRow() - k + 1;
                int minCol = move.getColumn() - k + 1;
                for (int j = 0; j < 2 * k - 1; j++) {
                    if (minRow >= 0 && minRow < n && minCol >= 0 && minCol < m) {
                        if (cells[minRow][minCol] == turn) {
                            inDiag1++;
                        } else if (inDiag1 >= k) {
                            return Result.WIN;
                        } else {
                            inDiag1 = 0;
                        }
                    } else if (inDiag1 >= k){
                        return Result.WIN;
                    }
                    minRow++;
                    minCol++;
                }
            } else {
                int maxRow = move.getRow() + k - 1;
                int maxCol = move.getColumn() + k - 1;
                for (int j = 0; j < 2 * k - 1; j++) {
                    if (maxRow >= 0 && maxRow < n && maxCol >= 0 && maxCol < m) {
                        if (cells[maxRow][maxCol] == turn) {
                            inDiag2++;
                        } else if (inDiag2 >= k) {
                            return Result.WIN;
                        } else {
                            inDiag2 = 0;
                        }
                    } else if (inDiag2 >= k){
                        return Result.WIN;
                    }
                    maxRow--;
                    maxCol--;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cells[i][j] == Cell.E) {
                    empty++;
                }
            }
        }

        if (empty == 0) {
            return Result.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return Result.UNKNOWN;
    }

    @Override
    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < n
                && 0 <= move.getColumn() && move.getColumn() < m
                && cells[move.getRow()][move.getColumn()] == Cell.E
                && turn == getCell();
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int i = 0; i < m; i++) {
            sb.append(i);
        }
        for (int r = 0; r < n; r++) {
            sb.append("\n");
            sb.append(r);
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c]));
            }
        }
        return sb.toString();
    }


}
