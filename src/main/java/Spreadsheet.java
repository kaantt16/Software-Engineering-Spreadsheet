import java.util.HashMap;
import java.util.Map;

public class Spreadsheet {
    private final HashMap<Coordinate, Cell> cells = new HashMap<>();

    public void setCell(String position, String value) {
        Coordinate coordinate = Coordinate.fromString(position);
        if (value == null || value.isEmpty()) {
            cells.remove(coordinate);
            return;
        }

        CellContent content;
        if (value.startsWith("=")) {
            content = new FormulaContent(value);
        } else {
            try {
                double number = Double.parseDouble(value);
                content = new NumberContent(number);
            } catch (IllegalArgumentException exception) {
                content = new TextContent(value);
            }
        }
        cells.put(coordinate, new Cell(coordinate, content));
    }

    public String getCellContent(String position) {
        Coordinate coordinate = Coordinate.fromString(position);
        Cell cell = cells.get(coordinate);

        if (cell == null) {
            return "";
        }
        return cell.getContentAsString();
    }

    public Map<Coordinate, Cell> getCells() {
        return cells;
    }

    public void display() {
        if (cells.isEmpty()) {
            System.out.println("Spreadsheet is empty.");
            return;
        }

        for (Map.Entry<Coordinate, Cell> entry : cells.entrySet()) {
            Coordinate coordinate = entry.getKey();
            Cell cell = entry.getValue();
            System.out.println(coordinate + " = " + cell.getContentAsString());
        }
    }
}
