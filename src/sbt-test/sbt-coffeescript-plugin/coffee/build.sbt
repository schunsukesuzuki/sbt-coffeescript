lazy val root = (project in file(".")).enablePlugins(SbtWeb)

val checkMapFileContents = taskKey[Unit]("check that map contents are correct")

checkMapFileContents := {
  val contents = IO.read((WebKeys.public in Assets).value / "coffee" / "a.js.map")
  if (contents != """{
                    |  "version": 3,
                    |  "file": "a.js",
                    |  "sourceRoot": "",
                    |  "sources": [
                    |    "a.coffee"
                    |  ],
                    |  "names": [],
                    |  "mappings": "AAAA;AAAA,MAAA;;EAAA,MAAA,GAAW;;EACX,QAAA,GAAW;AADX"
                    |}""".stripMargin) {
    sys.error(s"Unexpected contents: $contents")
  }
}