import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpreadsheetTest {
    @Test
    void storesNumbersTextAndFormulas() {
        Spreadsheet sheet = new Spreadsheet();
        sheet.setCell("A1", "10");
        sheet.setCell("B1", "Hello");
        sheet.setCell("C1", "=A1*2");

        assertEquals("10", sheet.getCellContent("A1"));
        assertEquals("Hello", sheet.getCellContent("B1"));
        assertEquals("=A1*2", sheet.getCellContent("C1"));
        assertInstanceOf(NumberContent.class, sheet.getCells().get(Coordinate.fromString("A1")).getContent());
        assertInstanceOf(TextContent.class, sheet.getCells().get(Coordinate.fromString("B1")).getContent());
        assertInstanceOf(FormulaContent.class, sheet.getCells().get(Coordinate.fromString("C1")).getContent());
    }

    @Test
    void supportsCaseInsensitiveMultiLetterCoordinatesAndClearingCells() {
        Spreadsheet sheet = new Spreadsheet();
        sheet.setCell("aa10", "42.5");
        assertEquals("42.5", sheet.getCellContent("AA10"));
        sheet.setCell("AA10", "");
        assertEquals("", sheet.getCellContent("AA10"));
        assertTrue(sheet.getCells().isEmpty());
    }

    @Test
    void rejectsInvalidCoordinates() {
        assertThrows(IllegalArgumentException.class, () -> Coordinate.fromString("A0"));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.fromString("1A"));
        assertThrows(IllegalArgumentException.class, () -> Coordinate.fromString(null));
    }
}
