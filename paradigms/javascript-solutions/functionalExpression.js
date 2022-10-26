const operation = f => (...operands) => (...values) => operands.length > 1
    ? f(operands[0](...values), operands[1](...values))
    : f(operands[0](...values));

const subtract = operation((a, b) => a - b);
const add = operation((a, b) => a + b);
const multiply = operation((a, b) => a * b);
const divide = operation((a, b) => a / b);
const negate = operation(a => -a);
const cnst = value => () => value;

const variable = name => (...values) => {
    switch (name) {
        case "x":
            return values[0];
        case "y":
            return values[1];
        case "z":
            return values[2];
    }
}

