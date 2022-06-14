import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {
    List<Station> routeNoConnections;
    List<Station> routeWithOneConnections;
    List<Station> routeWithTwoConnections;

    StationIndex stationIndex;
    RouteCalculator calculator;

    Station japanese;
    Station bulgarian;
    Station american;
    Station english;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        stationIndex = new StationIndex();

        Line line1 = new Line(1, "Красная");
        Line line2 = new Line(2, "Зеленая");
        Line line3 = new Line(3, "Белая");

        japanese = new Station("Японская", line1);
        Station russia = new Station("Русская", line1); // - czech
        bulgarian = new Station("Болгарская", line1);
        american = new Station("Американская", line2);
        Station czech = new Station("Чешская", line2);
        Station roman = new Station("Римская", line2);// - korean
        english = new Station("Английская", line3);
        Station chinese = new Station("Китайская", line3);
        Station korean = new Station("Корейская", line3);


        Stream.of(line1, line2, line3).forEach(stationIndex::addLine);
        Stream.of(japanese, russia, bulgarian, american, czech, roman, english, chinese, korean)
                .peek(s -> s.getLine().addStation(s))
                .forEach(stationIndex::addStation);

        stationIndex.addConnection(Stream.of(russia, czech).collect(Collectors.toList()));
        stationIndex.addConnection(Stream.of(roman, korean).collect(Collectors.toList()));

        calculator = new RouteCalculator(stationIndex);

        routeNoConnections = Stream.of(japanese, russia, bulgarian).collect(Collectors.toList());
        routeWithOneConnections = Stream.of(japanese, russia, czech, american).collect(Collectors.toList());
        routeWithTwoConnections = Stream.of(japanese, russia, czech, roman, korean, chinese, english).collect(Collectors.toList());
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(routeWithTwoConnections);
        double expected = 17;
        assertEquals(expected, actual);
    }

    public void testGetShortestCalculator() {
        List<Station> actualNoTransfers = calculator.getShortestRoute(japanese, bulgarian);
        List<Station> actualOneTransfers = calculator.getShortestRoute(japanese, american);
        List<Station> actualTwoTransfers = calculator.getShortestRoute(japanese, english);

        List<Station> expectedNoTransfers = routeNoConnections;
        List<Station> expectedOneTransfers = routeWithOneConnections;
        List<Station> expectedTwoTransfers = routeWithTwoConnections;

        assertEquals(expectedNoTransfers, actualNoTransfers);
        assertEquals(expectedOneTransfers, actualOneTransfers);
        assertEquals(expectedTwoTransfers, actualTwoTransfers);
    }
}