public class NumberContent extends CellContent {
    private final double number;

    public NumberContent(double number) {
        if (!Double.isFinite(number)) {
            throw new IllegalArgumentException("Number must be finite");
        }
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    @Override
    public String getContent() {
        if (number == (long) number) {
            return String.valueOf((long) number);
        }
        return String.valueOf(number);
    }
}
