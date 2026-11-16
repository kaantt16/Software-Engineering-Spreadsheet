public class FormulaContent extends CellContent {
    private final String formulaText;

    public FormulaContent(String formulaText) {
        if (formulaText == null || !formulaText.startsWith("=")) {
            throw new IllegalArgumentException("A formula must start with =");
        }
        this.formulaText = formulaText;
    }

    @Override
    public String getContent() {
        return formulaText;
    }
}
