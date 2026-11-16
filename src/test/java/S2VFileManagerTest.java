import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class S2VFileManagerTest {
    @TempDir
    Path tempDir;

    @Test
    void preservesEmptyCellsAndRoundTripsContent() throws Exception {
        Spreadsheet sheet = new Spreadsheet();
        sheet.setCell("A1", "10");
        sheet.setCell("C1", "30");
        Path file = tempDir.resolve("sheet.s2v");

        S2VFileManager.save(sheet, file.toString());
        String savedContent = Files.readString(file);
        String expectedContent = "10;;30" + System.lineSeparator();
        assertEquals(expectedContent, savedContent);

        Spreadsheet loaded = S2VFileManager.load(file.toString());
        assertEquals("10", loaded.getCellContent("A1"));
        assertEquals("", loaded.getCellContent("B1"));
        assertEquals("30", loaded.getCellContent("C1"));
    }

    @Test
    void convertsFormulaArgumentSeparatorsForStorage() throws Exception {
        Spreadsheet sheet = new Spreadsheet();
        sheet.setCell("AA2", "=SUM(A1;B1)");
        Path file = tempDir.resolve("formula.s2v");

        S2VFileManager.save(sheet, file.toString());
        String secondLine = Files.readAllLines(file).get(1);
        assertEquals("=SUM(A1,B1)", secondLine.split(";", -1)[26]);

        Spreadsheet loaded = S2VFileManager.load(file.toString());
        assertEquals("=SUM(A1;B1)", loaded.getCellContent("AA2"));
    }
}
