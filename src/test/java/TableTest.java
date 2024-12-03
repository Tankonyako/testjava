import org.example.Core.Http.HtmlRenderer.HTMLTable;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableTest
{
	@Test
	public void Test()
	{
		List<Map<String, Object>> table = List.of(
				Map.of(
						"Name", "Georgy"
				),
				Map.of(
						"Name", "Duck"
				)
		);

		assertEquals(
				"<table border='1'><thead><tr><th>Name</th></tr></thead><tbody><tr><td>Georgy</td></tr><tr><td>Duck</td></tr></tbody></table>",
				new HTMLTable(table).render()
		);


		List<Map<String, Object>> table2 = List.of(
				Map.of(
						"Beers", List.of("Stout", "Lvivske")
				),
				Map.of(
						"Beers", List.of("Stout", "Zibert", "Chernihivske")
				)
		);

		assertEquals(
				"<table border='1'><thead><tr><th>Beers 1</th><th>Beers 2</th><th>Beers 3</th></tr></thead><tbody><tr><td>Stout</td><td>Lvivske</td><td></td></tr><tr><td>Stout</td><td>Zibert</td><td>Chernihivske</td></tr></tbody></table>",
				new HTMLTable(table2).render(),
				"Test multiple values"
		);
	}
}
