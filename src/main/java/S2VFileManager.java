import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class S2VFileManager {
    private S2VFileManager() { }

    public static void save(Spreadsheet spreadsheet, String filename) throws IOException {
        int maxRow = 0;
        int maxColumn = 0;

        for (Coordinate coordinate : spreadsheet.getCells().keySet()) {
            maxRow = Math.max(maxRow, coordinate.getRow());
            maxColumn = Math.max(maxColumn, columnToNumber(coordinate.getColumn()));
        }

        StringBuilder fileContent = new StringBuilder();

        for (int row = 1; row <= maxRow; row++) {
            for (int col = 1; col <= maxColumn; col++) {
                String position = numberToColumn(col) + row;
                String content = spreadsheet.getCellContent(position);

                // S2V uses commas inside formulas because semicolons separate cells.
                if (content.startsWith("=")) {
                    content = content.replace(';', ',');
                }

                fileContent.append(content);
                if (col < maxColumn) {
                    fileContent.append(";");
                }
            }
            fileContent.append(System.lineSeparator());
        }

        Files.writeString(Path.of(filename), fileContent.toString());
    }

    public static Spreadsheet load(String filename) throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet();
        List<String> lines = Files.readAllLines(Path.of(filename));

        for (int row = 0; row < lines.size(); row++) {
            String[] values = lines.get(row).split(";", -1);

            for (int col = 0; col < values.length; col++) {
                String content = values[col];

                // Restore the formula separator used by the spreadsheet.
                if (content.startsWith("=")) {
                    content = content.replace(',', ';');
                }

                if (!content.isEmpty()) {
                    String position = numberToColumn(col + 1) + (row + 1);
                    spreadsheet.setCell(position, content);
                }
            }
        }
        return spreadsheet;
    }


    static int columnToNumber(String column) {
        int result = 0;
        for (char letter : column.toCharArray()) {
            int letterValue = letter - 'A' + 1;
            result = result * 26 + letterValue;
        }
        return result;
    }


    static String numberToColumn(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Column number must be positive");
        }

        StringBuilder result = new StringBuilder();
        while (number > 0) {
            number--;
            int remainder = number % 26;
            char letter = (char) ('A' + remainder);
            result.insert(0, letter);
            number /= 26;
        }
        return result.toString();
    }
}
