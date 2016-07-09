
(import '[java.net Socket]
        '[java.io PrintStream InputStreamReader BufferedReader])

(def server "www.google.com")

(let [socket (Socket. server 80)
      out (PrintStream. (.getOutputStream socket))
      in (-> socket
             .getInputStream
             InputStreamReader.
             BufferedReader.)]

  (.println out "GET / HTTP/1.0")
  (.println out)

  (loop []
    (when-let [line (.readLine in)]
      (println line)
      (recur)))

  (.close in)
  (.close out)
  (.close socket))
