public class Match {
    int pointsToWin;
    Game game;
    Player player1;
    Player player2;

    public Match(int pointsToWin, Player player1, Player player2) {
        this.pointsToWin = pointsToWin;
        this.player1 = player1;
        this.player2 = player2;
    }

    public void play(int m, int n, int k) {
        boolean side = true;
        int pointsOfPlayer1 = 0;
        int pointsOfPlayer2 = 0;
        int result;
        while (pointsOfPlayer1 < pointsToWin && pointsOfPlayer2 < pointsToWin) {
            game = new Game(true, player1, player2);
            MNKBoard board = new MNKBoard(m, n, k);
            result = game.play(board);
            if (result == 0) {
                side = !side;
            } else {
                if (side) {
                    if (result == 1) {
                        pointsOfPlayer1++;
                    } else {
                        pointsOfPlayer2++;
                    }
                } else {
                    if (result == 1) {
                        pointsOfPlayer2++;
                    } else {
                        pointsOfPlayer1++;
                    }
                }
                side = !side;
            }
            System.out.println(pointsOfPlayer1 + pointsOfPlayer2 + " game finished! Points: Player 1 = " + pointsOfPlayer1 + " Player 2 = " + pointsOfPlayer2);
            Player tmp = player1;
            player1 = player2;
            player2 = tmp;
        }
        if (pointsOfPlayer1 == pointsToWin) {
            System.out.println("Player 1 won the match");
        } else {
            System.out.println("Player 2 won the match");
        }
    }

}
