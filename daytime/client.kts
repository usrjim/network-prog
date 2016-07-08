import java.net.Socket

val stream = Socket("time-a.nist.gov", 13).getInputStream()
stream.reader().buffered().use {
  it.lines().forEach(::println)
}
