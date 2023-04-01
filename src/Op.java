public enum Op {
    PLUS {
        @Override
        public double execute(double left, double right) {
            return left + right;
        }
    },
    SUB {
        @Override
        public double execute(double left, double right) {
            return left - right;
        }
    },
    MULT {
        @Override
        public double execute(double left, double right) {
            return left * right;
        }
    },
    DIV {
        @Override
        public double execute(double left, double right) {
            return left / right;
        }
    };

    public abstract double execute(double left, double right);
}
