'use strict';

function Expression(operation, signOfOperation, ...operands) {
    this.getOperation = function() { return operation };
    this.getOperands = function() { return operands };
    this.getSign = function() { return signOfOperation };
}
Expression.prototype = {
    evaluate(...values) { 
        let opArray = this.getOperands().map(op => op.evaluate(...values));
        return this.getOperation()(...opArray);
    },
    toString() {
        let opArray = this.getOperands().map(op => op.toString());
        return opArray.join(" ") + " " + this.getSign();
    },
    prefix() {
        let opArray = this.getOperands().map(op => op.prefix());
        return "(" + this.getSign() + " " + opArray.join(" ") + ")";
    },
    constructor: Expression
}

function UnaryOperation(operation, signOfOperation, operand) {
    Expression.call(this, operation, signOfOperation, operand);
}
UnaryOperation.prototype = Object.create(Expression.prototype);

function BinaryOperation(operation, signOfOperation, firstOp, secondOp) {
    Expression.call(this, operation, signOfOperation, firstOp, secondOp);
}
BinaryOperation.prototype = Object.create(Expression.prototype);

function ArcTan2(firstOp, secondOp) {
    return new BinaryOperation(function(a, b) { return Math.atan2(a, b) }, "atan2", firstOp, secondOp);
}

function Multiply(firstOp, secondOp) {
     return new BinaryOperation(function(a, b) { return a * b}, "*", firstOp, secondOp)
}

function Subtract(firstOp, secondOp) {
     return new BinaryOperation(function(a, b) { return a - b}, "-", firstOp, secondOp)
}

function Divide(firstOp, secondOp) {
     return new BinaryOperation(function(a, b) { return a / b}, "/", firstOp, secondOp)
}

function Add(firstOp, secondOp) {
     return new BinaryOperation(function(a, b) { return a + b}, "+", firstOp, secondOp)
}

function ArcTan(operand) {
    return new UnaryOperation(function(value) { return Math.atan(value) }, "atan", operand);
}

function Sinh(operand) {
    return new UnaryOperation(function(value) { return Math.sinh(value) }, "sinh", operand);
}

function Cosh(operand) {
    return new UnaryOperation(function(value) { return Math.cosh(value) }, "cosh", operand);
}

function Negate(operand) {
    return new UnaryOperation(function(value) { return -value }, "negate", operand);
}

function Const(value) {
    this.getValue = function() { return value };
}
Const.prototype = {
    evaluate(...values) { return this.getValue() },
    toString() { return this.getValue().toString() },
    prefix() { return this.toString() }
}


function Variable(name) {
    this.getName = function() { return name };
}
Variable.prototype = {
    evaluate(...values) { 
        switch(this.getName()) {
        case "x":
            return values[0];
        case "y":
            return values[1];
        case "z":
            return values[2];
        }
    },
    toString() { return this.getName() },
    prefix() { return this.toString() }
}

function ParsingError(message, name) {
    Error.call(this, message, name);
    this.message = message;
    this.name = name;
}
ParsingError.prototype = Object.create(Error.prototype);
ParsingError.prototype.name = this.name;
ParsingError.constructor = ParsingError;

function MissingOpeningParenthesisError(position) {
    return new ParsingError("Missing opening parenthesis at position " + position, "MissingOpeningParenthesisError");
}

function MissingClosingParenthesisError(position) {
    return new ParsingError("Missing closing parenthesis at position " + position, "MissingClosingParenthesisError");
}

function IllegalVariableNameError(position) {
    return new ParsingError("Illegal name of variable at position " + position, "IllegalVariableNameError");
}

function EndOfExpressionExpectedError(position) {
    return new ParsingError("End of expression expected at position " + position, "EndOfExpressionExpectedError");
}

function IllegalOperationError(position) {
    return new ParsingError("Illegal operation at position " + position, "IllegalOperationError");
}

function EmptyExpressionError() {
    return new ParsingError("Empty expression", "EmptyExpressionError");
}

function InvalidArgumentError(position) {
    return new ParsingError("Invalid argument at position " + position, "InvalidArgumentError");
}

function InvalidNumberError(position) {
    return new ParsingError("Invalid number at position " + position, "InvalidNumberError");
}

