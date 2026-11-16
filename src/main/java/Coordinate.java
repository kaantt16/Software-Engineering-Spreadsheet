public class Coordinate {
    private final String column;
    private final int row;

    public Coordinate(String column, int row) {
        if (column == null || column.isEmpty()) {
            throw new IllegalArgumentException("Invalid column");
        }
        for (int i = 0; i < column.length(); i++) {
            if (!Character.isLetter(column.charAt(i))) {
                throw new IllegalArgumentException("Invalid column");
            }
        }
        if (row < 1) {
            throw new IllegalArgumentException("Row must be positive");
        }
        this.column = column.toUpperCase();
        this.row = row;
    }

    public static Coordinate fromString(String coordinate) {
        if (coordinate == null || coordinate.isEmpty()) {
            throw new IllegalArgumentException("Invalid coordinate");
        }

        int position = 0;
        while (position < coordinate.length()
                && Character.isLetter(coordinate.charAt(position))) {
            position++;
        }

        if (position == 0 || position == coordinate.length()) {
            throw new IllegalArgumentException("Invalid coordinate");
        }

        String column = coordinate.substring(0, position);
        String rowText = coordinate.substring(position);

        int row;
        try {
            row = Integer.parseInt(rowText);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Invalid coordinate");
        }

        return new Coordinate(column, row);
    }

    public String getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return column + row;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Coordinate)) {
            return false;
        }

        Coordinate other = (Coordinate) obj;
        return row == other.row && column.equals(other.column);
    }

    @Override
    public int hashCode() {
        return column.hashCode() + row;
    }
}
