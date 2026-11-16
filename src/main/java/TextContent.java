public class TextContent extends CellContent {
    private final String text;

    public TextContent(String text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null");
        }
        this.text = text;
    }

    @Override
    public String getContent() {
        return text;
    }
}