function Parser(source) {
    this.source = source;
}
Parser.prototype = {
    parseExpression() {
        if (this.source.getString().length == 0) {
            throw new EmptyExpressionError();
        }
        let result = this.parseExpr();
	    if (this.source.endOfString()) {
            return result;
       	}
        throw new EndOfExpressionExpectedError(this.source.getCurrentPos());
    },

    parseExpr() {
        this.skipWhitespaces();
        let result;
        if (this.test("(")) {
            result = this.parseOperation();  
            if (!this.test(")")) {
                throw new MissingClosingParenthesisError(this.source.getCurrentPos())
            }   
        } else if (this.test("-")) {
             result = this.parseConst(true);
        } else if (isDigit(this.source.getSymbol())) {
             result = this.parseConst(false);
        } else if (isLetter(this.source.getSymbol())) {
             result = this.parseVariable();   
        } else {
            throw new InvalidArgumentError(this.source.getCurrentPos());
        }
        this.skipWhitespaces();
        return result;
    },
	
    parseOperation() {
        let sign = this.parseSignOfOperation();
	    this.skipWhitespaces();	
        let result = this.parseExpr();
        if ((operation.get(sign))(result) instanceof UnaryOperation) {
            return operation.get(sign)(result);
        } else if ((operation.get(sign))(result) instanceof BinaryOperation) {
            result = operation.get(sign)(result, this.parseExpr());
        }
	    this.skipWhitespaces();
        if (isDigit(this.source.getSymbol()) || isLetter(this.source.getSymbol())) {
            throw new IllegalOperationError(this.source.getCurrentPos());
        }
        return result;
    },
    
    parseSignOfOperation() {
        this.skipWhitespaces();
        let startPos = this.source.getCurrentPos();
        while (illegalOperations.has(this.source.getSymbol()) || isLetter(this.source.getSymbol())) {
            this.source.next();
        }
        let sign = this.source.getString().substring(startPos, this.source.getCurrentPos());
        if (isDigit(this.source.getSymbol()) || isLetter(this.source.getSymbol()) || !operation.has(sign)) {
            throw new IllegalOperationError(this.source.getCurrentPos());
        }
        return sign;
    },

    parseVariable() {
        let startPos = this.source.getCurrentPos();
        while (isLetter(this.source.getSymbol())) {
            this.source.next();
        }
        let variable = this.source.getString().substring(startPos, this.source.getCurrentPos());
        if (illegalVariables.has(variable)) {
            return new Variable(variable);
        } else {
            throw new IllegalVariableNameError(startPos);
        } 
    },

    parseConst(negate) {
        let startPos = this.source.getCurrentPos();
        while (isDigit(this.source.getSymbol())) {
            this.source.next();
        }
        let result = this.source.getString().substring(startPos, this.source.getCurrentPos());
        if (negate) {
            return new Const(parseInt("-" + result));
        } else {
            return new Const(parseInt(result));
        }
    },
    
    skipWhitespaces() {
       while(isWhitespace(this.source.getSymbol())) {
            this.source.next();
        } 
    },

    test(symbol) {
        if (this.source.getSymbol() === symbol) {
            this.source.next();
            return true;
        }
        return false;
    }   
}
Parser.prototype.constructor = Parser;

function Source(string) {
	this.getString = function() { return string };
    this.pos = 0;
}
Source.prototype = {
    constructor: Source,
    getSymbol() {
        return this.getString()[this.pos];
    },
    next() {
        this.pos++;
    },
    getCurrentPos() {
        return this.pos;
    },
    endOfString() {
        return this.pos >= this.getString().length; 
    }
}

function isLetter(symbol) {
    return symbol >= 'a' && symbol <= 'z';
}

function isDigit(symbol) {
    return '0' <= symbol && symbol <= '9';
}

const operation = new Map([
    ["+", (a, b) => new Add(a, b)], 
    ["-", (a, b) => new Subtract(a, b)], 
    ["*", (a, b) => new Multiply(a, b)], 
    ["/", (a, b) => new Divide(a, b)], 
    ["negate", a => new Negate(a)],
    ["sinh", a => new Sinh(a)],
    ["cosh", a => new Cosh(a)],
    ]);

const illegalVariables = new Set(["x", "y", "z"]);

const illegalOperations = new Set(["+", "*", "-", "/", "negate"]);

function isWhitespace(symbol) {
    return (symbol === ' ' || symbol === '\n' || symbol === '\r' || symbol === '\t');
}

function parsePrefix(string) {
    return new Parser(new Source(string.trim())).parseExpression();
}