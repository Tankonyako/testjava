import org.example.Core.Utils.DateUtil;
import org.example.Database;
import org.example.Models.Call;
import org.example.Models.Ticker;
import org.example.Repository.CallsRepository;
import org.example.Repository.TickersRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest
{
	static TickersRepository repo;
	static CallsRepository repo2;

	@BeforeAll
	public static void before()
	{
		repo = Database.Repositories.Tickers;
		repo2 = Database.Repositories.Calls;
	}

	@Test
	public void TestSerialize()
	{
		var firstTicker = new Ticker(0, "AAPL_UQ", "Apple inc.");

		assertFalse(firstTicker.toMap().isEmpty());
		assertEquals("AAPL_UQ", firstTicker.toMap().get("name"));
		assertNull(firstTicker.toMap().getOrDefault("test", null));
	}

	@Test
	public void TestCrud()
	{
		var firstTicker = new Ticker(0, "AAPL_UQ", "Apple inc.");
		repo.insert(firstTicker);

		assertEquals(repo.list().size(), 1);

		assertEquals(0, repo.where("id", (long) 1).size());
		assertEquals(1, repo.where("id", 0L).size());
		assertEquals(1, repo.where("name", "AAPL_UQ").size());
		assertEquals(0, repo.where("name", "TEST_UQ").size());
		assertEquals(firstTicker, repo.where("name", "AAPL_UQ").stream().findFirst().get());
		assertEquals(1, repo.where("fullName", "Apple inc.").size());

		repo.delete(firstTicker.id());
		assertEquals(0, repo.list().size());
	}

	@Test
	public void TestCrudRelations() throws ParseException
	{
		var firstTicker = new Ticker(0, "AAPL_UQ", "Apple inc.");
		repo.insert(firstTicker);
		var firstCall = new Call(10, "Autocall", 0.4, List.of(firstTicker), DateUtil.parse("09-07-2024", DateUtil.ENGLISH_FORMAT));
		repo2.insert(firstCall);

		assertEquals(1, repo2.list().size());

		assertEquals(1, repo2.where("id", 10L).size());
		assertEquals(1, repo2.where("strikeDate", DateUtil.parse("09-07-2024", DateUtil.ENGLISH_FORMAT)).size());

		assertEquals(1, firstCall.tickers().size());

		repo.delete(firstTicker.id());
		assertEquals(0, repo.list().size());
	}
}
