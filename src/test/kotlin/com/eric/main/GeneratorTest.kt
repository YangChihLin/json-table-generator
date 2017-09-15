import org.junit.Test
import com.eric.main.Generator
import java.io.File

class GeneratorTest {

	@Test fun testGen_from_file() {
		val classLoader = GeneratorTest::class.java.getClassLoader();
		val f = File(classLoader.getResource("test.json").getFile())
		println(Generator.gen(f));
	}
}