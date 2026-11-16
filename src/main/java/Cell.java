public class Cell {
    private final Coordinate coordinate;
    private CellContent content;

    public Cell(Coordinate coordinate, CellContent content) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate cannot be null");
        }
        this.coordinate = coordinate;
        this.content = content;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public CellContent getContent() {
        return content;
    }

    public void setContent(CellContent content) {
        this.content = content;
    }

    public String getContentAsString() {
        if (content == null) {
            return "";
        }
        return content.getContent();
    }
}
