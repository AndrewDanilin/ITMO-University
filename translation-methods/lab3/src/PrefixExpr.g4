grammar PrefixExpr;

code returns [String str]
    @init {
        StringBuilder sb = new StringBuilder("def main():\n");
    }
    @after {
        sb.append("\nmain()");
        $str = sb.toString();
    }
    : blocksOfCode[1] {
        sb.append($blocksOfCode.str);
    }
    | EOF
    ;

blocksOfCode[int tabs] returns [String str]
    @init {
        StringBuilder sb = new StringBuilder();
    }
    @after {
        $str = sb.toString();
    }
    : blockOfCode[$tabs] {
        sb.append($blockOfCode.str);
    }
    | BOTH blockOfCode[$tabs] blocksOfCode[$tabs] {
        sb.append($blockOfCode.str).append("\n").append($blocksOfCode.str);
    }
    ;

blockOfCode[int tabs] returns [String str]
    : blockIf[$tabs] {
        $str = $blockIf.str;
    }
    | instruction[$tabs] {
        $str = $instruction.str;
    }
    ;

instruction[int tabs] returns [String str]
    : assignment {
        $str = "\t".repeat($tabs) + $assignment.str;
    }
    | print {
        $str = "\t".repeat($tabs) + $print.str;
    }
    ;

blockIf[int tabs] returns [String str]
    @init {
        StringBuilder sb = new StringBuilder();
    }
    @after {
        $str = sb.toString();
    }
    : IF logicalExpr blocksOfCode[$tabs + 1] blockIfContinue[$tabs] {
        sb.append("\t".repeat($tabs)).append("if").append(" ")
            .append($logicalExpr.str).append(":")
            .append("\n")
            .append($blocksOfCode.str);

        if (!$blockIfContinue.str.isEmpty()) {
            sb.append("\n")
                .append("\t".repeat($tabs)).append("el").append($blockIfContinue.str.trim());
        }
    }
    ;

blockIfContinue[int tabs] returns[String str]
    : {
        $str = "";
    }
    | blockIf[$tabs] {
        $str = $blockIf.str;
    }
    | blocksOfCode[$tabs + 1] {
        StringBuilder sb = new StringBuilder();
        sb.append("se").append(":").append("\n").append($blocksOfCode.str);
        $str = sb.toString();
    }
    ;

expr returns [String str]
    : logicalExpr {
        $str = $logicalExpr.str;
    }
    | arithmeticExpr {
        $str = $arithmeticExpr.str;
    }
    ;

logicalExpr returns [String str]
    : logicalBinaryOp left = logicalExpr right = logicalExpr {
        $str = $left.str + " " + $logicalBinaryOp.str + " " + $right.str;
    }
    | logicalUnaryOp logicalExpr {
        $str = $logicalUnaryOp.str + "(" + $logicalExpr.str + ")";
    }
    | comparison {
        $str = $comparison.str;
    }
    | VAR {
        $str = $VAR.text;
    }
    ;

logicalBinaryOp returns [String str]
    : XOR {
        $str = "xor";
    }
    | OR {
        $str = "or";
    }
    | AND {
        $str = "and";
    }
    ;

logicalUnaryOp returns [String str]: NOT {
    $str = "not";
};

comparison returns [String str]: comparisonOp left = arithmeticExpr right = arithmeticExpr {
    $str = $left.str + " " + $comparisonOp.str + " " + $right.str;
};

comparisonOp returns [String str]
    : GE {
        $str = $GE.text;
    }
    | GT {
        $str = $GT.text;
    }
    | LE {
        $str = $LE.text;
    }
    | LT {
        $str = $LT.text;
    }
    | EQ {
        $str = $EQ.text;
    }
    | NEQ {
        $str = $NEQ.text;
    }
    ;

arithmeticExpr returns [String str]
    : arithmeticBinaryOp left = arithmeticExpr right = arithmeticExpr {
        $str = "(" + $left.str + " " + $arithmeticBinaryOp.str + " " + $right.str + ")";
    }
    | arithmeticUnaryOp arithmeticExpr {
        $str = $arithmeticUnaryOp.str + "(" + $arithmeticExpr.str + ")";
    }
    | NUMBER {
        $str = $NUMBER.text;
    }
    | VAR {
        $str = $VAR.text;
    }
    ;

arithmeticBinaryOp returns [String str]
    : PLUS {
        $str = $PLUS.text;
    }
    | MINUS {
        $str = $MINUS.text;
    }
    | MUL {
        $str = $MUL.text;
    }
    | DIV {
        $str = $DIV.text;
    };

arithmeticUnaryOp returns [String str]: MINUS {
    $str = $MINUS.text;
};

print returns [String str]
    : PRINT printOperands {
        $str = $PRINT.text + "(" + $printOperands.str + ")";
    }
    ;

printOperands returns [String str]:
    | expr {
        $str = $expr.str;
    }
    | BOTH expr printOperands {
        $str = $expr.str + ", " + $printOperands.str;
    }
    ;

assignment returns [String str]: ASSIGNMENT VAR expr {
    $str = $VAR.text + " " + $ASSIGNMENT.text + " " + $expr.str;
};

PLUS  : '+' ;
MINUS : '-' ;
MUL   : '*' ;
DIV   : '/' ;

NOT : '!' ;
XOR : '^' ;
AND : '&' ;
OR  : '|' ;

GE  : '>=' ;
GT  : '>' ;
LE  : '<=' ;
LT  : '<' ;
EQ  : '==' ;
NEQ : '!=' ;

IF    : 'if' ;
PRINT : 'print' ;
BOTH  : 'both' ;

VAR    : [a-z] ;
NUMBER : [0-9]+ ;

ASSIGNMENT : '=' ;

WS : [ \t\r\n]+ -> skip;


