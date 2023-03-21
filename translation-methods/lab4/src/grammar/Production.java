package grammar;

import java.util.ArrayList;
import java.util.List;

public record Production(
    List<NonTerminal> nonTerminals,
    List<Terminal> terminals,
    List<BroadcastSymbol> broadcastSymbols,
    List<Integer> order,
    List<Integer> type
) {

    public Terminal getTerminalByPos(int pos) {
        return terminals.get(order.get(pos));
    }

    public NonTerminal getNonTerminalByPos(int pos) {
        return nonTerminals.get(order.get(pos));
    }

    public BroadcastSymbol getBroadcastSymbolByPos(int pos) {
        return broadcastSymbols.get(order.get(pos));
    }

    public Production getGamma(int pos) {
        List<NonTerminal> nonTerminalList = new ArrayList<>();
        List<Terminal> terminalList = new ArrayList<>();
        List<BroadcastSymbol> broadcastSymbolList = new ArrayList<>();
        List<Integer> typeList = new ArrayList<>();
        List<Integer> orderList = new ArrayList<>();

        for (int i = pos; i < order.size(); i++) {
            switch (type.get(i)) {
                case 0 -> {
                    orderList.add(nonTerminalList.size());
                    nonTerminalList.add(getNonTerminalByPos(i));
                    type.add(0);
                }
                case 1 -> {
                    orderList.add(terminalList.size());
                    terminalList.add(getTerminalByPos(i));
                    type.add(1);
                }
                case 2 -> {
                    orderList.add(broadcastSymbolList.size());
                    broadcastSymbolList.add(getBroadcastSymbolByPos(i));
                    type.add(2);
                }
            }
        }
        return new Production(
                nonTerminalList,
                terminalList,
                broadcastSymbolList,
                orderList,
                typeList
        );
    }

}
