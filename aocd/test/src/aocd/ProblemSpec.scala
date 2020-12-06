package aocd

import utest._
import org.mockito.MockitoSugar

object ProblemSpec extends TestSuite with MockitoSugar {
  // Envvar is set by forkEnv in build.sc.
  val TEST_TOKEN = "foobar"
  val tempDir = os.temp.dir()
  val tokenFile = Problem.getTokenFile(tempDir)

  override def utestAfterAll(): Unit = os.remove.all(tempDir)

  val tests = Tests {
    test("getTokenFrom") {
      test("checks envvar") {
        Problem.getTokenFrom(tokenFile) ==> TEST_TOKEN
      }
      test("checks token file if envvar not set") {
        os.write(tokenFile, "mytoken")
        Problem.getTokenFrom(tokenFile, "random") ==> "mytoken"
        os.remove(tokenFile)
      }
      test("throws an exception if nothing found") {
        val error = intercept[TokenNotFound] {
          Problem.getTokenFrom(tokenFile, "random")
          ()
        }
        error.getMessage ==> TokenNotFound.msg
      }
    }

    test("only fetches data once") {
      val expectedInputData = "inputdata2"
      val (year, day) = (1, 1)
      val api = mock[Api]
      val mockResponse = requests.Response(
        url = Api.mkUrl(year, day),
        statusCode = 200,
        statusMessage = "Ok",
        data = new geny.Bytes(expectedInputData.toCharArray().map(_.toByte)),
        headers = Map.empty,
        history = None
      )
      when(api.getData(year, day, TEST_TOKEN)).thenReturn(mockResponse)

      class TestProblem extends Problem(year, day, api, tempDir) {
        def run(input: List[String]): Unit = ()
      }

      new TestProblem()
      new TestProblem()
      verify(api, times(1)).getData(year, day, TEST_TOKEN)
    }
  }
}
