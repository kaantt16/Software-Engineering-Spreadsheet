import java.util.ArrayList;
import java.util.List;

public class Function extends Expression {
    private final String name;
    private final List<Expression> arguments;

    public Function(String name, List<Expression> arguments) {
        this.name = name;
        this.arguments = new ArrayList<>(arguments);
    }

    public String getName() {
        return name;
    }

    public List<Expression> getArguments() {
        return new ArrayList<>(arguments);
    }
}
